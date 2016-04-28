import java.awt.*;
import java.awt.geom.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class ass3 {
   static String level = "Easy",host_address;
   static int width,height,players_num = 2;
   public static void main(String[] args) {
      JFrame frame = new JFrame();
      JLayeredPane lpane = new JLayeredPane();                    //using JLayeredPane for multiple planes
      frame.setTitle("Ping Pong - 2014CS10217_2014CS10253_2014CS50277");
      
      frame.setLayout(new BorderLayout());
      Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
      width = screenSize.width;
      height = screenSize.height;
      
      /*Setting exit on close action*/
      frame.addWindowListener(new WindowAdapter() {
         public void windowClosing(WindowEvent e) {
            System.exit(0);
         }
      });
      Container contentPane = frame.getContentPane();

      
      
      
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

      
      ButtonGroup group = new ButtonGroup();             //Group the radio buttons.
      group.add(easy);
      group.add(medium);
      group.add(hard);

      levels.add(easy);
      levels.add(medium);
      levels.add(hard); 

      ////players panel for single/multi player and number of players///////
      JPanel players = new JPanel();
      players.setLayout(new BorderLayout());

      ///number panel for number of players in case of a multi player game////
      JPanel number = new JPanel();
      number.setLayout(new FlowLayout());
      SpinnerModel spinnerModel =
         new SpinnerNumberModel(2, //initial value
            2, //min
            4, //max
            1);//step
      JSpinner numOfPlayers = new JSpinner(spinnerModel);
      numOfPlayers.addChangeListener(new ChangeListener() {
         public void stateChanged(ChangeEvent e) {
            players_num = Integer.parseInt((((JSpinner)e.getSource()).getValue()).toString());
         }
      });
      number.add(numOfPlayers);

      //type panel for single/multi player game////
      JPanel type = new JPanel();
      type.setLayout(new FlowLayout());
      JRadioButton single = new JRadioButton("Single Player",true);
      JRadioButton multi = new JRadioButton("Multi-player") ;
      single.setFont(single.getFont().deriveFont(27.0f)); 
      multi.setFont(multi.getFont().deriveFont(27.0f)); 


      single.addItemListener(new ItemListener() {
         public void itemStateChanged(ItemEvent e) {         
            if(e.getStateChange()==1) {
               players_num = 1;
               number.setVisible(false);
            }
         }           
      });

      multi.addItemListener(new ItemListener() {
         public void itemStateChanged(ItemEvent e) {             
            if(e.getStateChange()==1) {
               players_num = 2;
               number.setVisible(true);
               players.add(number,BorderLayout.CENTER);
            }
         }           
      });

      ButtonGroup group1 = new ButtonGroup();             //Group the radio buttons.
      group1.add(single);
      group1.add(multi);

      type.add(single);
      type.add(multi);

      players.add(type,BorderLayout.NORTH);


      ///host panel for host address for joining a game///
      JPanel host = new JPanel();
      host.setLayout(new FlowLayout());
      JLabel label = new JLabel("Enter host IP address: ",JLabel.LEFT);
      JTextField ip = new JTextField(15);
      host_address = ip.getText();
      host.add(label);
      host.add(ip);

      /////start_game panel for Start Game button////
      JPanel start_game = new JPanel();
      start_game.setLayout(new FlowLayout());
      JButton startgame = new JButton("Start Game");
      startgame.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            input.setVisible(false);
            lpane.setVisible(true);
            movingObjects ball_paddles = new movingObjects((width-600)/2,(height-600)/2,"Easy");
            board gameboard = new board(width,height);
            print_message timer_message = new print_message("Timer for Special Power",(screenSize.width-600)/2 + 400, (screenSize.height-600)/2 + 20);
            gameboard.setBounds(0,0,screenSize.width,screenSize.height);
      
            ball_paddles.setBounds(0,0,screenSize.width,screenSize.height);
            ball_paddles.setOpaque(false);

            timer_message.setBounds(0,0,screenSize.width,screenSize.height);
            timer_message.setOpaque(false);

            
            /*Adding paddle, lives, board and ball to the frame*/
            lpane.add(gameboard, new Integer(0), 0);
            lpane.add(ball_paddles,new Integer(1), 0);
            lpane.add(timer_message,new Integer(2), 0);
            contentPane.add(lpane,BorderLayout.CENTER);
         }
      });
      start_game.add(startgame);


      ////// buttons panel for new game/join game//////////
      JPanel buttons = new JPanel();
      buttons.setLayout(new FlowLayout());
      JButton new_game = new JButton("New Game");
      JButton join_game = new JButton("Join Game");

      new_game.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            buttons.setVisible(false);
            input.add(players,BorderLayout.CENTER);
            input.add(start_game,BorderLayout.SOUTH);
         }          
      });

      join_game.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            buttons.setVisible(false);
            input.add(host,BorderLayout.CENTER);
            input.add(start_game,BorderLayout.SOUTH);
         }          
      });
      buttons.add(new_game);
      buttons.add(join_game);



      

      input.add(levels,BorderLayout.NORTH);
      input.add(buttons,BorderLayout.CENTER);
      
      //////////////////////////

      

      contentPane.add(input);

           
      
      
      
      
        
      frame.setVisible(true);
      frame.setSize(screenSize.width,screenSize.height);
   }
}