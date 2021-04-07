package TheGameOfLifePackage;

public class TheGameOfLife {
		
	public static void main(String[] args) {
		Board board = new Board(20);
		play(board);
	}

	public static void play(Board b) {
		int sizeBoard = b.size;
		boolean[][] booleanMatrixBoard = b.booleanBoard();
		while(true) {
			sizeBoard = b.size;
			System.out.print("");
			if(b.running) {
				booleanMatrixBoard = b.booleanBoard();
				int n = 0;
				try {
					for(int i=0; i<sizeBoard; i++) {
						for(int j=0; j<sizeBoard; j++) {
							n = b.countNeighborsAlive(i, j);
							if(b.board[i][j].isAlive && (n < 2 || n>3)) {
								booleanMatrixBoard[i][j] = false;	
							}else if(!b.board[i][j].isAlive && n==3) {
								booleanMatrixBoard[i][j] = true;
							}
						}
					}
					for(int i=0; i<sizeBoard; i++) {
						for(int j=0; j<sizeBoard; j++) {
							b.board[i][j].setAlive(booleanMatrixBoard[i][j]);
						}
						Thread.sleep(2);
						System.out.println(i+"-esima riga ok");
					}
					b.NumberGeneration++;
					System.out.println(b.NumberGeneration);
					b.generation.setText("Generation: " + b.NumberGeneration);
					Thread.sleep(1000/b.framePerSecond);
				}catch (InterruptedException ie) {
					//implementa errore
					// authority 
				}
			}
		}
	}
}
