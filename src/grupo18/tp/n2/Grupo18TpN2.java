package grupo18.tp.n2;

import grupo18.tp.n2.individuo.IndividuoTp;
import main.java.ar.edu.utn.frba.ia.ag.AlgoritmoGenetico;
import main.java.ar.edu.utn.frba.ia.ag.Configuracion;
import main.java.ar.edu.utn.frba.ia.ag.cruzamiento.Cruzamiento;
import main.java.ar.edu.utn.frba.ia.ag.cruzamiento.Simple;
import main.java.ar.edu.utn.frba.ia.ag.mutacion.Mutacion;
import main.java.ar.edu.utn.frba.ia.ag.mutacion.MutacionSimple;
import main.java.ar.edu.utn.frba.ia.ag.paro.CriterioDeParo;
import main.java.ar.edu.utn.frba.ia.ag.paro.TiempoTranscurrido;
import main.java.ar.edu.utn.frba.ia.ag.seleccion.Seleccion;
import main.java.ar.edu.utn.frba.ia.ag.seleccion.Torneo;

public class Grupo18TpN2 {

    public static void main(String[] args) {

        // definimos los componentes del algoritmo
        Seleccion seleccion = new Torneo();

        Cruzamiento cruzamiento = new Simple();

        Mutacion mutacion = new MutacionSimple(0.1);

        CriterioDeParo criterioDeParo = new TiempoTranscurrido(0, 1, 0);

        // los incluimos en la configuracion
        Configuracion configuracion = new ConfiguracionTp(criterioDeParo, 10000, seleccion, cruzamiento, mutacion);

        AlgoritmoGenetico algoritmoGenetico = new AlgoritmoGenetico(configuracion, IndividuoTp.class);

        algoritmoGenetico.ejecutar();

    }

}
