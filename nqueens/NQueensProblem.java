package nqueens;

import java.util.*;

public class NQueensProblem {

	private static int N = 4;
	
	/* ld is an array where its indices indicate row - col + N - 1
	* (N - 1) is for shifting the difference to store 
	* negative indices.
	*/
	private int[] ld = new int[30];
	
	/* rd is an array where its indices indicates column and 
	* used to check whether a queen can be placed on
	* right diagonal or not.
	*/
	private int[] rd = new int[30];
	
	/* Column array where its indices indicates column and 
	* used to check whether a queen can be placed in that 
	* row or not.
	*/
	private int[] cl = new int[30];
	
	// A utility function to print solution
	private void printSolution(int[][] board) {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (board[i][j] == 1) {
					System.out.print(" Q ");
				} else {
					System.out.print(" . ");
				}
			}
			System.out.println();
		}
		System.out.println();
	}
	
	// A recusive utility function to solve N queens
	// problem.
	private boolean solveNQ(int[][] board, int col) {
		// Base case: if all queens are placed then
		// return true
		if (col >= N) return true;

		// Consider this column and try placing this
		// queen in all rows one by one
		for (int i = 0; i < N; i++) {
			// Check if the queen can be placed on 
			// board[i][col]

			// To check if a queen can be placed on board[row][col].
			// 	We just need to check ld[row - col + n-1] and 
			// rd[row + col] where ld and rd are for left and right
			// dialogal respectively.
			if ((ld[i - col + N - 1] != 1) && 
				 rd[i + col] != 1 && 
				 cl[i] != 1) {
					// Place the queen in board[i][col]
					board[i][col] = 1;
					ld[i - col + N - 1] = rd[i + col] = cl[i] = 1;

					// Recur to place rest of the queens
					printSolution(board);
					if (solveNQ(board, col + 1)) {
						return true;
					}

					// If placing queen in board[i][col] doesn't
					// lead to a solution, then remove the 
					// queen from board[i][col]
					System.out.println("Backtracking <<<<<<<<<<<");
					board[i][col] = 0; // BACKTRACKING
					ld[i - col + N - 1] = rd[i + col] = cl[i] = 0;
					printSolution(board);
			}
		}

		// If the queen cannot be placed in any row in this
		// column then return false.
		return false;
	}
	
	private boolean solve() {
		// Driver code
		final int[][] board = new int[][] {
			{0, 0 ,0, 0},
			{0, 0 ,0, 0},
			{0, 0 ,0, 0},
			{0, 0 ,0, 0},
		};
		
		if (!solveNQ(board, 0)) {
			System.out.println("No solution exist.");
			return false;
		}

		System.out.println("Solution: ");
		printSolution(board);
		return true;
	}
	
	public static void main(String[] args) {
		final NQueensProblem app = new NQueensProblem();
		app.solve();
	}
	
}