package grupo18.tp.n2.individuo;

import java.util.Random;
import main.java.ar.edu.utn.frba.ia.ag.Individuo;

public class IndividuoTp extends Individuo {

    private String nombre;
    private String acera;
    private int ubicacionCasa;
    private String sexo;

    @Override
    public double aptitud() {
        return esAptoPorSexo();
    }

    @Override
    public Individuo generarRandom() {

        int randomAcera = getNumeroRandom(0, 1);
        int randomSexo = getNumeroRandom(0, 1);
        int randomNombre = getNumeroRandom(0, 13);
        int randomUbicacionCasa = getNumeroRandom(1, 7);

        String nombreGenerado = IndividuoConfig.NOMBRES_POSIBLES.get(randomNombre);
        String sexoGenerado = IndividuoConfig.SEXOS_POSIBLES.get(randomSexo);
        String aceraGenerada = IndividuoConfig.ACERAS_POSIBLES.get(randomAcera);
        int ubicacionCasaGenerada = randomUbicacionCasa;

        IndividuoTp individuoTp = new IndividuoTp();
        individuoTp.setAcera(aceraGenerada);
        individuoTp.setNombre(nombreGenerado);
        individuoTp.setSexo(sexoGenerado);
        individuoTp.setUbicacionCasa(ubicacionCasaGenerada);

        return individuoTp;
    }

    private int esAptoPorSexo() {
        if (esHombre() || esMujer()) {
            return 10;
        } else {
            return -10;
        }
    }

    private boolean esHombre() {
        return IndividuoConfig.NOMBRES_SEXO_MASCULINO.contains(this.nombre) && this.sexo.equals("M");
    }

    private boolean esMujer() {
        return IndividuoConfig.NOMBRES_SEXO_FEMENINO.contains(this.nombre) && this.sexo.equals("F");
    }

    private int getNumeroRandom(int minimo, int maximo) {
        Random random = new Random();
        return random.nextInt((maximo - minimo) + 1) + minimo;
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
