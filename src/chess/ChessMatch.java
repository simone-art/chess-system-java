package chess;

import boardgame.Board;

public class ChessMatch {
	
	private Board board;

	public ChessMatch() {
		
		//Tamanho do tabuleiro 8 * 8
		board = new Board(8, 8);
		
	}
	
	//Método que retorna uma matriz de peça de xadrez
	public ChessPiece[][] getPieces() {
		ChessPiece[][] mat = new ChessPiece[board.getRows()][board.getColumns()];
		for(int i =0; i<board.getRows(); i++) {
			for(int j = 0; j<board.getColumns(); j++) {
				//(chessPiece) é um downcasting
				mat[i][j] = (ChessPiece) board.piece(i, j);
			}
		}
		return mat;
	}
	
	
	
	

}
