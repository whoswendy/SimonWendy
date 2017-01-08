package Simon;

import java.awt.Color;
import java.util.ArrayList;

import GUIpractice.ClickableScreen;
import GUIpractice.components.Action;
import GUIpractice.components.Button;
import GUIpractice.components.TextLabel;
import GUIpractice.components.Visible;

public class SimonScreenWendy extends ClickableScreen implements Runnable{
	
	private ArrayList<MoveInterfaceWendy> moves;
	private ProgressInterfaceWendy progress;
	private ButtonInterfaceWendy[] buttons;
	private TextLabel label; 
	private int rounds;
	private boolean acceptedInput;
	private int sequenceIndex;
	private int lastSelectedButton;

	public SimonScreenWendy(int width, int height) {
		super(width, height);
		// TODO Auto-generated constructor stub
		Thread play = new Thread(this);
		play.start();
	}

	@Override
	public void initAllObjects(ArrayList<Visible> viewObjects) {
		// TODO Auto-generated method stub
		addButtons();
		progress = getProgress();
		label = new TextLabel(130,230,300,40,"Let's play Simon!");
		moves = new ArrayList<MoveInterfaceWendy>();
		//add 2 moves to start
		lastSelectedButton = -1;
		moves.add(randomMove());
		moves.add(randomMove());
		rounds = 0;
		viewObjects.add(progress);
		viewObjects.add(label);
	}

	private MoveInterfaceWendy randomMove() {
		// TODO Auto-generated method stub
		ButtonInterfaceWendy Move;
		int rand = (int) (Math.random() * buttons.length);
		while(rand == lastSelectedButton)
		{
			rand = (int) (Math.random() * buttons.length);
		}
		Move = buttons[rand];
		lastSelectedButton = rand;
		return getMove(Move);
	}
	
	
	/*
	Placeholder until partner finishes implementation of ProgressInterface
	*/
	private MoveInterfaceWendy getMove(ButtonInterfaceWendy move) {
		// TODO Auto-generated method stub
		return null;
	}
	
	private ProgressInterfaceWendy getProgress() {
		// TODO Auto-generated method stub
		return null;
	}

	private ButtonInterfaceWendy getAButton() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	private void addButtons() {
		// TODO Auto-generated method stub
		int NumOfButtons = 6;
		Color[] colors = {Color.red,Color.blue,Color.pink,Color.green,Color.orange,Color.yellow};
		for(int i = 0; i< NumOfButtons; i++)
		{
			final ButtonInterfaceWendy b = getAButton();
			b.setColor(colors[i]);
			b.setX((int) (i * 100 * Math.sin(Math.PI/3)));//x=rsin(beta)
			b.setY((int) (i * 100 * Math.cos(Math.PI/3)));//y=rcos(beta)
			b.setAction(new Action(){
				
				public void act(){
					if(acceptedInput)
					{
						Thread blink = new Thread(new Runnable(){

							public void run(){
							
								b.highlight();
								try {
									Thread.sleep(800);
									b.dim();
								} catch (InterruptedException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}
						});
						blink.start();
						if(b == moves.get(sequenceIndex).getButton())
						{
							sequenceIndex++;
						}
						else
						{
							getProgress().gameOver();
						}
						
						if(sequenceIndex == moves.size())
						{
							Thread nextRound = new Thread(SimonScreenWendy.this);
							nextRound.start();
						}
					}
				}
			});
			addObject(b);
		}
	}


	@Override
	public void run() {
		// TODO Auto-generated method stub
		label.setText("");
		nextRound();
		
	}

	private void nextRound() {
		// TODO Auto-generated method stub
		acceptedInput = false;
		rounds ++;
		randomMove();
		getProgress().setRound(rounds);
		getProgress().setSequenceSize(moves.size());
		changeText("Simon's Turn");
		label.setText("");
		playSequence();
		changeText("Your Turn");
		acceptedInput = true;
		sequenceIndex = 0;
		
	}

	private void playSequence() {
		// TODO Auto-generated method stub
		ButtonInterfaceWendy b = null;
		for(int i = 0; i < moves.size(); i++)
		{
			if(b != null)
			{
				b.dim();
			}
			b = getAButton();
			b.highlight();
			int sleepTime = 1000*2/rounds+500;
			try {
				Thread.sleep(sleepTime);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		b.dim();
	}

	private void changeText(String string) {
		// TODO Auto-generated method stub
		try {
			Thread.sleep(1000);
			label.setText(string);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
