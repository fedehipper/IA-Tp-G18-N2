package grupo18.tp.n2.individuo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import static java.util.stream.Collectors.toList;
import main.java.ar.edu.utn.frba.ia.ag.Individuo;

public class IndividuoTp extends Individuo {

    private List<Gen> genes = new ArrayList<>();

    @Override
    public double aptitud() {
        return esAptoPorCantidadHombres()
                + esAptoPorCantidadMujeres()
                + reglaMariaViveAlLadoDeUnaMujer();
    }

    @Override
    public Individuo generarRandom() {
        IndividuoTp individuoTp = new IndividuoTp();
        Map<Integer, Map<Integer, Integer>> filasConUbicaciones = filasConUbicacionesSinRepetir();
        List<Integer> listaRandomUbicacionesUno = filasConUbicaciones.get(1).keySet().stream().collect(toList());
        List<Integer> listaRandomUbicacionesDos = filasConUbicaciones.get(2).keySet().stream().collect(toList());
        List<Integer> listaRandomUbicacionesCasa = new ArrayList<>();
        listaRandomUbicacionesUno
                .forEach(aceraUno -> listaRandomUbicacionesCasa.add(aceraUno));
        listaRandomUbicacionesDos
                .forEach(aceraDos -> listaRandomUbicacionesCasa.add(aceraDos));

        List<Integer> listaRandomAcerasUno = filasConUbicaciones.get(1).values().stream().collect(toList());
        List<Integer> listaRandomAcerasDos = filasConUbicaciones.get(2).values().stream().collect(toList());
        List<Integer> listaRandomAceras = new ArrayList<>();
        listaRandomAcerasUno
                .forEach(aceraUno -> listaRandomAceras.add(aceraUno));
        listaRandomAcerasDos
                .forEach(aceraDos -> listaRandomAceras.add(aceraDos));

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

    /* metodos para random de genes */
    private List<Integer> listaRandomUbicacionesCasaPorFila() {
        List<Integer> listaUbicacionesCasa = new ArrayList<>();
        int randomUbicacionCasa = 0;
        while (true) {
            randomUbicacionCasa = getNumeroRandom(1, 7);
            if (!listaUbicacionesCasa.contains(randomUbicacionCasa) && listaUbicacionesCasa.size() < 7) {
                listaUbicacionesCasa.add(randomUbicacionCasa);
            }
            if (listaUbicacionesCasa.size() == 7) {
                break;
            }
        }
        return listaUbicacionesCasa;
    }

    private Map<Integer, Map<Integer, Integer>> filasConUbicacionesSinRepetir() {
        List<Integer> ubicacionesFilaUno = listaRandomUbicacionesCasaPorFila();
        List<Integer> ubicacionesFilaDos = listaRandomUbicacionesCasaPorFila();

        Map<Integer, Integer> ubicacionesConFilaUno = new HashMap<>();
        Map<Integer, Integer> ubicacionesConFilaDos = new HashMap<>();

        ubicacionesFilaUno
                .forEach(ubicacionFilaUno -> ubicacionesConFilaUno.put(ubicacionFilaUno, -1));
        ubicacionesFilaDos
                .forEach(ubicacionFilaDos -> ubicacionesConFilaDos.put(ubicacionFilaDos, -1));

        for (int i = 0; i < 7; i++) {
            ubicacionesConFilaUno.put(i + 1, getNumeroRandom(0, 1));
        }

        for (int j = 0; j < 7; j++) {
            ubicacionesConFilaDos.put(j + 1, complemento(ubicacionesConFilaUno.get(j + 1)));
        }

        Map<Integer, Map<Integer, Integer>> filasConUbicacionesSinRepetir = new HashMap<>();
        filasConUbicacionesSinRepetir.put(1, ubicacionesConFilaUno);
        filasConUbicacionesSinRepetir.put(2, ubicacionesConFilaDos);

        return filasConUbicacionesSinRepetir;
    }

    private int complemento(int unoOCero) {
        return unoOCero == 1 ? 0 : 1;
    }

}
