package grupo18.tp.n2.individuo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import main.java.ar.edu.utn.frba.ia.ag.Individuo;

public class IndividuoTp extends Individuo {

    private List<Gen> genes = new ArrayList<>();

    @Override
    public double aptitud() {
        return esAptoPorCantidadHombres() + esAptoPorCantidadMujeres();
    }

    @Override
    public Individuo generarRandom() {
        IndividuoTp individuoTp = new IndividuoTp();
        List<Integer> listaRandomAceras = listaRandomAceras();
        List<Integer> listaRandomUnicacionesCasa = listaRandomUbicacionesCasa();
        
        
        for (int i = 0; i < 14; i++) {
            int randomSexo = getNumeroRandom(0, 1);

            String nombre = IndividuoConfig.NOMBRES_POSIBLES.get(i);
            String sexoGenerado = IndividuoConfig.SEXOS_POSIBLES.get(randomSexo);
            String aceraGenerada = IndividuoConfig.ACERAS_POSIBLES.get(listaRandomAceras.get(i));
            int ubicacionCasaGenerada = listaRandomUnicacionesCasa.get(i);

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
//    private Gen obtenerGenPorNombre(String nombre) {
//        return this.genes
//                .stream()
//                .filter(gen -> nombre.equals(gen.getNombre()))
//                .findFirst()
//                .get();
//    }
//
//    private Gen obtenerGenPorUbicacionCasa(int ubicacionCasa) {
//        return this.genes
//                .stream()
//                .filter(gen -> ubicacionCasa.equals(gen.getUbicacionCasa()))
//                
//    }
//
//    private int reglaMariaViveAlLadoDeUnaMujer() {
//        Gen genMaria = obtenerGenPorNombre("Maria");
//        this.genes
//                .stream()
//                .filter(gen -> genMaria.getAcera().equals(gen.getAcera()))
//                
//    }
    private List<Integer> listaRandomAceras() {
        int cantidadAcerasUno = 0;
        int cantidadAcerasDos = 0;
        List<Integer> listaAceras = new ArrayList<>();
        int randomAcera = 0;

        while (true) {
            randomAcera = getNumeroRandom(0, 1);
            if (randomAcera == 0 && cantidadAcerasUno < 7) {
                cantidadAcerasUno++;
                listaAceras.add(randomAcera);
            }
            if (randomAcera == 1 && cantidadAcerasDos < 7) {
                cantidadAcerasDos++;
                listaAceras.add(randomAcera);
            }
            if (cantidadAcerasDos == 7 && cantidadAcerasUno == 7) {
                break;
            }
        }
        return listaAceras;
    }
    
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
    
    private List<Integer> listaRandomUbicacionesCasa() {
        List<Integer> listaRandomUbicacionesCasa = new ArrayList<>();
        listaRandomUbicacionesCasaPorFila()
                .forEach(ubicacion -> listaRandomUbicacionesCasa.add(ubicacion));
        listaRandomUbicacionesCasaPorFila()
                .forEach(ubicacion -> listaRandomUbicacionesCasa.add(ubicacion));
        
        return listaRandomUbicacionesCasa;
    }

}
