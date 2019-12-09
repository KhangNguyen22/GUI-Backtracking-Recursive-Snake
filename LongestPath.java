package LongestPath;

import ui.UIAuxiliaryMethods;
import ui.UserInterfaceFactory;
import ui.LabyrinthUserInterface;
import java.util.*;


import java.io.File;
import java.io.FileNotFoundException;


public class LongestPath {
	LabyrinthUserInterface ui;
	static final int WIDTH = 32; 
	static final int HEIGHT = 24;

	CoordinateArray wall;
	CoordinateArray currentPath;
	CoordinateArray longestPath;
	Coordinate currentCoordinate;
	Coordinate destinationCoordinate;


	
	public LongestPath() {
		ui = UserInterfaceFactory.getLabyrinthUI(WIDTH, HEIGHT);
		
	}

	public void findPath(){
		Coordinate west = new Coordinate(currentCoordinate.getX() - 1, currentCoordinate.getY());
		Coordinate south = new Coordinate(currentCoordinate.getX(), currentCoordinate.getY() + 1);
		Coordinate east = new Coordinate(currentCoordinate.getX() + 1, currentCoordinate.getY());
		Coordinate north = new Coordinate(currentCoordinate.getX(), currentCoordinate.getY() - 1);	
		// if (isEmpty(west)){
		// 		;
		// }
		// elif (isEmpty(south)){
		// 	;
		// }
		// elif (isEmpty(east)){
		// 		;
		// }
		// elif (isEmpty(north)){
		// 	;
		// }
	}

	// public boolean isEmpty(Coordinate direction){

	// }

	public void printMyWalls(Scanner fileScanner){
		System.out.println("In printMyWalls");
		String firstWall = fileScanner.nextLine();	
		String[] array1 = firstWall.substring(1).split(" ");
		ui.place(Integer.parseInt(array1[0]), Integer.parseInt(array1[1]), ui.WALL);
		System.out.println(fileScanner.nextLine());
		while (fileScanner.hasNextLine()) {
			// System.out.println(fileScanner.hasNextLine());
			// System.out.println(fileScanner.nextLine());
				String currentWall = fileScanner.nextLine();
				String[] currentArray = currentWall.split(" ");
				ui.place(Integer.parseInt(currentArray[0]), Integer.parseInt(currentArray[1]), ui.WALL);	
				// System.out.println(Arrays.toString(currentArray));
			}

	}

	public void setUpScreen(Scanner fileScanner){
		fileScanner.useDelimiter("=");
		String start = fileScanner.next();
		String[] startArray = start.split(" ");
		currentCoordinate = new Coordinate(Integer.parseInt(startArray[0]),Integer.parseInt(startArray[1])); 
		ui.place(currentCoordinate.getX(), currentCoordinate.getY(), ui.PATH);
		// ui.place(3, 1, ui.PATH);

		System.out.println(Arrays.toString(startArray));
		String end = fileScanner.next();
		String[] endArray = end.split(" ");
		destinationCoordinate = new Coordinate(Integer.parseInt(endArray[0]),Integer.parseInt(endArray[1]));
		ui.encircle(destinationCoordinate.getX(), destinationCoordinate.getY());
		// System.out.println(Arrays.toString(endArray));

		Scanner myWalls = fileScanner; // technically, you don't need to store in variable
		printMyWalls(myWalls);
		ui.showChanges();
	}
	
	public void start() {
//		Scanner fileScanner = UIAuxiliaryMethods.askUserForInput().getScanner();
		File file = new File("/Users/khangnguyen/eclipse-workspace/Introduction to Programming/module1/LongestPath/LongestPathInput1.txt");
		try {
			Scanner fileScanner = new Scanner(file);
			setUpScreen(fileScanner);
			fileScanner.close();
			// findPath();

		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		new LongestPath().start();

	}

}
