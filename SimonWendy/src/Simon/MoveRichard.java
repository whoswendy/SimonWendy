package Simon;

public class MoveRichard implements MoveInterfaceWendy {

	private ButtonInterfaceWendy move;
	
	@Override
	public ButtonInterfaceWendy getButton() {
		// TODO Auto-generated method stub
		return move;
	}

	public MoveRichard(ButtonInterfaceWendy m){
		this.move = m;
	}
}
