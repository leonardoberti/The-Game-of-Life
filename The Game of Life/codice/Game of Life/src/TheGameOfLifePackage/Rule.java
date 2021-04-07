package TheGameOfLifePackage;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Rule extends JFrame implements ActionListener{
	
	public JLabel title = new JLabel("The Game of Life - Rules");
	public JPanel titlePanel = new JPanel();
	public JButton close = new JButton("BACK TO GAME");
	public JPanel buttonPanel = new JPanel();
	public JTextArea rules = new JTextArea();
	public JPanel rulesPanel = new JPanel();
	public Board currentBoard;
	
	public Rule(Board b) {
		super("The Game of Life - Rules");
		setSize(780, 380); 
		setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        setUndecorated(true);
        setVisible(true);
        currentBoard = b;
        initComponent();
        
	}
	
	private void setColors() {
		title.setForeground(currentBoard.getForeground());
		titlePanel.setBackground(currentBoard.getBackground());
		buttonPanel.setBackground(currentBoard.getBackground());
		buttonPanel.setForeground(currentBoard.getForeground());
		close.setBackground(currentBoard.getBackground());
		close.setForeground(currentBoard.getForeground());
		close.setBorder(BorderFactory.createLineBorder(currentBoard.getForeground()));
		rulesPanel.setBackground(currentBoard.getBackground());
		rulesPanel.setForeground(currentBoard.getForeground());
		rules.setForeground(currentBoard.getForeground());
		rules.setBackground(currentBoard.getBackground());
	}
	
	private void initComponent() {
		titlePanel.setLayout(new FlowLayout());
		title.setFont(new Font("Helvetica", Font.ITALIC, 32));
		
		titlePanel.setLayout(new FlowLayout());
		
		titlePanel.add(title);
		
		
		buttonPanel.setLayout(new FlowLayout());
		buttonPanel.add(close);
		
		close.setFont(new Font("Helvetica", Font.BOLD, 20));
		close.addActionListener(this);
        close.setPreferredSize(new Dimension(250,50));
		
		writeRules(rules);
		
		rules.setFont(new Font("Helvetica", Font.ROMAN_BASELINE, 15));
		rules.setWrapStyleWord(true);
		rulesPanel.add(rules);
		rulesPanel.setLayout(new FlowLayout());
		//rulesPanel.setBorder(BorderFactory.createLineBorder(Color.yellow));
		setColors();
		add(titlePanel, BorderLayout.NORTH);
		add(rulesPanel, BorderLayout.CENTER);
		add(buttonPanel, BorderLayout.SOUTH);
		
		
	}

	private void writeRules(JTextArea rules) {
		rules.setText("The game is played on a two-dimensional grid (or board).\n\nEach grid location is either empty or "+
				"populated by a single cell. A location’s neighbors are any cells in the surrounding \neight adjacent "+
				"locations.\n\nThe simulation of starts from an initial state of populated locations (chosen by you) and then progresse "+
				"through time. \n\nThe evolution of the board state is governed by a few simple rules:\n"+
				"\t1. Each populated location with one or zero neighbors dies (from loneliness).\n"+
				"\t2. Each populated location with four or more neighbors dies (from overpopulation).\n"+
				"\t3. Each populated location with two or three neighbors survives.\n"+
				"\t4. Each unpopulated location that becomes populated if it has exactly three populated neighbors.\n"+
				"\t5. All updates are performed simultaneously in parallel.");
		rules.setEditable(false);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==close) {
			this.dispose();
		}
	}

}
