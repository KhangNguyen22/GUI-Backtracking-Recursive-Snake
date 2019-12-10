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

	CoordinateArray walls;
	CoordinateArray currentPath;
	CoordinateArray longestPath;
	Coordinate currentCoordinate;
	Coordinate destinationCoordinate;
	int waitingTime = 30;


	
	public LongestPath() {
		ui = UserInterfaceFactory.getLabyrinthUI(WIDTH, HEIGHT);
		walls = new CoordinateArray();
		currentPath = new CoordinateArray();
		longestPath = new CoordinateArray();
	}

	public void findPath(){
		Coordinate west = new Coordinate(currentCoordinate.getX() - 1, currentCoordinate.getY());
		Coordinate south = new Coordinate(currentCoordinate.getX(), currentCoordinate.getY() + 1);
		Coordinate east = new Coordinate(currentCoordinate.getX() + 1, currentCoordinate.getY());
		Coordinate north = new Coordinate(currentCoordinate.getX(), currentCoordinate.getY() - 1);	
		if (isEmpty(west)){
			System.out.println();
			System.out.println("WEST");
			// Check if we reach destination in west
			currentCoordinate = west;
			currentPath.append(west);
			ui.place(west.getX(), west.getY(), ui.PATH);
			ui.showChanges();
			ui.wait(waitingTime);

			if ( west.getX() == destinationCoordinate.getX() && west.getY() == destinationCoordinate.getY() ){
				return;
			}
			else {
					findPath();
			}
			System.out.println("West Return");
			clearLastCircle();

		}
		
		if (isEmpty(south)){
			System.out.println();
			System.out.println("SOUTH");
			currentCoordinate = south;
			currentPath.append(south);
			ui.place(south.getX(), south.getY(), ui.PATH);
			ui.showChanges();
			ui.wait(waitingTime);
			if ( south.getX() == destinationCoordinate.getX() && south.getY() == destinationCoordinate.getY() ){
				return;
			}
			else {
					findPath();
			}
			System.out.println("SOUTH Return");
			clearLastCircle();	
		}
		if (isEmpty(east)){
			System.out.println();
			System.out.println("EAST");
			currentCoordinate = east;
			currentPath.append(east);
			ui.place(east.getX(), east.getY(), ui.PATH);
			ui.showChanges();
			ui.wait(waitingTime);
			if ( east.getX() == destinationCoordinate.getX() && east.getY() == destinationCoordinate.getY() ){
				System.out.println("east is up");
				System.out.println(currentPath.getPathLength());
				if (currentPath.getPathLength() > longestPath.getPathLength()){
					longestPath = new CoordinateArray();
					for (int i = 0; i < currentPath.getPathLength(); i++){
						longestPath.append(currentPath.getPath()[i]);
					}
					System.out.println("Longest Path currently: ");
					System.out.println(longestPath.getPathLength());
					System.out.println();
				}
				// return;
			}
			else {
					findPath();
			}
			System.out.println("EAST Return");
			clearLastCircle();
				
		}
		System.out.println(currentPath.getPath()[currentPath.getPathLength() - 1].getX());
		System.out.println(currentPath.getPath()[currentPath.getPathLength() - 1].getY());
		if (isEmpty(north)){
			System.out.println();
			System.out.println("North");
			currentCoordinate = north;
			System.out.println(currentCoordinate.getX());
			System.out.println(currentCoordinate.getY());
			currentPath.append(north);
			ui.place(north.getX(), north.getY(), ui.PATH);
			ui.showChanges();
			ui.wait(waitingTime);
			
			if ( north.getX() == destinationCoordinate.getX() && north.getY() == destinationCoordinate.getY() ){
				return;
			}
			else {
					findPath();
			}
			System.out.println("NORTH return");
			clearLastCircle();
		}
		// if all of this fails, please back track and clear current coordinate
		// Coordinate lastElement = currentPath.pop();
		// System.out.print(Integer.toString(lastElement.getX()) + ",");
		// System.out.println(lastElement.getY());
		// ui.place(lastElement.getX(), lastElement.getY(), ui.EMPTY);
		// ui.showChanges();
		// clearLastCircle();
		// System.out.println("Stuck");
		ui.wait(10);
	}

	public void clearLastCircle(){
		Coordinate lastElement = currentPath.pop();
		System.out.print(Integer.toString(lastElement.getX()) + ",");
		System.out.println(lastElement.getY());
		ui.place(lastElement.getX(), lastElement.getY(), ui.EMPTY);
		ui.showChanges();
	}
	public boolean isEmpty(Coordinate direction){
		// check wall and currentPath (as you don't want to eat yourself)

		for (int x = 0; x < walls.getPathLength(); x++){
			if ( direction.getX() == walls.getPath()[x].getX() && direction.getY() == walls.getPath()[x].getY() ){
				return false;
			}
		}

		for (int i = 0; i < currentPath.getPathLength(); i++){
			if ( direction.getX() == currentPath.getPath()[i].getX() && direction.getY() == currentPath.getPath()[i].getY() ) {
				return false;
			}
		}

		return true;

	}

	public void printMyWalls(Scanner fileScanner){
		System.out.println("In printMyWalls");
		String firstWall = fileScanner.skip("=").nextLine();	
		String[] array1 = firstWall.split(" ");
		Coordinate wallCoordinate = new Coordinate(Integer.parseInt(array1[0]), Integer.parseInt(array1[1]));
		ui.place(wallCoordinate.getX(), wallCoordinate.getY(), ui.WALL);

		walls.append(wallCoordinate);

		while (fileScanner.hasNextLine()) {
				String currentWall = fileScanner.nextLine();
				String[] currentArray = currentWall.split(" ");
				Coordinate currentWallCoordinate = new Coordinate(Integer.parseInt(currentArray[0]), Integer.parseInt(currentArray[1]));
				ui.place(currentWallCoordinate.getX(), currentWallCoordinate.getY(), ui.WALL);
				
				walls.append(currentWallCoordinate);
			
			}

		// System.out.println("we are here");
        // System.out.println(walls.getPathLength());

	}

	public void setUpScreen(Scanner fileScanner){
		fileScanner.useDelimiter("=");
		String start = fileScanner.next();
		String[] startArray = start.split(" ");
		currentCoordinate = new Coordinate(Integer.parseInt(startArray[0]),Integer.parseInt(startArray[1])); 
		ui.place(currentCoordinate.getX(), currentCoordinate.getY(), ui.PATH);
		
		// As the currentCoordinate is currently the starting coordinate, we append to currentPath.
		currentPath.append(currentCoordinate);
		// ui.place(24, 1, ui.PATH);

		System.out.println(Arrays.toString(startArray));
		String end = fileScanner.next();
		String[] endArray = end.split(" ");
		destinationCoordinate = new Coordinate(Integer.parseInt(endArray[0]),Integer.parseInt(endArray[1]));
		ui.encircle(destinationCoordinate.getX(), destinationCoordinate.getY());

		Scanner myWalls = fileScanner; // technically, you don't need to store in variable
		printMyWalls(myWalls);
		ui.showChanges();
	}

	public void drawFinalPath(){
		System.out.println("In Draw FINAL");
		for (int i = 0; i < currentPath.getPathLength(); i++){
			Coordinate cur = currentPath.getPath()[i];
			ui.place(cur.getX(), cur.getY(), ui.EMPTY);
			ui.showChanges();
		}

		for (int x = 0; x < longestPath.getPathLength(); x++){
			Coordinate cur = longestPath.getPath()[x];
			ui.place(cur.getX(), cur.getY(), ui.PATH);
			ui.showChanges();
		}
	}
	
	public void start() {
//		Scanner fileScanner = UIAuxiliaryMethods.askUserForInput().getScanner();
		File file = new File("/Users/khangnguyen/eclipse-workspace/Introduction to Programming/module1/LongestPath/LongestPathInput1.txt");
		try {
			Scanner fileScanner = new Scanner(file);
			setUpScreen(fileScanner);
			fileScanner.close();
			findPath();

			if (longestPath != null){
				System.out.println("FINAL Longest PATH: ");
				System.out.println(longestPath.getPathLength());
				System.out.println();
				drawFinalPath();
			}
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		new LongestPath().start();
		System.out.println("ending!");
	}

}
