import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.StringTokenizer;

public class GameFrame extends JFrame implements ActionListener {

    JButton hour;
    JButton minute;
    JButton second;

    JButton START;
    JButton RESET;

    JPanel panel1;
    JPanel panel2;

    javax.swing.Timer timer;

    boolean start = false;

    int ho = 0;
    int mi = 0;
    int se = 0;

    GameFrame() {
        this.setTitle("Timer");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(450, 220);
        this.getContentPane().setBackground(Color.LIGHT_GRAY);
        this.setLayout(null);

        timer = new javax.swing.Timer(1000, this);

        panel1 = new JPanel();
        panel1.setLayout(new GridLayout(1, 3, 10, 10));
        panel1.setBounds(0, 0, 450, 100);
        panel1.setBackground(Color.cyan);

        panel2 = new JPanel();
        panel2.setBounds(0, 100, 450, 250);
        panel2.setBackground(Color.CYAN);

        START = new JButton("Start");
        START.setBorder(BorderFactory.createEtchedBorder());
        START.setFont(new Font("MV BOLI", Font.PLAIN, 25));
        START.addActionListener(this);
        START.setFocusable(false);
        START.setBackground(Color.DARK_GRAY);
        START.setForeground(Color.CYAN);
        panel2.add(START);

        RESET = new JButton("Reset");
        RESET.setBorder(BorderFactory.createEtchedBorder());
        RESET.setFont(new Font("MV BOLI", Font.PLAIN, 25));
        RESET.addActionListener(this);
        RESET.setFocusable(false);
        RESET.setBackground(Color.DARK_GRAY);
        RESET.setForeground(Color.CYAN);
        panel2.add(RESET);

        hour = new JButton("Hour");
        hour.setBorder(BorderFactory.createEtchedBorder());
        hour.setFont(new Font("MV BOLI", Font.PLAIN, 25));
        hour.addActionListener(this);
        hour.setFocusable(false);
        hour.setBackground(Color.DARK_GRAY);
        hour.setForeground(Color.CYAN);
        panel1.add(hour);

        minute = new JButton("Minute");
        minute.setBorder(BorderFactory.createEtchedBorder());
        minute.setFont(new Font("MV BOLI", Font.PLAIN, 25));
        minute.addActionListener(this);
        minute.setFocusable(false);
        minute.setBackground(Color.DARK_GRAY);
        minute.setForeground(Color.CYAN);
        panel1.add(minute);

        second = new JButton("Second");
        second.setBorder(BorderFactory.createEtchedBorder());
        second.setFont(new Font("MV BOLI", Font.PLAIN, 25));
        second.addActionListener(this);
        second.setFocusable(false);
        second.setBackground(Color.DARK_GRAY);
        second.setForeground(Color.CYAN);
        panel1.add(second);

        this.add(panel1);
        this.add(panel2);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == hour) {
            if (hour.getText() == "Hour") {
                hour.setText("1 hour");
            } else if (Integer.parseInt(new StringTokenizer(hour.getText()).nextToken()) == 23) {
                hour.setText("0 hour");
            } else {
                int x = Integer.parseInt(new StringTokenizer(hour.getText()).nextToken());
                hour.setText(String.valueOf((int) x + 1) + " hour");
            }
        }

        if (e.getSource() == minute) {
            if (minute.getText() == "Minute") {
                minute.setText("1 minute");
            } else if (Integer.parseInt(new StringTokenizer(minute.getText()).nextToken()) == 59) {
                minute.setText("0 minute");
            } else {
                int x = Integer.parseInt(new StringTokenizer(minute.getText()).nextToken());
                minute.setText(String.valueOf((int) x + 1) + " minute");
            }
        }

        if (e.getSource() == second) {
            if (second.getText() == "Second") {
                second.setText("1 second");
            } else if (Integer.parseInt(new StringTokenizer(second.getText()).nextToken()) == 59) {
                second.setText("0 second");
            } else {
                int x = Integer.parseInt(new StringTokenizer(second.getText()).nextToken());
                second.setText(String.valueOf((int) x + 1) + " second");
            }
        }

        if (e.getSource() == RESET) {
            hour.setText("Hour");
            minute.setText("Minute");
            second.setText("Second");
            START.setText("Start");

            timer.stop();
            start = false;
            this.mi = 0;
            this.ho = 0;
            this.se = 0;
        }

        if (e.getSource() == START) {
            int ho = 0;
            int mi = 0;
            int se = 0;

            try { ho = Integer.parseInt(new StringTokenizer(hour.getText()).nextToken()); } catch (Exception error) {}

            try { mi = Integer.parseInt(new StringTokenizer(minute.getText()).nextToken()); } catch (Exception error) {}

            try { se = Integer.parseInt(new StringTokenizer(second.getText()).nextToken()); } catch (Exception error) {}

            if (ho == 0 || mi == 0 || se == 0) return;

            if (start) {
                if (START.getText() == "Stop" && start) {
                    timer.stop();
                    START.setText("Resume");
                } else if (START.getText() == "Resume" && start) {
                    timer.start();
                    START.setText("Stop");
                }
            } else {
                start = true;
                timer.start();
            }
            
        }

        if (START.getText() == "Start" && start) {
            START.setText("Stop");
        }

        if (start) {

            int ho = 0;
            int mi = 0;
            int se = 0;

            try { ho = Integer.parseInt(new StringTokenizer(hour.getText()).nextToken()); } catch (Exception error) {}

            try { mi = Integer.parseInt(new StringTokenizer(minute.getText()).nextToken()); } catch (Exception error) {}

            try { se = Integer.parseInt(new StringTokenizer(second.getText()).nextToken()); } catch (Exception error) {}

            int tmp = se;
            {
                int min = (ho * 60); // min 
                se += (min * 60);   // sec

                se += (mi * 60);   // sec
                try { se += Integer.parseInt(new StringTokenizer(second.getText()).nextToken()); } catch (Exception error) {}

                se -= tmp;
            }

            if (this.se <= 0) {
                this.se = se;
            }
            
            if (this.se >= 60) {
                if (this.se / 60 >= 60) {
                    // int min = se / 60;
                    // hour.setText((int) min / 60 + " hour " + (min - min / 60) + " min" + (se -(min * 60)) + " sec");

                    // hour.setText((int) min / 60 + " hour");
                    // minute.setText(((int) min - (min / 60) * 60) + " min");
                    // second.setText(((int) this.se - (min * 60)) + " sec");
                    // second.setText(((int) this.se - (min / 60) * 60) + " sec");
                    
                    int min = this.se / 60; // minutes
                    hour.setText((int) min / 60 + " hour");
                    minute.setText(((int) min - (min / 60) * 60)  + " min");
                    second.setText(((int) this.se - (min * 60)) + " sec");
                } else {
                    hour.setText("0 hour");
                    int min = this.se / 60;
                    minute.setText(((int)min) + " min");
                    second.setText(((int)this.se - (min * 60)) + " min");
                    // System.out.println(min + " min " + (se - (min * 60)) + " sec");
                }
            } else {
                minute.setText("0 min");
                second.setText(this.se + " sec");
            }
            this.se--;

            if (this.se == 0) {
                timer.stop();
                hour.setText("Timer");
                minute.setText("was");
                second.setText("completed");
                START.setText("Start");
            }
        }
    }
}
