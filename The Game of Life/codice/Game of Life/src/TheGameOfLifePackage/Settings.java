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
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class Settings extends JFrame implements ActionListener{

	private Board currentBoard;
	public JLabel title = new JLabel("Settings");
	public JPanel titlePanel = new JPanel();
	public JPanel buttonsPanel = new JPanel();
	public JLabel dimension = new JLabel("Dimension: ");
	public JTextField dimField = new JTextField(5);
	private Color[] color = {Color.BLUE, Color.GREEN, Color.RED, Color.WHITE, Color.YELLOW, Color.BLACK};
	private String[] colorName = {"Blue", "Green", "Red", "White", "Yellow", "Black"} ;
	public JLabel backField = new JLabel("Background color: ");
	public JLabel foreField = new JLabel("Foreground color: ");
	public JComboBox<String> backMenu = new JComboBox<>();
	public JComboBox<String> foreMenu = new JComboBox<>();
	
	public JPanel framePanel = new JPanel();
	public JPanel settingsPanel = new JPanel();
	public JButton save = new JButton("SAVE");
	public JButton exit = new JButton("EXIT");
	
	public Settings(Board b) {
		super("The Game of Life - Settings");
		this.currentBoard = b;
		setSize(300, 180); 
		setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        initComponent();
        setUndecorated(true);
        setVisible(true);
        
	}

	private void setColor(Color background, Color foreground) {
		titlePanel.setBackground(background);
		title.setForeground(foreground);
		
		backMenu.setBackground(background);
		backMenu.setForeground(foreground);
		foreMenu.setForeground(foreground);
		foreMenu.setBackground(background);
		settingsPanel.setBackground(background);
		dimension.setForeground(foreground);
		backField.setForeground(foreground);
		backMenu.setBorder(BorderFactory.createLineBorder(foreground));
		foreField.setForeground(foreground);
		
		save.setBackground(background);
        save.setForeground(foreground);
        exit.setBackground(background);
        exit.setForeground(foreground);
        save.setBorder(BorderFactory.createLineBorder(foreground));
        exit.setBorder(BorderFactory.createLineBorder(foreground));
        buttonsPanel.setBackground(background);
	}
	private void initComponent() {
		framePanel.setLayout(new FlowLayout());

		titlePanel.add(title);
		title.setFont(new Font("Helvetica", Font.ITALIC, 32));
		
		for(int i=0; i<colorName.length; i++) {
			backMenu.addItem(colorName[i]);
			foreMenu.addItem(colorName[i]);
		}
		
		
		settingsPanel.setLayout(new GridLayout(3, 2));
		dimension.setHorizontalAlignment(SwingConstants.RIGHT);
		backField.setHorizontalAlignment(SwingConstants.RIGHT);
		foreField.setHorizontalAlignment(SwingConstants.RIGHT);
		
		dimension.setFont(new Font("Helvetica", Font.ROMAN_BASELINE, 17));
		settingsPanel.add(dimension);
		settingsPanel.add(dimField);
		backField.setFont(new Font("Helvetica", Font.ROMAN_BASELINE, 17));
		
		settingsPanel.add(backField);
		
		
		settingsPanel.add(backMenu);
		
		foreField.setFont(new Font("Helvetica", Font.ROMAN_BASELINE, 17));
		settingsPanel.add(foreField);
		settingsPanel.add(foreMenu);
		
		save.addActionListener(this);
		exit.addActionListener(this);
		save.setPreferredSize(new Dimension(100,40));
		exit.setPreferredSize(new Dimension(100,40));
		
        save.setFont(new Font("Helvetica", Font.BOLD, 17));
        exit.setFont(new Font("Helvetica", Font.BOLD, 17));
        
		buttonsPanel.add(save);
		buttonsPanel.add(exit);
		
		setColor(currentBoard.getBackground(), currentBoard.getForeground());
		add(titlePanel, BorderLayout.NORTH);
		add(settingsPanel, BorderLayout.CENTER);
		add(buttonsPanel, BorderLayout.SOUTH);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==save) {
			int i = backMenu.getSelectedIndex();
			int j = foreMenu.getSelectedIndex();
			if(i!=j) {
				setColor(color[i], color[j]);
				currentBoard.setColors(color[i], color[j]);
			}
			//int newDimension = Integer.parseInt(dimField.getText());
			//currentBoard.dispose();
			//currentBoard = new Board(newDimension, color[j], color[i], currentBoard.NumberGeneration);
						
		}
		if(e.getSource()==exit) {
			dispose();
		}
		
	}

}
