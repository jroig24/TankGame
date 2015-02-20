import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Player {
    private float x,y;
    private boolean viu;
    private boolean terra;
    private float velocitat;
    private float temps;
    private Image imatge;
    private Image cano;
    private float velocitatX;
    private float y0;
    private float vida;
    private String name;
    private float angle;
    private String nom;
    private int fuel;
    private int max_fuel;



    public void inicialitza(int x, int y){
        this.inicialitza();
        this.setX(x);
        this.setY(y);
    }

    public void inicialitza(){
        this.setViu(true);
        this.setTerra(false);
        this.setVelocitat(0);
        this.setTemps(0);
        this.setVelocitatX(5);//4 normal
        this.setImatge("tank.png");
        this.setCano("cano.png");
        this.vida = 100;
        this.max_fuel = 50;
        this.refill();
    }

    public void refill(){
        this.setFuel(this.getMax_fuel());
    }

    public void pick(Item objecte, Bomb bomba){
        this.setFuel(this.getFuel()+objecte.getFuel());
        if(this.getFuel()>this.getMax_fuel()){
            this.refill();
        }
        this.setMax_fuel(this.getMax_fuel()+objecte.getMax_fuel());
        this.setVida(this.getVida()+objecte.getHealth());
        bomba.setDmg(bomba.getDmg()+objecte.getDamage());
        bomba.setDmg(bomba.getDmg_initial()+objecte.getDamage());
        bomba.setRadi(bomba.getRadi()+objecte.getRadius());
        bomba.setRadi_initial(bomba.getRadi_initial()+objecte.getRadius());
    }

    public int getMax_fuel() {
        return max_fuel;
    }

    public void setMax_fuel(int max_fuel) {
        this.max_fuel = max_fuel;
    }

    public String getNom() {
        return nom;
    }

    public int getFuel() {
        return fuel;
    }

    public void setFuel(int fuel) {
        this.fuel = fuel;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public float getAngle() {
        return angle;
    }

    public void setAngle(float angle) {
        this.angle = angle;
    }

    public float getVida() {
        return vida;
    }

    public void setVida(float vida) {
        this.vida = vida;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getY0() {
        return y0;
    }

    public void setY0(float y0) {
        this.y0 = y0;
    }

    public float getVelocitatX() {
        return velocitatX;
    }

    public void setVelocitatX(float velocitatX) {
        this.velocitatX = velocitatX;
    }

    public Image getCano() {
        return this.cano;
    }

    public void setCano(String ubicacio) {
        try {
            this.cano = new Image(ubicacio);
        } catch (SlickException var3) {
            System.out.println("Error al carregar imatge.\n");
        }

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
    public boolean isTerra() {
        return terra;
    }

    public void setTerra(boolean terra) {
        this.terra = terra;
    }

    public float getVelocitat() {
        return velocitat;
    }

    public void setVelocitat(float velocitat) {
        this.velocitat = velocitat;
    }

    public float getTemps() {
        return temps;
    }

    public void setTemps(float temps) {
        this.temps = temps;
    }

    public boolean isViu() {
        return viu;
    }

    public void setViu(boolean viu) {
        this.viu = viu;
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
}
