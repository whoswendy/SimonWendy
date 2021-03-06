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
		addButtons(viewObjects);
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
		return new MoveRichard(move);
	}
	
	private ProgressInterfaceWendy getProgress() {
		// TODO Auto-generated method stub
		return new ProgressRichard();
	}

	private ButtonInterfaceWendy getAButton() {
		// TODO Auto-generated method stub
		return new ButtonRichard();
	}
	
	
	private void addButtons(ArrayList<Visible> viewObjects) {
		// TODO Auto-generated method stub
		int numberOfButtons = 6;
		//make new buttons
		buttons = new ButtonInterfaceWendy[numberOfButtons];
		
		Color[] color = {Color.blue, Color.green, Color.yellow, Color.black, Color.red, Color.cyan};
		for(int i = 0; i < numberOfButtons; i++){
			buttons[i] = getAButton();
			buttons[i].setColor(color[i]);
			buttons[i].setX((int) (i * 100 * Math.sin(Math.PI/3) + 50));//x=rsin(beta)
			buttons[i].setY((int) (i * 100 * Math.cos(Math.PI/3) + 80));//y=rcos(beta)
			final ButtonInterfaceWendy b = buttons[i];
			
			b.setAction(new Action(){
				public void act(){
					if(acceptedInput)
					{
						Thread blink = new Thread(new Runnable(){

							public void run(){
							
								b.highlight();
								try {
									Thread.sleep(800);
								} catch (InterruptedException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								b.dim();
							}
						});
						blink.start();
						if(b == moves.get(sequenceIndex).getButton())
						{
							sequenceIndex++;
						}
						else
						{
							progress.gameOver();
							changeText("GAME OVER");
							acceptedInput = false;
							return;
						}
						
						if(sequenceIndex == moves.size())
						{
							Thread nextRound = new Thread(SimonScreenWendy.this);
							nextRound.start();
						}
					}
				}
			});
			viewObjects.add(buttons[i]);
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
		progress.setRound(rounds);
		moves.add(randomMove());
		progress.setSequenceSize(moves.size());
		changeText("Simon's Turn");
		label.setText("");
		playSequence();
		changeText("Your Turn");
		label.setText("");
		acceptedInput = true;
		sequenceIndex = 0;
		
	}

	private void playSequence() {
		// TODO Auto-generated method stub
		
	//THIS ONE DOES NOT WORK	
		
//		ButtonInterfaceWendy b = null;
//		for(int i = 0; i < moves.size(); i++)
//		{
//			if(b != null)
//			{
//				b.dim();
//			}
//			b = getAButton();
//			b.highlight();
//			int sleepTime = 1000*2/rounds+500;
//			try {
//				Thread.sleep(sleepTime);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
//		b.dim();
		ButtonInterfaceWendy b = null;
		for(MoveInterfaceWendy m: moves){
			if(b!=null){
				b.dim();
			}
			b = m.getButton();
			b.highlight();
			int sleepTime = 1000*2/rounds+500;
			try{
				Thread.sleep(sleepTime);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		b.dim();
	}

	private void changeText(String string) {
		// TODO Auto-generated method stub
		try {
			label.setText(string);
			Thread.sleep(1000);
			label.setText(string);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
