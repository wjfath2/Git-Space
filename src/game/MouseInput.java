package game;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseInput implements MouseListener{

	
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	public void mousePressed(MouseEvent e) {
		int mx = e.getX();
		int my = e.getY();
		//play button
		if(mx >= Main.width / 2 + 120 && mx <= Main.width / 2 + 220) {
			if(my >= 150 && my <= 300) {
				Main.State = Main.STATE.Game;
			}
		}
		
	}

	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
