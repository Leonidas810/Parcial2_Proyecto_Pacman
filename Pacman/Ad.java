import greenfoot.*;


public class Ad extends Actor
{
    private int totalCount = 0;
    
    public Ad(String text)
    {
        setImage(new GreenfootImage(text, 20, Color.WHITE, Color.BLACK));
    }
    
    public Ad()
    {
        setImage(new GreenfootImage("0", 20, Color.WHITE, Color.BLACK));
    }

    public void Ad_IncreaseScore(int amount)
    {
        totalCount += amount;
        setImage(new GreenfootImage("" + totalCount, 20, Color.WHITE, Color.BLACK));
    }
}