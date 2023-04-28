package boardgame;

public class Position {
	
	//Uso do encapsulamento
	private int row;
	private int column;
	
	//Constructores com parâmetros
	public Position(int row, int column) {
		this.row = row;
		this.column = column;
	}

	//Getters e setters
	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getColumn() {
		return column;
	}

	public void setColum(int column) {
		this.column = column;
	}
	
	//To string para imprimir a posição na tela
	// Este sería o conceito de sobreposição porque estamos sobreescrevendo um 
	//método que pertence a classe Object do java
	@Override
	public String toString() {
		return row + ", " + column;
	}
	
	

}
