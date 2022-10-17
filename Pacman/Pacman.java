import greenfoot.*;
import java.util.List;
import java.util.ArrayList;

public class Pacman extends Mover
{
    private static GreenfootSound sound = new GreenfootSound("wakka.wav");
    private static GreenfootImage pacR1 = new GreenfootImage("pacman_r.png");
    private static GreenfootImage pacR2 = new GreenfootImage("pacman_r_1.png");
    private static GreenfootImage pacR3 = new GreenfootImage("pacman_r_2.png");
    private static GreenfootImage pacL1 = new GreenfootImage("pacman_l.png");
    private static GreenfootImage pacL2 = new GreenfootImage("pacman_l_1.png");
    private static GreenfootImage pacL3 = new GreenfootImage("pacman_l_2.png");
    private static GreenfootImage pacU1 = new GreenfootImage("pacman_u.png");
    private static GreenfootImage pacU2 = new GreenfootImage("pacman_u_1.png");
    private static GreenfootImage pacU3 = new GreenfootImage("pacman_u_2.png");
    private static GreenfootImage pacD1 = new GreenfootImage("pacman_d.png");
    private static GreenfootImage pacD2 = new GreenfootImage("pacman_d_1.png");
    private static GreenfootImage pacD3 = new GreenfootImage("pacman_d_2.png");
    private static GreenfootImage pacFull = new GreenfootImage("pacman_full.png");
    private GreenfootImage curImg = pacFull;
    private boolean isOpening = false;
    public boolean canMove = true;
   
    public void act() 
    {
        if(canMove==true) {
        String direction = getDirection();
        if(canMove(direction)){
            lastPressed = direction;
            move(direction);
        } else if (canMove(lastPressed)) {
            move(lastPressed);
        }
        
        if(isOn(Dot.class)) {
            eat(Dot.class);
        }else if(isOn(Powerpellet.class)) {
            eat(Powerpellet.class);
        }
        Pacworld world = (Pacworld) getWorld();
        
        if(world.ghostTimer > world.MAX_GHOST_TIME) {
            world.canEatGhosts = false;
            world.ghostTimer = 0;
        }
        
        if(world.canEatGhosts==true) {
            if(isOn(Ghost.class)) {
                eat(Ghost.class);
            }
            world.ghostTimer++;
        }
        
        if(atWorldEdge()) {
            if(direction == "right") {
                setLocation(0, getY());
            } else if (direction == "left") {
                setLocation(getWorld().getWidth(), getY());
            }
        }
        }
    }
    
    public void move(String direction) {

        if(direction == "right") {
            if(curImg == pacR1 || curImg == pacFull || (curImg != pacR1 && curImg != pacR2 && curImg != pacR3)) {
                setImage(pacR2);
                curImg = pacR2;
                isOpening = false;
            }
            if(curImg == pacR2 && isOpening) {
                setImage(pacR1);
                curImg = pacR1;
            }
            if(curImg == pacR2 && !isOpening) {
                setImage(pacR3);
                curImg = pacR3;
            }
            if(curImg == pacR3) {
                setImage(pacR2);
                curImg = pacR2;
                isOpening = true;
            }
            setLocation(getX()+1,getY());
            sound.play();
        }
        
        if(direction == "left") {
            if(curImg == pacL1 || curImg == pacFull || (curImg != pacL1 && curImg != pacL2 && curImg != pacL3)) {
                setImage(pacL2);
                curImg = pacL2;
                isOpening = false;
            }
            if(curImg == pacL2 && isOpening) {
                setImage(pacL1);
                curImg = pacL1;
            }
            if(curImg == pacL2 && !isOpening) {
                setImage(pacL3);
                curImg = pacL3;
            }
            if(curImg == pacL3) {
                setImage(pacL2);
                curImg = pacL2;
                isOpening = true;
            }
            setLocation(getX()-1,getY());
            sound.play();
        }
        
        if(direction == "up") {
            if(curImg == pacU1 || curImg == pacFull || (curImg != pacU1 && curImg != pacU2 && curImg != pacU3)) {
                setImage(pacU2);
                curImg = pacU2;
                isOpening = false;
            }
            if(curImg == pacU2 && isOpening) {
                setImage(pacU1);
                curImg = pacU1;
            }
            if(curImg == pacU2 && !isOpening) {
                setImage(pacU3);
                curImg = pacU3;
            }
            if(curImg == pacU3) {
                setImage(pacU2);
                curImg = pacU2;
                isOpening = true;
            }
            setLocation(getX(),getY()-1);
            sound.play();
        }
        
        if(direction == "down") {
            if(curImg == pacD1 || curImg == pacFull || (curImg != pacD1 && curImg != pacD2 && curImg != pacD3)) {
                setImage(pacD2);
                curImg = pacD2;
                isOpening = false;
            }
            if(curImg == pacD2 && isOpening) {
                setImage(pacD1);
                curImg = pacD1;
            }
            if(curImg == pacD2 && !isOpening) {
                setImage(pacD3);
                curImg = pacD3;
            }
            if(curImg == pacD3) {
                setImage(pacD2);
                curImg = pacD2;
                isOpening = true;
            }
            setLocation(getX(),getY()+1);
            sound.play();
        }
    }
    
    public String getDirection() {
        String direction = Greenfoot.getKey();
        if(Greenfoot.isKeyDown("w")) direction = "up";
        if(Greenfoot.isKeyDown("s")) direction = "down";
        if(Greenfoot.isKeyDown("a")) direction = "left";
        if(Greenfoot.isKeyDown("d")) direction = "right";
        if(direction != null) {
            return direction;
        } else {
            return lastPressed;
        }
    }
    private String lastPressed = null;
}