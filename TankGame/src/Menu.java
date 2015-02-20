import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Menu {
    private int angle;
    private float angle_Rad;
    private int torn;
    private boolean rdy;
    private Image imatge;

    public Image getImatge() {
        return this.imatge;
    }

    public void setImatge(String ubicacio) {
        try {
            this.imatge = new Image(ubicacio);
        } catch (SlickException var3) {
            System.out.println("Error al carregar imatge.\n");
        }

    }

    public Menu(){}

    public float getAngle_Rad() {
        return angle_Rad;
    }

    public void setAngle_Rad(float angle_Rad) {
        this.angle_Rad = angle_Rad;
    }

    public int getAngle() {
        return angle;
    }

    public void setAngle(float angle) {
        angle_Rad=angle;
        this.angle=(int)((-angle*360)/(Math.PI*2));
    }

    public boolean isRdy() {
        return rdy;
    }

    public void setRdy(boolean rdy) {
        this.rdy = rdy;
    }

    public int getTorn() {
        return torn;
    }

    public void setTorn(int torn) {
        this.torn = torn;
    }
}
