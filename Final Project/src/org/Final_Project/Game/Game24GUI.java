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
import org.Final_Project.Players.UNOPlayer;


//public class Game24GUI extends JFrame implements Serializable{
//	
//
//}
public class Game24GUI extends JFrame  implements KeyListener{
	public final int WIDTH24 = 960;
	public final int HEIGHT24 = WIDTH24*9/12;
	private JButton play24Button;
	private JButton instruction24Button;
	private JButton exit24Button;
	private ArrayList<Player24> players24;
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
	private boolean playerEntering = false;
	
    public Game24GUI(String name) {
		//super(string);
    	super(name);
    	game24 = new Game24();
    	
		setSize(WIDTH24, HEIGHT24);
		setLayout(new GridLayout(2,1));
		getContentPane().setBackground(Color.BLACK);
		
		//Creating a label for the window tol hold the UNO Main Menu Picture
		//JLabel label = new JLabel(new ImageIcon(new ImageIcon("images/24.jpg").getImage().getScaledInstance(960,640,Image.SCALE_SMOOTH)), JLabel.CENTER);
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
		
		//Creating a panel to hold the buttons in desired positions
		JPanel panel = new JPanel(new GridLayout(3,1));
		
		// Creating the list to hold all the players that will play UNO
		players24 = new ArrayList<Player24>();
		
		play24Button = new JButton("Play"); //Play button to start the UNO game
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
		//uni = univ1;

//		setSize(300, 100);
//
//		setLayout(new FlowLayout(FlowLayout.LEFT));
//
//		add(new JLabel("<HTML><center>Welcome to the University."
//				+ "<BR>Choose an action from the above menus.</center></HTML>"));
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		//buildGUI();
		//setVisible(true);
        //initUI();
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
		
		JLabel player1MessageLabel = new JLabel("Player One's Name:");
		JTextField player1NameField = new JTextField(10);
		JLabel player2MessageLabel = new JLabel("Player Two's Name:");
		JTextField player2NameField = new JTextField(10);
		JLabel player3MessageLabel = new JLabel("Player Three's Name:");
		JTextField player3NameField = new JTextField(10);
		JLabel player4MessageLabel = new JLabel("Player Four's Name:");
		JTextField player4NameField = new JTextField(10);
		
		// Creating button
		JButton okButton = new JButton("Ok");
		
		//Creating panel to add all objects to
		JPanel panel = new JPanel(new GridLayout(4,2));
		JPanel panel2 = new JPanel();
		
		// Creating players Name fields
			panel.add(player1MessageLabel);
			panel.add(player1NameField);
			panel.add(player2MessageLabel);
			panel.add(player2NameField);
			panel.add(player3MessageLabel);
			panel.add(player3NameField);
			panel.add(player4MessageLabel);
			panel.add(player4NameField);

		panel2.add(okButton);
		
		//Adding panel of objects to the JFrame window
		window.add(panel);
		window.add(panel2);
		window.setVisible(true); 
		
		//Event handler for OK button
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			for (int i = 0; i < 4; i++) {
				Player24 newPlayer = new Player24();
				if (i == 0) {
					newPlayer.setName(player1NameField.getText());
				}
				if (i == 1) {
					newPlayer.setName(player2NameField.getText());
				}
				if (i == 2) {
					newPlayer.setName(player3NameField.getText());
				}
				if (i == 3) {
					newPlayer.setName(player4NameField.getText());
				}
				game24.getPlayers24().add(newPlayer);
			}
			window.setVisible(false);
			dispose();
			
			play24(); //START THE GAME!!!!!
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
		if (!playerEntering) {
			setFocusable(true);
		}
		else {
			setFocusable(false);
		}
		
		JPanel cardPanel = new JPanel(new GridLayout(1, 4));
		if (!round) {
		a = game24.getGame24Deck().DealACard();
		b = game24.getGame24Deck().DealACard();
		c = game24.getGame24Deck().DealACard();
		d = game24.getGame24Deck().DealACard();
		round = true;
		}
//		a = game24.getGame24Deck().DealACard();
//		b = game24.getGame24Deck().DealACard();
//		c = game24.getGame24Deck().DealACard();
//		d = 
		
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
				gameWindow.dispose();
				play24();
				cardOp = true;
				usedA = true;
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
				gameWindow.dispose();
				play24();
				cardOp = true;
				usedB = true;
				}
				else {
					errorOrder();
				}
				
			}
		});
		buttonC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!cardOp || !usedC) {
				equationv2.append(c.getValue());
				gameWindow.dispose();
				play24();
				cardOp = true;
				usedC = true;
				}
				else {
					errorOrder();
				}
			}
		});
		buttonD.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!cardOp || !usedD) {
				equationv2.append(d.getValue());
				gameWindow.dispose();
				play24();
				cardOp = true;
				usedD = true;
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
					if (!cardOp) {
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
					if (game24.check24(equation)) {
						game24.calculatePoints(game24.getCurrentOperations());
						round = false;
					}
					else {
						//invalidate player's turn
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
		
		
		
//				JButton cardInPlayersHandPicture = new JButton(new ImageIcon(new ImageIcon("UNO_Card_Pictures\\" + game.getCurrentPlayerToPlay().getUNOPlayersHand().get(i).getName() +
//						".png").getImage().getScaledInstance(150,355,Image.SCALE_SMOOTH)));
//				
//				cardInPlayersHandPicture.setName(game.getCurrentPlayerToPlay().getUNOPlayersHand().get(i).getName() + " " + game.getCurrentPlayerToPlay().getUNOPlayersHand().get(i).getColor()
//						+ " " + Integer.toString(game.getCurrentPlayerToPlay().getUNOPlayersHand().get(i).getValue()));
//				cardInPlayersHandPicture.addActionListener(listener);
//				playerHandPanel.add(cardInPlayersHandPicture);
		
		
		JPanel playerScorePanel = new JPanel(new GridLayout(2, 2));
		playerScorePanel.setBackground(Color.DARK_GRAY);
		
		JLabel player1ScoreMessageLabel = new JLabel();
		JLabel player2ScoreMessageLabel = new JLabel();
		JLabel player3ScoreMessageLabel = new JLabel();
		JLabel player4ScoreMessageLabel = new JLabel();
		JLabel player1Score = new JLabel();
		JLabel player2Score = new JLabel();
		JLabel player3Score = new JLabel();
		JLabel player4Score = new JLabel();
		
		player1ScoreMessageLabel.setFont(new Font("Serif", Font.BOLD, 24));
		player1ScoreMessageLabel.setForeground(Color.RED);
		player1Score.setFont(new Font("Serif", Font.BOLD, 24));
		player1Score.setForeground(Color.RED);
		player2ScoreMessageLabel.setFont(new Font("Serif", Font.BOLD, 24));
		player2ScoreMessageLabel.setForeground(Color.BLUE);
		player2Score.setFont(new Font("Serif", Font.BOLD, 24));
		player2Score.setForeground(Color.BLUE);
		player3ScoreMessageLabel.setFont(new Font("Serif", Font.BOLD, 24));
		player3ScoreMessageLabel.setForeground(Color.GREEN);
		player3Score.setFont(new Font("Serif", Font.BOLD, 24));
		player3Score.setForeground(Color.GREEN);
		player4ScoreMessageLabel.setFont(new Font("Serif", Font.BOLD, 24));
		player4ScoreMessageLabel.setForeground(Color.YELLOW);
		player4Score.setFont(new Font("Serif", Font.BOLD, 24));
		player4Score.setForeground(Color.YELLOW);
		
		player1Score.setText(Integer.toString(game24.getPlayers24().get(0).getPlayerScore()));
		player1Score.setText(Integer.toString(game24.getPlayers24().get(1).getPlayerScore()));
		player2Score.setText(Integer.toString(game24.getPlayers24().get(2).getPlayerScore()));
		player3Score.setText(Integer.toString(game24.getPlayers24().get(3).getPlayerScore()));

		player1ScoreMessageLabel.setText(game24.getPlayers24().get(0).getName() + "'s Score: ");
		player2ScoreMessageLabel.setText(game24.getPlayers24().get(1).getName() + "'s Score: ");
		player3ScoreMessageLabel.setText(game24.getPlayers24().get(2).getName() + "'s Score: ");
		player4ScoreMessageLabel.setText(game24.getPlayers24().get(3).getName() + "'s Score: ");

		playerScorePanel.add(player1ScoreMessageLabel);
		playerScorePanel.add(player1Score);
		playerScorePanel.add(player2ScoreMessageLabel);
		playerScorePanel.add(player2Score);
		playerScorePanel.add(player3ScoreMessageLabel);
		playerScorePanel.add(player3Score);
		playerScorePanel.add(player4ScoreMessageLabel);
		playerScorePanel.add(player4Score);
		gameWindow.add(playerScorePanel);
		gameWindow.setVisible(true);
		
	}
    public void initUI() {       
        Card hi = new Card("Ten", "Club", 10);
        //ImageIcon ii = loadImage();
        //Image i = ii.getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT);
        BufferedImage image = null;
		try {
			image = ImageIO.read(new File("images/" + hi.getValue() + hi.getSuit() + ".png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        BufferedImage ret = new BufferedImage(180,180,BufferedImage.TYPE_INT_RGB);
        ret.getGraphics().drawImage(image,0,0,180,180,null);
        ImageIcon iii = new ImageIcon(ret);
        JLabel label = new JLabel(iii);

        createLayout(label);

        setTitle("Image");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private ImageIcon loadImage() {

        ImageIcon ii = new ImageIcon("images/10C.png");
        return ii;
    }

    private void createLayout(JComponent... arg) {

        Container pane = getContentPane();
        GroupLayout gl = new GroupLayout(pane);
        pane.setLayout(gl);

        gl.setAutoCreateContainerGaps(true);

        gl.setHorizontalGroup(gl.createSequentialGroup()
                .addComponent(arg[0])
        );

        gl.setVerticalGroup(gl.createParallelGroup()
                .addComponent(arg[0])
        );

        pack();
    }

    public static void main(String[] args) {
    	
        //EventQueue.invokeLater(() -> {
            Game24GUI ex = new Game24GUI("24");
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
	@Override
	public void keyPressed(KeyEvent e) {
		char key = e.getKeyChar();
		
	}
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
    
}
