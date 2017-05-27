package tictactoe;

import java.util.Scanner;

public class TicTacToe {
	public static char[][] board = new char[3][3];
	public char letter;
	public String player;
	
	public static void main(String[] args)
	{
		Scanner reader = new Scanner(System.in);
		TicTacToe player1 = new TicTacToe("Player 1");
		TicTacToe player2 = new TicTacToe("Player 2");
		boolean end = false;
		int turns = 0;
		
		startGame(player1, player2, reader);
		
		while(!end) {
			
			playerTurn(player1, player2, turns, reader);
			turns++;
			
			end = getResult(player1, player2, turns);
		}

	}
	
	public TicTacToe(String p) {
		letter = ' ';
		player = p;
	}

	
	public void setLetter(char l) {
		letter = l;
	}
	
	public void setPlayer(String p) {
		player = p;
	}
	
	public char getLetter() {
		return letter;
	}
	
	public String getPlayer() {
		return player;
	}
	
	public static void startGame(TicTacToe p1, TicTacToe p2, Scanner reader) {
		playerSetup(p1, p2, reader);
		
		System.out.println("Player 1 chose: '" + p1.getLetter() + "' Player 2 chose: '" + p2.getLetter() + "'");
		System.out.println("Game start!");
		
		startBoard(board);
		displayBoard(board);
	}
	
	public static void playerSetup(TicTacToe p1, TicTacToe p2, Scanner reader) {
		char answer1;
		char answer2;
		
		boolean check = false;
		
		while(!check) {
			System.out.println("Player One - select X or O?");
			answer1 = reader.next().charAt(0);
			
			check = checkAnswer(answer1);
			if(check) {
				if(answer1 == 'X' || answer1 =='x') {
					answer1 = 'X';
					answer2 = 'O';
					
					p1.setLetter(answer1);
					p2.setLetter(answer2);
				}
				else if(answer1 == 'O' || answer1 =='o') {
					answer1 = 'O';
					answer2 = 'X';
					
					p1.setLetter(answer1);
					p2.setLetter(answer2);
				}
			}
		}
	}
	
	public static boolean getResult(TicTacToe p1, TicTacToe p2, int turn) {
		boolean result = false;
		
		if(turn > 2 && turn < 9) {
			char winLetter = checkWin();
			
			if(winLetter != '_') {
				String winner;
				winner = getWinner(p1, p2, winLetter);
				System.out.println(winner + " wins!");
				
				result = true;
			}
		}
		else if(turn >= 9) {
			char winLetter = checkWin();
			if(winLetter == '_') {
				System.out.println("Tie!");
			}
			
			result = true;
		}
		
		
		return result;
	}
	
	//checkAnswer ensures the player input is an X or an O
	public static boolean checkAnswer(char l) {
		boolean check = false;
		char[] possible = {'X', 'x', 'O','o'};
		
		for (int i = 0; i < possible.length; i++) {
			if(l == possible[i]) {
				check = true;
			}
		}
		
		if(!check) {
			System.out.println("Invalid selection. Please try again.");
		}
		return check;
	}
	
	//startBoard initializes the board at the start of a game
	public static void startBoard(char[][] b) {
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				b[i][j] = '_';
			}
		}
	}
	
	public static void displayBoard(char[][] b) {
		System.out.println("  0 1 2");
		for(int i = 0; i < 3; i++) {
			System.out.print(i + " ");
			for(int j = 0; j < 3; j++) {
				System.out.print(b[i][j] + " ");
			}
			
			System.out.print("\n");
		}
	}
	
	public static void playerTurn(TicTacToe p1, TicTacToe p2, int turn, Scanner reader) {
		TicTacToe currentPlayer = checkTurn(p1, p2, turn);
		int row;
		int column;
		boolean check = false;
		
		while(!check) {
			System.out.println(currentPlayer.getPlayer() + " - Please enter the row and column numbers separated by a space:");
			row = reader.nextInt();
			column = reader.nextInt();
			
			check = checkLetter(row, column);
		
			if(check) {
				board[row][column] = currentPlayer.getLetter();
			}
		}	
		
		displayBoard(board);
	}
	
	//checkLetter function ensures that the input row and column are valid
	public static boolean checkLetter(int r, int c) {
		boolean check = false;
		
		if(r < 3 && c < 3) {
			if(board[r][c] == '_') {
				check = true;
			}
			else {
				System.out.println("Invalid action. Square is already taken");
			}
		}
		
		else {
			System.out.println("Invalid action. Incorrect row or column");
		}
	
		System.out.println("Last input - row: " + r + " column: " + c);
		return check;
	}
	
	public static TicTacToe checkTurn(TicTacToe p1, TicTacToe p2, int turn) {
		TicTacToe current;
		
		if((turn % 2) == 0) {
			current = p1;
		}
		else {
			current = p2;
		}
		
		return current;
	}
	
	public static char checkWin() {
		int r = 0;
		int c = 0;
		char win = '_';
		
		while(r < 3) {
			if(board[r][c] == board[r][c+1] && board[r][c] == board[r][c+2]) {
				if(board[r][c] != '_') {
					win = board[r][c];
				}
			}
			r++;
		}
		
		r = 0;
		c = 0;
		while(c < 3) {
			if(board[r][c] == board[r+1][c] && board[r][c] == board[r+2][c]) {
				if(board[r][c] != '_') {
					win = board[r][c];
				}
			}
			c++;
		}
		
		if(board[0][0] == board[1][1] && board[0][0] == board[2][2]) {
			if(board[0][0] != '_') {
				win = board[0][0];
			}
		}
		
		if(board[1][1] == board[0][2] && board[1][1] == board[2][0]){
			if(board[1][1] != '_') {
				win = board[1][1];
			}
		}
		
		return win;
	}
	
	public static String getWinner(TicTacToe p1, TicTacToe p2, char l) {
		String winner;
		if(p1.getLetter() == l) {
			winner = p1.getPlayer();
		}
		else {
			winner = p2.getPlayer();
		}
		
		return winner;
	}
	
}
