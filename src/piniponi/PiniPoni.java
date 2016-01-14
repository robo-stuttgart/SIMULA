
package piniponi;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class PiniPoni extends JComponent implements ActionListener, MouseMotionListener, KeyListener {
        private int ballX=120;
        private int[] ball_RED_X={400,400,400};
	private int ballY=80; 
        private int[] ball_posX_BLUE={120,120,120};
        private int[] ball_posY_RED={80,160,240};
        private int[] ball_posY_BLUE={80,160,240};
        private int TX=0;
        private int TY=0;
        private int rollerX=0;
        private int ballYSpeed=7;  //7
        private int ballXSpeed=5;  //5
        
        private int bSpeedX=7;
        private int bSpeedY=7;
        
        final JLabel dream=new JLabel();
        
        
    
    public static void main(String[] args) {
      
	      PiniPoni game=new PiniPoni(); 
                JFrame window=new JFrame("Pini Poni");
                
                JLabel Xaxis=new JLabel("X axis: ");
               // window.add(dream);
                Xaxis.setSize(50, 50);
                Xaxis.setLocation(600, 60);
                window.add(Xaxis);
                
                
                window.add(game);
                window.pack();
		window.setSize(560, 380);
                window.setVisible(true);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
		window.setLocationRelativeTo(null);
                
                //Timer for Ball
                Timer t=new Timer(50,game); // action listener every 50 ms
                t.start();
                window.addKeyListener(game);
                window.addMouseMotionListener(game);
    } //end of main



//@Override
protected void paintComponent(Graphics g){
   //field
    
        g.setColor(new Color(79,112,46));
    g.fillRect(2, 2, 550,350); // field size 550x350
  //frame
    g.setColor(Color.white);
    g.drawRect(5, 5, 542,343 ); //inside field size 542x343
   //center -circle
  g.setColor(Color.white);
    g.drawOval(240,138 , 80,80 ); 
   //center line
    g.setColor(Color.white);
    g.drawLine(280, 5,280,348 );
    //gate1
    g.setColor(Color.white);
    g.drawRect(5,136,30 ,76);
    //gate 2
    g.setColor(Color.white);
    g.drawRect(517,136,30 ,76);
    
   // g.setColor(Color.WHITE);
 //   g.fillRect(2, 2, 550,350);
  
// roller
   // g.setColor(Color.gray);
   // g.fillRect(rollerX, 550, 100, 20);
    
    //ball 1 run -blue-----------------------------
    g.setColor(Color.blue);
    g.fillOval(ballX, ballY, 20, 20);
    //ball 2 run -blue
    g.setColor(Color.blue);
    g.fillOval(ball_posX_BLUE[1], ball_posY_BLUE[1], 20, 20);
    //ball 3 run -blue
    g.setColor(Color.blue);
    g.fillOval(ball_posX_BLUE[2], ball_posY_BLUE[2], 20, 20);
    //keeper
    g.setColor(Color.blue);
    g.fillOval(30, 160, 20, 20);
    
    //ball 1 run -red----------------------------------
    g.setColor(Color.red);
    g.fillOval(ball_RED_X[0], ball_posY_RED[0], 20, 20);
    //ball 2 run -red
    g.setColor(Color.red);
    g.fillOval(ball_RED_X[1], ball_posY_RED[1], 20, 20);
    //ball 3 run -red
    g.setColor(Color.red);
    g.fillOval(ball_RED_X[2], ball_posY_RED[2], 20, 20);
    // keeper
    g.setColor(Color.red);
    g.fillOval(500, 160, 20, 20);
    
    
}

    @Override
    public void actionPerformed(ActionEvent e) {
         // intersection
       boolean cross=Intersection(ballX,ballY,ball_RED_X[0],ball_posY_RED[0]);
       
        ballX=ballX+ballXSpeed;
        ballY=ballY+ballYSpeed;
       
       //red player
        ball_RED_X[0]=ball_RED_X[0]+bSpeedX;
        ball_posY_RED[0]=ball_posY_RED[0]+bSpeedY;
        
        //for manually controlled robot----------------------
        //ball_posX_BLUE[2], ball_posY_BLUE[2]
        ball_posX_BLUE[2]=ball_posX_BLUE[2]+TX;
        ball_posY_BLUE[2]=ball_posY_BLUE[2]+TY;
        //ball_posY_RED[1]=ball_posY_RED[1]+Function(ball_posY_RED[1]);
        //ball_posY_RED[2]=ball_posY_RED[2]+Function(ball_posY_RED[2]);
        //if (ballX>=rollerX && ballX<=rollerX+100 && ballY>=540){
         //   ballYSpeed=-7; }
        // border intersection
        if (cross==true)
        {
            System.out.println("Intersection!!!!! Warning!!");
            //ballXSpeed=Math.abs(ballXSpeed); // blue team player speed
            
                   //bSpeedX=Math.abs(bSpeedX);
                    bSpeedY=Math.abs(bSpeedY);
        } // */
        
        if (ballX>=545){
        ballXSpeed=-5;   }
        if (ballX<=5){
        ballXSpeed=5;   }
        if (ballY<=5){
        ballYSpeed=7;
        }
        if (ballY>=345){
        ballYSpeed=-7;
        }
          ///------------------------------Red Team
      if (ball_RED_X[0]>=545){
       bSpeedX=-5;   }
       if (ball_RED_X[0]<=5){
        bSpeedX=5;   }
        if (ball_posY_RED[0]<=5){
        bSpeedY=7;
        }
        if (ball_posY_RED[0]>=345){
        bSpeedY=-7;
        }
       
        repaint();
    }
    
    protected boolean Intersection(int aX, int aY, int bX,int bY)
    {
        int dist=0;
              
        double AcenterX=aX+10;
        double AcenterY=aY+10;
                
        double BcenterX=bX+10;
        double BcenterY=bY+10;
        
      double tempX=AcenterX-BcenterX;
      double tempY=AcenterY-BcenterY;
      //System.out.println("AcenterY "+ AcenterY+"BcenterY: "+BcenterY);
      //dist=Math.abs(tempX);
      dist=(int)Math.sqrt(Math.pow(4,Math.abs(tempX))+Math.pow(2,Math.abs(tempY)));
      //System.out.println("diff(x1-x2): "+ tempX);
     // System.out.println("diff(y1-y2): "+ tempY);
      //System.out.println("distance: "+dist);
     if (dist<=20) // sum of radiuses 
         return true; // if two circles intersect the return true
      else
    return false;
    }
     
    @Override
    public void mouseDragged(MouseEvent e) {
       
    }

    @Override
    public void mouseMoved(MouseEvent e) {
       rollerX=e.getX()-50;
       repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {
       }

    @Override
    public void keyPressed(KeyEvent e) {
    //ball_RED_X[2], ball_posY_RED[2]
        if (e.getKeyCode()==KeyEvent.VK_UP)
    {
    TY=TY-2;
    
    }
    else if (e.getKeyCode()==KeyEvent.VK_DOWN){
    TY=TY+2;
    }
    else if (e.getKeyCode()==KeyEvent.VK_RIGHT)
    { TX=TX+2;
    }
    else if (e.getKeyCode()==KeyEvent.VK_LEFT)
      TX=TX-2;
        }

    @Override
    public void keyReleased(KeyEvent e) {
        
         }
    
}
