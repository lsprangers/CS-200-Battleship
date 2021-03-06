/**
 * This file contains testing methods for the Battleship project. These methods are intended to 
 * provide an example of a way to incrementally test your code, and to provide example method calls
 * for the Battleship methods
 *
 * Toward these objectives, the expectation is that part of the grade for the Battleship project is 
 * to write some tests and write header comments summarizing the tests that have been written. 
 * Specific places are noted with FIXME but add any other comments you feel would be useful.
 */

import java.util.Random;
import java.util.Scanner;

/**
 * This class contains a few methods for testing methods in the Battleship class
 * as they are developed. These methods are all private as they are only
 * intended for use within this class.
 * 
 * @author Marc Renault
 * @author FIXME add your name here when you add test
 *
 */
public class TestBattleship {

	/**
	 * This is the main method that runs the various tests. Uncomment the tests when
	 * you are ready for them to run.
	 * 
	 * @param args
	 *            (unused)
	 */
	public static void main(String[] args) {
		// Milestone 1
		//testCoordAlphaToNum();
		//testCoordNumToAlpha();
		// Milestone 2
		//testCheckWater();
		//testPlaceShip1();
		//testRandomPlaceShip();
		//testPrintBoard();
		//testaddShip(); 
		// testMain();
		// Milestone 3
		// testTakeShot();
		// testCheckLost();
		//testTakeShot();
		//testCheckLost();
		testShootPlayer();
		//testShootComputer();
	}
	

	private static void testCoordAlphaToNum() {
		int numTests = 6;
		int passed = numTests;
		int res;
		if ((res = Battleship.coordAlphaToNum("BAAA")) != 17576) {
			System.out.println("FAILED: Battleship.coordAlphaToNum(\"BAAA\") != 17576, but " + res);
			passed--;
		}
		if ((res = Battleship.coordAlphaToNum("ZERTY")) != 11506714) {
			System.out.println("FAILED: Battleship.coordAlphaToNum(\"ZERTY\") != 11506714, but " + res);
			passed--;
		}
		if ((res = Battleship.coordAlphaToNum("zerty")) != 11506714) {
			System.out.println("FAILED: Battleship.coordAlphaToNum(\"zerty\") != 11506714, but " + res);
			passed--;
		}
		if ((res = Battleship.coordAlphaToNum("&é\"")) != 14747) {
			System.out.println("FAILED: Battleship.coordAlphaToNum(\"&é\\\"\") != -14747, but " + res);
			passed--;
		}
		if ((res = Battleship.coordAlphaToNum("TWIST")) != 9075111) {
			System.out.println("FAILED: Battleship.coordAlphaToNum(\"TWIST\") != 9075111, but " + res);
			passed--;
		}
		if ((res = Battleship.coordAlphaToNum("BARf")) != 18023) {
			System.out.println("FAILED: Battleship.coordAlphaToNum(\"BARf\") != 18023, but " + res);
			passed--;
		}
		System.out.println("testCoordAlphatoNum: Passed " + passed + " of " + numTests + " tests.");
	}

	private static void testCoordNumToAlpha() {
		int numTests = 4;
		int passed = numTests;
		if (!Battleship.coordNumToAlpha(9075111).equals("TWIST")) {
			System.out.println(
					"FAILED: Battleship.coordNumToAlpha(9075111) != TWIST, but " + Battleship.coordNumToAlpha(9075111));
			passed--;
		}
		if (!Battleship.coordNumToAlpha(17576).equals("BAAA")) {
			System.out.println(
					"FAILED: Battleship.coordNumToAlpha(17576) != BAAA, but " + Battleship.coordNumToAlpha(9075111));
			passed--;
		}
		if (!Battleship.coordNumToAlpha(11506714).equals("ZERTY")) {
			System.out.println("FAILED: Battleship.coordNumToAlpha(11506714) != ZWERTY, but "
					+ Battleship.coordNumToAlpha(9075111));
			passed--;
		}
		if (!Battleship.coordNumToAlpha(14747).equals("&é\\\"")) {
			System.out.println(
					"FAILED: Battleship.coordNumToAlpha(14747) != &é\\\\, but " + Battleship.coordNumToAlpha(14747));
			passed--;
		}
		System.out.println("testCoordNumtoAlpha: Passed " + passed + " of " + numTests + " tests.");
	}

	private static void testCheckWater() {
		int x = 5;
		int y = 4;
		char board[][] = new char[x][y];
		for (int i = 0; i < x ; ++i) {
			for (int j = 0; j < y; ++j) {
				board[i][j] = '~';
				System.out.print(board[i][j]);
			}
			System.out.println();
		}
		System.out.println("\n\n\n");
		
		board[1][1] = '&';
		board[2][2] = '&';
		board[3][4] = '&';
		board[0][3] = '&';
		board[3][0] = '&';
		for (int i = 0; i < x ; ++i) {
			for (int j = 0; j < y; ++j) {
				System.out.print(board[i][j]);
			}
			System.out.println();
		}
		int passed = 5;

		if ((Battleship.checkWater(board, 0, 1, 2, false)) != -1) {
			System.out.println("Failed 1");
			System.out.println(Battleship.checkWater(board, 0, 1, 2, false));
			--passed;
		}
		if ((Battleship.checkWater(board, 0, 4, 5, false)) != 1) {
			System.out.println("Failed 2");
			System.out.println((Battleship.checkWater(board, 0, 3, 5, false)));
			--passed;
		}
		if ((Battleship.checkWater(board, 1, 3, 4, false)) != -1) {
			System.out.println("Failed 3");
			--passed;
		}
		if ((Battleship.checkWater(board, 0, 1, 4, true)) != -1) {
			System.out.println("Failed 4");
			System.out.println((Battleship.checkWater(board, 0, 1, 4, true)));
			--passed;
		}
		if ((Battleship.checkWater(board, 0, 1, 5, true)) != -1) {
			System.out.println("Failed 5");
			--passed;
		}
		System.out.println("Passed: " + passed + " tests");
//**
	}

	private static void testPlaceShip1() {
		
		char board[][] = new char[5][5];
		for (int i = 0; i < 5; ++i) {
			for (int j = 0; j < 5; ++j) {
				board[i][j] = '~';
			}
		}

		Battleship.placeShip(board, 4, 1, 4, true, 5);
		for (int i = 0; i < 5; ++i) {
			for (int j = 0; j < 5; ++j) {
				System.out.print(board[i][j]);
			}
			System.out.println();
		}
	}
	
	private static void testRandomPlaceShip() {
		char board[][] = new char[5][5];
		for (int i = 0; i < 5; ++i) {
			for (int j = 0; j < 5; ++j) {
				board[i][j] = '~';
			}
		}
		Random rand = new Random(7);
		Battleship.placeRandomShip(board, 5, 8, rand);
		Battleship.placeRandomShip(board, 2, 6, rand);
	}
	
	private static void testPlaceShip() {
		char board[][] = new char[5][5];
		for (int i = 0; i < 5; ++i) {
			for (int j = 0; j < 5; ++j) {
				board[i][j] = '~';
			}
		}
		int passed = 5;
		Battleship.placeShip(board, 2, 1, 2, false, 1);
		if (Battleship.checkWater(board, 2, 1, 2, false) != -1) {
			--passed;
		}
		Battleship.placeShip(board, 1, 2, 3, false, 1);
		Battleship.checkWater(board, 1, 2, 3, false);
		Battleship.placeShip(board, 0, 3, 5, false, 1);
		Battleship.checkWater(board, 0, 3, 5, false);
		Battleship.placeShip(board, 4, 3, 2, true, 1);
		if (Battleship.checkWater(board, 4, 3, 2, true) != -1) {
			--passed;
		}

		Battleship.placeShip(board, 3, 0, 2, true, 1);
		Battleship.checkWater(board, 3, 0, 2, true);
		System.out.println(passed);

	}
	private static void testPrintBoard() {
		int x = 15;
		int y = 3;
		char board[][] = new char[y][x];
		for (int i = 0; i < y; ++i) {
			for (int j = 0; j < x; ++j) {
				board[i][j] = '~';
			}
		}
		Battleship.printBoard(board, "Board test");
	}
	private static void testaddShip() {
		Random rand = new Random(7);
		Scanner scnr = new Scanner(System.in);
		int x = 5;
		int y = 4;
		char board[][] = new char[y][x];
		for (int i = 0; i < y; ++i) {
			for (int j = 0; j < x; ++j) {
				board[i][j] = '~';
			}
		}
		char boardOpp[][] = new char[y][x];
		for (int i = 0; i < y; ++i) {
			for (int j = 0; j < x; ++j) {
				board[i][j] = '~';
			}
		}
		for ( int i = 0 ; i < board.length ; ++i) {
			for ( int k = 0 ; k < board[0].length ; ++k) {
				System.out.print(board[i][k] + " ");
			}
			System.out.println();
		}
		Battleship.addShip(scnr, board, boardOpp, 1, rand);
		for ( int i = 0 ; i < board.length ; ++i) {
			for ( int k = 0 ; k < board[0].length ; ++k) {
				System.out.print(board[i][k] + " ");
			}
			System.out.println();
		}
	}
	
	private static void testTakeShot() {
			int numTests = 6;
			int passed = numTests;
			int res;
			int x = 4;
			int y = 4;
			char [][] board = new char[x][y];
			for (int i = 0 ; i < x ; ++i) {
				for (int j = 0 ; j < x ; ++j) {
					board[i][j] = Config.WATER_CHAR;
				}
			}
			
			board[2][1] = '2';
			board[3][1] = '2';
			board[1][2] = '1';
			board[1][0] = '@';
			board[1][1] = '@';
			board[1][3] = '@';
			board[2][3] = '@';
			board[3][3] = '@';
			board[0][2] = '*';
			for (int i = 0 ; i < y ; ++i) {
				for (int j = 0 ; j < x ; ++j) {
					System.out.print(board[i][j]);
				}
				System.out.println();
			}
			
			if ((res = Battleship.takeShot(board , 1 , 0)) != 2) {
				System.out.println("FAILED 1, != 2, but " + res);
				passed--;
			}
			if ((res = Battleship.takeShot(board , 2 , 0)) != 3) {
				System.out.println("FAILED 2, != 3, but " + res);
				passed--;
			}
			if ((res = Battleship.takeShot(board , 0 , 1)) != 3) {
				System.out.println("FAILED 3, != 3, but " + res);
				passed--;
			}
			if ((res = Battleship.takeShot(board , 2 , 1)) != 1) {
				System.out.println("FAILED 4, != 1, but " + res);
				passed--;
			}
			if ((res = Battleship.takeShot(board , 4 , 0)) != -1) {
				System.out.println("FAILED 5, != -1, but " + res);
				passed--;
			}
			if ((res = Battleship.takeShot(board , 2 , 1)) != 1) {
				System.out.println("FAILED 6, != 1, but " + res);
				passed--;
			}
			System.out.println("takeShot: Passed " + passed + " of " + numTests + " tests.");
		}

	

	private static void testCheckLost() {
			int numTests = 2;
			int passed = numTests;
			int res;
			int x = 3;
			int y = 3;
			char [][] board = new char[x][y];
			for (int i = 0 ; i < x ; ++i) {
				for (int j = 0 ; j < x ; ++j) {
					board[i][j] = Config.WATER_CHAR;
				}
			}
			
			board[2][1] = '2';
			board[1][2] = '1';
			board[1][0] = Config.MISS_CHAR;
			board[1][1] = Config.MISS_CHAR;
			board[0][2] = Config.HIT_CHAR;
			
			for (int i = 0 ; i < y ; ++i) {
				for (int j = 0 ; j < x ; ++j) {
					System.out.print(board[i][j]);
				}
				System.out.println();
			}
			
			
			if (Battleship.checkLost(board)) {
				System.out.println("FAILED");
				passed--;
			}
			
			board[2][1] = '*';
			board[1][2] = '@';
			

			if (!Battleship.checkLost(board)) {
				System.out.println("FAILED");
				passed--;
			}
			
			System.out.println("checkLost: Passed " + passed + " of " + numTests + " tests.");
		}

	/**
	 * Interacts with the user to take a shot. The procedure is as follows: 1 -
	 * Using the promptStr method, prompt the user for the "x-coord shot". The
	 * maximum value should be based on the width of the board. You will need to use
	 * the coordAlphaToNum and coordNumToAlpha methods to covert between int and
	 * String values of coordinates. 2 - Using the promptInt method, prompt the user
	 * for the "y-coord shot". The maximum value should be calculated based on the
	 * width of the board. 3 - Check the shot, using the takeShot method. If it
	 * returns: -1: Print out an error message "Coordinates out-of-bounds!",
	 * terminated by a new line. 3: Print out an error message "Shot location
	 * previously targeted!", terminated by a new line. 1 or 2: Update the cells in
	 * board and boardTrack with Config.HIT_CHAR or Config.MISS_CHAR accordingly.
	 * This process should repeat until the takeShot method returns 1 or 2.
	 *
	 * @param sc
	 *            The Scanner instance to read from System.in.
	 * @param board
	 *            The computer opponent board (containing the ship placements).
	 * @param boardTrack
	 *            The human player tracking board.
	 */
	private static void testShootPlayer() {
	Scanner scnr = new Scanner(System.in);
	int x = 4;
	int y = 4;
	char [][] board = new char[x][y];
	for (int i = 0 ; i < x ; ++i) {
		for (int j = 0 ; j < x ; ++j) {
			board[i][j] = Config.WATER_CHAR;
		}
	}
	char [][] board2 = new char[x][y];
	for (int i = 0 ; i < x ; ++i) {
		for (int j = 0 ; j < x ; ++j) {
			board2[i][j] = Config.WATER_CHAR;
		}
	}
	
	int numTests = 2;
	int passed = numTests;
	int res;
	//Battleship.shootPlayer(scnr , board , board2);
	
	String xMax = Battleship.coordNumToAlpha(board[0].length - 1);
	int retry = 0;
	int xCoord = 0;
	int yCoord = 0;
	
	do {
		
	String xCoordS = Battleship.promptStr(scnr, "x coord shot" , "A" , xMax);
	xCoord = Battleship.coordAlphaToNum(xCoordS);
	yCoord = Battleship.promptInt(scnr, "y coord shot" , 0 , board.length - 1);
	System.out.println("" + xCoord + " " + yCoord);
	int userShot = Battleship.takeShot(board , xCoord , yCoord);
	//System.out.println(userShot);
	switch (userShot) {
	case -1:
		System.out.println("Coordinates out-of-bounds!");
		break;
	case 1:
		board[yCoord][xCoord] = Config.HIT_CHAR;
		retry = 1;
		System.out.println(board[yCoord][xCoord]);
		break;
	case 2:
		board[yCoord][xCoord] = Config.MISS_CHAR;
		retry = 1;
		System.out.println(retry);
		break;
	case 3:
		System.out.println("Shot location previously targeted!");
		break;
	default :
		System.out.println("COOKOO");
		
	}
	
	} while (retry == 0);
	
}

	private static void testShootComputer() {
		Random rand = new Random();
		int x = 4;
		int y = 4;
		
		char board[][] = new char[y][x];
		for (int i = 0 ; i < x ; ++i) {
			for (int j = 0 ; j < x ; ++j) {
				board[i][j] = Config.WATER_CHAR;
			}
		}
		char c = (char)(rand.nextInt(board[0].length - 1) + 'a');
		System.out.println(c);
	}
}
