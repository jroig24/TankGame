import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Bomb {
    float radi;
    float radi_initial=30;
    float x,y;
    float x0, y0;
    float temps, vX, vY;
    float velocitat;
    float angle;
    boolean activa;
    int impacte;
    float radi_visual;
    boolean rdy;
    float dmg;
    float dmg_initial=40;
    float velocitatAUX;
    Image imatge;


    public Bomb(){}

    public void inicialitza(){
        radi = radi_initial;
        radi_visual = 5F;
        temps = 0F;
        vX=0F;
        vY=0F;
        activa = false;
        rdy = false;
        dmg =dmg_initial;
        this.setImatge("tret.png");
    }

    public void calculaTret(Player origen, Player desti){
    /*Set velocitat
    * set angle
    * set vx
    * set vy
    * x0
    * y0
    * */
        //vX = (desti.getY()-origen.getY()-0.5*9.81*)/();
     }

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

    public float getRadi_initial() {
        return radi_initial;
    }

    public void setRadi_initial(float radi_initial) {
        this.radi_initial = radi_initial;
    }

    public float getDmg_initial() {
        return dmg_initial;
    }

    public void setDmg_initial(float dmg_initial) {
        this.dmg_initial = dmg_initial;
    }

    public float getVelocitatAUX() {
        return velocitatAUX;
    }

    public void setVelocitatAUX(float velocitatAUX) {
        this.velocitatAUX = velocitatAUX;
    }

    public float getDmg() {
        return dmg;
    }

    public void setDmg(float dmg) {
        this.dmg = dmg;
    }

    public boolean isRdy() {
        return rdy;
    }

    public void setRdy(boolean rdy) {
        this.rdy = rdy;
    }

    public float getRadi_visual() {
        return radi_visual;
    }

    public void setRadi_visual(float radi_visual) {
        this.radi_visual = radi_visual;
    }

    public float getAngle() {
        return angle;
    }

    public void setAngle(float angle) {
        this.angle = angle;
    }

    public float getVelocitat() {
        return velocitat;
    }

    public void setVelocitat(float velocitat) {
        this.velocitat = velocitat;
    }

    public int getImpacte() {
        return impacte;
    }

    public void setImpacte(int impacte) {
        this.impacte = impacte;
    }

    public float getX0() {
        return x0;
    }

    public void setX0(float x0) {
        this.x0 = x0;
    }

    public float getY0() {
        return y0;
    }

    public void setY0(float y0) {
        this.y0 = y0;
    }

    public float getRadi() {
        return radi;
    }

    public void setRadi(float radi) {
        this.radi = radi;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getTemps() {
        return temps;
    }

    public void setTemps(float temps) {
        this.temps = temps;
    }

    public float getvX() {
        return vX;
    }

    public void setvX(float vX) {
        this.vX = vX;
    }

    public float getvY() {
        return vY;
    }

    public void setvY(float vY) {
        this.vY = vY;
    }

    public boolean isActiva() {
        return activa;
    }

    public void setActiva(boolean activa) {
        this.activa = activa;
    }
}
