import java.util.ArrayList;

public class Parabola {
    float radi;
    float x,y;
    float x0, y0;
    float vX, vY;
    float velocitat;
    float angle;
    boolean activa;
    float temps;
    ArrayList<Punt> posicions = new ArrayList<Punt>();
    int quants;

    public Parabola(){}

    public void inicialitza(){
        radi = 3;
        activa = true;
        velocitat = 30F;
        radi = 5F;
        temps = 0F;
        vX = 0F;
        vY = 0F;
        activa = false;
        velocitat = 30F;
        temps = 0;
        quants = 0;
    }

    public void addPosicio(float x, float y){
        Punt aux = new Punt();
        aux.setX(x);
        aux.setY(y);
        posicions.add(posicions.size(),aux);
    }

    public Punt getPosicio(int i){
        return posicions.get(i);
    }

    public int getQuants() {
        return quants;
    }

    public void setQuants(int quants) {
        this.quants = quants;
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

    public float getVelocitat() {
        return velocitat;
    }

    public void setVelocitat(float velocitat) {
        this.velocitat = velocitat;
    }

    public float getAngle() {
        return angle;
    }

    public void setAngle(float angle) {
        this.angle = angle;
    }

    public boolean isActiva() {
        return activa;
    }

    public void setActiva(boolean activa) {
        this.activa = activa;
    }
}
