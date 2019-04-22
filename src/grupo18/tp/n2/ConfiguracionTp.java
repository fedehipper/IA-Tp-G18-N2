package grupo18.tp.n2;

import main.java.ar.edu.utn.frba.ia.ag.Configuracion;
import main.java.ar.edu.utn.frba.ia.ag.cruzamiento.Cruzamiento;
import main.java.ar.edu.utn.frba.ia.ag.mutacion.Mutacion;
import main.java.ar.edu.utn.frba.ia.ag.paro.CriterioDeParo;
import main.java.ar.edu.utn.frba.ia.ag.seleccion.Seleccion;

public class ConfiguracionTp extends Configuracion {

    public ConfiguracionTp(CriterioDeParo criterioDeParo,
            int poblacionInicial,
            Seleccion metodoDeSeleccion,
            Cruzamiento cruzamiento,
            Mutacion mutacion) {

        super(criterioDeParo, poblacionInicial, metodoDeSeleccion, cruzamiento, mutacion);
    }
}
