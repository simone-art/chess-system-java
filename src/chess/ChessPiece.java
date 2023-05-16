package chess;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;

public abstract class ChessPiece extends Piece{
	

	private Color color;
	
	//Este construtor tem que ser feito porque a classe Piece
	//Que é a classe pai tem um constructor, e como ela extends tem
	// que ser incluída
	public ChessPiece(Board board, Color color) {
		super(board);
		// TODO Auto-generated constructor stub
		this.color = color;
	}

	//Só foi criado o get porque não queremos que o color seja alterado,
	//Somente acessado
	public Color getColor() {
		return color;
	}
	
	//Método protegido para somente ser acessado pela subclasse e mesmo pacote
	protected boolean isThereOpponentPiece(Position position) {
		//(ChessPiece)getBoard() isto é um downCasting
		ChessPiece p = (ChessPiece)getBoard().piece(position);
			return p != null && p.getColor() != color;
	}


	

}
