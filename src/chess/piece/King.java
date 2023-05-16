package chess.piece;

import boardgame.Board;
import boardgame.Position;
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
		return mat;
	}

}
