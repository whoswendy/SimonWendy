package Simon;

import GUIpractice.GUIApplication;

public class SimonGameWendy extends GUIApplication {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		SimonGameWendy game = new SimonGameWendy();
		Thread app = new Thread(game);
		app.start();
		
	}
	public SimonGameWendy() {
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void initScreen() {
		// TODO Auto-generated method stub
		SimonScreenWendy sScreen = new SimonScreenWendy(getWidth(),getHeight());
		setScreen(sScreen);
	}


}
