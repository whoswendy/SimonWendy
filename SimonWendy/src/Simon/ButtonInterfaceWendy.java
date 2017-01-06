package Simon;

import java.awt.Color;

import GUIpractice.components.Action;
import GUIpractice.components.Clickable;

public interface ButtonInterfaceWendy extends Clickable {
	
	void setAction(Action a);

	void setColor(Color color);

	void setX(int i);

	void setY(int i);
	
	void highlight();

	void dim();

	
	

}
