package gdDN;

//import javax.swing.JTextField;
//
//import java.awt.Color;
//import java.awt.Graphics;
//import java.awt.Graphics2D;
//import java.awt.RenderingHints;
//
//public class jT extends JTextField{
//
//	 private boolean mouseOver = false;
//
//	    public jT() {
//	        super();
//	        addMouseListener(new java.awt.event.MouseAdapter() {
//	            public void mouseEntered(java.awt.event.MouseEvent evt) {
//	                mouseOver = true;
//	                repaint();
//	            }
//
//	            public void mouseExited(java.awt.event.MouseEvent evt) {
//	                mouseOver = false;
//	                repaint();
//	            }
//	        });
//	    }
//
//	    @Override
//	    public void paint(Graphics grphcs)
//		{
//			super.paint(grphcs);
//			Graphics2D g2 =  (Graphics2D) grphcs;
//			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
//			g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB);
//			int width = getWidth();
//			int height = getHeight();
//			if(mouseOver)
//			{
//				g2.setColor(Color.YELLOW);
//			}
//			else
//			{
//				g2.setColor(Color.red);
//			}
//			
//			g2.fillRect(2, height - 1, width - 4, 1);
//			g2.dispose();
//		}
//}


import javax.swing.JTextField;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class jT extends JTextField implements FocusListener {
    private boolean focused = false;

    public jT() {
        super();
        addFocusListener(this);
    }

    @Override
    public void paint(Graphics grphcs)
	{
		super.paint(grphcs);
		Graphics2D g2 =  (Graphics2D) grphcs;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB);
		int width = getWidth();
		int height = getHeight();
		if(focused)
		{
			g2.setColor(Color.YELLOW);
		}
		else
		{
			g2.setColor(Color.red);
		}
		
		g2.fillRect(2, height - 1, width - 4, 1);
		g2.dispose();
	}

    @Override
    public void focusGained(FocusEvent e) {
        focused = true;
        repaint();
    }

    @Override
    public void focusLost(FocusEvent e) {
        focused = false;
        repaint();
    }
}
