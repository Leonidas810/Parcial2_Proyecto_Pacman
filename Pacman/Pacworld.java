import greenfoot.*;
import java.util.List;

public class Pacworld  extends World
{
    private Ad adScore=new Ad();
    private Ad adready = new Ad("READY!");
    public Pacman pacman = new Pacman();
    public boolean canEatGhosts = false;
    private int frameCount = 0;
    private boolean isEnding = false;
    private boolean isGameOver = false;
    private boolean isGameRunning = true;
    public int ghostTimer = 50;
    public int MAX_GHOST_TIME = 250;
    public Pacworld()
    {    
        super(5*19, 5*25, 4);
        Greenfoot.setSpeed(43);
        new GreenfootSound("start.wav").play();
        setPaintOrder(Wall.class,Pacman.class,Ghost.class);
        addObject(adScore, 47, 7);
        addObject(new Ad("HIGH SCORE:"), 47, 2);
        addObject(adready, 47, 72);
        addObject(pacman, 47, 92);
        
        //Diseño del Nivel
       int[] Level1 = {
            0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
            0,1,1,1,1,1,1,1,1,0,1,1,1,1,1,1,1,1,0,
            0,2,0,0,1,0,0,0,1,0,1,0,0,0,1,0,0,2,0,
            0,1,0,0,1,0,0,0,1,0,1,0,0,0,1,0,0,1,0,
            0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,
            0,1,0,0,1,0,1,0,0,0,0,0,1,0,1,0,0,1,0,
            0,1,1,1,1,0,1,1,1,0,1,1,1,0,1,1,1,1,0,
            0,0,0,0,1,0,0,0,3,0,3,0,0,0,1,0,0,0,0,
            3,3,3,0,1,0,3,3,3,3,3,3,3,0,1,0,3,3,3,
            0,0,0,0,1,0,3,0,0,3,0,0,3,0,1,0,0,0,0,
            3,3,3,3,1,3,3,0,3,3,3,0,3,3,1,3,3,3,3,
            0,0,0,0,1,0,3,0,0,0,0,0,3,0,1,0,0,0,0,
            3,3,3,0,1,0,3,3,3,3,3,3,3,0,1,0,3,3,3,
            0,0,0,0,1,0,3,0,0,0,0,0,3,0,1,0,0,0,0,
            0,1,1,1,1,1,1,1,1,0,1,1,1,1,1,1,1,1,0,
            0,1,0,0,1,0,0,0,1,0,1,0,0,0,1,0,0,1,0,
            0,2,1,0,1,1,1,1,1,3,1,1,1,1,1,0,1,2,0,
            0,0,1,0,1,0,1,0,0,0,0,0,1,0,1,0,1,0,0,
            0,1,1,1,1,0,1,1,1,0,1,1,1,0,1,1,1,1,0,
            0,1,0,0,0,0,0,0,1,0,1,0,0,0,0,0,0,1,0,
            0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,
            0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0
        };
        newLevel(Level1);

        
        Gate gate= new Gate();
        Ghost ghostG= new Ghost("green");
        addObject(ghostG, 42, 62);
        Ghost ghostP= new Ghost("pink");
        addObject(ghostP, 47, 62);
        Ghost ghostY= new Ghost("yellow");
        addObject(ghostY, 52, 62);
        Ghost ghostR= new Ghost("red");
        addObject(ghostR, 47, 57);
        
    }
    
    public void act() {
        if(isEnding==true) endGame();
        if (isGameOver==false ) {  
            int points = getObjects( Dot.class ).size();
            if ( isGameRunning==true && points == 0 ) {  
                isGameOver = true;  
                pacman.canMove = false;
                List ghosts = getObjects(Ghost.class);
                for(int i = 0;i<ghosts.size();i++) {
                    Ghost ghost = (Ghost) ghosts.get(i);
                    ghost.canMove = false;
                }
            Ad gameOverText = new Ad("Game Completed");
            addObject(gameOverText, 47, 72);
            }
        }
    }
      
    public void started() {
        removeObject(adready);
    }
    
    
    public Ad getCounter() {
        return adScore;
    }
    
    //Crearemos el nivel
    public void newLevel(int array[]) {
        int i = 0;
        
        for (int y = 4/2+10; y<24*5; y+=5) {
            for (int x = 4/2; x<19*5; x+=5) {
                int type = array[i];
                
                if(type == 0) {
                    addObject(new Wall(), x, y);
                } else if (type == 1) {
                    addObject(new Dot(), x, y);
                } else if (type == 2) {
                    addObject(new Powerpellet(), x, y);
                } else if (type == 3) {
                }
                
                if (i < array.length-1) i++;
            }
        }
    }

    public void endGame() {
        if(frameCount == 0) {    
            isEnding = true;
            Ad gameDoneText = new Ad("Game Over");
            addObject(gameDoneText, 47, 52);   
            pacman.canMove = false;
            new GreenfootSound("die.wav").play();
            pacman.setImage("pacman_c.png");
        } else if(frameCount == 1) {
            pacman.setImage("pacman_c_1.png");
        } else if(frameCount == 3) {
            pacman.setImage("pacman_c_2.png");
        } else if(frameCount == 5) {
            pacman.setImage("pacman_c_3.png");
        } else if(frameCount == 7) {
            pacman.setImage("pacman_c_4.png");
        } else if(frameCount == 9) {
            pacman.setImage("pacman_c_5.png");
        } else if(frameCount == 11) {
            pacman.setImage("pacman_c_6.png");
        }
        frameCount++;
        List ghosts = getObjects(Ghost.class);
        for(int i = 0;i<ghosts.size();i++) {
                    Ghost ghost = (Ghost) ghosts.get(i);
                    ghost.canMove = false;
        }
    }
}
