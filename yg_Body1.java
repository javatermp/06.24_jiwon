package Term;

import java.awt.*;
import java.awt.event.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.*;
import java.net.*;

public class Body1 extends JPanel {
	private JButton button;
	private PictureChange ch;
	private ImageIcon img;
	private JLabel la1, la2;
	private JTextField tf;
	private JPasswordField pf;
	private String id = null, pw = null;
	
	static int count = 1;

	Socket myClient = null;
	BufferedReader br = null;
	PrintWriter out = null;

	public Body1(PictureChange temp) {
		try {
			myClient = new Socket("127.0.0.1", 8189);
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			br = new BufferedReader(new InputStreamReader(myClient.getInputStream()));
			out = new PrintWriter(myClient.getOutputStream(), true);
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		setLayout(null);
		ch = temp;
		
		la1 = new JLabel("ID");
		la2 = new JLabel("PW");
		tf = new JTextField();
		pf = new JPasswordField();

		setPreferredSize(new Dimension(500, 300));
		img = new ImageIcon("C:/Users/MELEE/eclipse-workspace/Term/src/Term/Background1.gif");
		
		ImageIcon normalIcon = new ImageIcon("C:/Users/MELEE/eclipse-workspace/Term/src/Term/login1.png"); // 누르기전
		ImageIcon rolloverIcon = new ImageIcon("C:/Users/MELEE/eclipse-workspace/Term/src/Term/login2.png"); // 눌렀을 때
		ImageIcon pressedIcon = new ImageIcon("C:/Users/MELEE/eclipse-workspace/Term/src/Term/login2.png");

		la1.setBounds(120, 40, 50, 20);
		la1.setForeground(Color.pink);
		la1.setFont(new Font("Serif", Font.BOLD, 15));
		tf.setBounds(150, 40, 200, 35);
		add(la1);
		add(tf);
		
		la2.setBounds(120, 120, 50, 20);
		la2.setForeground(Color.pink);
		la2.setFont(new Font("Serif", Font.BOLD, 15));
		pf.setBounds(150, 120, 200, 35);
		add(la2);
		add(pf);
		
		button = new JButton("sign in", normalIcon);
		button.setSize(130, 50);
		button.setLocation(180, 200);
		button.setPressedIcon(pressedIcon);
		button.setRolloverIcon(rolloverIcon);
		button.addActionListener(new ChangeListener());
		add(button);
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(img.getImage(), 0, 0, this);
	}
	
	private class ChangeListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			id = tf.getText();
			pw = pf.getText();
			int gold = 1234;
			if (id.equals("st1") && pw.equals("1")) {
				out.print(count + " ");
				out.println("a");
				count++;
				try {
					System.out.println(br.readLine() + " >> Body1");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Body2.gold.setText("골드 : " + gold);
				ch.change("picture2");
			}
			if (id.equals("st2") && pw.equals("2")) {
				ch.change("picture2");
			}
		}
	}
}
