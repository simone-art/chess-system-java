package boardgame;

public class Board {
	
	private int rows;
	private int columns;
	
	//Matriz de peças
	private Piece [][] pieces;

	// Constructor
	public Board(int rows, int columns) {
		super();
		//Fazendo a programação defensiva
		if(rows < 1 || columns < 1) {
			throw new BoardException("Error creating board: There must be at least 1 row and 1 colum");
		}
		this.rows = rows;
		this.columns = columns;
		pieces = new Piece[rows][columns];
	}

	public int getRows() {
		return rows;
	}

	
	public int getColumns() {
		return columns;
	}

	
	//Método que retorna a peça
	public Piece piece(int row, int column) {
		//Programação defensiva
		if(!positionExits(row, column)) {
			throw new BoardException("Position not on the board");
		}
		return pieces[row][column];
	}
	
	//Método que remove a peça do tabuleiro(ela não tem posição mais)
		public Piece removePiece(Position position) {
			if(!positionExits(position)) {
				throw new BoardException("Position not on the board");
			}
			if(piece(position) == null) {
				return null;
			}
			//declarada variável aux do tipo peça
			Piece aux = piece(position);
			aux.position = null;
			pieces[position.getRow()][position.getColumn()] = null;
			//Variável aux contém a peça que foi retirada
			return aux;
			
		}
	
	//Sobrecarga do método que retorna a peçã pela posição
	public Piece piece(Position position) {
		//Programação defensiva
		if(!positionExits(position)) {
			throw new BoardException("Position not on the board");
		}
		return pieces[position.getRow()] [position.getColumn()];
	}
	
	// O position aqui pode ser accessado porque ele foi nomeado
	//como atributo de referència como protected por estar no mesmo pacote
	// assim eu consigo acessar livremente a posição da peça.
	public void placePiece(Piece piece, Position position) {
		//Programação defensiva
		if(thereIsAPiece(position)) {
			throw new BoardException("There is already a position" + position);
		}
		pieces[position.getRow()][position.getColumn()] = piece;
		piece.position = position;
	}
	
	//Método auxiliar que valida a existencia da posição no tabuleiro
	private boolean positionExits(int row, int column) {
		return row >= 0 && row < rows && column >= 0 && column < columns;
		
	}
	
	public boolean positionExits(Position position) {
		return positionExits(position.getRow(), position.getColumn());
	}
	
	public boolean thereIsAPiece(Position position) {
		if(!positionExits(position)) {
			throw new BoardException("Position not on the board");
		}
		return piece(position) != null;
		
	}
	
	

}
