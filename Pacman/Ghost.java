import greenfoot.*;

public class Ghost  extends Mover
{
    private boolean isOut = false;
    public boolean canMove = true;
    private String direction = null;
    private String color = null;
    private int frameCount = 0;
    private boolean isDead = false;
    private static int MAX_DEAD_TIME = 200;
    public Ghost(String ghostColor) {
        color = ghostColor;
        setColor();
    }
    
    public void act() 
    {
        if(isOut==true && canMove==true) {
            go();

            Pacworld world = (Pacworld) getWorld();
            if(isDead==false) {
                if(world.canEatGhosts==true) {
                    setImage("ghost_b.png");
                } else {
                    setColor();
                }
            }else{
                if(frameCount >= MAX_DEAD_TIME) {

                    isDead = true;
                    setColor();
                    frameCount = -1;
                }
                frameCount++;
            }
            
            if(isOn(Pacman.class) && world.canEatGhosts==false && isDead==false) {
                eat(Pacman.class);
            }
            
        } else {//Cuando los fantasmas no han salido, manualmente los movemos hacia afuera, una vez hecho eso, podemos darles el movimiento aleatorio
            int x = getX();
            int y = getY();
            if(x == 47 && y == 57) {
                // red
                setLocation(47, 56);
            } else if (x == 47 && y == 56) {
                setLocation(47, 55);
            } else if (x == 47 && y == 55) {
                setLocation(47, 54);
            } else if (x == 47 && y == 54) {
                setLocation(47, 53);
            } else if (x == 47 && y == 53) {
                setLocation(47, 52);
                isOut = true;
                frameCount = -1;
            } else if (x == 47 && y == 62) {
                // pink
                setLocation(47, 61);
            } else if (x == 47 && y == 61) {
                setLocation(47, 60);
            } else if (x == 47 && y == 60) {
                setLocation(47, 59);
            } else if (x == 47 && y == 59) {
                setLocation(47, 58);
            } else if (x == 47 && y == 58) {
                setLocation(47, 57);
            } else if (x == 42 && y == 62) {
                // green
                setLocation(43, 62);
            } else if (x == 43 && y == 62) {
                setLocation(44, 62);
            } else if (x == 44 && y == 62) {
                setLocation(45, 62);
            } else if (x == 45 && y == 62) {
                setLocation(46, 62);
            } else if (x == 46 && y == 62) {
                setLocation(47, 62);
            } else if (x == 52 && y == 62 && frameCount == 5) {
                // yellow
                setLocation(51, 62);
            } else if (x == 51 && y == 62) {
                setLocation(50, 62);
            } else if (x == 50 && y == 62) {
                setLocation(49, 62);
            } else if (x == 49 && y == 62) {
                setLocation(48, 62);
            } else if (x == 48 && y == 62) {
                setLocation(47, 62);
            }
            frameCount++;
        }
    }
    
    public void go() {
        if(direction != null && canMove(direction)) {
            move(direction);
        } else {
            direction = setDir(Greenfoot.getRandomNumber(4));
            go();
        }
    }
    
    public String setDir(int randNum) {
        String direction = "";
        if (randNum == 0) {
            direction = "left";
        } else if (randNum == 1) {
            direction = "right";
        } else if (randNum == 2) {
            direction = "up";
        } else {
            direction = "down";
        }
        return direction;
    }
    
    public void setColor() {
        if(color == "green") {
            setImage("ghost_g.png");
        } else if (color == "pink") {
            setImage("ghost_p.png");
        } else if (color == "red") {
            setImage("ghost_r.png");
        } else if (color == "yellow") {
            setImage("ghost_y.png");
        }
    }
    
    public void move(String direction) {
        if(direction == "right") {
            setLocation(getX()+1,getY());
        }
        if(direction == "left") {
            setLocation(getX()-1,getY());
        }
        if(direction == "up") {
            setLocation(getX(),getY()-1);
        }
        if(direction == "down") {
            setLocation(getX(),getY()+1);
        }
    }
    
    public void die() {
        setImage("ghost_eyes.png");
        isDead = true;
        new GreenfootSound("siren.wav").play();
    }
}