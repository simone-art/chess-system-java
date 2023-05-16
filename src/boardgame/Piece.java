package boardgame;

public abstract class Piece {
	
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
	
	//Método é abstracto porque a classe Piece e genérica
	public abstract boolean [][] possibleMoves();
	
	//possibleMove é um método concreto que pode ser usado por um abstracto
	//Isto se chama huckMétodo(um método que faz um gancho com uma subclasse concreta)
	public boolean possibleMove(Position position) {
		return possibleMoves()[position.getRow()] [position.getColumn()];
	}
	
	public boolean isThereAnyPossivelMove() {
		boolean[][] mat = possibleMoves();
		for(int i = 0; i < mat.length; i++ ) {
			for(int j = 0; j < mat.length; j++ ) {
				if(mat[i][j]){
					return true;
				}
			}
		}
	return false;

	}
}	

