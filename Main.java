package org.example;
import org.example.email.GMailSender;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class Main {

    private static ResultSet rs = null;
    static int s=59,m=1;
    static int qcount=1;
    static int score = 0;
    static  String ans = "";
    public static void main(String[] args) {


        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/questionbank", "root", "639838");

            Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs = stmt.executeQuery("select * from questionbank where qtag = 'GK'");
            rs.next();

        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex);
        }
        Font plain = new Font("Serif", 0, 30);
        Font bold1 = new Font("Serif", 1, 30);
        Font bold = new Font("Serif", 1, 20);

        JFrame f = new JFrame("UPSC Practice Quiz");
        f.setSize(1100, 1000);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setLocationRelativeTo(null);

        f.setLayout(null);


        JFrame login = new JFrame("Login Page");
        ImageIcon ic1=new ImageIcon("C:\\Users\\91639\\Downloads\\2384075.jpg");
        JLabel l2=new JLabel(ic1);
        login.setContentPane(l2);

        JLabel email = new JLabel("Email");
        JTextField emailfield = new JTextField();
        JButton loginButton = new JButton("Enter");
        loginButton.setBounds(200,270,100,50);
        email.setBounds(100,100,300,50);
        emailfield.setBounds(250,100,200,50);

        email.setFont(bold);
        email.setForeground(Color.gray);
        loginButton.setForeground(Color.black);

        JTextField password = new JTextField();
        password.setBounds(250,180,200,50);
        JLabel pass = new JLabel("Username");

        pass.setBounds(100,180,100,50);
        pass.setFont(plain);
        pass.setFont(bold);

        pass.setForeground(Color.gray);

        login.add(pass);
        login.add(password);

        loginButton.setBackground(new Color(15, 149, 220));
        login.add(emailfield);
        login.add(email);
        login.add(loginButton);

        login.setSize(600, 500);
        login.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        login.setLocationRelativeTo(null);

        login.setLayout(null);
        login.setVisible(true);


        ImageIcon ic=new ImageIcon("C:\\Users\\91639\\Downloads\\2384075.jpg");
        JLabel l1=new JLabel(ic);
        f.setContentPane(l1);

        Image icon = Toolkit.getDefaultToolkit().getImage("C:\\Users\\91639\\Downloads\\istockphoto-1341560999-1024x1024.jpg");
        f.setIconImage(icon);


        JLabel question = new JLabel();
        question.setFont(bold);
        JRadioButton opa = new JRadioButton();
        opa.setFont(plain);
        JRadioButton opb = new JRadioButton();
        opb.setFont(plain);
        JRadioButton opc = new JRadioButton();
        opc.setFont(plain);
        JRadioButton opd = new JRadioButton();
        opd.setFont(plain);

        JButton next = new JButton("NEXT");
        next.setFont(bold);
        JButton prev = new JButton("PREVIOUS");
        prev.setFont(bold);
        JButton clear = new JButton("CLEAR");
        clear.setFont(bold);
        JButton end = new JButton("END TEST");
        end.setFont(bold);
        ButtonGroup bg = new ButtonGroup();
        bg.add(opa);
        bg.add(opb);
        bg.add(opc);
        bg.add(opd);

        end.setBounds(40, 550, 150, 30);
        prev.setBounds(250, 550, 150, 30);
        next.setBounds(450, 550, 150, 30);
        clear.setBounds(650, 550, 150, 30);

        next.setBackground(Color.decode("#7d53cc"));
        prev.setBackground(Color.decode("#7d53cc"));
        clear.setBackground(Color.decode("#7d53cc"));
        end.setBackground(Color.RED);
        f.add(next);
        f.add(prev);
        f.add(clear);
        f.add(end);


        f.add(opa);
        opa.setBounds(180, 240, 450, 35);
        f.add(opb);
        opb.setBounds(180, 310, 450, 35);
        f.add(opc);
        opc.setBounds(180, 380, 450, 35);
        f.add(opd);
        opd.setBounds(180, 450, 450, 35);


        opa.setFont(plain);
        opb.setFont(plain);
        opc.setFont(plain);
        opd.setFont(plain);
        opa.setForeground(Color.BLUE);
        opa.setBackground(new Color(135,206,235));
        opb.setBackground(new Color(135,206,235));
        opb.setForeground(Color.BLUE);
        opc.setBackground(new Color(135,206,235));
        opc.setForeground(Color.BLUE);
        opd.setBackground(new Color(135,206,235));
        opd.setForeground(Color.BLUE);



        f.add(question);
        question.setBounds(100, 70, 1000, 200);
        question.setFont(bold1);
        question.setForeground(Color.BLACK);


        try {
            String q = rs.getString(1);
            String a = rs.getString(2);
            String b = rs.getString(3);
            String c = rs.getString(4);
            String d = rs.getString(5);
            question.setText("Q"+qcount+" :- "+ q);
            opa.setLabel(a);
            opb.setLabel(b);
            opc.setLabel(c);
            opd.setLabel(d);
        } catch (SQLException exc) {
            System.out.println(exc);
        }



        //Timer
        JLabel timer=new JLabel();

        Thread timer_thread=new Thread(
                ()->{
                    while (true){
                        s--;
                        if(s==0){
                            m--;s=59;
                        }
                        try{
                            Thread.sleep(1000);
                        }
                        catch(InterruptedException ex) {}

                        timer.setText("Time Left: " + m + " : " + s);
                        if(m==-1){
                            try {
                                ///resetColors();
                                rs.next();
                                String q = rs.getString(1);
                                String a = rs.getString(2);
                                String b = rs.getString(3);
                                String c = rs.getString(4);
                                String d = rs.getString(5);
                                question.setText("Q"+qcount+" :- "+ q);
                                opa.setLabel(a);
                                opb.setLabel(b);
                                opc.setLabel(c);
                                opd.setLabel(d);
                                m=1;s=59;
                            } catch (SQLException exc) {
                                System.out.println(exc);
                            }
                        }
                    }

                }
        );

        timer_thread.start();

        f.add(timer);
        timer.setBounds(700, 40, 300, 100);
        timer.setFont(bold1);
        timer.setForeground(Color.RED);
        JLabel emailaddress = new JLabel();
        emailaddress.setBounds(20,40,400,100);
        f.add(emailaddress);

        JLabel invalid = new JLabel();
        invalid.setBounds(210,270,500,200);
        login.add(invalid);
        class MyListener implements ActionListener, ItemListener {

            public void resetColors() {

                opa.setBackground(new Color(135,206,235));
                opb.setBackground(new Color(135,206,235));
                opc.setBackground(new Color(135,206,235));
                opd.setBackground(new Color(135,206,235));
            }
            public void actionPerformed(ActionEvent ae) {

                if(ae.getSource()==loginButton){
                    if(!emailfield.getText().contains("@gmail.com")){
                        invalid.setText("Please enter valid email");
                        invalid.setForeground(new Color(218, 18, 18, 145));
                    } else {
                        login.setVisible(false);
                        f.setVisible(true);
                        m=1;s=59;
                        emailaddress.setText("Hello, " + password.getText());
                        emailaddress.setFont(bold1);
                    }

                }

                if (ae.getSource() == clear) {
                    bg.clearSelection();
                    resetColors();
                }


                else if (ae.getSource() == end) {
                    //display the result page

                    f.setVisible(false);


                    //logic for sending the email with Java Mail API
                    GMailSender gMailSender = new GMailSender();

                    String to = emailfield.getText();
                    String from = "Nishant.12116456@lpu.in";
                    String subject = "Score Card for UPSC Quiz";
                    String content = "Hey "+password.getText()+",\n"+"Your score in UPSC Quiz is "+ score +"/"+(qcount)+". \n" + ans +
                            "\nResources Link- https://youtu.be/cr3QuAIjM70?si=C1x5Y4hcVGlT1ISl  \nKeep Learning! \n" +
                            "Regards \nNishant Maithil";
                    boolean b = gMailSender.sendEmail(to,from,subject,content);
                    if(b){
                        System.out.println( "Email working");
                    } else{
                        System.out.println("Problem");
                    }

                }

                else if (ae.getSource() == next) {

                    resetColors();
                    qcount=qcount+1;
                    m=1;s=59;

                      bg.clearSelection();
                    try {

                         ans += (qcount-1)+". "+rs.getString(1) + "\nAnswser-"+ rs.getString(7) +"\n";

                        rs.next();

                        String q = rs.getString(1);
                        String a = rs.getString(2);
                        String b = rs.getString(3);
                        String c = rs.getString(4);
                        String d = rs.getString(5);

                        question.setText("Q"+qcount+" :- "+ q);
                        opa.setText(a);;
                        opb.setText(b);
                        opc.setText(c);
                        opd.setText(d);
                    }

                    catch(SQLException ie){
                        System.out.println(ie);
                    }


                } else if (ae.getSource() == prev) {
                    resetColors();
                      bg.clearSelection();
                    try {


                        if (rs.previous()) {

                            String q = rs.getString(1);
                            String a = rs.getString(2);
                            String b = rs.getString(3);
                            String c = rs.getString(4);
                            String d = rs.getString(5);
                            qcount--;
                            question.setText("Q"+qcount+" :- "+ q);
                            opa.setText(a);
                            opb.setText(b);
                            opc.setText(c);
                            opd.setText(d);
                        } else {
                            //Beginning
                        }
                    } catch (SQLException exec) {
                        System.out.println(exec);
                    }
                }
            }

            public void itemStateChanged(ItemEvent e) {
                if (opa.isSelected()) {
                    try {

                        opb.setBackground(new Color(135,206,235));
                        opc.setBackground(new Color(135,206,235));
                        opd.setBackground(new Color(135,206,235));
                       // bg.clearSelection();
                        if (opa.getText().equals(rs.getString(7))) {
                            opa.setBackground(Color.GREEN);
                            score++;

                        } else {
                            opa.setBackground(Color.RED);

                        }
                    } catch (SQLException ex) {
                        System.out.println(ex);
                    }

                }


                else if (opb.isSelected()) {
                    try {

                        opa.setBackground(new Color(135,206,235));
                        opc.setBackground(new Color(135,206,235));
                        opd.setBackground(new Color(135,206,235));
                        //bg.clearSelection();
                        if (opb.getText().equals(rs.getString(7))) {
                            opb.setBackground(Color.GREEN);
                            score++;

                        } else {
                            opb.setBackground(Color.RED);
                        }
                    } catch (SQLException ex) {
                        System.out.println(ex);
                    }
                }


                if (opc.isSelected()) {
                    try {

                        opb.setBackground(new Color(135,206,235));
                        opa.setBackground(new Color(135,206,235));
                        opd.setBackground(new Color(135,206,235));
                       // bg.clearSelection();
                        if (opc.getText().equals(rs.getString(7))) {
                            opc.setBackground(Color.GREEN);
                            score++;
                        } else {
                            opc.setBackground(Color.RED);
                        }
                    } catch (SQLException ex) {
                        System.out.println(ex);
                    }
                }


                else if (opd.isSelected()) {
                    try {

                        opb.setBackground(new Color(135,206,235));
                        opc.setBackground(new Color(135,206,235));
                        opa.setBackground(new Color(135, 193,235));
                      //  bg.clearSelection();
                        if (opd.getText().equals(rs.getString(7))) {
                            opd.setBackground(Color.GREEN);
                            score++;

                        } else {
                            opd.setBackground(Color.RED);
                        }
                    } catch (SQLException ex) {
                        System.out.println(ex);
                    }
                }
            }
        }


        MyListener ml = new MyListener();
        end.addActionListener(ml);
        clear.addActionListener(ml);
        prev.addActionListener(ml);
        next.addActionListener(ml);
        loginButton.addActionListener(ml);


        opa.addItemListener(ml);
        opb.addItemListener(ml);
        opc.addItemListener(ml);
        opd.addItemListener(ml);



    }
}