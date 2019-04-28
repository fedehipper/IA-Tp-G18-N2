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
        for (int i = 0; i < 14; i++) {
            int randomAcera = getNumeroRandom(0, 1);
            int randomSexo = getNumeroRandom(0, 1);
            int randomUbicacionCasa = getNumeroRandom(1, 7);

            String nombre = IndividuoConfig.NOMBRES_POSIBLES.get(i);
            String sexoGenerado = IndividuoConfig.SEXOS_POSIBLES.get(randomSexo);
            String aceraGenerada = IndividuoConfig.ACERAS_POSIBLES.get(randomAcera);
            int ubicacionCasaGenerada = randomUbicacionCasa;

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

}
