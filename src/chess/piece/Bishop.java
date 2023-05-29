package chess.piece;

import boardgame.Board;
import boardgame.Position;
import chess.ChessPiece;
import chess.Color;

public class Bishop extends ChessPiece{
	public Bishop(Board board, Color color) {
		super(board, color);
		
	}
	
	//Método que vai imprimir no tabuleiro a peça Torre
	@Override
	public String toString() {
		return "B";
	}

	@Override
	public boolean[][] possibleMoves() {
		//Criada matriz de booleanos com a dimensão do tabuleiro
		//Por padrão todas as posições da matriz começam com false
		boolean[][] mat = new boolean [getBoard().getRows()] [getBoard().getColumns()];
		Position p = new Position(0,0);
		
		//position.getRow() -1 linha cima da posiçao da peça
		//Position é a posição da peça
		//Código que faz a peça ir pra cima
		//Noroeste
		p.setValues(position.getRow() -1, position.getColumn()-1);
		//O while vai existir enquanto estiver casas vazias
		while(getBoard().positionExits(p) && !getBoard().thereIsAPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true;
			p.setValues(p.getRow() -1, p.getColumn() -1);
		}
		if(getBoard().positionExits(p) && isThereOpponentPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		
		//Código que faz a peça ir para o nordeste ne
				p.setValues(position.getRow() -1, position.getColumn()+1);
				//O while vai existir enquanto estiver casas vazias
				while(getBoard().positionExits(p) && !getBoard().thereIsAPiece(p)) {
					mat[p.getRow()][p.getColumn()] = true;
					p.setValues(p.getRow() -1, p.getColumn() + 1);
				}
				if(getBoard().positionExits(p) && isThereOpponentPiece(p)) {
					mat[p.getRow()][p.getColumn()] = true;
				}
				
		//Código que faz a peça ir para o sudeste se
				p.setValues(position.getRow() + 1, position.getColumn() + 1);
				//O while vai existir enquanto estiver casas vazias
				while(getBoard().positionExits(p) && !getBoard().thereIsAPiece(p)) {
					mat[p.getRow()][p.getColumn()] = true;
					p.setValues(p.getRow() + 1, p.getColumn() + 1);
				}
				if(getBoard().positionExits(p) && isThereOpponentPiece(p)) {
					mat[p.getRow()][p.getColumn()] = true;
				}
				
				//Código que faz a peça ir para sudeste sudoeste sw
				p.setValues(position.getRow() + 1, position.getColumn() -1);
				//O while vai existir enquanto estiver casas vazias
				while(getBoard().positionExits(p) && !getBoard().thereIsAPiece(p)) {
					mat[p.getRow()][p.getColumn()] = true;
					p.setValues(p.getRow() + 1, p.getColumn() - 1);
				}
				if(getBoard().positionExits(p) && isThereOpponentPiece(p)) {
					mat[p.getRow()][p.getColumn()] = true;
				}
				
		
		return mat;
	}

}
