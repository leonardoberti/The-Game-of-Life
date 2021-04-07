package TheGameOfLifePackage;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class Cell extends JButton implements ActionListener{
	public boolean isAlive;
	public int generationsAlive;
	private Board currentBoard;
	
	public Cell(Board b) {
		currentBoard = b;
		generationsAlive = 0;
		setAlive(false);
		this.setBorder(BorderFactory.createLineBorder(Color.gray));
		this.addActionListener(this);
	}

	public boolean getAlive() {
		return isAlive;
	}

	public void setAlive(boolean isAlive) {
		this.isAlive = isAlive;
		colorCell();
	}
	
	public void colorCell() {
		if(isAlive) {
			this.setBackground(currentBoard.getForeground());
		}else
			this.setBackground(currentBoard.getBackground());
	}

	
	
	

	@Override
	public void actionPerformed(ActionEvent e) {
		setAlive(!isAlive);
	}
}
