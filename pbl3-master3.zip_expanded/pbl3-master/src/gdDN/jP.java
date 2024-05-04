package gdDN;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.ImageIcon;
import javax.swing.JPasswordField;
import javax.swing.plaf.basic.BasicBorders.MenuBarBorder;

public class jP extends JPasswordField implements FocusListener {

	 private boolean focused = false;
//	 private final Image eye;
//	 private final Image eye_hide;
	 private boolean hide = true;
	    public jP() {
	        super();
	        addFocusListener(this);
//	        addMouseListener(new MouseAdapter() {
//	        	@Override
//	        	public void mousePressed(MouseEvent e) {
//	        		int x =getWidth() - 30;
//	        		if(new Rectangle(x, 0, 30, 30).contains(e.getPoint()))
//	        		{
//	        			hide =!hide;
//	        			if(hide)
//	        				setEchoChar('●');
//	        			else
//	        				setEchoChar((char) 0);
//	        		}
//	        	}
//	        });
//	        addMouseMotionListener(new MouseMotionAdapter() {
//	        	@Override
//	        	public void mouseMoved(MouseEvent e) {
//	        		int x =getWidth() - 30;
//	        		if(new Rectangle(x, 0, 30, 30).contains(e.getPoint()))
//	        		{
//	        			setCursor(new Cursor(Cursor.HAND_CURSOR));
//	        		}
//	        		else
//	        		{
//	        			setCursor(new Cursor(Cursor.TEXT_CURSOR));
//	        		}
//	        	}
//			});
//	        eye =new ImageIcon(getClass().getResource("/gd/eye.png")).getImage();
//	        eye_hide =new ImageIcon(getClass().getResource("/gd/eye_hide.png")).getImage();
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
			//createShowHide(g2);
			g2.dispose();
		}
//	    private void createShowHide(Graphics2D g2)
//	    {
//	    	int x = getWidth() - 48 + 5;
//	    	int y = (getHeight() - 35) / 2;
//	    	g2.drawImage(hide? eye_hide : eye, x, y,null);
//	    }

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
