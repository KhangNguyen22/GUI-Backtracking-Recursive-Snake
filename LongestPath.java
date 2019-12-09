package LongestPath;

import ui.UserInterfaceFactory;
import ui.UIAuxiliaryMethods;
import ui.LabyrinthUserInterface;
import java.util.*;

public class LongestPath {
	LabyrinthUserInterface ui;
	static final int WIDTH = 32; 
	static final int HEIGHT = 24;

	
	public LongestPath() {
		ui = UserInterfaceFactory.getLabyrinthUI(WIDTH, HEIGHT);
		
	}
	
	public void start() {
		Scanner fileScanner = UIAuxiliaryMethods.askUserForInput().getScanner();
		System.out.println(fileScanner);	
	}
	
	public static void main(String[] args) {
		new LongestPath().start();
	}

}
