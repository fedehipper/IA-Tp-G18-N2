package grupo18.tp.n2.individuo;

import main.java.ar.edu.utn.frba.ia.ag.Individuo;

public class IndividuoTp extends Individuo {

    private int colorOjos = 0;
    private int colorPiel = 1;
    private int colorPelo = 1;

    @Override
    public double aptitud() {
        return 0.2;
    }

    public int getColorOjos() {
        return colorOjos;
    }

    public void setColorOjos(int colorOjos) {
        this.colorOjos = colorOjos;
    }

    public int getColorPiel() {
        return colorPiel;
    }

    public void setColorPiel(int colorPiel) {
        this.colorPiel = colorPiel;
    }

    public int getColorPelo() {
        return colorPelo;
    }

    public void setColorPelo(int colorPelo) {
        this.colorPelo = colorPelo;
    }

}
