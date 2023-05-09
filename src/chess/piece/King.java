package chess.piece;

import boardgame.Board;
import chess.ChessPiece;
import chess.Color;

public class King extends ChessPiece{

	public King(Board board, Color color) {
		super(board, color);
	}
	
	
	//Método que vai imprimir no tabuleiro a peça King
	@Override
	public String toString() {
		return "K";
	}

}
