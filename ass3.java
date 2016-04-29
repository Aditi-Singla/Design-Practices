import java.awt.*;
import java.net.*;
import java.awt.geom.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.border.Border;

public class ass3 {
   static String level = "Easy",host_address;
   static int width,height,players_num = 1;
  static boolean flag_initiator;
   public static void main(String[] args) 
   {
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
      // input.setLayout(new BorderLayout());
      input.setLayout(new GridLayout(5,1,20,20));


      //////levels panel for difficulty//////
      JPanel levels = new JPanel();
      levels.setLayout(new FlowLayout());
      JRadioButton easy = new JRadioButton("Easy",true);
      easy.setForeground (new Color(51,102,153));
      easy.setFont(new Font("Helvetica", Font.PLAIN, 32));
      easy.setFocusable(false);
      JRadioButton medium = new JRadioButton("Medium") ;
      medium.setForeground (new Color(51,102,153));
      medium.setFont(new Font("Helvetica", Font.PLAIN, 32));
      medium.setFocusable(false);
      JRadioButton hard = new JRadioButton("Hard");
      hard.setForeground (new Color(51,102,153));
      hard.setFont(new Font("Helvetica", Font.PLAIN, 32));
      hard.setFocusable(false);
      
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

      

      JPanel onclickButtons = new JPanel();
      onclickButtons.setLayout(new BorderLayout());

      JPanel levels_back = new JPanel();
      levels_back.setLayout(new GridLayout(1,3));

      SpinnerModel spinnerModel =
               new SpinnerNumberModel(2, //initial value
                  2, //min
                  4, //max
                  1);//step
      JSpinner numOfPlayers = new JSpinner(spinnerModel);

      JTextField ip = new JTextField(20);
            ip.setBorder(new RoundedBorder(10));

      /////start_game panel for Start Game button////
      JPanel start_game = new JPanel();
      start_game.setLayout(new FlowLayout());
      JButton startgame = new JButton("Start Game");
      startgame.setBorder(new RoundedBorder(10));
      startgame.setBackground(new Color(255,230,179));
      startgame.setForeground(Color.RED);
      startgame.setFont(new Font("Helvetica", Font.PLAIN, 20));
      startgame.setPreferredSize(new Dimension(200, 50));
      startgame.setFocusable(false);

      startgame.addMouseListener(new java.awt.event.MouseAdapter() {
         public void mouseEntered(java.awt.event.MouseEvent evt) {
               startgame.setBackground(new Color(255, 221, 153));
         }

         public void mouseExited(java.awt.event.MouseEvent evt) {
               startgame.setBackground(new Color(255,230,179));
         }
      });

      startgame.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            
            host_address = ip.getText();
            if(!flag_initiator && (host_address == null || host_address.trim().length()==0)) {
               JOptionPane.showMessageDialog(null, "Enter a valid IP address");
            }
            else {
               input.setVisible(false);
                        
                        movingObjects ball_paddles = new movingObjects((width-600)/2,(height-600)/2,level,players_num,flag_initiator,host_address);
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
                        SwingUtilities.updateComponentTreeUI(frame);}
         }
      });
      start_game.add(startgame);


      ////// buttons panel for new game/join game//////////
      JPanel buttons = new JPanel();
      buttons.setLayout(new FlowLayout());
      JButton new_game = new JButton("New Game");
      new_game.setBorder(new RoundedBorder(10));
      new_game.setBackground(new Color(255,230,179));   ///beige
      new_game.setForeground(Color.RED);
      new_game.setFont(new Font("Helvetica", Font.PLAIN, 20));
      new_game.setPreferredSize(new Dimension(200, 50));
      new_game.setFocusable(false);

      new_game.addMouseListener(new java.awt.event.MouseAdapter() {
         public void mouseEntered(java.awt.event.MouseEvent evt) {
               new_game.setBackground(new Color(255, 221, 153));
         }

         public void mouseExited(java.awt.event.MouseEvent evt) {
               new_game.setBackground(new Color(255,230,179));
         }
      });


      JButton join_game = new JButton("Join Game");
      join_game.setBorder(new RoundedBorder(10));
      join_game.setBackground(new Color(255,230,179));
      join_game.setForeground(Color.RED);
      join_game.setFont(new Font("Helvetica", Font.PLAIN, 20));
      join_game.setPreferredSize(new Dimension(200, 50));
      join_game.setFocusable(false);

      join_game.addMouseListener(new java.awt.event.MouseAdapter() {
         public void mouseEntered(java.awt.event.MouseEvent evt) {
               join_game.setBackground(new Color(255, 221, 153));
         }

         public void mouseExited(java.awt.event.MouseEvent evt) {
               join_game.setBackground(new Color(255,230,179));
         }
      });

      ImageIcon image = new ImageIcon("back.png");
      JButton back = new JButton(image);
      back.setOpaque(false);
      back.setContentAreaFilled(false);
      back.setBorderPainted(false);
      back.setPreferredSize(new Dimension(80, 80));
      back.setFocusable(false);
      JPanel goback = new JPanel();
      goback.add(back);
      goback.setVisible(false);
      JPanel extra = new JPanel();

      back.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            buttons.setVisible(true);
            onclickButtons.setVisible(false);
            goback.setVisible(false);
            start_game.setVisible(false);
         }          
      });      
      
      new_game.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            buttons.setVisible(false);
           flag_initiator = true;
            ////players panel for single/multi player and number of players///////
            onclickButtons.removeAll();
            JPanel players = new JPanel();
            players.setLayout(new BorderLayout());

            ///number panel for number of players in case of a multi player game////
            JPanel number = new JPanel();
            number.setLayout(new FlowLayout());
            
            
            
            numOfPlayers.setPreferredSize(new Dimension(80, 40));
            numOfPlayers.setBorder(new RoundedBorder(10));
            numOfPlayers.setForeground(Color.RED);
            numOfPlayers.setFont(new Font("Helvetica", Font.PLAIN, 20));
           
            numOfPlayers.addChangeListener(new ChangeListener() {
               public void stateChanged(ChangeEvent e) {
                  players_num = Integer.parseInt((((JSpinner)e.getSource()).getValue()).toString());
               }
            });
            numOfPlayers.setFocusable(false);
            number.add(numOfPlayers);

            JButton showIP = new JButton("Show my IP address");
            showIP.setBorder(new RoundedBorder(5));
            showIP.setBackground(new Color(128,128,128));
            showIP.setForeground(Color.BLACK);
            showIP.setFont(new Font("Helvetica", Font.PLAIN, 15));
            showIP.setPreferredSize(new Dimension(200, 40));
            showIP.setFocusable(false);

            showIP.addMouseListener(new java.awt.event.MouseAdapter() {
               public void mouseEntered(java.awt.event.MouseEvent evt) {
                     showIP.setBackground(new Color(204, 204, 204));
               }

               public void mouseExited(java.awt.event.MouseEvent evt) {
                     showIP.setBackground(new Color(128,128,128));
               }
            });

            showIP.addActionListener(new ActionListener() {
               public void actionPerformed(ActionEvent e) {
                  String x;
                  try {
                     x = Inet4Address.getLocalHost().getHostAddress();
                  }catch(UnknownHostException ex) {
                     x = "Unable to acquire IP address";
                  }
                  JLabel label = new JLabel(x,JLabel.CENTER);
                  label.setFont(new Font("Helvetica", Font.PLAIN, 20));
                  label.setForeground (new Color(51,102,153));
                  showIP.setVisible(false);
                  // number.remove(showIP);
                  number.add(label);
               }
            });
            
            number.add(showIP);



            //type panel for single/multi player game////
            JPanel type = new JPanel();
            type.setLayout(new FlowLayout());
            JRadioButton single = new JRadioButton("Single Player",true);
            single.setForeground (Color.RED);
            single.setFont(new Font("Helvetica", Font.PLAIN, 32));
            single.setFocusable(false);
            JRadioButton multi = new JRadioButton("Multi-player") ;
            multi.setForeground (Color.RED);
            multi.setFont(new Font("Helvetica", Font.PLAIN, 32));
            multi.setFocusable(false);

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
                     SwingUtilities.updateComponentTreeUI(frame);
                  }
               }           
            });

            ButtonGroup group1 = new ButtonGroup();             //Group the radio buttons.
            group1.add(single);
            group1.add(multi);

            type.add(single);
            type.add(multi);

            players.add(type,BorderLayout.NORTH);
            onclickButtons.add(players,BorderLayout.CENTER);
            onclickButtons.setVisible(true);
            goback.setVisible(true);
            start_game.setVisible(true);
         }          
      });

      join_game.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            buttons.setVisible(false);
           flag_initiator = false;
           players_num = 2;
            ///host panel for host address for joining a game///
            onclickButtons.removeAll();
            JPanel host = new JPanel();
            host.setLayout(new FlowLayout());
            JLabel label = new JLabel("Enter host IP address: ",JLabel.LEFT);
            label.setFont(new Font("Helvetica", Font.PLAIN, 20));
            label.setForeground (new Color(51,102,153));

            
            host_address = ip.getText();
            ip.setBorder(new RoundedBorder(10));
            ip.setBackground(new Color(255,230,179));   ///beige
            host.add(label);
            host.add(ip);
            onclickButtons.add(host,BorderLayout.CENTER);
            onclickButtons.setVisible(true);
            goback.setVisible(true);
            start_game.setVisible(true);
         }          
      });
      buttons.add(new_game);
      buttons.add(join_game);



      

      
      // input.add(levels,BorderLayout.NORTH);///
      // input.add(buttons,BorderLayout.CENTER);///
      // input.add(start_game,BorderLayout.SOUTH);////
      JButton gametitle = new JButton("Ping Pong Game");
      gametitle.setFocusable(false);
      gametitle.setForeground (new Color(51,102,153));
      gametitle.setFont(new Font("Helvetica", Font.PLAIN, 50));

      levels_back.add(goback).setLocation(1,1);
      levels_back.add(levels).setLocation(1,2);
      levels_back.add(extra).setLocation(1,3);
      
      input.add(gametitle).setLocation(1,1);
      input.add(levels_back).setLocation(2,1);
      input.add(buttons).setLocation(3,1);
      input.add(onclickButtons).setLocation(4,1);
      start_game.setVisible(false);
      input.add(start_game).setLocation(5,1);
      

      //////////////////////////

      contentPane.add(input,BorderLayout.CENTER);

      frame.setFocusable(false);
        
      frame.setVisible(true);
      frame.setSize(screenSize.width,screenSize.height);
   }

   private static class RoundedBorder implements Border {
      private int radius;
      RoundedBorder(int radius) {
         this.radius = radius;
      }
      public Insets getBorderInsets(Component c) {
         return new Insets(this.radius+1, this.radius+1, this.radius+2, this.radius);
      }
      public boolean isBorderOpaque() {
         return true;
      }
      public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
         g.drawRoundRect(x, y, width-1, height-1, radius, radius);
      }
   }
}