package graphics;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.geom.RoundRectangle2D;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
public class JRoundTextField extends JTextField{ 

    private int arcw=20; 
    private int arch=20; 


     
    public JRoundTextField() { 
        setOpaque(false); 
        setBorder(new EmptyBorder(0,5,0,2)); 
    } 

    @Override 
     protected void paintComponent(Graphics g) { 

        Graphics2D g2 = (Graphics2D) g; 
        Paint oldPaint = g2.getPaint(); 

        RoundRectangle2D.Float r2d = new RoundRectangle2D.Float( 
                0,0,getWidth(),getHeight(),arcw,arch); 
        g2.clip(r2d); 

        g2.setPaint(new GradientPaint(0.0f, 0.0f, getBackground(), 
                0.0f, getHeight(), getBackground())); 
        g2.fillRect(0,0,getWidth(),getHeight()); 
    
        g2.setPaint(new GradientPaint(0.0f, 0.0f, new Color(219, 225, 222), 
                0.0f, getHeight(), new Color(219, 225, 222))); 
        g2.drawRoundRect(0, 0, getWidth(), getHeight(), arcw, arch); 

        g2.setPaint(oldPaint); 
        super.paintComponent(g); 

    } 

    public int getArcw() { 
        return arcw; 
    } 

    public void setArcw(int arcw) { 
        this.arcw = arcw; 
    } 

    public int getArch() { 
        return arch; 
    } 

    public void setArch(int arch) { 
        this.arch = arch; 
    } 
}
