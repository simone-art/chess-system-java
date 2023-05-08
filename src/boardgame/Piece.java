package boardgame;

public class Piece {
	
	//O atributo de referência de classe Position será protected para não ser 
	//Visivel para outras classes
	
	protected Position position;
	private Board board;
	
	//Constructor somente com o board porque 
	public Piece(Board board) {
		this.board = board;
		//position igual a null é uma peça recem criada no tabuleiro
		position = null;
	}

	// O tabuleiro não vai ser accesível por outras classes
	//protected é para uso interno
	protected Board getBoard() {
		return board;
	}


	
	
	
	
	
	

}
