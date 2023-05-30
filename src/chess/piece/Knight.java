package chess.piece;

import boardgame.Board;
import boardgame.Position;
import chess.ChessPiece;
import chess.Color;

public class Knight extends ChessPiece{
	
	public Knight(Board board, Color color) {
		super(board, color);
	}
	
	
	//Método que vai imprimir no tabuleiro a peça King
	@Override
	public String toString() {
		return "N";
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
		
		p.setValues(position.getRow() - 1, position.getColumn() - 2);
		if(getBoard().positionExits(p)&& canMove(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		
		p.setValues(position.getRow() - 2, position.getColumn() - 1);
		if(getBoard().positionExits(p)&& canMove(p)) {
		mat[p.getRow()][p.getColumn()] = true;
		}
		
		p.setValues(position.getRow() - 2, position.getColumn() + 1);
		if(getBoard().positionExits(p)&& canMove(p)) {
		mat[p.getRow()][p.getColumn()] = true;
		}
		
		p.setValues(position.getRow() - 1, position.getColumn() + 2);
		if(getBoard().positionExits(p)&& canMove(p)) {
		mat[p.getRow()][p.getColumn()] = true;
		}
		

		p.setValues(position.getRow() + 1, position.getColumn() + 2);
		if(getBoard().positionExits(p)&& canMove(p)) {
		mat[p.getRow()][p.getColumn()] = true;
		}
		
		p.setValues(position.getRow() + 2, position.getColumn() + 1);
		if(getBoard().positionExits(p)&& canMove(p)) {
		mat[p.getRow()][p.getColumn()] = true;
		}
		
	
		p.setValues(position.getRow() + 2, position.getColumn() - 1);
		if(getBoard().positionExits(p)&& canMove(p)) {
		mat[p.getRow()][p.getColumn()] = true;
		}
		
		p.setValues(position.getRow() + 1, position.getColumn() - 2);
		if(getBoard().positionExits(p)&& canMove(p)) {
		mat[p.getRow()][p.getColumn()] = true;
				}
		return mat;
	}

}
