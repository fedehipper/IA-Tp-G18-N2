package grupo18.tp.n2.individuo;

import java.util.ArrayList;
import static java.util.Arrays.asList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import main.java.ar.edu.utn.frba.ia.ag.Individuo;

public class IndividuoTp extends Individuo {

    private List<Gen> genes = new ArrayList<>();

    @Override
    public double aptitud() {
        return esAptoPorCantidadHombres()
                + esAptoPorCantidadMujeres()
                + reglaMariaViveAlLadoDeUnaMujer()
                + reglaRicardoViveAlLadoDeCarlos()
                + reglaAnaViveAceraOpuestaAMarta()
                + reglaAlejandraViveEnEsquina()
                + reglaCarlosViveAMitadDeCuadra()
                + reglaAnaViveEnEsquina()
                + reglaUnaPersonaViveEnFrenteDeOtraPersona("Rosa", "Ana")
                + reglaUnaPersonaViveEnFrenteDeOtraPersona("Andres", "Alejandra")
                + reglaUnaPersonaViveEnFrenteDeOtraPersona("Pablo", "Maria")
                + reglaUnaPersonaViveEnFrenteDeOtraPersona("Ricardo", "Marta")
                + reglaCarolinaViveEntreDosHombres()
                + reglaMaríaViveMismaAceraQueCarolina()
                + reglaMartaDiagonalACarolina()
                + reglaJuanViveDiagonalADosHombres()
                + reglaEnAceraQueViveDavidVivenMasHombresQueMujeres()
                + reglaLuisaViveTanCercaDeRosaComoCarlosDeAndres();
    }

    @Override
    public Individuo generarRandom() {
        IndividuoTp individuoTp = new IndividuoTp();

        List<Integer> ubicacionesUno = asList(1, 2, 3, 4, 5, 6, 7);
        List<Integer> ubicacionesDos = asList(1, 2, 3, 4, 5, 6, 7);
        Collections.shuffle(ubicacionesUno);
        Collections.shuffle(ubicacionesDos);

        List<Integer> listaRandomAceras = obtenerListaRandomAcerasPorUbicaciones(ubicacionesUno, ubicacionesDos);

        List<Integer> listaRandomUbicacionesCasa = concatenarListas(ubicacionesUno, ubicacionesDos);

        for (int i = 0; i < 14; i++) {
            int randomSexo = getNumeroRandom(0, 1);

            String nombre = IndividuoConfig.NOMBRES_POSIBLES.get(i);
            String sexoGenerado = IndividuoConfig.SEXOS_POSIBLES.get(randomSexo);
            String aceraGenerada = IndividuoConfig.ACERAS_POSIBLES.get(listaRandomAceras.get(i));
            int ubicacionCasaGenerada = listaRandomUbicacionesCasa.get(i);

            Gen gen = new Gen(nombre, aceraGenerada, ubicacionCasaGenerada, sexoGenerado);

            individuoTp.getGenes().add(gen);
        }
        return individuoTp;
    }

    private int esAptoPorCantidadHombres() {
        long cantidadHombres = this.genes
                .stream()
                .filter(gen -> esHombre(gen))
                .count();
        return cantidadHombres == 7 ? 10 : -10;
    }

    private int esAptoPorCantidadMujeres() {
        long cantidadMujeres = this.genes
                .stream()
                .filter(gen -> esMujer(gen))
                .count();
        return cantidadMujeres == 7 ? 10 : -10;
    }

    private boolean esHombre(Gen gen) {
        return IndividuoConfig.NOMBRES_SEXO_MASCULINO.contains(gen.getNombre()) && gen.getSexo().equals("M");
    }

    private boolean esMujer(Gen gen) {
        return !IndividuoConfig.NOMBRES_SEXO_MASCULINO.contains(gen.getNombre()) && gen.getSexo().equals("F");
    }

    private int getNumeroRandom(int minimo, int maximo) {
        Random random = new Random();
        return random.nextInt((maximo - minimo) + 1) + minimo;
    }

    public List<Gen> getGenes() {
        return genes;
    }

    public void setGenes(List<Gen> genes) {
        this.genes = genes;
    }

    /* metodos para random de genes */
    private List<Integer> obtenerListaRandomAcerasPorUbicaciones(List<Integer> ubicacionesUno, List<Integer> ubicacionesDos) {
        List<Integer> listaAceras = asList(-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        for (int i = 1; i < 8; i++) {
            int randomAcera = getNumeroRandom(0, 1);
            int valorUbicacionesUno = ubicacionesUno.get(i - 1);
            listaAceras.set(i - 1, randomAcera);
            listaAceras.set(ubicacionesDos.indexOf(valorUbicacionesUno) + 7, complemento(randomAcera));
        }
        return listaAceras;
    }

    private int complemento(int unoOCero) {
        return unoOCero == 1 ? 0 : 1;
    }

    private List<Integer> concatenarListas(List<Integer> ubicacionesUno, List<Integer> ubicacionesDos) {
        List<Integer> listaConcatenada = new ArrayList<>();
        listaConcatenada.addAll(ubicacionesUno);
        listaConcatenada.addAll(ubicacionesDos);
        return listaConcatenada;
    }

    /* Reglas acertijo */
    private Gen obtenerGenPorNombre(String nombre) {
        return this.genes
                .stream()
                .filter(gen -> nombre.equals(gen.getNombre()))
                .findFirst()
                .get();
    }

    private Gen obtenerGenPorUbicacionCasaYAcera(int ubicacionCasa, String acera) {
        return this.genes
                .stream()
                .filter(gen -> ubicacionCasa == gen.getUbicacionCasa() && acera.equals(gen.getAcera()))
                .findFirst()
                .get();
    }

    private int reglaMariaViveAlLadoDeUnaMujer() {
        Gen genMaria = obtenerGenPorNombre("Maria");

        if (genMaria.getUbicacionCasa() == 1) {
            Gen genProximoAMaria = obtenerGenPorUbicacionCasaYAcera(genMaria.getUbicacionCasa() + 1, genMaria.getAcera());
            if ("F".equals(genProximoAMaria.getSexo())) {
                return 10;
            }
        }

        if (genMaria.getUbicacionCasa() == 7) {
            Gen genProximoAMaria = obtenerGenPorUbicacionCasaYAcera(genMaria.getUbicacionCasa() - 1, genMaria.getAcera());
            if ("F".equals(genProximoAMaria.getSexo())) {
                return 10;
            }
        }

        if (genMaria.getUbicacionCasa() != 7 && genMaria.getUbicacionCasa() != 1) {
            Gen genAIzquierdaDeMaria = obtenerGenPorUbicacionCasaYAcera(genMaria.getUbicacionCasa() + 1, genMaria.getAcera());
            Gen genADerechaDeMaria = obtenerGenPorUbicacionCasaYAcera(genMaria.getUbicacionCasa() - 1, genMaria.getAcera());

            if ("F".equals(genAIzquierdaDeMaria.getSexo()) || "F".equals(genADerechaDeMaria.getSexo())) {
                return 10;
            }
        }

        return 0;
    }

    private int reglaRicardoViveAlLadoDeCarlos() {
        Gen genRicardo = obtenerGenPorNombre("Ricardo");
        Gen genCarlos = obtenerGenPorNombre("Carlos");
        int ubicacionRicardo = genRicardo.getUbicacionCasa();
        int ubicacionCarlos = genCarlos.getUbicacionCasa();

        if (!genRicardo.getAcera().equals(genCarlos.getAcera())) {
            return 0;
        } else {
            if (ubicacionRicardo == 1 && ubicacionCarlos == 2) {
                return 10;
            }

            if (ubicacionRicardo == 7 && ubicacionCarlos == 6) {
                return 10;
            }

            if (ubicacionRicardo != 7 && genRicardo.getUbicacionCasa() != 1) {
                if ((ubicacionRicardo + 1 == ubicacionCarlos) || (ubicacionRicardo - 1 == ubicacionCarlos)) {
                    return 10;
                }
            }
        }
        return 0;
    }

    private int reglaAnaViveAceraOpuestaAMarta() {
        Gen genAna = obtenerGenPorNombre("Ana");
        Gen genMarta = obtenerGenPorNombre("Marta");
        return genAna.getAcera().equals(genMarta.getAcera()) ? 0 : 10;
    }

    private int reglaMaríaViveMismaAceraQueCarolina() {
        Gen genMaria = obtenerGenPorNombre("Maria");
        Gen genCarolina = obtenerGenPorNombre("Carolina");
        return genMaria.getAcera().equals(genCarolina.getAcera()) ? 10 : 0;
    }

    private int reglaAlejandraViveEnEsquina() {
        Gen genAlejandra = obtenerGenPorNombre("Alejandra");
        return asList(1, 7).contains(genAlejandra.getUbicacionCasa()) ? 10 : 0;
    }

    private int reglaAnaViveEnEsquina() {
        Gen genAna = obtenerGenPorNombre("Ana");
        return asList(1, 7).contains(genAna.getUbicacionCasa()) ? 10 : 0;
    }

    private int reglaCarlosViveAMitadDeCuadra() {
        Gen genCarlos = obtenerGenPorNombre("Carlos");
        return genCarlos.getUbicacionCasa() == 4 ? 10 : 0;
    }

    private int reglaUnaPersonaViveEnFrenteDeOtraPersona(String unNombre, String otroNombre) {
        Gen genUnaPersona = obtenerGenPorNombre(unNombre);
        Gen genOtraPersona = obtenerGenPorNombre(otroNombre);

        return (!genUnaPersona.getAcera().equals(genOtraPersona.getAcera())
                && genUnaPersona.getUbicacionCasa() == genOtraPersona.getUbicacionCasa()) ? 10 : 0;
    }

    private int reglaCarolinaViveEntreDosHombres() {
        Gen genCarolina = obtenerGenPorNombre("Carolina");
        if (asList(1, 7).contains(genCarolina.getUbicacionCasa())) {
            return 0;
        } else {
            int ubicacionCasaCarolina = genCarolina.getUbicacionCasa();
            String aceraCarolina = genCarolina.getAcera();
            Gen genIzquierdaCarolina = obtenerGenPorUbicacionCasaYAcera(ubicacionCasaCarolina - 1, aceraCarolina);
            Gen genDerechaCarolina = obtenerGenPorUbicacionCasaYAcera(ubicacionCasaCarolina + 1, aceraCarolina);

            return "M".equals(genIzquierdaCarolina.getSexo()) && "M".equals(genDerechaCarolina.getSexo()) ? 10 : 0;
        }
    }

    private int reglaMartaDiagonalACarolina() {
        Gen genMarta = obtenerGenPorNombre("Marta");
        Gen genCarolina = obtenerGenPorNombre("Carolina");

        if (!genMarta.getAcera().equals(genCarolina.getAcera())) {
            int ubicacionMarta = genMarta.getUbicacionCasa();
            int ubicacionCarolina = genCarolina.getUbicacionCasa();

            return asList(ubicacionMarta + 1, ubicacionMarta - 1).contains(ubicacionCarolina) ? 10 : 0;
        } else {
            return 0;
        }
    }

    private int reglaJuanViveDiagonalADosHombres() {
        Gen genJuan = obtenerGenPorNombre("Juan");
        int ubicacionJuan = genJuan.getUbicacionCasa();
        String aceraJuan = genJuan.getAcera();

        if (asList(1, 7).contains(ubicacionJuan)) {
            return 0;
        } else {
            Gen genDiagonalDerechaAJuan = obtenerGenPorUbicacionCasaYAcera(ubicacionJuan + 1, aceraJuan);
            Gen genDiagonalIzquierdaAJuan = obtenerGenPorUbicacionCasaYAcera(ubicacionJuan - 1, aceraJuan);

            if ("M".equals(genDiagonalDerechaAJuan.getSexo()) && "M".equals(genDiagonalIzquierdaAJuan.getSexo())) {
                return 10;
            } else {
                return 0;
            }
        }
    }

    private int reglaEnAceraQueViveDavidVivenMasHombresQueMujeres() {
        Gen genDavid = obtenerGenPorNombre("David");
        String aceraDavid = genDavid.getAcera();
        long cantidadHombresAceraDavid = this.genes
                .stream()
                .filter(gen -> gen.getAcera().equals(aceraDavid))
                .filter(gen -> gen.getSexo().equals("M"))
                .count();

        return cantidadHombresAceraDavid > 3 ? 10 : 0;
    }

    private int cercania(Gen unGen, Gen otroGen) {
        int cercania = unGen.getUbicacionCasa() - otroGen.getUbicacionCasa();
        return cercania > 0 ? cercania : -1 * cercania;
    }

    private int reglaLuisaViveTanCercaDeRosaComoCarlosDeAndres() {
        Gen genLuisa = obtenerGenPorNombre("Luisa");
        Gen genRosa = obtenerGenPorNombre("Rosa");
        Gen genCarlos = obtenerGenPorNombre("Carlos");
        Gen genAndres = obtenerGenPorNombre("Andres");

        int cercaniaLuisYRosa = cercania(genLuisa, genRosa);
        int cercaniaCarlosYAndres = cercania(genCarlos, genAndres);

        return cercaniaLuisYRosa == cercaniaCarlosYAndres ? 10 : 0;
    }

}
