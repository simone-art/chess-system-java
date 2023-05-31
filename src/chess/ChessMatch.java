package chess;



import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;
import chess.piece.Bishop;
import chess.piece.King;
import chess.piece.Knight;
import chess.piece.Pawn;
import chess.piece.Queen;
import chess.piece.Rook;

public class ChessMatch {
	
	
	private int turn;
	private Color currentPlayer;
	private Board board;
	private boolean check;
	private boolean checkMate;
	
	private List<Piece> piecesOnTheBoard = new ArrayList<>();
	private List<Piece> capturedPieces = new ArrayList<>();

	public ChessMatch() {
		
		//Tamanho do tabuleiro 8 * 8
		board = new Board(8, 8);
		turn = 1;
		currentPlayer = Color.WHITE;
		check = false;
		initialSetup();	
	}
	
	public int getTurn() {
		return turn;
	}


	public Color getCurrentPlayer() {
		return currentPlayer;
	}
	
	public boolean getCheck() {
		return check;
	}
	
	public boolean getCheckMate() {
		return checkMate;
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
		
		if(testCheck(currentPlayer)) {
			//desfaz o movimento
			undoMove(source, target, capturedPiece);
			throw new ChessException("You can´t put yourself in check");
		}
		//expressão condicional ternária
		check = (testCheck(opponent(currentPlayer))) ? true : false;
		
		if(testCheckMate(opponent(currentPlayer))) {
			checkMate = true;
		}
		else {
			nextTurn();
		}
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
		ChessPiece p = (ChessPiece)board.removePiece(source);
		p.increaseMoveCount();
		Piece capturedPiece = board.removePiece(target);
		board.placePiece(p, target);
		
		if(capturedPiece != null) {
			piecesOnTheBoard.remove(capturedPiece);
			capturedPieces.add(capturedPiece);
			
		}
		
		//SpecialMove castling Kingside rook roque pequeno
		if(p instanceof King && target.getColumn() == source.getColumn() + 2) {
			Position sourceT = new Position(source.getRow(), source.getColumn() + 3);
			Position targetT = new Position(source.getRow(), source.getColumn() + 1);
			ChessPiece rook = (ChessPiece)board.removePiece(sourceT);
			board.placePiece(rook, targetT);
			rook.increaseMoveCount();
		}
		
		//SpecialMove castling queenside rook roque grande
		if(p instanceof King && target.getColumn() == source.getColumn() - 2) {
			Position sourceT = new Position(source.getRow(), source.getColumn() - 4);
			Position targetT = new Position(source.getRow(), source.getColumn() - 1);
			ChessPiece rook = (ChessPiece)board.removePiece(sourceT);
			board.placePiece(rook, targetT);
			rook.increaseMoveCount();
		}
		
		return capturedPiece;
		
	}
	
	//Método que desfaz a movimentação da peça pra evitar o cheque
	private void undoMove(Position source, Position target, Piece capturedPiece) {
		//Remove a peça do destino
		ChessPiece p = (ChessPiece)board.removePiece(target);
		p.decreaseMoveCount();
		//Devolve a peça para a posição de origem
		board.placePiece(p, source);
		
		if(capturedPiece != null) {
			board.placePiece(capturedPiece, target);
			//remove a peça da lista de peças capturadas
			capturedPieces.remove(capturedPiece);
			//adiciona a peça na lista de peças no tabuleiro
			piecesOnTheBoard.add(capturedPiece);
		}
		
		//SpecialMove castling Kingside rook roque pequeno
		//Desfaz a movimentação do roque no caso da Torre
		if(p instanceof King && target.getColumn() == source.getColumn() + 2) {
			Position sourceT = new Position(source.getRow(), source.getColumn() + 3);
			Position targetT = new Position(source.getRow(), source.getColumn() + 1);
			ChessPiece rook = (ChessPiece)board.removePiece(targetT);
			board.placePiece(rook, sourceT);
			rook.decreaseMoveCount();;
				}
				
		//SpecialMove castling queenside rook roque grande
		//Desfaz a movimentação do roque no caso da Torre
		if(p instanceof King && target.getColumn() == source.getColumn() - 2) {
			Position sourceT = new Position(source.getRow(), source.getColumn() - 4);
			Position targetT = new Position(source.getRow(), source.getColumn() - 1);
			ChessPiece rook = (ChessPiece)board.removePiece(sourceT);
			board.placePiece(rook, sourceT);
			rook.decreaseMoveCount();
		}
//		board.placePiece(p, source);
	}
	

	
	//Método que recebe as coordenadas do xadrez
	private void placeNewPiece(char column, int row, ChessPiece piece) {
		//Converte o xadrez para a posição de matriz
		board.placePiece(piece, new ChessPosition(column, row).toPosition());
		//Adiciona a peça na lista de peças no tabuleiro
		piecesOnTheBoard.add(piece);
		
	}
	
	private Color opponent(Color color) {
		//Se a cor for igual a branco retorno preto. Caso contrário retorno branco
		return (color == Color.WHITE )? Color.BLACK : Color.WHITE;
	}
	
	
	
	//Método que verifica a cor da peça Rei
	private ChessPiece king(Color color) {
		//Expressões lambda são necessárias para filtrar listas
		List<Piece> list = piecesOnTheBoard.stream()
				.filter(x ->((ChessPiece)x).getColor() == color)
						.collect(Collectors.toList());
		
		for(Piece p : list) {
			//Si a peça for uma instância de Rei, significa que o Rei foi achado
			if(p instanceof King) {
				return (ChessPiece)p;
			}
		}
		throw new IllegalStateException("There is no " + color + " King on the board");
		
	}
	
	
	
	//Método que verifica se existe uma peça adversária que possa fazer um movimennto de check
	private boolean testCheck(Color color) {
		//Código que vai pegar a posição e cor do rei
		Position kingPosition = king(color).getChessPosition().toPosition();
		//Lista de peças do oponente que podem levar até o Rei
		List<Piece> opponentPieces = piecesOnTheBoard.stream()
				.filter(x ->((ChessPiece)x).getColor() == opponent(color))
				.collect(Collectors.toList());
		for(Piece p : opponentPieces) {
			boolean[][] mat = p.possibleMoves();
			if(mat[kingPosition.getRow()][kingPosition.getColumn()]){
				return true;
			}
		}
		//Se for esgotada a lista e não encontrar nada então é falso
		return false;
	}
	
	private boolean testCheckMate(Color color) {
		if (!testCheck(color)) {
			return false;
		}
		List<Piece> list = piecesOnTheBoard.stream().filter(x -> 
		((ChessPiece)x).getColor() == color).collect(Collectors.toList());
		for (Piece p : list) {
			boolean[][] mat = p.possibleMoves();
			for (int i=0; i<board.getRows(); i++) {
				for (int j=0; j<board.getColumns(); j++) {
					if (mat[i][j]) {
						Position source = ((ChessPiece)p).getChessPosition().toPosition();
						Position target = new Position(i, j);
						Piece capturedPiece = makeMove(source, target);
						boolean testCheck = testCheck(color);
						undoMove(source, target, capturedPiece);
						if (!testCheck) {
							return false;
						}
					}
				}
			}
		}
		return true;
	}	
	
	
	//Método que vai imprimir  e inicializar no tabuleiro as peças Torre e King
	private void initialSetup() {
		//Código que vai dar um checkMate de cara
		 	placeNewPiece('a', 1, new Rook(board, Color.WHITE));
			placeNewPiece('b', 1, new Knight(board, Color.WHITE));
		 	placeNewPiece('c', 1, new Bishop(board, Color.WHITE));
		 	placeNewPiece('d', 1, new Queen(board, Color.WHITE));
	        placeNewPiece('e', 1, new King(board, Color.WHITE, this));
	        placeNewPiece('f', 1, new Bishop(board, Color.WHITE));
	        placeNewPiece('g', 1, new Knight(board, Color.WHITE));
	        placeNewPiece('h', 1, new Rook(board, Color.WHITE));
	        placeNewPiece('a', 2, new Pawn(board, Color.WHITE));
	        placeNewPiece('b', 2, new Pawn(board, Color.WHITE));
	        placeNewPiece('c', 2, new Pawn(board, Color.WHITE));
	        placeNewPiece('d', 2, new Pawn(board, Color.WHITE));
	        placeNewPiece('e', 2, new Pawn(board, Color.WHITE));
	        placeNewPiece('f', 2, new Pawn(board, Color.WHITE));
	        placeNewPiece('g', 2, new Pawn(board, Color.WHITE));
	        placeNewPiece('h', 2, new Pawn(board, Color.WHITE));
	        
	        placeNewPiece('a', 8, new Rook(board, Color.BLACK));
	        placeNewPiece('b', 8, new Knight(board, Color.BLACK));
	        placeNewPiece('c', 8, new Bishop(board, Color.BLACK));
	        placeNewPiece('d', 8, new Queen(board, Color.BLACK));
	        placeNewPiece('e', 8, new King(board, Color.BLACK, this));
	        placeNewPiece('f', 8, new Bishop(board, Color.BLACK));
	        placeNewPiece('g', 8, new Knight(board, Color.BLACK));
	        placeNewPiece('h', 8, new Rook(board, Color.BLACK));
	        placeNewPiece('a', 7, new Pawn(board, Color.BLACK));
	        placeNewPiece('b', 7, new Pawn(board, Color.BLACK));
	        placeNewPiece('c', 7, new Pawn(board, Color.BLACK));
	        placeNewPiece('d', 7, new Pawn(board, Color.BLACK));
	        placeNewPiece('e', 7, new Pawn(board, Color.BLACK));
	        placeNewPiece('f', 7, new Pawn(board, Color.BLACK));
	        placeNewPiece('g', 7, new Pawn(board, Color.BLACK));
	        placeNewPiece('h', 7, new Pawn(board, Color.BLACK));
//		 	placeNewPiece('h', 7, new Rook(board, Color.WHITE));
//	        placeNewPiece('d', 1, new Rook(board, Color.WHITE));
//	        placeNewPiece('e', 1, new King(board, Color.WHITE));
//	        placeNewPiece('e', 1, new Rook(board, Color.WHITE));
//	        placeNewPiece('d', 1, new King(board, Color.WHITE));

//	        placeNewPiece('b', 8, new Rook(board, Color.BLACK));
//	        placeNewPiece('a', 8, new King(board, Color.BLACK));
//	        placeNewPiece('d', 7, new Rook(board, Color.BLACK));
//	        placeNewPiece('e', 7, new Rook(board, Color.BLACK));
//	        placeNewPiece('e', 8, new Rook(board, Color.BLACK));
//	        placeNewPiece('d', 8, new King(board, Color.BLACK));
	}
	
	
	
	

}
