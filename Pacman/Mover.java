import greenfoot.*;
import java.util.List;
import java.util.ArrayList;


public class Mover  extends Actor
{
    public boolean atWorldEdge()
    {
        if(getX() > getWorld().getWidth() - 2 || getX() == 0)
            return true;
        if(getY() > getWorld().getHeight() - 2 || getY() == 0)
            return true;
        else
            return false;
    }
    
    public boolean canMove(String direction) {
        int x;
        int y;
        if(direction == "right") {
            x = getX()+1;
            y = getY();
        } else if(direction == "left") {
            x = getX()-1;
            y = getY();
        } else if(direction == "up") {
            x = getX();
            y = getY()-1;
        } else {
            x = getX();
            y = getY()+1;
        }
        int origX = getX();
        int origY = getY();
        setLocation(x,y);
        Actor theWall = getOneIntersectingObject(Wall.class); //Devuelve un objeto que se cruza con este objeto. Esto tiene en cuenta la extensión gráfica de los objetos.
        Actor theGate = getOneIntersectingObject(Gate.class);
        setLocation(origX, origY);
        return(theWall == null && theGate == null);//returnar unicamente cuando theWall y theGate sean diferente de nulo 
    }
    
    public boolean isOn(Class object) {
        Actor actor = getOneIntersectingObject(object);
        return actor != null;
    }
    
    public void eat(Class object) {
        Actor actor = getOneObjectAtOffset(0, 0, object);//getOneObjectAtOffset Devuelve un objeto que se encuentra en la celda especificada 
        if(actor != null && object != Pacman.class && object != Ghost.class) //Mientras el objeto no sea null, de la clase Pacman o Ghost, eliminar el objeto
        {
            getWorld().removeObject(actor);
        }
        Pacworld world = (Pacworld) getWorld();
        Ad adScore = world.getCounter();
        if(object == Dot.class) {
            adScore.Ad_IncreaseScore(10);
        } else if (object == Powerpellet.class) {
            adScore.Ad_IncreaseScore(50);
            world.canEatGhosts = true;
            world.ghostTimer = 0;
        } else if (object == Ghost.class && world.canEatGhosts == true) {
            adScore.Ad_IncreaseScore(200);
            List ghosts = world.getObjectsAt(getX(), getY(), Ghost.class);
            for(int i = 0;i<ghosts.size();i++) {
                Ghost ghost = (Ghost) ghosts.get(i);
                ghost.die();
            }
        }else if(object==Pacman.class)world.endGame();//Cuando llamamos al metodo eat y mandamos como parametro un objeto de la clase Ghost, al tocar al objeto de la clase Pacman acaba el juego
   }
}
