package chess.piece;

import boardgame.Board;
import boardgame.Position;
import chess.ChessPiece;
import chess.Color;

public class Queen extends ChessPiece {

	public Queen(Board board, Color color) {
		super(board, color);

	}

	// Método que vai imprimir no tabuleiro a peça Torre
	@Override
	public String toString() {
		return "Q";
	}

	@Override
	public boolean[][] possibleMoves() {

		boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];
		Position p = new Position(0, 0);

		p.setValues(position.getRow() - 1, position.getColumn());

		while (getBoard().positionExits(p) && !getBoard().thereIsAPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true;
			p.setRow(p.getRow() - 1);
		}
		if (getBoard().positionExits(p) && isThereOpponentPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}

		// Código que faz a peça ir para a esquerda (left)
		p.setValues(position.getRow(), position.getColumn() - 1);
		// O while vai existir enquanto estiver casas vazias
		while (getBoard().positionExits(p) && !getBoard().thereIsAPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true;
			p.setColumn(p.getColumn() - 1);
		}
		if (getBoard().positionExits(p) && isThereOpponentPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}

		// Código que faz a peça ir para a direita (right)
		p.setValues(position.getRow(), position.getColumn() + 1);
		// O while vai existir enquanto estiver casas vazias
		while (getBoard().positionExits(p) && !getBoard().thereIsAPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true;
			p.setColumn(p.getColumn() + 1);
		}
		if (getBoard().positionExits(p) && isThereOpponentPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}

		// Código que faz a peça ir para abaixo (below)
		p.setValues(position.getRow() + 1, position.getColumn());
		// O while vai existir enquanto estiver casas vazias
		while (getBoard().positionExits(p) && !getBoard().thereIsAPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true;
			p.setRow(p.getRow() + 1);
		}
		if (getBoard().positionExits(p) && isThereOpponentPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}

		// Noroeste
		p.setValues(position.getRow() - 1, position.getColumn() - 1);
		// O while vai existir enquanto estiver casas vazias
		while (getBoard().positionExits(p) && !getBoard().thereIsAPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true;
			p.setValues(p.getRow() - 1, p.getColumn() - 1);
		}
		if (getBoard().positionExits(p) && isThereOpponentPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}

		// Código que faz a peça ir para o nordeste ne
		p.setValues(position.getRow() - 1, position.getColumn() + 1);
		// O while vai existir enquanto estiver casas vazias
		while (getBoard().positionExits(p) && !getBoard().thereIsAPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true;
			p.setValues(p.getRow() - 1, p.getColumn() + 1);
		}
		if (getBoard().positionExits(p) && isThereOpponentPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}

		// Código que faz a peça ir para o sudeste se
		p.setValues(position.getRow() + 1, position.getColumn() + 1);
		// O while vai existir enquanto estiver casas vazias
		while (getBoard().positionExits(p) && !getBoard().thereIsAPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true;
			p.setValues(p.getRow() + 1, p.getColumn() + 1);
		}
		if (getBoard().positionExits(p) && isThereOpponentPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}

		// Código que faz a peça ir para sudeste sudoeste sw
		p.setValues(position.getRow() + 1, position.getColumn() - 1);
		// O while vai existir enquanto estiver casas vazias
		while (getBoard().positionExits(p) && !getBoard().thereIsAPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true;
			p.setValues(p.getRow() + 1, p.getColumn() - 1);
		}
		if (getBoard().positionExits(p) && isThereOpponentPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}

		return mat;
	}

}
