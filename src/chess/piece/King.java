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


	@Override
	public boolean[][] possibleMoves() {
		//Criada matriz de booleanos com a dimensão do tabuleiro
		//Por padrão todas as posições da matriz começam com false
		boolean[][] mat = new boolean [getBoard().getRows()] [getBoard().getColumns()];
		return mat;
	}

}
