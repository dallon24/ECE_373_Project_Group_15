package org.Final_Project.Game;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.io.Serializable;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.script.ScriptException;
import javax.swing.*;

import org.Final_Project.Deck.Card;
import org.Final_Project.Players.Player24;



//public class Game24GUI extends JFrame implements Serializable{
//	
//
//}
public class Game24GUI extends JFrame {
	public final int WIDTH24 = 960;
	public final int HEIGHT24 = WIDTH24*9/12;
	private JButton play24Button;
	private JButton instruction24Button;
	private JButton exit24Button;
	private Game24 game24;
	String equation = "";
	StringBuilder equationv2 = new StringBuilder();
	private boolean round = false;
	boolean cardOp = false;
	private Card a;
	private Card b;
	private Card c;
	private Card d;
	private boolean usedA = false;
	private boolean usedB = false;
	private boolean usedC = false;
	private boolean usedD = false;

	
    public Game24GUI(String name) {
		//super(string);
    	super(name);
    	game24 = new Game24();
    	
		setSize(WIDTH24, HEIGHT24);
		setLayout(new GridLayout(2,1));
		getContentPane().setBackground(Color.BLACK);
		

		BufferedImage image = null;
		try {
			image = ImageIO.read(new File("images/24.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        BufferedImage ret = new BufferedImage(WIDTH24,HEIGHT24,BufferedImage.TYPE_INT_RGB);
        ret.getGraphics().drawImage(image,0,50,WIDTH24,HEIGHT24,null);
        ImageIcon iii = new ImageIcon(ret);
        JLabel label = new JLabel(iii);
		add(label);
		
		
		JPanel panel = new JPanel(new GridLayout(3,1));
		
		
		
		play24Button = new JButton("Play"); //Play button to start the  game
		play24Button.setBackground(Color.BLACK);
		play24Button.setFont(new Font("Serif", Font.BOLD, 24));
		
		instruction24Button = new JButton("Instructions"); // Instructions button that will open a window that will show all the instructions
		instruction24Button.setBackground(Color.BLACK);
		instruction24Button.setFont(new Font("Serif", Font.BOLD, 24));
		
		exit24Button = new JButton("Exit");  // Exit button will close the UNO GUI window
		exit24Button.setBackground(Color.BLACK);
		exit24Button.setFont(new Font("Serif", Font.BOLD, 24));
		
		// Adding the buttons to the panel
		panel.add(play24Button);
		panel.add(instruction24Button);
		panel.add(exit24Button);
		
		add(panel); // adding panel to JFrame
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null); // Positioning the JFrame and opening the window
		setVisible(true);
		
		play24Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false); 
				dispose();
				enterPlayerNamesWindow();
			}
		});
		
		instruction24Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				instructionsWindow();
			}
		});
		
		exit24Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				dispose();
			}
		});

    }
    public void instructionsWindow() {
    	ByteArrayOutputStream hi = new ByteArrayOutputStream();
		PrintStream Jtext = new PrintStream(hi);
		System.setOut(Jtext);
		game24.printInstruction();
		
		JFrame window = new JFrame("24 Instructions");

		JOptionPane.showMessageDialog(window, hi.toString(), "24 Instructions",
				JOptionPane.PLAIN_MESSAGE);
	}
	public void enterPlayerNamesWindow() {
		JFrame window = new JFrame("Enter Players Names");
		window.setSize(600, 300);
		window.setLayout(new GridLayout(3,1));
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setLocationRelativeTo(null);
		
		JLabel player1MessageLabel = new JLabel("Player's Name:");
		JTextField player1NameField = new JTextField(10);

		
		// Creating button
		JButton okButton = new JButton("Ok");
		
		//Creating panel to add all objects to
		JPanel panel = new JPanel(new GridLayout(4,2));
		JPanel panel2 = new JPanel();
		
		// Creating players Name fields
			panel.add(player1MessageLabel);
			panel.add(player1NameField);

		panel2.add(okButton);
		
		//Adding panel of objects to the JFrame window
		window.add(panel);
		window.add(panel2);
		window.setVisible(true); 
		
		//Event handler for OK button
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Player24 newPlayer = new Player24();

				newPlayer.setName(player1NameField.getText());

				game24.getPlayers24().add(newPlayer);

				window.setVisible(false);
				dispose();

				play24(); 
				}
		});
		
	}
	public void errorOrder() {
		
		JFrame window = new JFrame("24 Instructions");

		JOptionPane.showMessageDialog(window, "Invalid card/operation", "24 Instructions",
				JOptionPane.ERROR_MESSAGE);
	}
	public void equationError() {
		
		JFrame window = new JFrame("24 Instructions");

		JOptionPane.showMessageDialog(window, "Please use all the cards", "24 Instructions",
				JOptionPane.ERROR_MESSAGE);
	}

	public void play24() {
		JFrame gameWindow = new JFrame("24");
		gameWindow.setSize(WIDTH24, HEIGHT24);
		gameWindow.setLayout(new GridLayout(5,1));
		gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gameWindow.setLocationRelativeTo(null);
		
		
		JPanel cardPanel = new JPanel(new GridLayout(1, 4));
		if (!round) {
		a = game24.getGame24Deck().DealACard();
		b = game24.getGame24Deck().DealACard();
		c = game24.getGame24Deck().DealACard();
		d = game24.getGame24Deck().DealACard();
		round = true;
		}

		
		JButton buttonA = new JButton(new ImageIcon(new ImageIcon("images/" + a.getValue() + a.getSuit() + ".png").getImage().getScaledInstance(WIDTH24/4, HEIGHT24/5, Image.SCALE_SMOOTH)));
		JButton buttonB = new JButton(new ImageIcon(new ImageIcon("images/" + b.getValue() + b.getSuit() + ".png").getImage().getScaledInstance(WIDTH24/4, HEIGHT24/5, Image.SCALE_SMOOTH)));
		JButton buttonC = new JButton(new ImageIcon(new ImageIcon("images/" + c.getValue() + c.getSuit() + ".png").getImage().getScaledInstance(WIDTH24/4, HEIGHT24/5, Image.SCALE_SMOOTH)));
		JButton buttonD = new JButton(new ImageIcon(new ImageIcon("images/" + d.getValue() + d.getSuit() + ".png").getImage().getScaledInstance(WIDTH24/4, HEIGHT24/5, Image.SCALE_SMOOTH)));
		buttonA.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!cardOp && !usedA) {
				//equation.concat(Integer.toString(a.getValue()));
				equationv2.append(a.getValue());
				//System.out.println(Integer.toString(a.getValue()));

				cardOp = true;
				usedA = true;
				gameWindow.dispose();
				play24();
				}
				else {
					errorOrder();
				}
			}
		});
		//equation.concat("h");
		//System.out.println(equation);
		//equationv2.append("h");
		//System.out.println(equationv2.toString());
		buttonB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!cardOp && !usedB) {
				equationv2.append(b.getValue());

				cardOp = true;
				usedB = true;
				gameWindow.dispose();
				play24();
				}
				else {
					errorOrder();
				}
				
			}
		});
		buttonC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!cardOp && !usedC) {
				equationv2.append(c.getValue());

				cardOp = true;
				usedC = true;
				gameWindow.dispose();
				play24();
				}
				else {
					errorOrder();
				}
			}
		});
		buttonD.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!cardOp && !usedD) {
				equationv2.append(d.getValue());

				cardOp = true;
				usedD = true;
				gameWindow.dispose();
				play24();
				}
				else {
					errorOrder();
				}
			}
		});
		cardPanel.add(buttonA);
		cardPanel.add(buttonB);
		cardPanel.add(buttonC);
		cardPanel.add(buttonD);
		gameWindow.add(cardPanel);
		
		JPanel opPanel = new JPanel(new GridLayout(1, 9));
		for (String s : game24.getAcceptableOperations()) {
			JButton op = new JButton(s);
			op.setFont(new Font("Serif", Font.BOLD, 24));
			op.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (!cardOp && (s != "(" && s != ")")) {
						errorOrder();
					}
					else {
					if (s == "(" || s == ")") {
						
					}
					else {
						game24.getCurrentOperations().add(s);
						cardOp = false;
					}
					equationv2.append(s);
					gameWindow.dispose();
					play24();
					
					}
					
				}
			});
			opPanel.add(op);
		}
		JButton equal = new JButton("=");
		equal.setFont(new Font("Serif", Font.BOLD, 24));
		equal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (game24.getCurrentOperations().size() < 3 || !cardOp) {
					
				}
				try {
					if (game24.check24(equationv2.toString())) {
						game24.getPlayers24().get(0).setScore(game24.calculatePoints(game24.getCurrentOperations()));
						round = false;
						equationv2.setLength(0);
					}
				} catch (ScriptException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				gameWindow.dispose();
				play24();
			}
		});
		opPanel.add(equal);
		gameWindow.add(opPanel);
		
		JPanel equationPanel = new JPanel(new FlowLayout());
		JLabel equationString = new JLabel(equationv2.toString());
		equationString.setFont(new Font("Serif", Font.BOLD, 50));
		equationPanel.add(equationString);
		gameWindow.add(equationPanel);
		
		
		JPanel playerScorePanel = new JPanel(new GridLayout(1,0));
		playerScorePanel.setBackground(Color.DARK_GRAY);
		
		JLabel player1ScoreMessageLabel = new JLabel();

		JLabel player1Score = new JLabel();

		
		player1ScoreMessageLabel.setFont(new Font("Serif", Font.BOLD, 24));
		player1ScoreMessageLabel.setForeground(Color.RED);
		player1Score.setFont(new Font("Serif", Font.BOLD, 24));
		player1Score.setForeground(Color.RED);

		
		player1Score.setText(Integer.toString(game24.getPlayers24().get(0).getPlayerScore()));

		player1ScoreMessageLabel.setText(game24.getPlayers24().get(0).getName() + "'s Score: ");

		playerScorePanel.add(player1ScoreMessageLabel);
		playerScorePanel.add(player1Score);

		gameWindow.add(playerScorePanel);
		
		JPanel options = new JPanel(new GridLayout(2,2));
		JButton redeal = new JButton("Redeal");
		JButton restart = new JButton("Restart");
		JButton returnToGameMenu = new JButton("Return to main menu");
		JButton exit = new JButton("Exit"); 
		redeal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cardOp = false;
				round = false;
				equationv2.setLength(0);
				gameWindow.dispose();
				play24();
			}
		});
		restart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			gameWindow.dispose();
			//game24.
			play24();
			}
		});
		returnToGameMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//return to main menu
			}
		});
		exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gameWindow.dispose();
			}
		});
		options.add(redeal);
		options.add(restart);
		options.add(returnToGameMenu);
		options.add(exit);
		gameWindow.add(options);
		gameWindow.setVisible(true);
		
	}


  

//    public static void main(String[] args) {
//    	
//        //EventQueue.invokeLater(() -> {
//            Game24GUI ex = new Game24GUI("24");
          //  ex.setVisible(true);
        //});
    }
//	private class MenuListener implements ActionListener {
//		public void actionPerformed(ActionEvent e) {
//			JMenuItem source = (JMenuItem) (e.getSource());
//
//			if (source.equals(fileSave)) {
//				handleFileSave();
//			} else if (source.equals(fileLoad)) {
//				handleFileLoad();
//			} else if (source.equals(fileExit)) {
//				handleFileExit();
//			} else if (source.equals(studentsAddCourse)) {
//				handleAddCourse();
//			} else if (source.equals(studentsDropCourse)) {
//				handleDropCourse();
//			} else if (source.equals(studentsPrintSchedule)) {
//				handlePrintSchedule();
//			} else if (source.equals(administratorsPrintAll)) {
//				handlePrintAll();
//			}
//
//		}

    
//}
