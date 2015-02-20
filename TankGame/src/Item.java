import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Item {
    private int type;
    private float health;
    private int fuel;
    private int max_fuel;
    private int radius;
    private int damage;
    private int pos_x;
    private int pos_y;
    private boolean terra;
    private float velocitat;
    private Image imatge;

    public Item(){}

    public void create(int tipus){
        this.reset();
        this.type=tipus;
        switch (type){
            case 1://Botiquin
                this.health = 50;
                this.setImatge("hp_up.png");
                break;
            case 2://millora fuel
                this.fuel = 20;
                this.max_fuel = 15;
                this.setImatge("fuel.png");
                break;
            case 3://millora tret
                this.damage = 10;
                this.radius = 5;
                this.setImatge("tret.png");
                break;
            default:
                this.setImatge("default.png");
        }
    }

    public void reset(){
        type=0;
        health=0;
        fuel=0;
        max_fuel=0;
        radius=0;
        damage=0;
        pos_x=0;
        pos_y=0;
        velocitat = 5;
        terra = false;
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

    public int getPos_y() {
        return pos_y;
    }

    public void setPos_y(int pos_y) {
        this.pos_y = pos_y;
    }

    public int getPos_x() {
        return pos_x;
    }

    public void setPos_x(int pos_x) {
        this.pos_x = pos_x;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public float getHealth() {
        return health;
    }

    public void setHealth(float health) {
        this.health = health;
    }

    public int getFuel() {
        return fuel;
    }

    public void setFuel(int fuel) {
        this.fuel = fuel;
    }

    public int getMax_fuel() {
        return max_fuel;
    }

    public void setMax_fuel(int max_fuel) {
        this.max_fuel = max_fuel;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }
}
