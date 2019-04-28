package grupo18.tp.n2.individuo;

public class Gen {

    private String nombre;
    private String acera;
    private int ubicacionCasa;
    private String sexo;

    public Gen(String nombre, String acera, int ubicacionCasa, String sexo) {
        this.nombre = nombre;
        this.acera = acera;
        this.ubicacionCasa = ubicacionCasa;
        this.sexo = sexo;
    }
    
    @Override
    public String toString() {
        return "Nombre: " + this.nombre + " Acera: " + this.acera + " Ubicacion casa: "+ this.ubicacionCasa + " Sexo: " + this.sexo;
    }

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
