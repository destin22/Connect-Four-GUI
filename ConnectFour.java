// W1frame.java   a very basic, yet complete java frame application

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Line2D;
import javax.swing.*;
import java.io.*;
    
public class ConnectFour extends JFrame implements ActionListener{

	
   JLabel connect;
   JLabel four;
	
   JLabel playerTurn;
	
   JPanel startPanel;
   JPanel helpPanel;
   JPanel quitPanel;
   JButton start;
   JButton quit;
   JButton help;
	JButton reset;
	
   JPanel gamePanel;
   JPanel theFrame;
	
	
	
  JLabel player1Wins;
  JLabel player2Wins;
  JLabel Tie;
  Popup victoryScreen;
  PopupFactory pop = new PopupFactory();
  
  
	
	//these are what the player will click
   JPanel choices;
   JButton c1;
   JButton c2;
   JButton c3;
   JButton c4;
   JButton c5;
   JButton c6;
   JButton c7;
	
	boolean c1off = false;
	boolean c2off = false;
	boolean c3off = false;
	boolean c4off = false;
	boolean c5off = false;
	boolean c6off = false;
	boolean c7off = false;
	
   JButton g00;
   JButton g01;
   JButton g02;
   JButton g03;
   JButton g04;
   JButton g05;
   JButton g06;
	
   JButton g10;
   JButton g11;
   JButton g12;
   JButton g13;
   JButton g14;
   JButton g15;
   JButton g16;
	
   JButton g20;
   JButton g21;
   JButton g22;
   JButton g23;
   JButton g24;
   JButton g25;
   JButton g26;
	
   JButton g30;
   JButton g31;
   JButton g32;
   JButton g33;
   JButton g34;
   JButton g35;
   JButton g36;
	
   JButton g40;
   JButton g41;
   JButton g42;
   JButton g43;
   JButton g44;
   JButton g45;
   JButton g46;
	
   JButton g50;
   JButton g51;
   JButton g52;
   JButton g53;
   JButton g54;
   JButton g55;
   JButton g56;

	
	
   int[][] gameBoard = new int[6][7];
   int player;
   int turn;
   boolean gameOver;
	boolean gameStarted = false;
	
	// 1 = player1, 2 = player2, 3 = draw
	int winner = 0;
	
 
  
   ConnectFour(){
   
      setTitle("Connect Four");
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//close program clicking exit
      setSize(650,460);
      setLocation(0,0);
      setVisible(true);
		ImageIcon img = new ImageIcon("connectfoursmall.png");
		this.setIconImage(img.getImage());
		
      theFrame = new JPanel();
      add(theFrame);
      theFrame.setLayout(new BoxLayout(theFrame,BoxLayout.PAGE_AXIS));
     // theFrame.setLayout(new FlowLayout());
	  
	  
	   player1Wins = new JLabel();
		player1Wins.setIcon(new ImageIcon("player1Wins.png"));
		
		player2Wins = new JLabel();
		player2Wins.setIcon(new ImageIcon("player2Wins.png"));
		
   	Tie = new JLabel();
		Tie.setIcon(new ImageIcon("Tie.png"));
   	
   	
      turn = 1;
      player = 1;
   	
   
   	// fill the board with zeros as empty spaces
      for (int row = 0; row < 6; row++){
         for (int col = 0; col < 7; col++){
         	
            gameBoard[row][col] = 0;
         	
         }
      }
   	
   	
   	//print the board
      for(int i = 0; i<6; i++){
         for(int j = 0; j<7; j++)
         {
            System.out.print(gameBoard[i][j]);
         }
         System.out.println();
      }
		System.out.println("------------------");
   	
   	
      connect = new JLabel();
      //four = new JLabel("Four!");
		
		
   	
      //connect.setForeground(Color.RED);
      //four.setForeground(Color.YELLOW);
   	
      //connect.setFont(new Font("Cafeteria", Font.PLAIN, 75));
      //four.setFont(new Font("Cafeteria", Font.PLAIN, 75));
   	
      theFrame.add(connect);
      //theFrame.add(four);
		connect.setIcon(new ImageIcon("connectLogo.png"));	
   	
      startPanel = new JPanel();
      theFrame.add(startPanel);
      start = new JButton("Start");
      start.setForeground(Color.BLUE);
      startPanel.add(start);		
      start.setVisible(true);
   	
      start.addActionListener(this);
   	
      helpPanel = new JPanel();
      theFrame.add(helpPanel);
      help = new JButton("Help");
      help.setForeground(Color.BLUE);
      helpPanel.add(help);		
      help.setVisible(true);
      help.addActionListener(this);
   	
      quitPanel = new JPanel();
      theFrame.add(quitPanel);
      quit = new JButton("Quit");
      quit.setForeground(Color.BLUE);
      quitPanel.add(quit);		
      quit.setVisible(true);
      quit.addActionListener(this);
   	
   	theFrame.setVisible(true);
		pack();
   	
   	setResizable(false);
   	
   }
  
   public void actionPerformed(ActionEvent e){
      if(e.getSource()== start ){
      	gameStarted = true;
         startGame();
      				
      }
      else if(e.getSource()== help ){
         System.out.println("help screen");
			String displayRules = "How to Play: click the buttons 1-7 to drop\na red or yellow piece on the board\nand try to get four in a row horizontally,\nvertically, or diagonally.\n\nIf the board is full and there is not 4 in a row\nthe game ends in a draw.";
			JOptionPane.showMessageDialog(null, displayRules, "Rules", JOptionPane.PLAIN_MESSAGE);
      }
      else if(e.getSource()== quit ){
         System.out.println("Thanks for playing!");
         System.exit(0);
      }
		else if(e.getSource()== reset ){
         new ConnectFour();
			dispose();
			startGame();
      }
      else if(e.getSource()== c1 ){
         
         for(int i=0;i<6;i++){
         	//if were at the end of the board we can drop a piece
            if(i+1 == 6){
               if(turn == 1){
                  gameBoard[5][0] = 1;
                  turn = 2;
						gameOver = checkWin(5,0);
                  break;
               }
               else{
                  gameBoard[5][0] = 2;
                  turn = 1;
						gameOver = checkWin(5,0);
                  break;
               }
					
            }
         	
         	//if the next slot is taken we swap our piece
            if(gameBoard[i+1][0] != 0){
               if(turn == 1){
                  gameBoard[i][0] = 1;
                  turn = 2;
						gameOver = checkWin(i,0);
                  break;
               }
               else{
                  gameBoard[i][0] = 2;
                  turn = 1;
						gameOver = checkWin(i,0);
                  break;
               }
            }
         
         }
      	
      	//print the board
         for(int i = 0; i<6; i++){
            for(int j = 0; j<7; j++)
            {
               System.out.print(gameBoard[i][j]);
            }
            System.out.println();
         }
      	System.out.println("------------------");
         if(turn == 1){
            playerTurn.setText("Turn: player 1");
         }
         else{
            playerTurn.setText("Turn: player 2"); 
         }
			
			if(gameBoard[0][0]!=0){
				c1off = true;
				c1.setEnabled(false);
			}
      	 	
      }
		
		//////////2nd columm///////////
		else if(e.getSource()== c2 ){
         
         for(int i=0;i<6;i++){
         	//if were at the end of the board we can drop a piece
            if(i+1 == 6){
               if(turn == 1){
                  gameBoard[5][1] = 1;
                  turn = 2;
						gameOver = checkWin(5,1);
                  break;
               }
               else{
                  gameBoard[5][1] = 2;
                  turn = 1;
						gameOver = checkWin(5,1);
                  break;
               }
            }
         	
         	//if the next slot is taken we swap our piece
            if(gameBoard[i+1][1] != 0){
               if(turn == 1){
                  gameBoard[i][1] = 1;
                  turn = 2;
						gameOver = checkWin(i,1);
                  break;
               }
               else{
                  gameBoard[i][1] = 2;
                  turn = 1;
						gameOver = checkWin(i,1);
                  break;
               }
            }
         
         }
      	
      	//print the board
         for(int i = 0; i<6; i++){
            for(int j = 0; j<7; j++)
            {
               System.out.print(gameBoard[i][j]);
            }
            System.out.println();
         }
      	System.out.println("------------------");
         if(turn == 1){
            playerTurn.setText("Turn: Player 1");
         }
         else{
            playerTurn.setText("Turn: Player 2"); 
         }
			
			if(gameBoard[0][1]!=0){
				c2off = true;
				c2.setEnabled(false);
			}
      	 	
      }
		
		
		/////////////column 3///////////
		else if(e.getSource()== c3 ){
         
         for(int i=0;i<6;i++){
         	//if were at the end of the board we can drop a piece
            if(i+1 == 6){
               if(turn == 1){
                  gameBoard[5][2] = 1;
                  turn = 2;
						gameOver = checkWin(5,2);
                  break;
               }
               else{
                  gameBoard[5][2] = 2;
                  turn = 1;
						gameOver = checkWin(5,2);
                  break;
               }
            }
         	
         	//if the next slot is taken we swap our piece
            if(gameBoard[i+1][2] != 0){
               if(turn == 1){
                  gameBoard[i][2] = 1;
                  turn = 2;
						gameOver = checkWin(i,2);
                  break;
               }
               else{
                  gameBoard[i][2] = 2;
                  turn = 1;
						gameOver = checkWin(i,2);
                  break;
               }
            }
         
         }
      	
      	//print the board
         for(int i = 0; i<6; i++){
            for(int j = 0; j<7; j++)
            {
               System.out.print(gameBoard[i][j]);
            }
            System.out.println();
         }
      	System.out.println("------------------");
         if(turn == 1){
            playerTurn.setText("Turn: Player 1");
         }
         else{
            playerTurn.setText("Turn: Player 2"); 
         }
			
			if(gameBoard[0][2]!=0){
			   c3off = true;
				c3.setEnabled(false);
			}
      	 	
      }
		
		
		///////////column 4////////////
		else if(e.getSource()== c4 ){
         
         for(int i=0;i<6;i++){
         	//if were at the end of the board we can drop a piece
            if(i+1 == 6){
               if(turn == 1){
                  gameBoard[5][3] = 1;
                  turn = 2;
						gameOver = checkWin(5,3);
                  break;
               }
               else{
                  gameBoard[5][3] = 2;
                  turn = 1;
						gameOver = checkWin(5,3);
                  break;
               }
            }
         	
         	//if the next slot is taken we swap our piece
            if(gameBoard[i+1][3] != 0){
               if(turn == 1){
                  gameBoard[i][3] = 1;
                  turn = 2;
						gameOver = checkWin(i,3);
                  break;
               }
               else{
                  gameBoard[i][3] = 2;
                  turn = 1;
						gameOver = checkWin(i,3);
                  break;
               }
            }
         
         }
      	
      	//print the board
         for(int i = 0; i<6; i++){
            for(int j = 0; j<7; j++)
            {
               System.out.print(gameBoard[i][j]);
            }
            System.out.println();
         }
      	System.out.println("------------------");
			
			
         if(turn == 1){
            playerTurn.setText("Turn: Player 1");
         }
         else{
            playerTurn.setText("Turn: Player 2"); 
         }
			
			
			
			if(gameBoard[0][3]!=0){
				c4off = true;
				c4.setEnabled(false);
			}
      	 	
      }
		
		////////column 5///////////
		else if(e.getSource()== c5 ){
         
         for(int i=0;i<6;i++){
         	//if were at the end of the board we can drop a piece
            if(i+1 == 6){
               if(turn == 1){
                  gameBoard[5][4] = 1;
                  turn = 2;
						gameOver = checkWin(5,4);
                  break;
               }
               else{
                  gameBoard[5][4] = 2;
                  turn = 1;
						gameOver = checkWin(5,4);
                  break;
               }
            }
         	
         	//if the next slot is taken we swap our piece
            if(gameBoard[i+1][4] != 0){
               if(turn == 1){
                  gameBoard[i][4] = 1;
                  turn = 2;
						gameOver = checkWin(i,4);
                  break;
               }
               else{
                  gameBoard[i][4] = 2;
                  turn = 1;
						gameOver = checkWin(i,4);
                  break;
               }
            }
         
         }
      	
      	//print the board
         for(int i = 0; i<6; i++){
            for(int j = 0; j<7; j++)
            {
               System.out.print(gameBoard[i][j]);
            }
            System.out.println();
         }
      	System.out.println("------------------");
			
			
         if(turn == 1){
            playerTurn.setText("Turn: Player 1");
         }
         else{
            playerTurn.setText("Turn: Player 2"); 
         }
			
			if(gameBoard[0][4]!=0){
				c5off=true;
				c5.setEnabled(false);
			}
			
      	 	
      }
		///////////column 6//////////
		else if(e.getSource()== c6 ){
         
         for(int i=0;i<6;i++){
         	//if were at the end of the board we can drop a piece
            if(i+1 == 6){
               if(turn == 1){
                  gameBoard[5][5] = 1;
                  turn = 2;
						gameOver = checkWin(5,5);
                  break;
               }
               else{
                  gameBoard[5][5] = 2;
                  turn = 1;
						gameOver = checkWin(5,5);
                  break;
               }
            }
         	
         	//if the next slot is taken we swap our piece
            if(gameBoard[i+1][5] != 0){
               if(turn == 1){
                  gameBoard[i][5] = 1;
                  turn = 2;
						gameOver = checkWin(i,5);
                  break;
               }
               else{
                  gameBoard[i][5] = 2;
                  turn = 1;
						gameOver = checkWin(i,5);
                  break;
               }
            }
         
         }
      	
      	//print the board
         for(int i = 0; i<6; i++){
            for(int j = 0; j<7; j++)
            {
               System.out.print(gameBoard[i][j]);
            }
            System.out.println();
         }
      	System.out.println("------------------");
         if(turn == 1){
            playerTurn.setText("Turn: Player 1");
         }
         else{
            playerTurn.setText("Turn: Player 2"); 
         }
			
			if(gameBoard[0][5]!=0){
				c6off = true;
				c6.setEnabled(false);
			}
      	 	
      }
		
		///////////column 7///////////
		else if(e.getSource()== c7 ){
         
         for(int i=0;i<6;i++){
         	//if were at the end of the board we can drop a piece
            if(i+1 == 6){
               if(turn == 1){
                  gameBoard[5][6] = 1;
                  turn = 2;
						gameOver = checkWin(5,6);
                  break;
               }
               else{
                  gameBoard[5][6] = 2;
                  turn = 1;
						gameOver = checkWin(5,6);
                  break;
               }
            }
         	
         	//if the next slot is taken we drop our piece
            if(gameBoard[i+1][6] != 0){
               if(turn == 1){
                  gameBoard[i][6] = 1;
                  turn = 2;
						gameOver = checkWin(i,6);
                  break;
               }
               else{
                  gameBoard[i][6] = 2;
                  turn = 1;
						gameOver = checkWin(i,6);
                  break;
               }
            }
         
         }
      	
      	//print the board
         for(int i = 0; i<6; i++){
            for(int j = 0; j<7; j++)
            {
               System.out.print(gameBoard[i][j]);
            }
            System.out.println();
         }
      	System.out.println("------------------");
         if(turn == 1){
            playerTurn.setText("Turn: Player 1");
         }
         else{
            playerTurn.setText("Turn: Player 2"); 
         }
			
			if(gameBoard[0][6]!=0){
				c7off = true;
				c7.setEnabled(false);
			}
			
					
      	 	
      }
		if(gameStarted){
		if(turn == 1){
			c1.setBackground(Color.RED);
			c2.setBackground(Color.RED);
			c3.setBackground(Color.RED);
			c4.setBackground(Color.RED);
			c5.setBackground(Color.RED);
			c6.setBackground(Color.RED);
			c7.setBackground(Color.RED);				
						
		}
		else{
			c1.setBackground(Color.YELLOW);
			c2.setBackground(Color.YELLOW);
			c3.setBackground(Color.YELLOW);
			c4.setBackground(Color.YELLOW);
			c5.setBackground(Color.YELLOW);
			c6.setBackground(Color.YELLOW);
			c7.setBackground(Color.YELLOW);
					
		}
		
		
		
			if(c1off){
				c1.setBackground(Color.GRAY);
			}
			if(c2off){
				c2.setBackground(Color.GRAY);
			}
			if(c3off){
				c3.setBackground(Color.GRAY);
			}
			if(c4off){
				c4.setBackground(Color.GRAY);
			}
			if(c5off){
				c5.setBackground(Color.GRAY);
			}
			if(c6off){
				c6.setBackground(Color.GRAY);
			}
			if(c7off){
				c7.setBackground(Color.GRAY);
			}
	
		
		if(gameOver){
			if(winner == 3){
				System.out.println("Tie");
				victoryScreen = pop.getPopup(theFrame,Tie,theFrame.getLocationOnScreen().x,theFrame.getLocationOnScreen().y) ;
				victoryScreen.show();
				//dispose();
			}
			else{
				System.out.println("Winner: Player"+ winner);
				if(winner == 1){
					victoryScreen = pop.getPopup(theFrame,player1Wins,theFrame.getLocationOnScreen().x,theFrame.getLocationOnScreen().y) ;
					victoryScreen.show();
				}
				else if(winner == 2){
					victoryScreen = pop.getPopup(theFrame,player2Wins,theFrame.getLocationOnScreen().x,theFrame.getLocationOnScreen().y) ;
					victoryScreen.show();
				}
				
			}
			
			c1off=true;
			c2off=true;
			c3off=true;
			c4off=true;
			c5off=true;
			c6off=true;
			c7off=true;
			c1.setEnabled(false);
			c2.setEnabled(false);
			c3.setEnabled(false);
			c4.setEnabled(false);
			c5.setEnabled(false);
			c6.setEnabled(false);
			c7.setEnabled(false);
			
			}
		}
   } 
	

	
	
   void startGame(){
	
   
      theFrame.remove(startPanel);
      theFrame.remove(helpPanel);
      theFrame.remove(quitPanel);
		theFrame.setLayout(new FlowLayout());
		setSize(585,460);
      repaint();
   	
      gameOver = false;
   	
      playerTurn = new JLabel();
      theFrame.add(playerTurn);
      if(turn == 1){
         playerTurn.setText("Turn: Player 1");
      }
      else{
         playerTurn.setText("Turn: Player 2"); 
      }
   	
      choices = new JPanel();
      c1 = new JButton(" 1 ");
      c2 = new JButton(" 2 ");
      c3 = new JButton(" 3 ");
      c4 = new JButton(" 4 ");
      c5 = new JButton(" 5 ");
      c6 = new JButton(" 6 ");
      c7 = new JButton(" 7 ");
		
		c1.setBackground(Color.RED);
		c2.setBackground(Color.RED);
		c3.setBackground(Color.RED);
		c4.setBackground(Color.RED);
		c5.setBackground(Color.RED);
		c6.setBackground(Color.RED);
		c7.setBackground(Color.RED);
		
   	
      choices.add(c1);
      choices.add(c2);
      choices.add(c3);
      choices.add(c4);
      choices.add(c5);
      choices.add(c6);
      choices.add(c7);
   	
   	//choices.setPreferredSize(new Dimension(350, 2));
   	
      choices.setLayout(new GridLayout(0,7,5,5));
   	
      theFrame.add(choices);
   	
     
      gamePanel = new JPanel();
      gamePanel.setLayout(new GridLayout(6,7,5,5));
   	
      gamePanel.add(g00 = new JButton("    "));
      gamePanel.add(g01 = new JButton(""));
      gamePanel.add(g02 = new JButton(""));
      gamePanel.add(g03 = new JButton(""));
      gamePanel.add(g04 = new JButton(""));
      gamePanel.add(g05 = new JButton(""));
      gamePanel.add(g06 = new JButton(""));
   	
      gamePanel.add(g10 = new JButton(""));
      gamePanel.add(g11 = new JButton(""));
      gamePanel.add(g12 = new JButton(""));
      gamePanel.add(g13 = new JButton(""));
      gamePanel.add(g14 = new JButton(""));
      gamePanel.add(g15 = new JButton(""));
      gamePanel.add(g16 = new JButton(""));
   	
      gamePanel.add(g20 = new JButton(""));
      gamePanel.add(g21 = new JButton(""));
      gamePanel.add(g22 = new JButton(""));
      gamePanel.add(g23 = new JButton(""));
      gamePanel.add(g24 = new JButton(""));
      gamePanel.add(g25 = new JButton(""));
      gamePanel.add(g26 = new JButton(""));
   	
      gamePanel.add(g30 = new JButton(""));
      gamePanel.add(g31 = new JButton(""));
      gamePanel.add(g32 = new JButton(""));
      gamePanel.add(g33 = new JButton(""));
      gamePanel.add(g34 = new JButton(""));
      gamePanel.add(g35 = new JButton(""));
      gamePanel.add(g36 = new JButton(""));
   	
      gamePanel.add(g40 = new JButton(""));
      gamePanel.add(g41 = new JButton(""));
      gamePanel.add(g42 = new JButton(""));
      gamePanel.add(g43 = new JButton(""));
      gamePanel.add(g44 = new JButton(""));
      gamePanel.add(g45 = new JButton(""));
      gamePanel.add(g46 = new JButton(""));
   	
      gamePanel.add(g50 = new JButton(""));
      gamePanel.add(g51 = new JButton(""));
      gamePanel.add(g52 = new JButton(""));
      gamePanel.add(g53 = new JButton(""));
      gamePanel.add(g54 = new JButton(""));
      gamePanel.add(g55 = new JButton(""));
      gamePanel.add(g56 = new JButton(""));
   	
	 
		g00.setBackground(Color.WHITE);
		g01.setBackground(Color.WHITE);
		g02.setBackground(Color.WHITE);
		g03.setBackground(Color.WHITE);
		g04.setBackground(Color.WHITE);
		g05.setBackground(Color.WHITE);
		g06.setBackground(Color.WHITE);
		
		g10.setBackground(Color.WHITE);
		g11.setBackground(Color.WHITE);
		g12.setBackground(Color.WHITE);
		g13.setBackground(Color.WHITE);
		g14.setBackground(Color.WHITE);
		g15.setBackground(Color.WHITE);
		g16.setBackground(Color.WHITE);
		
		g20.setBackground(Color.WHITE);
		g21.setBackground(Color.WHITE);
		g22.setBackground(Color.WHITE);
		g23.setBackground(Color.WHITE);
		g24.setBackground(Color.WHITE);
		g25.setBackground(Color.WHITE);
		g26.setBackground(Color.WHITE);
		
		g30.setBackground(Color.WHITE);
		g31.setBackground(Color.WHITE);
		g32.setBackground(Color.WHITE);
		g33.setBackground(Color.WHITE);
		g34.setBackground(Color.WHITE);
		g35.setBackground(Color.WHITE);
		g36.setBackground(Color.WHITE);
		
		g40.setBackground(Color.WHITE);
		g41.setBackground(Color.WHITE);
		g42.setBackground(Color.WHITE);
		g43.setBackground(Color.WHITE);
		g44.setBackground(Color.WHITE);
		g45.setBackground(Color.WHITE);
		g46.setBackground(Color.WHITE);
		
		g50.setBackground(Color.WHITE);
		g51.setBackground(Color.WHITE);
		g52.setBackground(Color.WHITE);
		g53.setBackground(Color.WHITE);
		g54.setBackground(Color.WHITE);
		g55.setBackground(Color.WHITE);
		g56.setBackground(Color.WHITE);
		
		gamePanel.setBackground(Color.BLUE);
		
		gamePanel.setBorder(BorderFactory.createLineBorder(Color.BLUE, 7));

      theFrame.add(gamePanel);
   
   
      c1.addActionListener(this);
      c2.addActionListener(this);
      c3.addActionListener(this);
      c4.addActionListener(this);
      c5.addActionListener(this);
      c6.addActionListener(this);
      c7.addActionListener(this);
		
		reset = new JButton("New Game");
		reset.setForeground(Color.BLUE);
		theFrame.add(quit,FlowLayout.CENTER);
      reset.addActionListener(this);
		theFrame.add(help,FlowLayout.CENTER);
		theFrame.add(reset,FlowLayout.CENTER);
		
   
   	
   }
	

	boolean checkWin(int x, int y){
	
		//we have to reach 4 pieces together so we check north and south together
		//west and east together
		// NE and SW together
		// NW and SE togehter should cover all directions
		
		int originalX = x;
		int originalY = y;
		
		int tally = 1;
		
		//north and south
		//gameBoard[x][y]
		
		int whoMoved;
		
		if(turn == 1){
			whoMoved = 2;
		}
		else{
			whoMoved = 1;
		}
		
		
		//update moves color
		if(x == 0 && y == 0){
			if(whoMoved == 1){
				g00.setBackground(Color.RED);
			}
			else{
				g00.setBackground(Color.YELLOW);
			}
		}
		//01
		if(x == 0 && y == 1){
			if(whoMoved == 1){
				g01.setBackground(Color.RED);
			}
			else{
				g01.setBackground(Color.YELLOW);
			}
		}
		
		//02
		if(x == 0 && y == 2){
			if(whoMoved == 1){
				g02.setBackground(Color.RED);
			}
			else{
				g02.setBackground(Color.YELLOW);
			}
		}
		//03
		if(x == 0 && y == 3){
			if(whoMoved == 1){
				g03.setBackground(Color.RED);
			}
			else{
				g03.setBackground(Color.YELLOW);
			}
		}
		//04
		if(x == 0 && y == 4){
			if(whoMoved == 1){
				g04.setBackground(Color.RED);
			}
			else{
				g04.setBackground(Color.YELLOW);
			}
		}
		//05
		if(x == 0 && y == 5){
			if(whoMoved == 1){
				g05.setBackground(Color.RED);
			}
			else{
				g05.setBackground(Color.YELLOW);
			}
		}
		//06
		if(x == 0 && y == 6){
			if(whoMoved == 1){
				g06.setBackground(Color.RED);
			}
			else{
				g06.setBackground(Color.YELLOW);
			}
		}
		//10
		if(x == 1 && y == 0){
			if(whoMoved == 1){
				g10.setBackground(Color.RED);
			}
			else{
				g10.setBackground(Color.YELLOW);
			}
		}
		//11
		if(x == 1 && y == 1){
			if(whoMoved == 1){
				g11.setBackground(Color.RED);
			}
			else{
				g11.setBackground(Color.YELLOW);
			}
		}
		//12
		if(x == 1 && y == 2){
			if(whoMoved == 1){
				g12.setBackground(Color.RED);
			}
			else{
				g12.setBackground(Color.YELLOW);
			}
		}
		//13
		if(x == 1 && y == 3){
			if(whoMoved == 1){
				g13.setBackground(Color.RED);
			}
			else{
				g13.setBackground(Color.YELLOW);
			}
		}
		//14
		if(x == 1 && y == 4){
			if(whoMoved == 1){
				g14.setBackground(Color.RED);
			}
			else{
				g14.setBackground(Color.YELLOW);
			}
		}
		//15
		if(x == 1 && y == 5){
			if(whoMoved == 1){
				g15.setBackground(Color.RED);
			}
			else{
				g15.setBackground(Color.YELLOW);
			}
		}
		//16
		if(x == 1 && y == 6){
			if(whoMoved == 1){
				g16.setBackground(Color.RED);
			}
			else{
				g16.setBackground(Color.YELLOW);
			}
		}
		//20
		if(x == 2 && y == 0){
			if(whoMoved == 1){
				g20.setBackground(Color.RED);
			}
			else{
				g20.setBackground(Color.YELLOW);
			}
		}
		//21
		if(x == 2 && y == 1){
			if(whoMoved == 1){
				g21.setBackground(Color.RED);
			}
			else{
				g21.setBackground(Color.YELLOW);
			}
		}
		//22
		if(x == 2 && y == 2){
			if(whoMoved == 1){
				g22.setBackground(Color.RED);
			}
			else{
				g22.setBackground(Color.YELLOW);
			}
		}
		//23
		if(x == 2 && y == 3){
			if(whoMoved == 1){
				g23.setBackground(Color.RED);
			}
			else{
				g23.setBackground(Color.YELLOW);
			}
		}
		//24
		if(x == 2 && y == 4){
			if(whoMoved == 1){
				g24.setBackground(Color.RED);
			}
			else{
				g24.setBackground(Color.YELLOW);
			}
		}
		//25
		if(x == 2 && y == 5){
			if(whoMoved == 1){
				g25.setBackground(Color.RED);
			}
			else{
				g25.setBackground(Color.YELLOW);
			}
		}
		//26
		if(x == 2 && y == 6){
			if(whoMoved == 1){
				g26.setBackground(Color.RED);
			}
			else{
				g26.setBackground(Color.YELLOW);
			}
		}
		//30
		if(x == 3 && y == 0){
			if(whoMoved == 1){
				g30.setBackground(Color.RED);
			}
			else{
				g30.setBackground(Color.YELLOW);
			}
		}
		//31
		if(x == 3 && y == 1){
			if(whoMoved == 1){
				g31.setBackground(Color.RED);
			}
			else{
				g31.setBackground(Color.YELLOW);
			}
		}
		//32
		if(x == 3 && y == 2){
			if(whoMoved == 1){
				g32.setBackground(Color.RED);
			}
			else{
				g32.setBackground(Color.YELLOW);
			}
		}
		//33
		if(x == 3 && y == 3){
			if(whoMoved == 1){
				g33.setBackground(Color.RED);
			}
			else{
				g33.setBackground(Color.YELLOW);
			}
		}
		//34
		if(x == 3 && y == 4){
			if(whoMoved == 1){
				g34.setBackground(Color.RED);
			}
			else{
				g34.setBackground(Color.YELLOW);
			}
		}
		//35
		if(x == 3 && y == 5){
			if(whoMoved == 1){
				g35.setBackground(Color.RED);
			}
			else{
				g35.setBackground(Color.YELLOW);
			}
		}
		//36
		if(x == 3 && y == 6){
			if(whoMoved == 1){
				g36.setBackground(Color.RED);
			}
			else{
				g36.setBackground(Color.YELLOW);
			}
		}
		//40
		if(x == 4 && y == 0){
			if(whoMoved == 1){
				g40.setBackground(Color.RED);
			}
			else{
				g40.setBackground(Color.YELLOW);
			}
		}
		//41
		if(x == 4 && y == 1){
			if(whoMoved == 1){
				g41.setBackground(Color.RED);
			}
			else{
				g41.setBackground(Color.YELLOW);
			}
		}
		//42
		if(x == 4 && y == 2){
			if(whoMoved == 1){
				g42.setBackground(Color.RED);
			}
			else{
				g42.setBackground(Color.YELLOW);
			}
		}
		//43
		if(x == 4 && y == 3){
			if(whoMoved == 1){
				g43.setBackground(Color.RED);
			}
			else{
				g43.setBackground(Color.YELLOW);
			}
		}
		//44
		if(x == 4 && y == 4){
			if(whoMoved == 1){
				g44.setBackground(Color.RED);
			}
			else{
				g44.setBackground(Color.YELLOW);
			}
		}
		//45
		if(x == 4 && y == 5){
			if(whoMoved == 1){
				g45.setBackground(Color.RED);
			}
			else{
				g45.setBackground(Color.YELLOW);
			}
		}
		//46
		if(x == 4 && y == 6){
			if(whoMoved == 1){
				g46.setBackground(Color.RED);
			}
			else{
				g46.setBackground(Color.YELLOW);
			}
		}
		//50
		if(x == 5 && y == 0){
			if(whoMoved == 1){
				g50.setBackground(Color.RED);
			}
			else{
				g50.setBackground(Color.YELLOW);
			}
		}
		//51
		if(x == 5 && y == 1){
			if(whoMoved == 1){
				g51.setBackground(Color.RED);
			}
			else{
				g51.setBackground(Color.YELLOW);
			}
		}
		//52
		if(x == 5 && y == 2){
			if(whoMoved == 1){
				g52.setBackground(Color.RED);
			}
			else{
				g52.setBackground(Color.YELLOW);
			}
		}
		//53
		if(x == 5 && y == 3){
			if(whoMoved == 1){
				g53.setBackground(Color.RED);
			}
			else{
				g53.setBackground(Color.YELLOW);
			}
		}
		//54
		if(x == 5 && y == 4){
			if(whoMoved == 1){
				g54.setBackground(Color.RED);
			}
			else{
				g54.setBackground(Color.YELLOW);
			}
		}
		//55
		if(x == 5 && y == 5){
			if(whoMoved == 1){
				g55.setBackground(Color.RED);
			}
			else{
				g55.setBackground(Color.YELLOW);
			}
		}
		//55
		if(x == 5 && y == 6){
			if(whoMoved == 1){
				g56.setBackground(Color.RED);
			}
			else{
				g56.setBackground(Color.YELLOW);
			}
		}

		
		
		////////////north and south///////////////
		//if were at 0 then we cant go north
		//north
		if(x != 0){
			while(x!=0){
				if(gameBoard[x-1][originalY]==whoMoved){
					tally++;
				}
				else{
					break;
				}
				x--;
			}					
		}
		x = originalX;
		y = originalY;
		
		//south
		if(x!=5){
			while(x!=5){
				if(gameBoard[x+1][originalY]==whoMoved){
					tally++;
				}
				else{
					break;
				}
				x++;
			}
		}
		x = originalX;
		y = originalY;
		
		//check north and south for win
		if(tally>=4){
			winner = whoMoved;
			return true;
		}
		tally = 1;
		
		
		//////////////check west and east/////////////////////
		
		//west//
		if(y!=0){
			while(y!=0){
				if(gameBoard[x][y-1]==whoMoved){
					tally++;
				}
				else{
					break;
				}
				y--;
			}
		}
		x = originalX;
		y = originalY;
		
		//east//
		if(y!=6){
			while(y!=6){
				if(gameBoard[x][y+1]==whoMoved){
					tally++;
				}
				else{
					break;
				}
				y++;
			}
		}
		x = originalX;
		y = originalY;
		
		if(tally>=4){
			winner = whoMoved;
			return true;
		}
		tally = 1;
		
		
		
		//////////////check north west and south east/////////////////////
		
		//north west//
		if(x!=0 && y!=0){
			while(y!=0 && x!=0){
				if(gameBoard[x-1][y-1]==whoMoved){
					tally++;
				}
				else{
					break;
				}
				x--;
				y--;
			}
		}
		x = originalX;
		y = originalY;
		
		//south east//
		if(y!=6 && x!=5){
			while(y!=6 && x!=5){
				if(gameBoard[x+1][y+1]==whoMoved){
					tally++;
				}
				else{
					break;
				}
				x++;
				y++;
			}
		}
		x = originalX;
		y = originalY;
		
		if(tally>=4){
			winner = whoMoved;
			return true;
		}
		tally = 1;
		
		
		//////////////check north east and south west/////////////////////
		
		//north east//
		if(x!=0 && y!=6){
			while(y!=6 && x!=0){
				if(gameBoard[x-1][y+1]==whoMoved){
					tally++;
				}
				else{
					break;
				}
				x--;
				y++;
			}
		}
		x = originalX;
		y = originalY;
		
		//south west//
		if(y!=0 && x!=5){
			while(y!=0 && x!=5){
				if(gameBoard[x+1][y-1]==whoMoved){
					tally++;
				}
				else{
					break;
				}
				x++;
				y--;
			}
		}
		x = originalX;
		y = originalY;
		
		if(tally>=4){
			winner = whoMoved;
			return true;
		}
		tally = 1;
		
		
		
		//check if gameboard is full and causes a draw
		boolean full = true;
		for(int i = 0; i<6; i++){
            for(int j = 0; j<7; j++)
            {
               if(gameBoard[i][j] == 0)
					{
						full = false;
					}
            }
        }
		  
		  if(full){
		  		winner = 3;// draw
		  		return true;
		  }
			
	
	
	//couldnt find four so return false
	return false;
	}
	
	

   public static void main(String[] args){
   
      new ConnectFour();  
   
   }
  
 	 
} 


