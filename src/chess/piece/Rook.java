package chess.piece;

import boardgame.Board;
import chess.ChessPiece;
import chess.Color;

public class Rook extends ChessPiece{

	public Rook(Board board, Color color) {
		super(board, color);
		
	}
	
	//Método que vai imprimir no tabuleiro a peça Torre
	@Override
	public String toString() {
		return "R";
	}

}
