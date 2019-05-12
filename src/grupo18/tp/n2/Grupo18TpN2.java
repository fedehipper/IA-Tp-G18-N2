package grupo18.tp.n2;

import grupo18.tp.n2.individuo.IndividuoTp;
import main.java.ar.edu.utn.frba.ia.ag.AlgoritmoGenetico;
import main.java.ar.edu.utn.frba.ia.ag.Configuracion;
import main.java.ar.edu.utn.frba.ia.ag.cruzamiento.BinomialAzar;
import main.java.ar.edu.utn.frba.ia.ag.cruzamiento.Cruzamiento;
import main.java.ar.edu.utn.frba.ia.ag.mutacion.Mutacion;
import main.java.ar.edu.utn.frba.ia.ag.mutacion.MutacionSimple;
import main.java.ar.edu.utn.frba.ia.ag.paro.CriterioDeParo;
import main.java.ar.edu.utn.frba.ia.ag.paro.TiempoTranscurrido;
import main.java.ar.edu.utn.frba.ia.ag.seleccion.Ranking;
import main.java.ar.edu.utn.frba.ia.ag.seleccion.Seleccion;

public class Grupo18TpN2 {

    public static void main(String[] args) {

        // definimos los componentes del algoritmo
        Seleccion seleccion = new Ranking(900);

        Cruzamiento cruzamiento = new BinomialAzar();

        Mutacion mutacion = new MutacionSimple(0.5);

        CriterioDeParo criterioDeParo = new TiempoTranscurrido(0, 2, 0);

        // los incluimos en la configuracion
        Configuracion configuracion = new ConfiguracionTp(criterioDeParo, 1000000, seleccion, cruzamiento, mutacion);

        AlgoritmoGenetico algoritmoGenetico = new AlgoritmoGenetico(configuracion, IndividuoTp.class);

        algoritmoGenetico.ejecutar();

    }

}
