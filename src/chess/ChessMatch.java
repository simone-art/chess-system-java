package chess;

import boardgame.Board;
import boardgame.Position;
import chess.piece.King;
import chess.piece.Rook;

public class ChessMatch {
	
	private Board board;

	public ChessMatch() {
		
		//Tamanho do tabuleiro 8 * 8
		board = new Board(8, 8);
		initialSetup();
		
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
	
	//Método que vai imprimir  e inicializar no tabuleiro as peças Torre e King
	private void initialSetup() {
		board.placePiece(new Rook(board, Color.WHITE), new Position(2,1));
		board.placePiece(new King(board, Color.BLACK), new Position(0,4));
		board.placePiece(new King(board, Color.WHITE), new Position(7,4));
	}
	
	
	
	

}
