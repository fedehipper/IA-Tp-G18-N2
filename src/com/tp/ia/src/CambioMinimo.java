package com.tp.ia.src;

import static com.tp.ia.TpIA.guardarPoblacion;
import org.jgap.Chromosome;
import org.jgap.Configuration;
import org.jgap.FitnessFunction;
import org.jgap.Gene;
import org.jgap.Genotype;
import org.jgap.IChromosome;
import org.jgap.impl.DefaultConfiguration;
import org.jgap.impl.IntegerGene;

public class CambioMinimo {

    private static final int MAX_EVOLUCIONES_PERMITIDAS = 2200;

    public static void calcularCambioMinimo(int monto) throws Exception {
// Se crea una configuracion con valores predeterminados.
// -------------------------------------------------------------
        Configuration conf = new DefaultConfiguration();
// Se indica en la configuracion que el elemento mas apto siempre pase a
// la proxima generacion
// -------------------------------------------------------------
        conf.setPreservFittestIndividual(true);
// Se Crea la funcion de aptitud y se setea en la configuracion
// ---------------------------------------------------------
        FitnessFunction myFunc = new CambioMinimoFuncionAptitud(monto);
        conf.setFitnessFunction(myFunc);

        // Ahora se debe indicar a la configuracion como seran los cromosomas: en
// este caso tendran 8 genes (uno para cada tipo de moneda) con un valor
// entero (cantidad de monedas de ese tipo).
// Se debe crear un cromosoma de ejemplo y cargarlo en la configuracion
// Cada gen tendra un valor maximo y minimo que debe setearse.
// --------------------------------------------------------------
        Gene[] sampleGenes = new Gene[6];
        sampleGenes[0] = new IntegerGene(conf, 0, Math.round(CambioMinimoFuncionAptitud.MAX_MONTO / 100)); // Moneda 1 dolar
        sampleGenes[1] = new IntegerGene(conf, 0, 10); // Moneda 50 centavos
        sampleGenes[2] = new IntegerGene(conf, 0, 10); // Moneda 25 centavos
        sampleGenes[3] = new IntegerGene(conf, 0, 10); // Moneda 10 centavos
        sampleGenes[4] = new IntegerGene(conf, 0, 10); // Moneda 5 centavos
        sampleGenes[5] = new IntegerGene(conf, 0, 10); // Moneda 1 centavo
        IChromosome sampleChromosome = new Chromosome(conf, sampleGenes);
        conf.setSampleChromosome(sampleChromosome);
// Por ultimo se debe indicar el tamaño de la poblacion en la
// configuracion
// ------------------------------------------------------------
        conf.setPopulationSize(2000);
        Genotype Poblacion;

        // El framework permite obtener la poblacion inicial de archivos xml
// pero para este caso particular resulta mejor crear una poblacion
// aleatoria, para ello se utiliza el metodo randomInitialGenotype que
// devuelve la poblacion random creada
        Poblacion = Genotype.randomInitialGenotype(conf);
// La Poblacion debe evolucionar para obtener resultados mas aptos
// ---------------------------------------------------------------
        long TiempoComienzo = System.currentTimeMillis();
        for (int i = 0; i < MAX_EVOLUCIONES_PERMITIDAS; i++) {
            Poblacion.evolve();
        }

        long TiempoFin = System.currentTimeMillis();
        System.out.println("Tiempo total de evolucion: " + (TiempoFin - TiempoComienzo) + " ms");
        guardarPoblacion(Poblacion);
// Una vez que la poblacion evoluciono es necesario obtener el cromosoma
// mas apto para mostrarlo como solucion al problema planteado para ello
// se utiliza el metodo getFittestChromosome
        IChromosome cromosomaMasApto = Poblacion.getFittestChromosome();
        System.out.println("El cromosoma mas apto encontrado tiene un valor de aptitud de: " + cromosomaMasApto.getFitnessValue());
        System.out.println("Y esta formado por la siguiente distribucion de monedas: ");
        System.out.println("\t" + CambioMinimoFuncionAptitud.getNumeroDeComendasDeGen(cromosomaMasApto, 0) + " Moneda 1 dolar");
        System.out.println("\t" + CambioMinimoFuncionAptitud.getNumeroDeComendasDeGen(cromosomaMasApto, 1) + " Moneda 50 centavos");

        System.out.println("\t" + CambioMinimoFuncionAptitud.getNumeroDeComendasDeGen(cromosomaMasApto, 2) + " Moneda 25 centavos");
        System.out.println("\t" + CambioMinimoFuncionAptitud.getNumeroDeComendasDeGen(cromosomaMasApto, 3) + " Moneda 10 centavos");
        System.out.println("\t" + CambioMinimoFuncionAptitud.getNumeroDeComendasDeGen(cromosomaMasApto, 4) + " Moneda 5 centavos");
        System.out.println("\t" + CambioMinimoFuncionAptitud.getNumeroDeComendasDeGen(cromosomaMasApto, 5) + " Moneda 1 centavo");

        System.out.println("Para un total de " + CambioMinimoFuncionAptitud.montoCambioMoneda(cromosomaMasApto) + " centimos en " + CambioMinimoFuncionAptitud.getNumeroTotalMonedas(cromosomaMasApto) + " monedas.");
    }

}
