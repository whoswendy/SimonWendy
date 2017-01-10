package Simon;

import java.awt.Color;
import java.awt.Graphics2D;

import GUIpractice.components.Component;

public class ProgressRichard extends Component implements ProgressInterfaceWendy{

	private int roundNumber;
	private int moveSize;
	private boolean gameOver;

	public ProgressRichard() {
		super(30, 30, 150, 70);
		// TODO Auto-generated constructor stub
	}

	
	@Override
	public void gameOver() {
		// TODO Auto-generated method stub
		gameOver = true;
		update();
	}

	@Override
	public void setRound(int i) {
		// TODO Auto-generated method stub
		roundNumber = i;
		update();
	}

	@Override
	public void setSequenceSize(int i) {
		// TODO Auto-generated method stub
		moveSize = i;
		update();
	}

	@Override
	public void update(Graphics2D g) {
		// TODO Auto-generated method stub
		if(gameOver == true){
			g.setColor(Color.black);
			g.fillRect(0, 0, 300, 50);
			g.setColor(Color.white);
			g.drawString("Game Over", 10, 20);
			g.drawString("Round " + roundNumber, 10, 30);
			g.drawString("Size " + moveSize, 10, 40);
		}
		else
		{
			g.setColor(Color.white);
			g.fillRect(0, 0, 300, 50);
			g.setColor(Color.black);
			g.drawString("Round " + roundNumber, 10, 30);
			g.drawString("Size " + moveSize, 10, 40);
		}
	}

}
