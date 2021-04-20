package models;

import oldcontrollers.MainFrameController;
import oldview.MainFrame;

public class Main {

	public static void main(String[] args) {
		MainFrame theView = new MainFrame();
		Model theModel = Model.getInstance();
		@SuppressWarnings("unused")
		MainFrameController mainFrameController = new MainFrameController(theView, theModel);
	}
}
