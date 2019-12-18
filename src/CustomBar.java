import java.awt.*;
import java.awt.Component.*;

import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import javafx.scene.chart.Axis;
import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.SpringLayout;

// This class is the custom component that displays the bar-graph-style component
// to display the outputted data from the calcualator class
public class CustomBar extends JComponent {
    // Left / Lowest Value extreme. Eg. Very Bad
    String LowExtremeText;
    // Right / Highest Value extreme. Eg. Very Good
    String HighExtremeText;
    // The ratio of the middle seciton reletive to the rest of the component
    float ModerateAreaRatio = (1.0f/3.0f);
    // This is how far the pointer is along the control
    public float PositionPercentage = 0.7f;
    
    
    // Component Size X
    int SizeX = 300;
    // Component Size Y
    int SizeY = 40;
    
    // Low Color
    Color LowColor = new Color(211, 35, 46);
    
    // Mod Color
    Color ModColor = new Color(255, 203, 23);
    
    // High Color
    Color HighColor = new Color(80, 160, 63);
    
    // Pointer Color
    Color PointerColor = new Color(0, 0, 0);
    
    // Text Color
    Color TextColor = new Color(0, 0, 0);
    
    int FontSize = 16;
    
    // Base constructor
    public CustomBar( ) { 
        this(300, 40, "Low", "High", (1.0f/3.0f)); 
    }
  
    public CustomBar(String lowExtremeText, String highExtremeText){
        this(300, 40, lowExtremeText, highExtremeText, (1.0f/3.0f));
    }
    
    public CustomBar(String lowExtremeText, String highExtremeText, float moderateAreaRatio){
        this(300, 40, lowExtremeText, highExtremeText, moderateAreaRatio);
    }
    public CustomBar(int sizeX, int sizeY, String lowExtremeText, String highExtremeText, float moderateAreaRatio){
        ModerateAreaRatio = moderateAreaRatio;
        
        SizeX = sizeX;
        sizeY = sizeY;
        
        LowExtremeText = lowExtremeText;
        HighExtremeText = highExtremeText;
        
        this.setSize(sizeX, sizeY);
        this.setDoubleBuffered(true);
        
        //LowExtremeText.setText(lowExtremeText);
        //HighExtremeText.setText(highExtremeText);
    }
    public void SetPositionPercentage(float p){
        this.PositionPercentage = p;
    }
    // TEST
    public void log(String s){
        System.out.println("REALSESTATE: " + s);
    }
    // TEST
    // Paint method
    public void paint(Graphics g) {
        // Gets and sets the size X and Y of the component
        SizeX = this.getSize().width;
        SizeY = this.getSize().height;
        
        // Gets and sets the Origin X and Y of the component
        int originY = this.getY();
        int originX = this.getX();
//        int originY = 0;
//        int originX = 0;
        
        // Set the Low (and High) width:E
        // The ratio of moderate area compared to the rest of the component 
        // (eg. 1/3 means the moderate section will be 1/3rd  of the size of
        // the control.)
        // Then, we get the length of the control, muinus the middle section.
        // Then, this is divided by two, so we have the length of the Low 
        // section.
        int LowWidth = (int)Math.round((SizeX-(ModerateAreaRatio*SizeX))/2);
        // The low section and the high section are the same so we set them
        // to the same value
        int HighWidth = LowWidth;
        // The middle, would usually by the ratio multiplied by the
        // width. However, due to rounding issues, we make the midddle section
        // equal to the width, minus High and low sections
        int ModWidth = SizeX-(LowWidth+HighWidth);
        
        // Turn the default graphics object to 2d and assign to variable g2d
        Graphics2D g2d = (Graphics2D)g;

        g2d.setPaint(Color.MAGENTA);
        g2d.fillRect(this.getX(), this.getY(), this.getSize().width, this.getSize().height);
        
       // Draw the Low Section Box
        // Set the Color to the LowColor
        g2d.setPaint(LowColor);
        // Draw the box
        g2d.fillRect(originX, originY, LowWidth, SizeY);
        
       // Draw the Moderate Section Box
        // Set the Color to the ModColor
        g2d.setPaint(ModColor);
        // Draw the box
        g2d.fillRect(originX+LowWidth, originY, ModWidth, SizeY);
        
       // Draw the High Section Box
        // Set the Color to the HighColor
        g2d.setPaint(HighColor);
        // Draw the box
        g2d.fillRect(originX+ModWidth+LowWidth, originY, HighWidth, SizeY);
       
       // Draw the Low and High Text
        // Set up font
        g2d.setPaint(TextColor);
        Font f = new Font("Segoe UI", Font.PLAIN, 16);
        
        g2d.setFont(f);
        g2d.setRenderingHint(
            RenderingHints.KEY_TEXT_ANTIALIASING,
            RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        
        // Draw Low Text
        
        // Draw Text
        g2d.drawString(LowExtremeText, 
                (int) (originX+5), 
                (int) (this.getY()+this.getSize().height)-g2d.getFontMetrics().getDescent());
        
        // Draw High Text
        // Draw Text
        g2d.drawString(HighExtremeText, 
                (int) (originX+this.getWidth()-(g2d.getFontMetrics().stringWidth(HighExtremeText)+5)), 
                (int) (this.getY()+this.getHeight())-g2d.getFontMetrics().getDescent());
        
        // Next we add the pointer 
        // Determine the pointer position. (percentage multiplied by the width);
        int pointerWidth = (int)(SizeX*0.015f);
        float pointerPos = PositionPercentage*SizeX;
        // Display the pointer
        g2d.setPaint(PointerColor);
        // Draw the box
        if(pointerPos >= this.getWidth()-g2d.getFontMetrics().stringWidth(HighExtremeText)-10 || 
           pointerPos <= g2d.getFontMetrics().stringWidth(LowExtremeText)+10){
            g2d.fillRect((int)((originX+pointerPos))-(int)(pointerWidth/2), originY, (int)pointerWidth, SizeY-g2d.getFontMetrics().getHeight()+5);
        }else{
            g2d.fillRect((int)(originX+pointerPos)-(int)(pointerWidth/2), originY, (int)pointerWidth, SizeY);
        }
        
    }
    public void update(Graphics g) {
        
    } 
    
    // When an event occurs, this function is called by the system.
    // All event handling for the component is handled here
    public boolean handleEvent(Event e) {
        // Is the Event Id equal to the ACTION_EVENT && is the target of that event applicable to the element
        if ((e.id == Event.ACTION_EVENT)) {
            
        }
        return(super.handleEvent(e));
   }
}
