package TheGameOfLifePackage;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Board extends JFrame implements ActionListener, ChangeListener{

	public JPanel titlePanel = new JPanel();
	public JPanel boardPanel = new JPanel();
	public JPanel buttonsPanel = new JPanel();
	public JPanel buttons = new JPanel();
	public JPanel info = new JPanel();
	public static Cell[][] board;
	public JLabel title = new JLabel("The Game of Life - Berti Leonardo");
	public JButton startAndStop = new JButton("START");
	public JButton rules = new JButton("RULES");
	public JButton clear = new JButton("CLEAR");
	public JButton settings = new JButton("SETTINGS");
	public JLabel framerateLabel = new JLabel("Frame per second: ");
	public JSlider framerate = new JSlider(JSlider.HORIZONTAL, 1, 10, 1);
	public static JLabel generation = new JLabel("Generation: 0");
	public boolean running; 
	public int NumberGeneration;
	public int size;
	public int framePerSecond;
	public Color background = Color.BLACK;
	public Color foreground = Color.YELLOW;
	
	
	public Board(int i) {
		super("The Game of Life - Berti Leonardo");
		size = i;
		NumberGeneration = 0;
		board = new Cell[size][size];
		setSize(800, 700); 
		setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        initComponent();
        setColors(Color.BLACK, Color.YELLOW);
        setVisible(true);
        this.running = false;
        framePerSecond = 1;
	}
	
	public Board(int i, Color fore, Color back, int gen) {
		super("The Game of Life - Berti Leonardo");
		size = i;
		board = new Cell[size][size];
		setSize(800, 700); 
		setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        initComponent();
        setColors(back, fore);
        setVisible(true);
        this.running = false;
        framePerSecond = 1;
        NumberGeneration = gen;
	}
	public void setBoard() {
		boardPanel = new JPanel();
		boardPanel.setLayout(new GridLayout(size,size));
		for(int i=0; i<size; i++) {
			for(int j=0; j<size; j++) {
				board[i][j] = new Cell(this);
				boardPanel.add(board[i][j]);
			}
		}
	}
	
	public void setSize(int newSize) {
		size = newSize;
		setBoard();
	}
	public Color getBackground() {
		return background;
	}
	public Color getForeground() {
		return foreground;
	}
	
	public void setColors(Color back, Color fore) {
		this.background = back;
		this.foreground = fore;
		colorComponent();
	}
	
	private void colorComponent() {
		rules.setForeground(foreground);
		rules.setBackground(background);
		rules.setBorder(BorderFactory.createLineBorder(foreground));
		
		startAndStop.setForeground(foreground);
        startAndStop.setBackground(background);
        startAndStop.setBorder(BorderFactory.createLineBorder(foreground));
        
        clear.setBackground(background);
        clear.setForeground(foreground);
        clear.setBorder(BorderFactory.createLineBorder(foreground));
        
        settings.setBackground(background);
        settings.setForeground(foreground);
        settings.setBorder(BorderFactory.createLineBorder(foreground));
        
        framerateLabel.setForeground(foreground);
        framerate.setBackground(background);
        framerate.setForeground(foreground);
        
        generation.setForeground(foreground);
        generation.setBackground(background);

		title.setForeground(foreground);
		titlePanel.setBackground(background);
		
		buttons.setBackground(background);
		info.setBackground(background);
		boardPanel.setBackground(background);

		for(int i=0; i<size; i++) {
			for(int j=0; j<size; j++) {
				board[i][j].setAlive(board[i][j].getAlive());
			}
		}
	}
	
	private void initComponent() {
		rules.setPreferredSize(new Dimension(100,40));
        rules.setFont(new Font("Helvetica", Font.BOLD, 20));
        rules.addActionListener(this);
        
        startAndStop.setPreferredSize(new Dimension(100,40));
        startAndStop.setFont(new Font("Helvetica", Font.BOLD, 20));
        startAndStop.addActionListener(this);
        
        clear.addActionListener(this);
        clear.setFont(new Font("Helvetica", Font.BOLD, 20));
        
        settings.setFont(new Font("Helvetica", Font.BOLD, 20));
        settings.addActionListener(this);

        //framerateLabel.setPreferredSize(new Dimension(120,40));
        
        framerateLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        framerateLabel.setFont(new Font("Helvetica", Font.ROMAN_BASELINE, 20));
        framerate.setMajorTickSpacing(1);
        framerate.setPaintTicks(true);
        framerate.setPaintLabels(true);
        framerate.addChangeListener(this);
        
        generation.setFont(new Font("Helvetica", Font.ROMAN_BASELINE, 20));
        generation.setHorizontalAlignment(SwingConstants.CENTER);
        
		
		title.setFont(new Font("Helvetica", Font.ITALIC, 35));
		titlePanel.setLayout(new FlowLayout());
		titlePanel.add(title);
		
		buttonsPanel.setLayout(new GridLayout(2,1));
		buttons.setLayout(new GridLayout(1,4));
		info.setLayout(new GridLayout(1,3));
		
		buttons.add(rules);
		buttons.add(startAndStop);
		buttons.add(clear);
		buttons.add(settings);
		
		info.add(generation);
		info.add(framerateLabel);
		info.add(framerate);
		
		buttonsPanel.add(buttons);
		buttonsPanel.add(info);

		//boardPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		//System.out.println(size);
		setBoard();
		
		add(titlePanel, BorderLayout.NORTH);
		add(boardPanel, BorderLayout.CENTER);
		add(buttonsPanel, BorderLayout.SOUTH);
	}
	
	
	public int countNeighborsAlive(int row, int column) {
		int count = 0;
		for(int i=row-1; i<row+2; i++) {
			for(int j=column-1; j<column+2; j++) {
				
				if(indexesCorrected(i, j) && (i!=row || j!=column)) {
					if(board[i][j].isAlive) {
						count++;
					}
				}
			}
			
		}
		return count;
	}
	
	private boolean indexesCorrected(int row, int column) {
		if((row>=0 && row<size) && (column>=0 && column<size)) {
			return true;
		}
		return false;
	}

	public boolean[][] booleanBoard() {
		boolean[][] m = new boolean[size][size];
		for(int i=0; i<size; i++) {
			for(int j=0; j<size; j++) {
					if(board[i][j].isAlive){ 
						m[i][j]=true;
					}else {
						m[i][j]=false;
					}
			}
		}
		return m;
	}
	
	private void disableBoard(boolean running) {
		for(int i=0; i<size; i++) {
			for(int j=0; j<size; j++) {
				if(running)
					board[i][j].setEnabled(false);
				else
					board[i][j].setEnabled(true);
			}
		}
	}
	
	private void clearBoard() {
		for(int i=0; i<size; i++) {
			for(int j=0; j<size; j++) {
				board[i][j].setAlive(false);
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==startAndStop) {
			running=!running;
			if(running) {
				startAndStop.setText("PAUSE");
			}else {
				startAndStop.setText("START");
			}
			
			disableBoard(running);
		}
		if(e.getSource()==clear) {
			if(!running) {
				clearBoard();
				NumberGeneration = 0;
				generation.setText("Generation: " + NumberGeneration);
			}
		}
		if(e.getSource()==rules) {
			if(!running) {
				new Rule(this);
			}
		}
		if(e.getSource()==settings) {
			if(!running) {
				new Settings(this);
			}
		}
	}

	
	@Override
	public void stateChanged(ChangeEvent e) {
		framePerSecond = ((JSlider)e.getSource()).getValue();
	}

}
