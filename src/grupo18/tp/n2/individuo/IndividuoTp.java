package grupo18.tp.n2.individuo;

import static java.util.Arrays.asList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import main.java.ar.edu.utn.frba.ia.ag.Individuo;

public class IndividuoTp extends Individuo {

    private String nombre;
    private String acera;
    private int ubicacionCasa;
    private String sexo;

    @Override
    public double aptitud() {
        return 1;
    }

    @Override
    public Individuo generarRandom() {
        List<String> nombresPosibles = asList(
                "Alejandra",
                "Ana",
                "Andres",
                "Carlos",
                "Carolina",
                "David",
                "Juan",
                "Luisa",
                "Maria",
                "Marta",
                "Pablo",
                "Pedro",
                "Ricardo",
                "Rosa"
        );

        List<String> sexosPosibles = asList("F", "M");
        List<String> acerasPosibles = asList("Fila 1", "Fila 2");

        int randomAcera = getNumeroRandom(0, 1);
        int randomSexo = getNumeroRandom(0, 1);
        int randomNombre = getNumeroRandom(0, 13);
        int randomUbicacionCasa = getNumeroRandom(0, 6);

        String nombreGenerado = nombresPosibles.get(randomNombre);
        String sexoGenerado = sexosPosibles.get(randomSexo);
        String aceraGenerada = acerasPosibles.get(randomAcera);
        int ubicacionCasaGenerada = randomUbicacionCasa;

        IndividuoTp individuoTp = new IndividuoTp();
        individuoTp.setAcera(aceraGenerada);
        individuoTp.setNombre(nombreGenerado);
        individuoTp.setSexo(sexoGenerado);
        individuoTp.setUbicacionCasa(ubicacionCasaGenerada);

        return individuoTp;
    }
    
    private int getNumeroRandom(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max);
    }

    /* Getters and Setters */
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getAcera() {
        return acera;
    }

    public void setAcera(String acera) {
        this.acera = acera;
    }

    public int getUbicacionCasa() {
        return ubicacionCasa;
    }

    public void setUbicacionCasa(int ubicacionCasa) {
        this.ubicacionCasa = ubicacionCasa;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

}
