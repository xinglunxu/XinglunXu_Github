import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import static java.lang.Math.random;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author xinglun xu
 */
public class Game 
{
    public final static int IMAGE_SIZE = 600;
    public final static int CRITTER_SIZE = 60;
    private final static int MAXIMUM_SPEED = 24;
    private final static int MINIMUM_SPEED = 5;
    private final static double SQUAREROOTOF3 = Math.sqrt(3);
    // other symbolic program constants go here
    
    private final Image image;
    private final Critter critterFactory;
    
    private List<Critter> critterList;
    private boolean isOver;
    private Graphics graphics;
        
    Game() 
    {         
        // * construct the Image object, of size IMAGE_SIZE
        image = new BufferedImage( IMAGE_SIZE, IMAGE_SIZE, BufferedImage.TYPE_INT_RGB );
        // * paint the image all white for its initial display
        graphics = image.getGraphics();
        graphics.fillRect(0, 0, IMAGE_SIZE, IMAGE_SIZE);
        // * instantiate critterFactory, a Critter [subclass] object, that is used as a "factory" to create Critter objects.
        critterFactory = new WowCritter();
    }
    
    public Image getImage(){return image;}
    
    public boolean getGameState(){return isOver;}
    
    public void start()
    {
        // 1. put the game in the state that it is not over.
        isOver = false;
        // 2. construct an ArrayList of 3 new Critters. 
        //    For each Critter that you create, use the critterFactory to randomly select a subclass of Critter to instantiate.
        critterList = new ArrayList<>();
        critterList.add(critterFactory.makeCritter());
        critterList.add(critterFactory.makeCritter());
        critterList.add(critterFactory.makeCritter());
    }
        
    public void draw()
    {       
        // 1. blank the previous image (otherwise, you get a "trail")
        graphics.setColor(Color.white);
        graphics.fillRect(0, 0, IMAGE_SIZE, IMAGE_SIZE);
        // 2. move then draw each critter.
        for(Critter critter: critterList)
        {
            critter.move();
            critter.draw(graphics);
        }
    }
        
    public void processClick( int x, int y )
    {
        // for each critter, if the mouse click is "in" the critter, remove it from the critter list.
        int arrayListLength = critterList.size();
        int delete = -1;
        for(int i=0;i<arrayListLength;i++)
        {
            if(critterList.get(i).hit(x, y)){delete = i;}
        }
        
        if(delete != -1)
        {
            critterList.remove(delete);
            delete = -1;
        }
        
        //                   if such a removel makes the list empty, put the game in a "game over" state. 
        if(critterList.isEmpty()){isOver = true;}
    }
    
    abstract private class Critter
    {
        // declare its attributes
        private Color color;
        private int x;
        private int y;
        private int delta_x;
        private int delta_y;
        
        public int getX()
        {
            return x;
        }
        
        public int getY()
        {
            return y;
        }
        
        public Color getColor()
        {
            return color;
        }
        
        Critter makeCritter() 
        {
            // construct a critter, selected randomly from the 3 Critter subclasses 
            
            int randomNum = (int)(random()*5);
            Critter critter = null;
            switch(randomNum)
            {
                case 0:
                    critter = new SquareCritter();
                    break;
                case 1:
                    critter = new RoundCritter();
                    break;
                case 2: 
                    critter = new WowCritter();
                    break;
                case 3:
                    critter = new PolyCritter();
                    break;
                case 4:
                    critter = new ArcCritter();
                    break;
            }
            return critter;
        }
        
        private Critter()
        {
            // set attributes with suitably random values.
            color = new Color((int)(random()*255),(int)(random()*255),(int)(random()*255));
            x = (int)(random()*600);
            y = (int)(random()*600);
            delta_x = (int)(random()*(MAXIMUM_SPEED-MINIMUM_SPEED)+MINIMUM_SPEED)*getRandomSign();
            delta_y = (int)(random()*(MAXIMUM_SPEED-MINIMUM_SPEED)+MINIMUM_SPEED)*getRandomSign();
        }
        
        boolean hit(int x, int y)
        {
            int xLeftLimit = this.x - CRITTER_SIZE/2;
            int xRightLimit = this.x + CRITTER_SIZE/2;
            int yTopLimit = this.y - CRITTER_SIZE/2;
            int yButtomLimit = this.y + CRITTER_SIZE/2;
            
            System.out.print(this.x+" "+this.y+" "+x+" "+y+"\n");
            return ( x>xLeftLimit && x<xRightLimit  &&  y>yTopLimit && y<yButtomLimit );
        }
        
        abstract void draw( Graphics graphics );
        
        void move()
        {
            // Move the object by applying deltas to its coordinates.           
            // Be careful to implement the wrap-around properly 
            // (consider what happens when applying the % operator to a negative number).
            x = adjustCoordinate(x + delta_x);
            y = adjustCoordinate(y + delta_y);
            
        }
        
    }
    
    // Subclass Critter with a square critter
    private class SquareCritter extends Critter
    {
        

        @Override
        void draw(Graphics graphics) 
        {
            graphics.setColor(getColor());
            graphics.fillRect(getX()-CRITTER_SIZE/2, getY()-CRITTER_SIZE/2, CRITTER_SIZE, CRITTER_SIZE);
        }
        // your implementation goes here.

   
    }
    
    // Subclass Critter with a round critter
    private class RoundCritter extends Critter
    {
        
        
        
        @Override
        void draw(Graphics graphics) 
        {
            graphics.setColor(getColor());
            graphics.fillOval(getX()-CRITTER_SIZE/2, getY()-CRITTER_SIZE/2, CRITTER_SIZE, CRITTER_SIZE);
        }
        // your implementation goes here.

    }
    // Subclass Critter with a round critter whose color is randomly chosen each time it is drawn
    private class WowCritter extends Critter
    {

        @Override
        void draw(Graphics graphics) 
        {
            graphics.setColor(new Color((int)(random()*255),(int)(random()*255),(int)(random()*255)));
            graphics.fillOval(getX()-CRITTER_SIZE/2, getY()-CRITTER_SIZE/2, CRITTER_SIZE, CRITTER_SIZE);
        }
        // your implementation goes here.

   
    }
    
    private class PolyCritter extends Critter
    {
        void draw(Graphics graphics)
        {
            graphics.setColor(getColor());
            graphics.fillPolygon( new int[]{getX(),getX()+CRITTER_SIZE/2,getX()-CRITTER_SIZE/2}, 
            new int[]{getY()+(int)(SQUAREROOTOF3*CRITTER_SIZE/2-CRITTER_SIZE/(2*SQUAREROOTOF3)),getY()-(int)(CRITTER_SIZE/(2*SQUAREROOTOF3)), getY()-(int)(CRITTER_SIZE/(2*SQUAREROOTOF3))}, 3);
            graphics.fillPolygon( new int[]{getX(),getX()+CRITTER_SIZE/2,getX()-CRITTER_SIZE/2}, 
            new int[]{getY()-(int)(SQUAREROOTOF3*CRITTER_SIZE/2-CRITTER_SIZE/(2*SQUAREROOTOF3)),getY()+(int)(CRITTER_SIZE/(2*SQUAREROOTOF3)), getY()+(int)(CRITTER_SIZE/(2*SQUAREROOTOF3))}, 3);
        }
    }
    
    private class ArcCritter extends Critter
    {
        @Override
        void draw(Graphics graphics)
        {
            graphics.setColor(getColor());
            graphics.fillArc(getX()-CRITTER_SIZE/2, getY()-CRITTER_SIZE/2, CRITTER_SIZE, CRITTER_SIZE, 45, 270);
        }
    }
    
    private int getRandomSign()
    {
        if((int)(random()*2) == 0 )
        {
            return -1;
        }
        return 1;
    }
    
    private int adjustCoordinate(int num)
    {
        if(num > 600){return num-600;}
        if(num < 0){return num+600;}
        return num;
    }
}
