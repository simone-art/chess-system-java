package chess.piece;

import boardgame.Board;
import boardgame.Position;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.Color;

public class King extends ChessPiece{
	
	// Associação entre as classes King e ChessMatch
	// é uma dependência entre a partida e o Rei
	private ChessMatch chessMatch;

	//Para associar od s dois, a classe ChessMatch deve estar no construtor
	public King(Board board, Color color, ChessMatch chessMatch) {
		super(board, color);
		this.chessMatch = chessMatch;
	}
	
	
	//Método que vai imprimir no tabuleiro a peça King
	@Override
	public String toString() {
		return "K";
	}
	
	//Método que vai testar se a torre está pronto para o movimento Roque
	private boolean testRookCastling(Position position) {
		ChessPiece p = (ChessPiece)getBoard().piece(position);
		return p != null && p instanceof Rook && p.getColor() == getColor()
				&& p.getMoveCount() == 0;
		
	}
   
	//Método que valida se a peça pode-se mover
	private boolean canMove(Position position) {
		ChessPiece p = (ChessPiece)getBoard().piece(position);
		//a casa está vazia ou tem uma peça adversária
		return p == null || p.getColor() != getColor();
		
	}

	@Override
	public boolean[][] possibleMoves() {
		//Criada matriz de booleanos com a dimensão do tabuleiro
		//Por padrão todas as posições da matriz começam com false
		boolean[][] mat = new boolean [getBoard().getRows()] [getBoard().getColumns()];
		
		Position p = new Position(0,0);
		
		//above - acima
		p.setValues(position.getRow() - 1, position.getColumn());
		if(getBoard().positionExits(p)&& canMove(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		
		//below - abaixo
		p.setValues(position.getRow() + 1, position.getColumn());
		if(getBoard().positionExits(p)&& canMove(p)) {
		mat[p.getRow()][p.getColumn()] = true;
		}
		
		//left - esquerda
		p.setValues(position.getRow(), position.getColumn() - 1);
		if(getBoard().positionExits(p)&& canMove(p)) {
		mat[p.getRow()][p.getColumn()] = true;
		}
		
		//rigth - direita
		p.setValues(position.getRow(), position.getColumn() + 1);
		if(getBoard().positionExits(p)&& canMove(p)) {
		mat[p.getRow()][p.getColumn()] = true;
		}
		
		//diagonal noroeste nw
		p.setValues(position.getRow() - 1, position.getColumn() - 1);
		if(getBoard().positionExits(p)&& canMove(p)) {
		mat[p.getRow()][p.getColumn()] = true;
		}
		
		//diagonal nordeste ne
		p.setValues(position.getRow() - 1, position.getColumn() + 1);
		if(getBoard().positionExits(p)&& canMove(p)) {
		mat[p.getRow()][p.getColumn()] = true;
		}
		
		//diagonal suleste se
		p.setValues(position.getRow() + 1, position.getColumn() - 1);
		if(getBoard().positionExits(p)&& canMove(p)) {
		mat[p.getRow()][p.getColumn()] = true;
		}
		
		//diagonal suldeste sd
		p.setValues(position.getRow() + 1, position.getColumn() + 1);
		if(getBoard().positionExits(p)&& canMove(p)) {
		mat[p.getRow()][p.getColumn()] = true;
		
	    }
		
		// #specialmove castling
				if (getMoveCount() == 0 && !chessMatch.getCheck()) {
					// #specialmove castling kingside rook Roque pequeno
					Position posT1 = new Position(position.getRow(), position.getColumn() + 3);
					if (testRookCastling(posT1)) {
						Position p1 = new Position(position.getRow(), position.getColumn() + 1);
						Position p2 = new Position(position.getRow(), position.getColumn() + 2);
						if (getBoard().piece(p1) == null && getBoard().piece(p2) == null) {
							mat[position.getRow()][position.getColumn() + 2] = true;
						}
					}
					// #specialmove castling queenside rook Roque grande
					Position posT2 = new Position(position.getRow(), position.getColumn() - 4);
					if (testRookCastling(posT2)) {
						//Código que verifica que tem vagas para mover o Rei para a esquerda
						Position p1 = new Position(position.getRow(), position.getColumn() - 1);
						Position p2 = new Position(position.getRow(), position.getColumn() - 2);
						Position p3 = new Position(position.getRow(), position.getColumn() - 3);
						if (getBoard().piece(p1) == null && getBoard().piece(p2) == null && getBoard().piece(p3) == null) {
							mat[position.getRow()][position.getColumn() - 2] = true;
						}
					}
				}

		return mat;
	}

}
