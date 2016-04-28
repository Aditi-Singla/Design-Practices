import java.awt.*;
import java.awt.geom.*;
import java.awt.event.*;
import javax.swing.*;

public class ass3 {
   static String level = "Easy";
   static int width,height;
   public static void main(String[] args) {
      JFrame frame = new JFrame();
      JLayeredPane lpane = new JLayeredPane();                    //using JLayeredPane for multiple planes
      frame.setTitle("Ping Pong - 2014CS10217_2014CS10253_2014CS50277");
      
      frame.setLayout(new BorderLayout());
      Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
      width = screenSize.width;
      height = screenSize.height;
      movingObjects ball_paddles = new movingObjects((width-600)/2,(height-600)/2,"Easy");
      board gameboard = new board(width,height);
      print_message timer_message = new print_message("Timer for Special Power",(screenSize.width-600)/2 + 400, (screenSize.height-600)/2 + 20);
      /*Setting exit on close action*/
      frame.addWindowListener(new WindowAdapter() {
         public void windowClosing(WindowEvent e) {
            System.exit(0);
         }
      });
      Container contentPane = frame.getContentPane();

      gameboard.setBounds(0,0,screenSize.width,screenSize.height);
      
      ball_paddles.setBounds(0,0,screenSize.width,screenSize.height);
      ball_paddles.setOpaque(false);

      timer_message.setBounds(0,0,screenSize.width,screenSize.height);
      timer_message.setOpaque(false);

      
      /*Adding paddle, lives, board and ball to the frame*/
      lpane.add(gameboard, new Integer(0), 0);
      lpane.add(ball_paddles,new Integer(1), 0);
      lpane.add(timer_message,new Integer(2), 0);
      
      
      /*Taking input from user*/
      JPanel input = new JPanel();
      input.setLayout(new BorderLayout());
      //////levels panel for difficulty//////
      JPanel levels = new JPanel();
      levels.setLayout(new FlowLayout());
      JRadioButton easy = new JRadioButton("Easy",true);
      JRadioButton medium = new JRadioButton("Medium") ;
      JRadioButton hard = new JRadioButton("Hard");
      easy.setFont(easy.getFont().deriveFont(24.0f)); 
      medium.setFont(medium.getFont().deriveFont(24.0f)); 
      hard.setFont(hard.getFont().deriveFont(24.0f)); 


      easy.addItemListener(new ItemListener() {
         public void itemStateChanged(ItemEvent e) {         
            level = (e.getStateChange()==1?"Easy":level);
         }           
      });

      medium.addItemListener(new ItemListener() {
         public void itemStateChanged(ItemEvent e) {             
            level = (e.getStateChange()==1?"Medium":level);
         }           
      });

      hard.addItemListener(new ItemListener() {
         public void itemStateChanged(ItemEvent e) {             
            level = (e.getStateChange()==1?"Hard":level);
         }           
      });

      //Group the radio buttons.
      ButtonGroup group = new ButtonGroup();
      group.add(easy);
      group.add(medium);
      group.add(hard);

      levels.add(easy);
      levels.add(medium);
      levels.add(hard); 

      ////players panel for single/multi player///////
      JPanel players = new JPanel();

      ////// buttons panel for new game/join game//////////
      JPanel buttons = new JPanel();
      buttons.setLayout(new FlowLayout());
      JButton new_game = new JButton("New Game");
      JButton join_game = new JButton("Join Game");

      new_game.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            buttons.setVisible(false);
            input.add(players,BorderLayout.CENTER);
         }          
      });

      join_game.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            buttons.setVisible(false);
            input.add(host,BorderLayout.CENTER);
         }          
      });



      /////start_game panel for Start Game button////
      JPanel start_game = new JPanel();
      start_game.setLayout(new FlowLayout());
      JButton startgame = new JButton("Start Game");
      startgame.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            input.setVisible(false);
            contentPane.add(lpane,BorderLayout.CENTER);
         }
      });

      input.add(levels,BorderLayout.NORTH);
      input.add(buttons,BorderLayout.CENTER);
      input.add(start_game,BorderLayout.SOUTH);
      //////////////////////////

      

      contentPane.add(input);

           
      
      
      
      
        
      frame.setVisible(true);
      frame.setSize(screenSize.width,screenSize.height);
   }
}