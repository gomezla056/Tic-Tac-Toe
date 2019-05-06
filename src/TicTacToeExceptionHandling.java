package ticTacToeExceptionHandling;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * The class TicTacToeExceptionHandling begins a
 * Tic-Tac-Toe game with two human players and asks
 * for their names. Whoever puts their name first
 * is the first player. The players input rows and
 * columns to place their marker down and the game
 * checks each turn to see if there's a winner or a draw.
 * If any user inputs an incorrect value, the error is
 * caught and that user must retry to input a value again.
 */
public class TicTacToeExceptionHandling 
{
	public static final int PLAYER_X = 1;
	public static final int PLAYER_O = 2;
	
	/**
	 * Creates Tic-Tac-Toe game for two human players
	 * and runs until a tie or win occurs
	 * 
	 * @param args	takes no arguments
	 */
	public static void main(String[] args)
	{
		char[][] board = new char[3][3];
		
		Scanner keyboard = new Scanner(System.in);
		
		//Prompt for players' full names and get the names
		System.out.print("Player 1, type your full name: ");
		String nameP1 = keyboard.nextLine();
		System.out.print("Player 2 type your full name: ");
		String nameP2 = keyboard.nextLine();
		
		int player = PLAYER_X; //player X (the 1st player) plays first
		boolean done = false;
		while (!done)
		{
			System.out.println();
			printBoard(board);
			
			// Prompt for current player's turn
			// Chooses row and column to place their marker
			
			System.out.println("Player " + player + "'s turn.");
			System.out.print("Choose row (0 - 2): ");
			int row = getRow(keyboard);
			System.out.print("Choose column (0 - 2): ");
			int col = getCol(keyboard);
			
			if (!isLegalMove(board, row, col))
			{
				System.out.println("Invalid move, try again");
			} 
			else 
			{
				placeMarker(board, row, col, player);
				if (playerWins(board, player)) 
				{
					// Current player wins
					// Show current player's name and winning message
					done = true;
					System.out.println();
					printBoard(board);
					if (player == PLAYER_X) System.out.println(nameP1 + " has won the game!");
					else System.out.println(nameP2 + " has won the game!");
				} 
				else if (isDraw(board)) 
				{
					// Game is a draw
					System.out.println();
					printBoard(board);
					System.out.println("It's a draw!");
					done = true;
				} 
				else 
				{
					// Switch player
					player = (player == PLAYER_X) ? PLAYER_O : PLAYER_X;
				}
			}
		} 
		System.out.println("Thanks for playing!");
	}
	
	/**
	 * Prints the contents of the board to the user
	 * 
	 * @param board	current state of board
	 */
	public static void printBoard(char[][] board) 
	{
		for (int i = 0; i < 3; i++)
		{
			for (int j = 0; j < 3; j++)
			{
				System.out.print(board[i][j]);
				if(j < 2)
				{
					System.out.print(" | ");
				}
				if(j >= 2 )
					System.out.println();
			}
			if(i < 2)
				System.out.println("--|---|--");
		}
		System.out.println();
	}
	
	/**
	 * Gets a row from user input and checks
	 * if the input is valid or not.
	 * 
	 * @param keyboard	input integer
	 * @return			desire row user wants
	 */
	public static int getRow(Scanner keyboard) 
	{
		boolean wrongInput = true;
		int row = 0;
		while(wrongInput)
		{
			try
			{
				row = keyboard.nextInt();
				wrongInput = false;
			}
			catch(InputMismatchException e)
			{
				System.out.println("Error: Input must be an integer.");
				keyboard.next();
			}
		}
		return row;
	}
	
	/** 
	 * Gets column from user input and checks
	 * if the input is valid or not.
	 * 
	 * @param keyboard	input integer
	 * @return			desire column user wants
	 */
	public static int getCol(Scanner keyboard) 
	{
		boolean wrongInput = true;
		int column = 0;
		while(wrongInput)
		{
			try
			{
				column = keyboard.nextInt();
				wrongInput = false;
			}
			catch(InputMismatchException e)
			{
				System.out.println("Error: Input must be an integer.");
				keyboard.next();
			}
		}
		return column;	
	}
	
	/**
	 * Checks to see if the user input is a valid move
	 * 
	 * @param board	Current state of board
	 * @param row	Choose from 0-2 rows
	 * @param col	Choose from 0-2 columns
	 * @return		True if a valid move, false otherwise
	 */
	public static boolean isLegalMove(char[][] board, int row, int col)
	{
		return (row >= 0 & row <= 2 && col >= 0 && col <= 2 && board[row][col] == '\u0000');
	}
	
	/**
	 * Allows the user to place its marker on the row and column of the board
	 * 
	 * @param board		Current state of board
	 * @param row		Choose from 0-2 rows
	 * @param col		Choose from 0-2 columns
	 * @param player	Current player's turn 
	 */
	public static void placeMarker(char[][] board, int row, int col,int player)
	{
		if (player == PLAYER_X)
			board[row][col] = 'X';
		else
			board[row][col] = 'O';
	}
	
	/** 
	 * Checks all 8 possible ways to win the game
	 * 
	 * @param board		Current state of board
	 * @param player	Current player
	 * @return			If the current player has won the game
	 */
	public static boolean playerWins(char[][] board, int player)
	{
		char letter;
		if (player == 1) letter = 'X';
		else letter = 'O';
		return ((board[0][0] == letter && board[0][1] == letter && board[0][2] == letter) ||
				(board[1][0] == letter && board[1][1] == letter && board[1][2] == letter) ||
				(board[2][0] == letter && board[2][1] == letter && board[2][2] == letter) ||
				(board[0][0] == letter && board[0][1] == letter && board[0][2] == letter) ||
				(board[1][0] == letter && board[1][1] == letter && board[1][2] == letter) ||
				(board[2][0] == letter && board[2][1] == letter && board[2][2] == letter) ||
				(board[0][0] == letter && board[1][1] == letter && board[2][2] == letter) ||
				(board[2][0] == letter && board[1][1] == letter && board[0][2] == letter));
	}
	
	/**
	 * Checks if there are no more moves to do
	 * 
	 * @param board		Current state of board
	 * @return			If there are no more moves to be made
	 */
	public static boolean isDraw(char[][] board) 
	{
		return (board[0][0] != '\u0000' && board[0][1] != '\u0000' && board[0][2] != '\u0000' &&
				board[1][0] != '\u0000' && board[1][1] != '\u0000' && board[1][2] != '\u0000' &&
				board[2][0] != '\u0000' && board[2][1] != '\u0000' && board[2][2] != '\u0000');
	}
}
