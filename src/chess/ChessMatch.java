package chess;



import java.util.ArrayList;
import java.util.List;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;
import chess.piece.King;
import chess.piece.Rook;

public class ChessMatch {
	
	
	private int turn;
	private Color currentPlayer;
	private Board board;
	
	private List<Piece> piecesOnTheBoard = new ArrayList<>();
	private List<Piece> capturedPieces = new ArrayList<>();

	public ChessMatch() {
		
		//Tamanho do tabuleiro 8 * 8
		board = new Board(8, 8);
		turn = 1;
		currentPlayer = Color.WHITE;
		initialSetup();	
	}
	
	public int getTurn() {
		return turn;
	}


	public Color getCurrentPlayer() {
		return currentPlayer;
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
	
	public boolean[][] possibleMoves(ChessPosition sourcePosition){
     Position position = sourcePosition.toPosition();
     validateSourcePosition(position);
     return board.piece(position).possibleMoves();
	}
	
	//Método que move a peça
	//Source position: posição de origem
	// Target position: posição de destino da peça
	public ChessPiece performChessMove(ChessPosition sourcePosition, ChessPosition targetPosition) {
		Position source = sourcePosition.toPosition();
		Position target = targetPosition.toPosition();
		validateSourcePosition(source);
		validateTargetPosition(source,target);
		Piece capturedPiece = makeMove(source, target);
		nextTurn();
		//Downcasting da classe ChessPiece
		return (ChessPiece) capturedPiece;
	}
	
	//Valida a posição de origem da peça no tabuleiro
	private void validateSourcePosition(Position position) {
		if (!board.thereIsAPiece(position)) {
			throw new ChessException("There is no piece on source position");
		}
		//Valida que o jogador não possa pegar uma peça adversária
		if(currentPlayer != ((ChessPiece)board.piece(position)).getColor()) {
		   throw new ChessException("The choisen piece is not yours");
		}
		//Valida se existe movimento possível para a peça
		if (!board.piece(position).isThereAnyPossivelMove()) {
			throw new ChessException("There is no possible moves for the choisen piece");
		}
	}
	
	//Valida a posição de destino da peça no tabuleiro
		private void validateTargetPosition(Position source, Position target) {
			if (!board.piece(source).possibleMove(target)) {
				throw new ChessException("The chosen piece can't move to target position");
			}
		}

    //Método que permite a troca de turno do jogador
	private void nextTurn() {
		//Incremento do turno
		turn++;
		//Expressão condicional ternária
		//currentPlayer == Color.WHITE) ? Color.BLACK se o jogador comecou
		//Com uma peça branca, agora vai pra preta.
		//Color.BLACK : Color.WHITE; contrário se for preta vai pra branca
		currentPlayer = (currentPlayer == Color.WHITE) ? Color.BLACK : Color.WHITE;
		
	}
	
	//Método que realiza o movimento da peça
	private Piece makeMove(Position source, Position target) {
		Piece p = board.removePiece(source);
		Piece capturedPiece = board.removePiece(target);
		board.placePiece(p, target);
		
		if(capturedPiece != null) {
			piecesOnTheBoard.remove(capturedPiece);
			capturedPieces.add(capturedPiece);
			
		}
		return capturedPiece;
		
	}
	
	
	//Método que recebe as coordenadas do xadrez
	private void placeNewPiece(char column, int row, ChessPiece piece) {
		//Converte o xadrez para a posição de matriz
		board.placePiece(piece, new ChessPosition(column, row).toPosition());
		//Adiciona a peça na lista de peças no tabuleiro
		piecesOnTheBoard.add(piece);
		
	}
	
	//Método que vai imprimir  e inicializar no tabuleiro as peças Torre e King
	private void initialSetup() {
		 placeNewPiece('c', 2, new Rook(board, Color.WHITE));
	        placeNewPiece('d', 2, new Rook(board, Color.WHITE));
	        placeNewPiece('e', 2, new Rook(board, Color.WHITE));
	        placeNewPiece('e', 1, new Rook(board, Color.WHITE));
	        placeNewPiece('d', 1, new King(board, Color.WHITE));

	        placeNewPiece('c', 7, new Rook(board, Color.BLACK));
	        placeNewPiece('c', 8, new Rook(board, Color.BLACK));
	        placeNewPiece('d', 7, new Rook(board, Color.BLACK));
	        placeNewPiece('e', 7, new Rook(board, Color.BLACK));
	        placeNewPiece('e', 8, new Rook(board, Color.BLACK));
	        placeNewPiece('d', 8, new King(board, Color.BLACK));
	}
	
	
	
	

}
