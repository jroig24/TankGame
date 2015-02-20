import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;


public class Terreny {
    public int alt[];
    public int tamany;
    public int tipus;


    public Terreny(){}

    public void omplaTerreny(int altura, int tamany){
        alt=new int[tamany+1];
        this.tamany=tamany+1;
        for(int i = 0; i<this.tamany;i++) {
            this.alt[i] = altura;
        }
    }

    public int getTamany() {
        return tamany;
    }

    public void setTamany(int tamany) {
        this.tamany = tamany;
    }

    public int getAlt(int posicio) {
        return alt[posicio];
    }

    public void setAlt(int posicio ,int altura) {
        if(this.getTamany()>=posicio) {
            this.alt[posicio] = altura;
        }
    }

    public int getTipus() {
        return tipus;
    }

    public void setTipus(int tipus) {
        this.tipus = tipus;
    }
}
