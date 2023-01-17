import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
class Homepage implements ActionListener{
    JFrame frame2 = new JFrame();
    static JTextField p1_name = new JTextField();
    static JTextField p2_name= new JTextField();
    JLabel p1_label; 
    JLabel p2_label;
    JButton start= new JButton();
    JButton exit= new JButton();
    JLabel backround_label= new JLabel();
    JTextField heading_text= new JTextField();
    JPanel heading_panel= new JPanel();
    Homepage(){
        frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // this.setLocationRelativeTo(null);
        frame2.setSize(800,800);
        frame2.setTitle("TIC-TAC-TOE");
        frame2.setLayout(new BorderLayout());
        Image icon = Toolkit.getDefaultToolkit().getImage("tic-tac-toe.png");  
        frame2.setIconImage(icon);

        backround_label=new JLabel(new ImageIcon("tictoe.jpg"));
        backround_label.setSize(800,800);
        

        heading_text.setText("TicTacToe");
        heading_text.setBackground(new Color(25,25,25));
        heading_text.setForeground(new Color(25,255,0));
        heading_text.setFont(new Font("Monospaced",Font.BOLD,75));
        heading_text.setOpaque(true);
        heading_text.setHorizontalAlignment(JLabel.CENTER);
        heading_text.setEditable(false);

        heading_panel.setLayout(new BorderLayout());
        heading_panel.setBounds(0, 0, 800, 100);
        heading_panel.add(heading_text);

        

        p1_name.setBounds(400, 300, 250, 70);
        p2_name.setBounds(400, 400, 250, 70);
        p1_name.setBackground(new Color(25,25,25));
        p2_name.setBackground(new Color(25,25,25));
        p1_name.setForeground(Color.white);
        p2_name.setForeground(Color.white);
        p1_name.setFont(new Font("Britannic Bold",Font.PLAIN,30));
        p2_name.setFont(new Font("Britannic Bold",Font.PLAIN,30));
        p1_name.setHorizontalAlignment(JLabel.CENTER);
        p2_name.setHorizontalAlignment(JLabel.CENTER);

        p1_label = new JLabel("PLAYER 1: ");
        p2_label = new JLabel("PLAYER 2: ");
        p1_label.setBounds(200, 300, 200, 70);
        p2_label.setBounds(200, 400, 200, 70);
        p1_label.setFont(new Font("Copperplate Gothic Bold",Font.BOLD,30));
        p2_label.setFont(new Font("Copperplate Gothic Bold",Font.BOLD,30));
        p1_label.setForeground(Color.white);
        p2_label.setForeground(Color.white);


        exit.setBounds(450, 600, 150, 50);
        exit.setBackground(Color.red);
        exit.setForeground(Color.white);
        exit.setText("EXIT");
        exit.setFocusable(false);
        exit.setFont(new Font("Britannic Bold",Font.PLAIN,30));

        start.setBounds(200, 600, 150, 50);
        start.setBackground(new Color(25,255,0));
        start.setForeground(Color.white);
        start.setText("START");
        start.setFocusable(false);
        start.setFont(new Font("Britannic Bold",Font.PLAIN,30));   
        
        start.addActionListener(this);
        exit.addActionListener(this);


        backround_label.add(heading_panel,BorderLayout.NORTH);
        backround_label.add(p1_name);
        backround_label.add(p2_name);
        backround_label.add(p1_label);
        backround_label.add(p2_label);
        backround_label.add(start);
        backround_label.add(exit);


        
        frame2.add(backround_label,BorderLayout.CENTER);
        frame2.setVisible(true);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==exit){
            System.exit(0);
        }
        if(e.getSource()==start){
           frame2.dispose();
           p1_name.setEnabled(false);
           p2_name.setEnabled(false);
           TicTacToe obj = new TicTacToe();
        }
        
        
    }
}
class TicTacToe implements ActionListener {
    JFrame frame = new JFrame();
    Random random = new Random();
    JPanel titlePanel = new JPanel();
    JPanel btnpanel = new JPanel();
    JTextField textfield = new JTextField();
    JButton[] btns = new JButton[9];
    Boolean player1_turn;
    String name1= Homepage.p1_name.getText();
    String name2= Homepage.p2_name.getText();
    int draw=0;
    int i=0;
    int pqr=0;
    int flag=0;


    TicTacToe(){
        Image icon = Toolkit.getDefaultToolkit().getImage("tic-tac-toe.png");    
        frame.setIconImage(icon);
        frame.setTitle("TIC-TAC-TOE");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800,800);
        frame.setLayout(new BorderLayout());
        frame.setLocationRelativeTo(null);
        frame.getContentPane().setBackground(new Color(50,50,50));

        textfield.setText("TicTacToe");
        textfield.setBackground(new Color(25,25,25));
        textfield.setForeground(new Color(25,255,0));
        textfield.setFont(new Font("Monospaced",Font.BOLD,75));
        textfield.setOpaque(true);
        textfield.setHorizontalAlignment(JLabel.CENTER);
        textfield.setEditable(false);

        titlePanel.setLayout(new BorderLayout());
        titlePanel.setBounds(0, 0, 800, 100);
        titlePanel.add(textfield);

        btnpanel.setLayout(new GridLayout(3,3));
        btnpanel.setBackground(Color.GRAY);

        for(int i=0;i<9;i++){
            btns[i] = new JButton();
            btnpanel.add(btns[i]);
            btns[i].addActionListener(this);
            btns[i].setFocusable(false);
            btns[i].setFont(new Font("MV Boli",Font.BOLD,120));
        }
        
        frame.add(titlePanel,BorderLayout.NORTH);
        frame.add(btnpanel);
        frame.setVisible(true);
        
        playeTurn();


    }
    public void actionPerformed(ActionEvent e){
        for(i=0;i<9;i++){
            if(e.getSource()==btns[i]){
                if(player1_turn){
                    if(btns[i].getText()==""){
                        btns[i].setForeground(new Color(255,127,127));
                        btns[i].setText("X");
                        player1_turn=false;
                        textfield.setText(name2+"'s Turn");
                        flag++;
                        check();
                        }
                    }
                    else if(player1_turn==false){
                        if(btns[i].getText()==""){
                            btns[i].setForeground(new Color(51,153,255));
                            btns[i].setText("O");
                            player1_turn=true;
                            textfield.setText(name1+"'s Turn");
                            flag++;
                            check();
                        }
                    }
                }
        }
        // draw();

    }
    public void playeTurn(){
        try{
            Thread.sleep(1000);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        if(random.nextInt(2)==0){
            player1_turn = true;
            textfield.setText(name1+"'s Turn");
        }
        else{
            player1_turn = false;
            textfield.setText(name2+"'s Turn");
        }
    }
    public void check(){
        
        //x-Check()
        if((btns[0].getText()=="X") && (btns[1].getText()=="X") && (btns[2].getText()=="X")){
            xWins(0,1,2);
        }
        else if((btns[3].getText()=="X") && (btns[4].getText()=="X") && (btns[5].getText()=="X")){
            xWins(3,4,5);
        }
        else if((btns[6].getText()=="X") && (btns[7].getText()=="X") && (btns[8].getText()=="X")){
            xWins(6,7,8);
        }
        else if((btns[0].getText()=="X") && (btns[3].getText()=="X") && (btns[6].getText()=="X")){
            xWins(0,3,6);
        }
        else if((btns[1].getText()=="X") && (btns[4].getText()=="X") && (btns[7].getText()=="X")){
            xWins(1,4,7);
        }
        else if((btns[2].getText()=="X") && (btns[5].getText()=="X") && (btns[8].getText()=="X")){
            xWins(2,5,8);
        }
        else if((btns[0].getText()=="X") && (btns[4].getText()=="X") && (btns[8].getText()=="X")){
            xWins(0,4,8);
        }
        else if((btns[2].getText()=="X") && (btns[4].getText()=="X") && (btns[6].getText()=="X")){
            xWins(2,4,6);
        }
        //Y-Check
        else if((btns[0].getText()=="O") && (btns[1].getText()=="O") && (btns[2].getText()=="O")){
            oWins(0,1,2);
        }
        else if((btns[3].getText()=="O") && (btns[4].getText()=="O") && (btns[5].getText()=="O")){
            oWins(3,4,5);
        }
        else if((btns[6].getText()=="O") && (btns[7].getText()=="O") && (btns[8].getText()=="O")){
            oWins(6,7,8);
        }
        else  if((btns[0].getText()=="O") && (btns[3].getText()=="O") && (btns[6].getText()=="O")){
            oWins(0,3,6);
        }
        else  if((btns[1].getText()=="O") && (btns[4].getText()=="O") && (btns[7].getText()=="O")){
            oWins(1,4,7);
        }
        else  if((btns[2].getText()=="O") && (btns[5].getText()=="O") && (btns[8].getText()=="O")){
            oWins(2,5,8);
        }
        else  if((btns[0].getText()=="O") && (btns[4].getText()=="O") && (btns[8].getText()=="O")){
            oWins(0,4,8);
        }
        else if((btns[2].getText()=="O") && (btns[4].getText()=="O") && (btns[6].getText()=="O")){
            oWins(2,4,6);
        }
        else if(flag==9){
            textfield.setText("It's a Draw!!");
        }
         
    }
    public void xWins(int a, int b , int c){
        btns[a].setBackground(new Color(57,255,20));
        btns[b].setBackground(new Color(57,255,20));
        btns[c].setBackground(new Color(57,255,20));
        for(int i=0;i<9;i++){
            btns[i].setEnabled(false);
        }
        textfield.setText(name1+" Wins");
    }
    public void oWins(int a , int b, int c){
        btns[a].setBackground(new Color(57,255,20));
        btns[b].setBackground(new Color(57,255,20));
        btns[c].setBackground(new Color(57,255,20));
        for(int i=0;i<9;i++){
            btns[i].setEnabled(false);
        }
        textfield.setText(name2+" Wins");
    }
    // public void draw(){
    //     int a=0;
    //     for(int i=0;i<9;i++){
    //         if(btns[i].getText()!=""){
    //             draw++;
    //         }
    //     }
    //     if(draw==9){
    //         if(textfield.getText()!="X Wins" && textfield.getText()!="O Wins"){
    //             textfield.setText("dr");
    //         }
    //     }
        // return draw;
        // if(a==9){
        //     draw=true;
        // }

}

public class TicTacToeMain{
public static void main(String[] args) {
    Homepage obj2 = new Homepage();
}
}