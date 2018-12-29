
import java.util.Scanner;
import java.util.Random;


public class Battleship1 {


	/**
	 * This method converts a String representing a base (or radix) 26 number into a
	 * decimal (or base 10) number. The String representation of the base 26 number
	 * uses the letters of the Latin alphabet to represent the 26 digits. That is, A
	 * represents 0, B represents 1, C represents 2, ..., Y represents 24, and Z
	 * represents 25.
	 * 
	 * 	final int MIN_WIDTH = 1; // Minimum number of columns
	final int MAX_WIDTH = 675; // Maximum number of columns
	final int MIN_HEIGHT = 1; // Minimum number of rows
	final int MAX_HEIGHT = 99; // Maximum number of rows
	final int MAX_COL_WIDTH = 3; // Maximum number of characters per column
	final int MIN_SHIPS = 1; // Minimum number of ships
	final int MAX_SHIPS = 9; // Maximum number of ships
	final int MIN_SHIP_LEN = 1; // Minimum ship length


	final char WATER_CHAR = '~'; // Water character (not yet targeted)
	final char HIT_CHAR = '*'; // Hit character
	final char MISS_CHAR = '@'; // Miss character


	final long SEED = 1234; // The random seed
	final int RAND_SHIP_TRIES = 20; // Maximum number of tries to place a ship

	 * 
	 * This method used a loop to go through every character one by one to change
	 * each part into a number, then it's all added together into a variable as one
	 * number.
	 *
	 * This method is used in conjunction with the coordNumToAlpha method to convert
	 * the coordinate letters given by the user to numbers to allow the computer to
	 * read it as a number and use it as a coordinate system.
	 *
	 * @param coord
	 *            The coordinate value in base 26 as described above.
	 * @param coordUp
	 *            Holds the upper case string of coord to keep characters consistent
	 *            throughout the string.
	 * @param res
	 *            The numeric representation of the coordinate that the method
	 *            returns.
	 * 
	 */
	public static int coordAlphaToNum(String coord) {

		int res = 0;
		String coordUp = coord.toUpperCase();

		for (int i = 0; i < coordUp.length(); i++) {
			int choice = (int) (coordUp.charAt(i) - 'A');
			res += ((choice * (int) (Math.pow(26, coordUp.length() - i - 1))));
		}
		return res;
	}

	/**
	 * This method converts an int value into a base (or radix) 26 number, where the
	 * digits are represented by the 26 letters of the Latin alphabet. That is, A
	 * represents 0, B represents 1, C represents 2, ..., Y represents 24, and Z
	 * represents 25. A couple of examples: 17576 is BAAA, 11506714 is ZERTY.
	 * 
	 * It first checks to see if the value given is 0, then it'd break out and
	 * return A. Otherwise it loops through the whole string to regain the string
	 * from the number.
	 *
	 * This method is used in conjunction with the coordAlphaToNum method to convert
	 * the coordinate letters given by the user to numbers to allow the computer to
	 * read it as a number and use it as a coordinate system.
	 *
	 * @param coord
	 *            The integer value to covert into an alpha coordinate.
	 * @param revString
	 *            Method reverses the string, variable is used to store the reversed
	 *            string.
	 * @param theRealString
	 *            Used to reverse revString to change the letters in the correct
	 *            order.
	 * @param actualChar
	 *            Local variable used inside a loop
	 * @return The alpha coordinate in base 26 as described above. If coord is
	 *         negative, an empty string is returned.
	 */

	public static String coordNumToAlpha(int coord) {
		int res = coord;
		String revString = "";
		String theRealString = "";

		if (coord == 0) {
			return "A";
		}
		while (res != 0) {
			int tempRes = res % 26;
			char actualChar = (char) (tempRes + (int) 'A');
			res = res / 26;
			revString += actualChar;
		}

		for (int i = revString.length() - 1; i >= 0; i--) {
			theRealString = theRealString + revString.charAt(i);
		}

		return theRealString;
	}

	/**
	 * This method uses a loop to make sure that the integer value that user gives
	 * is actually integer. Prompts the user for an integer value, displaying the
	 * following: "Enter the valName (min to max): " After prompting the user, the
	 * method will read an int from the console and consume an entire line of input.
	 * If the value read is between min and max (inclusive), that value is returned.
	 * Otherwise, "Invalid value." terminated by a new line is output and the user
	 * is prompted again.
	 * 
	 * This method creates a streamlined approach to prompt the user for integer
	 * values that are used throughout the program for lengths, number of ships,
	 * etc.
	 *
	 * @param sc
	 *            The Scanner instance to read from System.in.
	 * @param valName
	 *            The name of the value for which the user is prompted.
	 * @param min
	 *            The minimum acceptable int value (inclusive).
	 * @param min
	 *            The maximum acceptable int value (inclusive).
	 * @param userNumber
	 *            Stores the integer value from the user input.
	 * @return Returns the value read from the user.
	 */
	public static int promptInt(Scanner sc, String valName, int min, int max) {

		while (true) {
			System.out.print("Enter the " + valName + " (" + min + " to " + max + "): ");
			if (sc.hasNextInt()) {
				int userNumber = sc.nextInt();
				if (userNumber <= max && userNumber >= min) {
					return userNumber;

				} else {
					System.out.println("Invalid value.");
				}

			}
		}
	}

	/**
	 * This method uses a loop to check if the user gives a valid coordinate between
	 * the boundaries. Prompts the user for an String value, displaying the
	 * following: "Enter the valName (min to max): "After prompting the user, the
	 * method will read an entire line of input, trimming any trailing or leading
	 * whitespace.
	 * 
	 * If the value read is(lexicographically ignoring case) between min and max
	 * (inclusive), that value is returned. Otherwise, "Invalid value." terminated
	 * by a new line is output and the user is prompted again.
	 *
	 * @param sc
	 *            The Scanner instance to read from System.in.
	 * @param valName
	 *            The name of the value for which the user is prompted.
	 * @param min
	 *            The minimum acceptable String value (inclusive).
	 * @param min
	 *            The maximum acceptable String value (inclusive).
	 * @param choice
	 *            Stores the trimmed and uppercase string from the user.
	 * @return Returns the value read from the user.
	 */
	public static String promptStr(Scanner sc, String valName, String min, String max) {

		while (true) {
			System.out.print("Enter the " + valName + " (" + min + " to " + max + "): ");
			String choice = sc.next().trim().toUpperCase();

			if (choice.compareTo(max) <= 0 && choice.compareTo(min) >= 0) {
				return choice.toLowerCase();
			} else {
				System.out.println("Invalid value.");
				continue;

			}
		}

	}

	/**
	 * This method prompts the user for an char value. The prompt displayed is the
	 * contents of the String referenced by the prompt parameter.
	 *
	 * After prompting the user, the method will read an entire line of input and
	 * return the first non-whitespace character in lower case.
	 *
	 * @param sc
	 *            The Scanner instance to read from System.in
	 * @param prompt
	 *            The user prompt.
	 * @param userChoice
	 *            Stores the character value given by the user.
	 * @param userChoice1
	 *            Stores the character to a lower case value.
	 * @return Returns the first non-whitespace character (in lower case) read from
	 *         the user. If there are no non-whitespace characters read, the null
	 *         character is returned.
	 */
	public static char promptChar(Scanner sc, String prompt) {

		System.out.print(prompt);
		char userChoice = sc.next().charAt(0);
		char userChoice1 = Character.toLowerCase(userChoice);

		return userChoice1;
	}

	/**
	 * Initializes a game board so that all the entries are the water characters.
	 *
	 * @param board
	 *            The game board to initialize.
	 */
	public static void initBoard(char board[][]) {

		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				board[i][j] = '~';
			}
		}
	}

	/**
	 * Prints the game boards as viewed by the user. This method is used to print
	 * the game boards as the user is placing their ships and during the game play.
	 *
	 * @param board
	 *            The board to print.
	 * @param caption
	 *            The board caption.
	 */
	public static void printBoard(char board[][], String caption) {

		System.out.println(caption);

		String boardFormat = "%" + 3 + "c";
		String columnFormat = "%" + 3 + "s";
		String rowFormat = "%" + 3 + "d";

		System.out.printf(columnFormat, " ");

		for (int i = 0; i < board[0].length; i++) {
			System.out.printf(columnFormat, coordNumToAlpha(i));
		}

		System.out.println();

		for (int i = 0; i < board.length; i++) {

			System.out.printf(rowFormat, i);

			for (int j = 0; j < board[0].length; j++) {
				System.out.printf(boardFormat, board[i][j]);
			}

			System.out.println();
			System.out.println();
		}
		return;
	}

	/**
	 * This method determines if a sequence of cells of length in a game board is
	 * clear or not. This is used to determine if a ship of length len will fit on a
	 * given game board. The x and y coordinates passed in as parameters represent
	 * the top-left cell of the ship when considering the grid.
	 * 
	 * @param board
	 *            The game board to search.
	 * @param xcoord
	 *            The x-coordinate of the top-left cell of the ship.
	 * @param startX
	 *            Copies the value of xcoord and uses it as the starting x coord.
	 * @param endX
	 *            The last value of the xcoord length.
	 * @param ycoord
	 *            The y-coordinate of the top-left cell of the ship.
	 * @param startY
	 *            Copies the value of xcoord and uses it as the starting x coord.
	 * @param endY
	 *            The last value of the ycoord length.
	 * @param len
	 *            The length of the ship.
	 * @param dir
	 *            true if the ship will be vertical, otherwise horizontal
	 * @return 1 if the cells to be occupied by the ship are all Config.WATER_CHAR,
	 *         -1 if the cells to be occupied are not Config.WATER_CHAR, and -2 if
	 *         the ship would go out-of-bounds of the board.
	 */
	public static int checkWater(char board[][], int xcoord, int ycoord, int len, boolean dir) {

		int startX = xcoord;
		int startY = ycoord;
		int endX;
		int endY;

		if (dir) {
			endX = xcoord;
			endY = ycoord + len - 1;
		} else {
			endX = xcoord + len - 1;
			endY = ycoord;
		}

		if (startX < 0 || startX > board[0].length - 1) {
			return -2;
		}
		if (endX < 0 || endX > board[0].length - 1) {
			return -2;
		}
		if (startY < 0 || startY > board.length - 1) {
			return -2;
		}
		if (endY < 0 || endY > board.length - 1) {
			return -2;
		}

		if (dir) {

			for (int i = 0; i < len; ++i) {
				if (board[startY + i][xcoord] != '~') {
					return -1;
				}
			}
		} else {
			for (int i = 0; i < len; ++i) {
				if (board[startY][xcoord + i] != '~') {
					return -1;
				}
			}
		}
		return 1;
	}

	/**
	 * Checks the cells of the game board to determine if all the ships have been
	 * sunk.
	 *
	 * @param board
	 *            The game board to check.
	 * @return true if all the ships have been sunk, false otherwise.
	 */
	public static boolean checkLost(char board[][]) {
		boolean lost = true;

		for (int i = 0; i < board[0].length; i++) {
			for (int j = 0; j < board.length; j++) {
				if ((board[j][i] >= '0') && (board[j][i] <= '9')) {
					lost = false;
					return lost;
				}
			}
		}
		return lost;
	}

	/**
	 * Places a ship into a game board. The coordinate passed in the parameters
	 * xcoord and ycoord represent the top-left coordinate of the ship. The ship is
	 * represented on the game board by the Character representation of the ship id.
	 *
	 * @param board
	 *            The game board to search.
	 * @param xcoord
	 *            The x-coordinate of the top-left cell of the ship.
	 * @param startX
	 *            Copies the value of xcoord and uses it as the starting x coord.
	 * @param endX
	 *            The last value of the xcoord length.
	 * @param ycoord
	 *            The y-coordinate of the top-left cell of the ship.
	 * @param startY
	 *            Copies the value of xcoord and uses it as the starting x coord.
	 * @param endY
	 *            The last value of the ycoord length.
	 * @param len
	 *            The length of the ship.
	 * @param dir
	 *            true if the ship will be vertical, otherwise horizontal.
	 * @param id
	 *            The ship id, assumed to be 1 to 9.
	 * @return false if the ship goes out-of-bounds of the board, true otherwise.
	 */
	public static boolean placeShip(char board[][], int xcoord, int ycoord, int len, boolean dir, int id) {
		int startX = xcoord;
		int startY = ycoord;
		int endX;
		int endY;

		if (dir) {
			endX = xcoord;
			endY = ycoord + len - 1;
		} else {
			endX = xcoord + len - 1;
			endY = ycoord;
		}
		// Checks to see if the ship's length is within the boundaries
		if (startX < 0 || startX > board[0].length - 1) {
			return false;
		}
		if (endX < 0 || endX > board[0].length - 1) {
			return false;
		}
		if (startY < 0 || startY > board.length - 1) {
			return false;
		}
		if (endY < 0 || endY > board.length - 1) {
			return false;
		}

		// Checks to see if the ship is vertical or horizontal, then places the ships
		if (dir) {
			for (int i = ycoord; i <= endY; ++i) {
				board[i][xcoord] = (char) ('0' + id);
			}
			return true;

		} else if (dir == false) {
			for (int i = xcoord; i <= endX; ++i) {
				board[ycoord][i] = (char) ('0' + id);
			}
			return true;
		}

		return true;
	}

	/**
	 * Randomly attempts to place a ship into a game board. The random process is as
	 * follows: 1 - Pick a random boolean, using rand. True represents vertical,
	 * false horizontal. 2 - Pick a random integer, using rand, for the x-coordinate
	 * of the top-left cell of the ship. The number of integers to choose from
	 * should be calculated based on the width of the board and length of the ship
	 * such that the placement of the ship won't be out-of-bounds. 3 - Pick a random
	 * integer, using rand, for the y-coordinate of the top-left cell of the ship.
	 * The number of integers to choose from should be calculated based on the
	 * height of the board and length of the ship such that the placement of the
	 * ship won't be out-of-bounds. 4 - Verify that this random location can fit the
	 * ship without intersecting another ship (checkWater method). If so, place the
	 * ship with the placeShip method.
	 * 
	 * @param board
	 *            The game board to search.
	 * @param len
	 *            The length of the ship.
	 * @param id
	 *            The ship id, assumed to be 1 to 9..
	 * @param rand
	 *            The Random object.
	 * @param xcoord
	 *            Represents the value of the x coordinate.
	 * @param ycoord
	 *            Represents the value of the y coordinate.
	 * @param horzOrVert
	 *            Stores the random true of false used for direction.
	 * @return true if the ship is placed successfully, false otherwise.
	 */
	public static boolean placeRandomShip(char board[][], int len, int id, Random rand) {
		for (int i = 0; i <= 20; i++) {

			int xcoord;
			int ycoord;
			boolean horzOrVert = rand.nextBoolean();

			if (horzOrVert == true) {
				xcoord = rand.nextInt(board[0].length);

			} else {
				if (board[0].length - len + 1 > 0) {
					xcoord = rand.nextInt(board[0].length - len + 1);
				} else {
					continue;
				}
			}

			if (horzOrVert == false) {
				ycoord = rand.nextInt(board.length);
			} else {
				if (board.length - len + 1 > 0) {
					ycoord = rand.nextInt(board.length - len + 1);
				} else {
					continue;
				}
			}

			int ship = checkWater(board, xcoord, ycoord, len, horzOrVert);

			if (ship == 1) {
				if (placeShip(board, xcoord, ycoord, len, horzOrVert, id)) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * This method interacts with the user to place a ship on the game board of the
	 * human player and the computer opponent. The process is as follows: 1 - Print
	 * the user primary board, using the printBoard. 2 - Using the promptChar
	 * method, it prompts the user with "Vertical or horizontal? (v/h) ". A response
	 * of v is interpreted as vertical. Anything else is assumed to be horizontal. 3
	 * - Using the promptInt method, it prompts the user for an integer representing
	 * the "ship length", where the minimum ship length is Config.MIN_SHIP_LEN and
	 * the maximum ship length is width or height of the game board, depending on
	 * the input of the user from step 1. 4 - Using the promptStr method, it prompts
	 * the user for the "x-coord". The maximum value should be calculated based on
	 * the width of the board and the length of the ship. The coordAlphaToNum and
	 * coordNumToAlpha methods are needed to covert between int and String values of
	 * coordinates. 5 - Using the promptInt method, it prompts the user for the
	 * "y-coord". The maximum value should be calculated based on the width of the
	 * board and the length of the ship. 6 - Checks if there is space on the board
	 * to place the ship. 6a - If so: - Places the ship on the board using
	 * placeShip. - Then, calls placeRandomShip to place the opponents ships of the
	 * same length. - If placeRandomShip fails, it print out the error message
	 * (terminated by a new line): "Unable to place opponent ship: id", where id is
	 * the ship id, and return false. 6b - If not: - Using promptChar, it prompts
	 * the user with "No room for ship. Try again? (y/n): " - If the user enters a
	 * 'y', restart the process at Step 1. - Otherwise, return false.
	 *
	 * @param sc
	 *            The Scanner instance to read from System.in.
	 * @param boardPrime
	 *            The human player board.
	 * @param boardOpp
	 *            The opponent board.
	 * @param id
	 *            The ship id, assumed to be 1 to 9.
	 * @param rand
	 *            The Random object.
	 * @return true if ship placed successfully by player and computer opponent,
	 *         false otherwise.
	 */
	public static boolean addShip(Scanner sc, char boardPrime[][], char boardOpp[][], int id, Random rand) {
		boolean choice;
		int len;
		int boardMax;
		int x;
		int y;

		while (true) {
			printBoard(boardPrime, "My Ships:");
			char vertOrHorz = promptChar(sc, "Vertical or horizontal? (v/h): ");

			if (vertOrHorz == 'v' || vertOrHorz == 'V') {
				choice = true;
			} else {
				choice = false;
			}

			if (choice) {
				boardMax = boardPrime.length;
			} else {
				boardMax = boardPrime[0].length;
			}
			len = promptInt(sc, "ship length", 1, boardMax);

			if (choice == true) {
				x = boardPrime[0].length - 1;
			} else {
				x = boardPrime[0].length - len;
			}
			String tempMinStr = "" + coordNumToAlpha(0);
			String tempMaxStr = "" + coordNumToAlpha(x);
			int xcoord = coordAlphaToNum(promptStr(sc, "x-coord", tempMinStr, tempMaxStr));

			if (choice == true) {
				y = boardPrime.length - len;
			} else {
				y = boardPrime.length - 1;
			}
			int ycoord = promptInt(sc, "y-coord", 0, y);

			int waterSpace = checkWater(boardPrime, xcoord, ycoord, len, choice);
			if (waterSpace == 1) {
				placeShip(boardPrime, xcoord, ycoord, len, choice, id);
				if (!placeRandomShip(boardOpp, len, id, rand)) {
					System.out.println("Unable to place opponent ship:" + id);
					return false;
				}
				return true;
			} else {
				char answer = promptChar(sc, "No room for ship. Try again? (y/n): ");
				if (answer == 'n') {
					return false;

				}
			}
		}
	}

	/**
	 * Checks the state of a targeted cell on the game board. This method does not
	 * change the contents of the game board.
	 *
	 * @return 3 if the cell was previously targeted. 2 if the shot would be a miss.
	 *         1 if the shot would be a hit. -1 if the shot is out-of-bounds.
	 */
	public static int takeShot(char[][] board, int x, int y) {

		if (x < 0 || x >= board[0].length || y < 0 || y >= board.length) {
			return -1;
		} else if ((board[y][x] == '*')) {
			return 3;
		} else if ((board[y][x] == '@')) {
			return 3;
		} else if (board[y][x] == '~') {
			return 2;
		} else {
			return 1;
		}
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
	public static void shootPlayer(Scanner sc, char[][] board, char[][] boardTrack) {

		String tempMinStr = "" + coordNumToAlpha(0);
		String tempMaxStr = "" + coordNumToAlpha(board[0].length - 1);

		int xcoord = coordAlphaToNum(promptStr(sc, "x-coord shot", tempMinStr, tempMaxStr));
		int ycoord = promptInt(sc, "y-coord shot", 0, board.length - 1);

		int possibleShot = takeShot(board, xcoord, ycoord);

		if (possibleShot == -1) {
			System.out.println("Coordinates out-of-bounds!");
			return;
		} else if (possibleShot == 3) {
			System.out.println("Shot location previously targeted!");
			return;
		} else if (possibleShot == 2) {
			board[ycoord][xcoord] = '@';
			boardTrack[ycoord][xcoord] = '@';
			return;
		} else if (possibleShot == 1) {
			board[ycoord][xcoord] = '*';
			boardTrack[ycoord][xcoord] = '*';
			return;
		}
	}

	/**
	 * Takes a random shot on the game board. The random process works as follows: 1
	 * - Pick a random valid x-coordinate 2 - Pick a random valid y-coordinate 3 -
	 * Check the shot, using the takeShot method. This process should repeat until
	 * the takeShot method returns 1 or 2, then update the cells in board with
	 * Config.HIT_CHAR or Config.MISS_CHAR accordingly.
	 *
	 * Note: Unlike the placeRandomShip method, this method continues until it is
	 * successful. This may seem risky, but in this case the random process will
	 * terminate (find an untargeted cell) fairly quickly. For more details, see the
	 * appendix of the Big Program 1 subject.
	 *
	 * @param rand
	 *            The Random object.
	 * @param board
	 *            The human player game board.
	 * @var
	 */
	public static void shootComputer(Random rand, char[][] board) {

		while (true) {
			int xcoord = rand.nextInt(board[0].length);
			int ycoord = rand.nextInt(board.length);

			int possibleShot = takeShot(board, xcoord, ycoord);

			if (possibleShot == -1) {
				continue;
			} else if (possibleShot == 3) {
				continue;
			} else if (possibleShot == 2) {
				board[ycoord][xcoord] = '@';
				break;

			} else if (possibleShot == 1) {
				board[ycoord][xcoord] = '*';
				break;
			}

		}
	}

	/**
	 * This is the main method for the Battleship game. It consists of the main game
	 * and play again loops with calls to the various supporting methods. When the
	 * program launches (prior to the play again loop), a message of "Welcome to
	 * Battleship!", terminated by a newline, is displayed. After the play again
	 * loop terminates, a message of "Thanks for playing!", terminated by a newline,
	 * is displayed.
	 *
	 * The Scanner object to read from System.in and the Random object with a seed
	 * of Config.SEED will be created in the main method and used as arguments for
	 * the supporting methods as required.
	 *
	 * Also, the main method will require 3 game boards to track the play: - One for
	 * tracking the ship placement of the user and the shots of the computer, called
	 * the primary board with a caption of "My Ship". - One for displaying the shots
	 * (hits and misses) taken by the user, called the tracking board with a caption
	 * of "My Shots"; and one for tracking the ship placement of the computer and
	 * the shots of the user. - The last board is never displayed, but is the
	 * primary board for the computer and is used to determine when a hit or a miss
	 * occurs and when all the ships of the computer have been sunk. Notes: - The
	 * size of the game boards are determined by the user input. - The game boards
	 * are 2d arrays that are to be viewed as row-major order. This means that the
	 * first dimension represents the y-coordinate of the game board (the rows) and
	 * the second dimension represents the x-coordinate (the columns).
	 *
	 * @param args
	 *            Unused.
	 */
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Random rand = new Random(1234);
		System.out.println("Welcome to Battleship!");

		int boardHeight = 0;
		int boardWidth = 0;

		boolean userChoice = true;

		while (userChoice) {
			boardHeight = promptInt(sc, "board height", 1, 99);
			boardWidth = promptInt(sc, "board width", 1, 675);

			// My Ship
			char[][] primaryBoard = new char[boardHeight][boardWidth];
			initBoard(primaryBoard);

			// My Shots
			char[][] secondaryBoard = new char[boardHeight][boardWidth];
			initBoard(secondaryBoard);

			// Game Board for computer
			char[][] tertiaryBoard = new char[boardHeight][boardWidth];
			initBoard(tertiaryBoard);

			System.out.println();

			int numShips = promptInt(sc, "number of ships", 1, 9);

			for (int i = 1; i <= numShips; i++) {

				if ((addShip(sc, primaryBoard, tertiaryBoard, i, rand)) == false) {
					char response = promptChar(sc, "Error adding ships. Restart game? (y/n): ");
					if (response == 'y') {
						break;
					} else {
						continue;
					}
				}
			}

			do {
				printBoard(primaryBoard, "My Ships:");
				printBoard(secondaryBoard, "My Shots:");

				shootPlayer(sc, tertiaryBoard, secondaryBoard);

				if ((checkLost(tertiaryBoard)) == true) {
					System.out.println("Congratulations, you sunk all the computer's ships!");
					printBoard(primaryBoard, "My Ships:");
					printBoard(secondaryBoard, "My Shots:");
					break;
				} else {
					shootComputer(rand, primaryBoard);
					if (checkLost(primaryBoard) == true) {
						System.out.println("Oh no! The computer sunk all your ships!");
						printBoard(primaryBoard, "My Ships:");
						printBoard(secondaryBoard, "My Shots:");
						break;
					}
				}

			} while (true);

			char userCharacter = promptChar(sc, "Would you like to play again? (y/n): ");
			if (userCharacter != 'y') {
				userChoice = false;
				System.out.println("Thanks for playing!");
			}
		}
		return;
	}
}
