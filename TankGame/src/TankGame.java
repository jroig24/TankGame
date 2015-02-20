import java.lang.Override;import java.lang.String;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.lwjgl.opengl.EXTAbgr;
import org.newdawn.slick.*;


public class TankGame extends BasicGame{

    public final static float GRAVETAT = (float)9.8;
    Terreny terra = new Terreny();
    Player jugadors[] = new Player[10];
    Bomb bombes[] = new Bomb[10];
    ArrayList<Item> objectes = new ArrayList<Item>();
    Menu m = new Menu();
    int quant_punts, pos;

    Scanner sc = new Scanner(System.in);

    public boolean dreta, esquerra, click, fi_torn, fi_partida, space, r_click, auto, spawn, debug;

    public int torn, max_torn,aux_torn, vius, target;

    public TankGame(String gamename)
    {
        super(gamename);
    }

    float angle;
    Punt p1 = new Punt();
    Punt p2 = new Punt();


    @Override
    public void init(GameContainer gc) throws SlickException {
        fi_partida=false;

        max_torn =0;
        torn = 0;
        aux_torn=0;

        terra.omplaTerreny(400, 1000); //Terra pla

        try {
            m.setImatge("fons.png");
        } catch (Exception e) {
            System.out.println("Error al carregar imatge.\n");
        }

        for (int ompla = 0; ompla <= 1000; ompla++) {
            terra.setAlt(ompla, (int) (Math.cos((2 * Math.PI * ompla / 1000) + Math.PI) * 100 + 425));
        }
        max_torn = 0;
        torn=0;
        do{
            //System.out.println("Nom del jugador numero " + max_torn);
            //jugadors[max_torn].setNom(Integer.toString(max_torn));

            jugadors[max_torn] = new Player();
            pos = 100+(int)(Math.random()*900);
            jugadors[max_torn].inicialitza(pos, terra.getAlt(pos) - 20);
            jugadors[max_torn].setY0(jugadors[max_torn].getY());
            vius++;
            bombes[max_torn] = new Bomb();
            bombes[max_torn].inicialitza();
            max_torn++;
        }while(max_torn !=2);//numero de jugadors. minim 2 i maxim 10.

        objectes.clear(); //Treiem tots els objectes cada cop que s'inicia una partida.

    }

    @Override
    public void update(GameContainer gc, int i) throws SlickException {

        dreta = gc.getInput().isKeyDown(Input.KEY_D);
        esquerra = gc.getInput().isKeyDown(Input.KEY_A);
        click = gc.getInput().isMouseButtonDown(0);
        space = gc.getInput().isKeyDown(Input.KEY_SPACE);
        fi_torn = gc.getInput().isKeyPressed(Input.KEY_ENTER);
        r_click =gc.getInput().isMouseButtonDown(1);
        //auto = gc.getInput().isKeyPressed(Input.KEY_K);
        //spawn = gc.getInput().isKeyPressed(Input.KEY_H);
        //debug = gc.getInput().isKeyDown(Input.KEY_M);
        spawn = false;

        //moviment vertical (EIX Y)
        aux_torn=0;
        do {
            if (jugadors[aux_torn].getX() - jugadors[aux_torn].getImatge().getWidth() / 2 >= 0 && jugadors[aux_torn].getX() + jugadors[aux_torn].getImatge().getWidth() / 2 <= 1000) {
                p1.setX(jugadors[aux_torn].getX() - jugadors[aux_torn].getImatge().getWidth() / 2);
                p1.setY(terra.getAlt((int) (jugadors[aux_torn].getX() - jugadors[aux_torn].getImatge().getWidth() / 2)));

                p2.setX(jugadors[aux_torn].getX() + jugadors[aux_torn].getImatge().getWidth() / 2);
                p2.setY(terra.getAlt((int) (jugadors[aux_torn].getX() + jugadors[aux_torn].getImatge().getWidth() / 2)));

                angle = (float) (360 / (2 * Math.PI)) * (float) (Math.atan2(p1.getY() - p2.getY(), p1.getX() - p2.getX()));

                if (angle > 0) {
                    angle -= 180;
                } else {
                    angle += 180;
                }
                angle = -angle;

                jugadors[aux_torn].setAngle(angle);
            }
            if (!jugadors[aux_torn].isTerra()) {
                jugadors[aux_torn].setTemps(jugadors[aux_torn].getTemps() + 0.2F);
                jugadors[aux_torn].setY(jugadors[aux_torn].getY0() + (float) (jugadors[aux_torn].getVelocitat() * jugadors[aux_torn].getTemps() + 0.5F * GRAVETAT * Math.pow(jugadors[aux_torn].getTemps(), 2)));
            } else {
                jugadors[aux_torn].setY0(jugadors[aux_torn].getY());
            }

            if (jugadors[aux_torn].getY() >= terra.getAlt((int) jugadors[aux_torn].getX())) {
                jugadors[aux_torn].setTerra(true);
                jugadors[aux_torn].setY(terra.getAlt((int) jugadors[aux_torn].getX()));
            }else{
                jugadors[aux_torn].setTerra(false);
            }

            aux_torn++;
        }while(aux_torn<max_torn);

        if(!bombes[torn].isRdy()&&!fi_partida&&jugadors[torn].getFuel()>0){
            //Moviment horitzontal (eix X)

            if (!(jugadors[torn].getX() == 1000)&&jugadors[torn].getAngle()<45&&!bombes[torn].isActiva()) {
                if (dreta && jugadors[torn].getX()+jugadors[torn].getImatge().getWidth()/2 < 1000 && jugadors[torn].isTerra() && jugadors[torn].isViu()) {
                    if (jugadors[torn].getX() + jugadors[torn].getVelocitatX() >= 1000) {
                        jugadors[torn].setX(1000F);
                    } else {
                        jugadors[torn].setX(jugadors[torn].getX() + jugadors[torn].getVelocitatX());
                    }
                    jugadors[torn].setFuel(jugadors[torn].getFuel()-1);
                }

            }
            if (!(jugadors[torn].getX() == 0)&&angle>-45&&!bombes[torn].isActiva()) {
                if (esquerra && jugadors[torn].getX()-jugadors[torn].getImatge().getWidth()/2 > 0 && jugadors[torn].isTerra() && jugadors[torn].isViu()) {
                    if (jugadors[torn].getX() - jugadors[torn].getVelocitatX() <= 0) {
                        jugadors[torn].setX(0F);
                    } else {
                        jugadors[torn].setX(jugadors[torn].getX() - jugadors[torn].getVelocitatX());
                    }
                    jugadors[torn].setFuel(jugadors[torn].getFuel()-1);
                }
            }
        }

        //BOMBES

        if(!bombes[torn].isRdy()) {
            m.setAngle((float) Math.atan2(gc.getInput().getMouseY() - jugadors[torn].getY(), gc.getInput().getMouseX() - jugadors[torn].getX()));
            bombes[torn].setAngle((float) Math.atan2(gc.getInput().getMouseY() - jugadors[torn].getY(), gc.getInput().getMouseX() - jugadors[torn].getX()));
        }

        if(r_click&&!bombes[torn].isActiva()&&!fi_partida){
            bombes[torn].setRdy(false);
        }

        if(space&&bombes[torn].isRdy()&&!fi_partida){
            bombes[torn].setActiva(true);
            bombes[torn].setRdy(false);
        }

        if(!bombes[torn].isActiva()&&!bombes[torn].isRdy()) {
            bombes[torn].setVelocitat(0.3F*(float) Math.sqrt(Math.pow(jugadors[torn].getX() - gc.getInput().getMouseX(), 2) + Math.pow(jugadors[torn].getY() - gc.getInput().getMouseY(), 2)));
            if(bombes[torn].getVelocitat()>100){
                bombes[torn].setVelocitat(100);
            }
            if(bombes[torn].getVelocitat()<25){
                bombes[torn].setVelocitat(25);
            }
            bombes[torn].setvX(bombes[torn].getVelocitat() * (float) Math.cos(bombes[torn].getAngle())); //?
            bombes[torn].setvY(bombes[torn].getVelocitat() * (float) Math.sin(bombes[torn].getAngle()));
        }
        if((click||auto)&&!bombes[torn].isActiva()&&jugadors[torn].isViu()&&!fi_partida){
            if(auto&&vius>=2){ //apuntara sempre que quedin 2 jugadors o més.
                //set angle i velocitat
                System.out.println("TRET AUTOMATIC");
                target=-1;
                do{//l'objectiu sera un jugador viu que no sigui el propi jugador
                    target++;
                    if(target==torn) {
                        target++;
                    }
                    if(target>jugadors.length){
                        target = torn;
                    }
                }while(!jugadors[target].isViu());
                bombes[torn].calculaTret(jugadors[torn],jugadors[target]);
                bombes[torn].setAngle(3);
                bombes[torn].setVelocitat(50);
                //Set VX
                //Set VY
            }else {
                bombes[torn].setAngle((float) Math.atan2(gc.getInput().getMouseY() - jugadors[torn].getY(), gc.getInput().getMouseX() - jugadors[torn].getX()));
                bombes[torn].setvX(bombes[torn].getVelocitat() * (float) Math.cos(bombes[torn].getAngle()));
                bombes[torn].setvY(bombes[torn].getVelocitat() * (float) Math.sin(bombes[torn].getAngle()));
            }
            bombes[torn].setRdy(true);
            m.setRdy(true);
            bombes[torn].setY(jugadors[torn].getY() - jugadors[torn].getImatge().getHeight() / 2);
            bombes[torn].setX(jugadors[torn].getX());
            bombes[torn].setY0(bombes[torn].getY());
        }

        if(bombes[torn].isActiva()){
            bombes[torn].setTemps(bombes[torn].getTemps() + 0.2F);
            bombes[torn].setY(bombes[torn].getY0() + (float) (bombes[torn].getvY() * bombes[torn].getTemps() + 0.5F * GRAVETAT * Math.pow(bombes[torn].getTemps(), 2)));
            if(bombes[torn].getX()>1000||bombes[torn].getX()<0) {
                bombes[torn].inicialitza();
                fi_torn= true;
            }else{
                bombes[torn].setX(jugadors[torn].getX() + bombes[torn].getX0() + bombes[torn].getvX()*bombes[torn].getTemps());
            }
        }

        //ELIMINAR JUGADORS SEGONS HITBOX
        if(bombes[torn].isActiva()) {
            for (int tocat = 0; tocat < max_torn; tocat++) {
                if (torn!=tocat&&bombes[torn].getY() <= jugadors[tocat].getY() && bombes[torn].getY() >= jugadors[tocat].getY()-jugadors[tocat].getImatge().getHeight() && bombes[torn].getX() >= jugadors[tocat].getX() - jugadors[tocat].getImatge().getWidth() / 2 && bombes[torn].getX() <= jugadors[tocat].getX() + jugadors[tocat].getImatge().getWidth() / 2) {
                    bombes[torn].setY(jugadors[tocat].getY() + 1);
                }
            }
        }

        //IMPACTE
        if(bombes[torn].isActiva()&&bombes[torn].getX()>=0&&bombes[torn].getX()<=1000&&bombes[torn].getY()>terra.getAlt((int)bombes[torn].getX())){
            bombes[torn].setActiva(false);
            fi_torn= true;
            bombes[torn].setImpacte((int) bombes[torn].getX());
            spawn = true;
            for(int explosio = -(int)bombes[torn].getRadi();explosio<bombes[torn].getRadi();explosio++){
                if(bombes[torn].getImpacte() + explosio<=1000&&bombes[torn].getImpacte() + explosio>=0) {
                    //Modificació del terreny
                    terra.setAlt(bombes[torn].getImpacte()+explosio,(int)(terra.getAlt(explosio+bombes[torn].getImpacte())+bombes[torn].getRadi()*Math.sin(0.5F * Math.PI * (explosio + bombes[torn].getRadi()) / bombes[torn].getRadi())));

                    for(int j = 0;j<objectes.size();j++){
                        if(objectes.get(j).getPos_x()==bombes[torn].getImpacte()+explosio){
                            objectes.get(j).setTerra(false);
                        }
                    }
                }
                for(aux_torn=0;aux_torn<max_torn;aux_torn++){
                    if(jugadors[aux_torn].getX()==bombes[torn].getImpacte()+explosio&&jugadors[aux_torn].isViu()){
                        jugadors[aux_torn].setVida(jugadors[aux_torn].getVida()-Math.abs(bombes[torn].getDmg()*(float)Math.cos(bombes[torn].getImpacte()-jugadors[aux_torn].getX())));
                        if(jugadors[aux_torn].getVida()<=0) {
                            jugadors[aux_torn].setViu(false);//el jugador mor.
                        }
                    }
                }

            }
            bombes[torn].inicialitza();
        }

        //Spawn d'objectes
        if(spawn&&(int)((float)(Math.random()*100)%100)<=45){//provabilitat de  que caigui una ajuda.
            Item objecte_aux = new Item();
            objecte_aux.reset();
            objecte_aux.create(1+(int)(Math.random()*3)%3);//El numero varia segons el numero d'objectes que tenim definits.hh
            objecte_aux.setPos_x(100 + (int) (Math.random() * 800));
            objecte_aux.setPos_y(0);
            objectes.add(objecte_aux);
        }


        //moviment vertical d'objectes
        for(int j = 0;j < objectes.size();j++){
            if(!objectes.get(j).isTerra()) {
                if (objectes.get(j).getPos_y() + objectes.get(j).getVelocitat() >= terra.getAlt(objectes.get(j).getPos_x()) || objectes.get(j).getPos_y()> terra.getAlt(objectes.get(j).getPos_y())) {
                    objectes.get(j).setPos_y(terra.getAlt(objectes.get(j).getPos_x()));
                    objectes.get(j).setTerra(true);
                }else{
                    objectes.get(j).setPos_y(objectes.get(j).getPos_y() + (int) objectes.get(j).getVelocitat());
                }
            }
        }


        //Recollida d'objectes
        for(int j = 0;j<max_torn;j++){
            for(int y = 0; y<objectes.size();y++){
                if(jugadors[j].getX()-jugadors[j].getImatge().getWidth()/2<=objectes.get(y).getPos_x()&&jugadors[j].getX()+jugadors[j].getImatge().getWidth()/2>=objectes.get(y).getPos_x()&&objectes.get(y).isTerra()){
                    jugadors[j].pick(objectes.get(y),bombes[torn]);
                    objectes.remove(y);
                }
            }
        }

        //Modifiquem el terreny si sobrepassa del limit.(600)
        for(int aux_t = 0;aux_t<=1000;aux_t++){
            if(terra.getAlt(aux_t)>600){
                terra.setAlt(aux_t,600);
            }
        }

        //Passar torn amb tecla enter
        if((fi_torn||!jugadors[torn].isViu())&&!bombes[torn].isActiva()) {
            jugadors[torn].refill();
            torn++;
            if(torn>max_torn-1){
                torn=0;
            }
        }

        //Recompte de jugadors vius.
        vius=0;
        for(int j = 0;j<max_torn;j++){
            if(jugadors[j].isViu()){
                vius++;
            }
        }

        if(vius <=1){
            fi_partida = true;
        }

        if(fi_partida&&gc.getInput().isKeyPressed(Input.KEY_N)){
            init(gc);
        }
    }

    @Override
    public void render(GameContainer gc, Graphics g) throws SlickException{
        //Fons
        try {
            g.drawImage(m.getImatge(), 0, 0);
        }catch (Exception e){

        }
        g.setColor(Color.white);

        if(fi_partida){
            g.setColor(Color.blue);
            g.drawString("GAME OVER",490,300);
            g.drawString("PRESS N",500,320);
        }
        for(int i=0;i<terra.getTamany();i++){
            g.setColor(Color.lightGray);
            g.drawLine(i, terra.getAlt(i), i, 600);
        }
        if(!bombes[torn].isActiva()&&!fi_partida){
            g.setColor(Color.red);
            quant_punts = 8-(int)bombes[torn].getVelocitat()/30;
            for(int i = 0; i < quant_punts;i++) {
                g.fillOval(jugadors[torn].getX() + bombes[torn].getvX() * i, -jugadors[torn].getImatge().getHeight() / 2 + jugadors[torn].getY() + (float) (bombes[torn].getvY() * i + 0.5F * GRAVETAT * Math.pow(i, 2)), 4, 4);
            }
        }

        for(aux_torn=0;aux_torn<max_torn;aux_torn++){
            if (jugadors[aux_torn].isViu()) {
                jugadors[aux_torn].getCano().setCenterOfRotation(1, 2);
                jugadors[aux_torn].getCano().setRotation((360/(2*(float)Math.PI))*((float)Math.atan2(gc.getInput().getMouseY() - jugadors[aux_torn].getY(), gc.getInput().getMouseX() - jugadors[aux_torn].getX())));
                g.drawImage(jugadors[aux_torn].getCano(),jugadors[aux_torn].getX(),jugadors[aux_torn].getY()-jugadors[aux_torn].getImatge().getHeight()+2);

                jugadors[aux_torn].getImatge().setRotation(-jugadors[aux_torn].getAngle());
                g.drawImage(jugadors[aux_torn].getImatge(), jugadors[aux_torn].getX() - jugadors[aux_torn].getImatge().getWidth() / 2, jugadors[aux_torn].getY() - jugadors[aux_torn].getImatge().getHeight());

                g.setColor(Color.green);
                if (jugadors[aux_torn].getVida() <= 50) {
                    g.setColor(Color.orange);
                    if (jugadors[aux_torn].getVida() <= 25) {
                        g.setColor(Color.red);
                    }
                }
                //Marcador de vida
                g.fillRect(jugadors[aux_torn].getX() - jugadors[aux_torn].getVida() / 6, jugadors[aux_torn].getY() - jugadors[aux_torn].getImatge().getHeight() -10,jugadors[aux_torn].getVida()/3, 4);

                if(jugadors[torn].isViu()) {
                    g.setColor(Color.white);
                    g.drawString("V", jugadors[torn].getX(), jugadors[torn].getY() - 80);
                }
            }
        }

        //Rotació de la bala
        if(bombes[torn].isActiva()&&jugadors[torn].isViu()){
            bombes[torn].getImatge().setRotation((float)(360/(Math.PI*2))*(float)Math.atan2(/*Y*/bombes[torn].getY0() + (float) (bombes[torn].getvY() * bombes[torn].getTemps()+0.2F + 0.5F * GRAVETAT * Math.pow(bombes[torn].getTemps()+0.2F, 2))-bombes[torn].getY(),/*X*/bombes[torn].getvX()));
            g.drawImage(bombes[torn].getImatge(), bombes[torn].getX(), bombes[torn].getY());
        }

        g.setColor(Color.blue);
        for(int i = 0;i<objectes.size();i++){
            g.drawImage(objectes.get(i).getImatge(),objectes.get(i).getPos_x()-objectes.get(i).getImatge().getWidth()/2,objectes.get(i).getPos_y()-objectes.get(i).getImatge().getHeight());
        }

        //MENU

        g.setColor(Color.darkGray);
        g.fillRect(1000, 0, 200, 600);//Espai gris

        g.setColor(Color.black);
        g.drawString("Players left: "+vius,1010,550);

        if(fi_partida) {
            //Radar/angle
            g.setColor(Color.black);
            g.fillOval(1050, 80, 100, 100);
            g.setColor(Color.green);
            g.drawOval(1050, 80, 100, 100);

            g.setColor(Color.black);
            g.drawString("PLAYER ", 1010, 20);
            g.drawString("Angle: ", 1010, 40);
            g.setColor(Color.red);
            g.drawString("BOMB NOT READY", 1010, 200);
            g.setColor(Color.black);
            g.drawString("X: ", 1010, 220);
            g.drawString("Y: ", 1010, 240);
            g.drawString("Radius: ", 1010, 280);

            g.drawString("Health: ", 1010, 300);
            g.drawString("Velocity: ", 1010, 320);
        }else {
            //Radar/angle
            g.setColor(Color.black);
            g.fillOval(1050, 80, 100, 100);
            g.setColor(Color.green);
            g.drawOval(1050, 80, 100, 100);

            g.setColor(Color.black);
            g.drawString("PLAYER " + torn, 1010, 20);
            g.drawString("Angle: " + m.getAngle(), 1010, 40);

            //Linea radar
            g.setColor(Color.green);
            g.drawLine(1100, 130, 1100 + (int) (Math.cos(m.getAngle_Rad()) * 50), 130 + (int) (Math.sin(m.getAngle_Rad()) * 50));

            if (bombes[torn].isRdy()) {
                g.setColor(Color.green);
                g.drawString("BOMB READY", 1010, 200);
            } else {
                g.setColor(Color.red);
                g.drawString("BOMB NOT READY", 1010, 200);
            }

            g.setColor(Color.black);
            g.drawString("X: " + jugadors[torn].getX(), 1010, 220);
            g.drawString("Y: " + jugadors[torn].getY(), 1010, 240);
            g.drawString("Radius: " + bombes[torn].getRadi(), 1010, 280);

            g.drawString("Health: " + jugadors[torn].getVida(), 1010, 300);
            if(bombes[torn].getVelocitat()>=100||bombes[torn].getVelocitat()<=25){
                g.setColor(Color.red);
            }
            g.drawString("Velocity: " + bombes[torn].getVelocitat(), 1010, 320);
            g.setColor(Color.black);
            g.drawString("Fuel: " + jugadors[torn].getFuel(), 1010, 340);

            g.setColor(Color.green);
            if(((float)jugadors[torn].getFuel()/jugadors[torn].getMax_fuel())<=0.5F){
                g.setColor(Color.orange);
            }else{
                if(((float)jugadors[torn].getFuel()/jugadors[torn].getMax_fuel())<=0.25F){
                    g.setColor(Color.red);
                }
            }
            g.fillRect(1010,360,(160*((float)jugadors[torn].getFuel()/jugadors[torn].getMax_fuel())),10);
            g.setColor(Color.white);
            g.drawRect(1010,360,160,10);
        }


    }

    public static void main(String[] args){
        try{
            AppGameContainer appgc;
            appgc = new AppGameContainer(new TankGame("Rainbow Tank (ノಠ益ಠ)ノ彡┻━┻"));
            appgc.setDisplayMode(1200,600, false);
            appgc.setTargetFrameRate(60);
            appgc.setShowFPS(false);
            appgc.start();
        }
        catch (SlickException ex){
            Logger.getLogger(TankGame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
