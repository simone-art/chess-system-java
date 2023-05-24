package application;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import chess.ChessMatch;
import chess.ChessPiece;
import chess.ChessPosition;
import chess.Color;

public class UI {

	// https://stackoverflow.com/questions/5762491/how-to-print-color-in-console-using-system-out-println

	//Código especial pra imprimir cores no console
	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_BLACK = "\u001B[30m";
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_GREEN = "\u001B[32m";
	public static final String ANSI_YELLOW = "\u001B[33m";
	public static final String ANSI_BLUE = "\u001B[34m";
	public static final String ANSI_PURPLE = "\u001B[35m";
	public static final String ANSI_CYAN = "\u001B[36m";
	public static final String ANSI_WHITE = "\u001B[37m";

	public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
	public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
	public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
	public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
	public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
	public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
	public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
	public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";
	
	//Método que limpa o console 
	public static void clearScreen() {
		System.out.print("\033[H\033[2J");
		System.out.flush();
	}
	
	//Métood que lê a posição do xadrez
	public static ChessPosition readChessPosition(Scanner sc) {
		try {
			String s = sc.nextLine();
			char column = s.charAt(0);
			int row = Integer.parseInt(s.substring(1));
			return new ChessPosition(column, row);
			
		}catch (RuntimeException e){
			//InputMisMatchException erro de entrada de dados
			throw new InputMismatchException("Error reading chessPosition. Valid values from a1 to h8");
		}
	}
	
	//Método que printa a jogada
	public static void PrintMatch(ChessMatch chessMatch, List<ChessPiece> captured) {
		printBoard(chessMatch.getPieces());
		System.out.println();
		printCapturedPieces(captured);
		System.out.println();
		System.out.println("Turn : " +  chessMatch.getTurn());
		//Se a jogada nçao estiver em cheque mate
		if(!chessMatch.getCheckMate()) {
			System.out.println("Wainting player : " +  chessMatch.getCurrentPlayer());
			if(chessMatch.getCheck()) {
			System.out.println("CHECK!");
			
			}
		}
		else {
			System.out.println("CHECKMATE!");
			System.out.println("WINNER: " + chessMatch.getCurrentPlayer());
		}
		
		
	}

	//Com o false, o tabuleiro vai estar sen nenhuma peça colorida
	public static void printBoard(ChessPiece[][] pieces) {
		for (int i = 0; i < pieces.length; i++) {
			System.out.print((8 - i) + " ");
			for (int j = 0; j < pieces.length; j++) {
				printPiece(pieces[i][j], false);
			}
			System.out.println();
		}
		System.out.print("  a b c d e f g h");

	}

	//Método que printa as possiveis posições vazias que tem uma peça
	//possibleMoves[i][j] printa o fundo colorido do tabuleiro
	public static void printBoard(ChessPiece[][] pieces, boolean [][]possibleMoves) {
		for (int i = 0; i < pieces.length; i++) {
			System.out.print((8 - i) + " ");
			for (int j = 0; j < pieces.length; j++) {
				printPiece(pieces[i][j], possibleMoves[i][j]);
			}
			System.out.println();
		}
		System.out.print("  a b c d e f g h");

	}
	
	
	
	// Método pra imprmir a peça no tabuleiro
	private static void printPiece(ChessPiece piece, boolean background) {
		if (background) {
			System.out.print(ANSI_BLUE_BACKGROUND);
		}
		if (piece == null) {
            System.out.print("-");
        }
        else {
            if (piece.getColor() == Color.WHITE) {
                System.out.print(ANSI_WHITE + piece + ANSI_RESET);
            }
            else {
                System.out.print(ANSI_YELLOW + piece + ANSI_RESET);
            }
        }
        System.out.print(" ");
	}
	
	//Método que manipula as peças capturadas
	private static void printCapturedPieces(List<ChessPiece> captured) {
		//Operação básica com lambda para filtrar uma lista
		List<ChessPiece> white = captured.stream().filter( x -> x.getColor() == Color.WHITE).collect(Collectors.toList());
		List<ChessPiece> black = captured.stream().filter( x -> x.getColor() == Color.BLACK).collect(Collectors.toList());
		System.out.println("Captured pieces: ");
		System.out.print("White: ");
		System.out.print(ANSI_WHITE);
		//Imprime Array de valores no Java
		System.out.println(Arrays.toString(white.toArray()));
		System.out.print(ANSI_RESET);
		
		System.out.print("Black: ");
		System.out.print(ANSI_YELLOW);
		//Imprime Array de valores no Java
		System.out.println(Arrays.toString(black.toArray()));
		System.out.print(ANSI_RESET);
	}
}
