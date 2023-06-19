package application;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import boardgame.Board;
import boardgame.Position;
import chess.ChessException;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.ChessPosition;

public class Program {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

//		Position pos = new Position(3, 5);
//		System.out.println(pos);

//		Board board = new Board(8, 8);

		Scanner sc = new Scanner(System.in);
		ChessMatch chessMatch = new ChessMatch();
		List<ChessPiece> captured = new ArrayList<>();

		while (!chessMatch.getCheckMate()) {
			// Limpa a tela a cada vez que volta no While
			try {
				UI.clearScreen();
				UI.PrintMatch(chessMatch, captured);
//				UI.printBoard(chessMatch.getPieces());
				System.out.println();
				System.out.print("Source: ");
				ChessPosition source = UI.readChessPosition(sc);

				boolean[][] possibleMoves = chessMatch.possibleMoves(source);
				UI.clearScreen();
				UI.printBoard(chessMatch.getPieces(), possibleMoves);
                System.out.println();
				System.out.print("Target: ");
				ChessPosition target = UI.readChessPosition(sc);

				ChessPiece capturedPiece = chessMatch.performChessMove(source, target);
				if (capturedPiece != null) {
					captured.add(capturedPiece);
				}
				
				if(chessMatch.getPromoted() != null) {
					System.out.println("Enter piece for promotion: (B/N/R/Q): ");
				    String type = sc.nextLine().toUpperCase();
				    while(!type.equals("B") && !type.equals("N") && !type.equals("R") && 
							!type.equals("Q")) {
				    	System.out.println("Invalid value! Enter piece for promotion: (B/N/R/Q): ");
					    type = sc.nextLine().toUpperCase();
					}
				    chessMatch.replacePromotedPiece(type);
				}
			} catch (ChessException e) {
				System.out.println(e.getMessage());
				// sc.nextLine(); espera que o usuário teclee o enter
				sc.nextLine();

			} catch (InputMismatchException e) {
				System.out.println(e.getMessage());
				// sc.nextLine(); espera que o usuário teclee o enter
				sc.nextLine();
			}

		}
		UI.clearScreen();
		UI.PrintMatch(chessMatch, captured);
	}
}
