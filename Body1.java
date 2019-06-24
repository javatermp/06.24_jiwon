
//Body1.java

import java.awt.*;
import java.awt.event.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.*;
import java.net.*;

public class Body1 extends JPanel {
	static final int PORT = 3012;
	Socket myClient;
	BufferedReader is = null;
	DataOutputStream os = null;
	private JButton button;
	private PictureChange ch;
	private ImageIcon img;
	private JLabel la1, la2;
	private JTextField tf;
	private JPasswordField pf;
	public static String id = null, pw = null;
	public static String reply_id = null, reply_pw = null;
	public static String reply_gold = null, reply_exp = null, reply_Item1 = null, reply_Item2 = null,
			reply_Item3 = null, reply_Item4 = null;

	public Body1(PictureChange temp) {
//		try {
//
//			myClient = new Socket("localhost", PORT);
//
//			is = new BufferedReader(new InputStreamReader(myClient.getInputStream()));
//			os = new DataOutputStream(myClient.getOutputStream());
//
//		} catch (UnknownHostException uhe) {
//			uhe.printStackTrace();
//		} catch (IOException error) {
//			error.printStackTrace();
//		}

		setLayout(null);
		ch = temp;

		la1 = new JLabel("ID");
		la2 = new JLabel("PW");
		tf = new JTextField();
		pf = new JPasswordField();

		setPreferredSize(new Dimension(500, 300));
		img = new ImageIcon("C:/Users/MELEE/eclipse-workspace/TP/src/TP/Background1.gif");

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

		button = new JButton("sign in");
		button.setSize(100, 50);
		button.setLocation(200, 200);

		button.addActionListener(new ChangeListener());
		add(button);

	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(img.getImage(), 0, 0, this);
	}

	public class ChangeListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {

			try {

				myClient = new Socket("localhost", PORT);

				is = new BufferedReader(new InputStreamReader(myClient.getInputStream()));
				os = new DataOutputStream(myClient.getOutputStream());

			} catch (UnknownHostException uhe) {
				uhe.printStackTrace();
			} catch (IOException error) {
				error.printStackTrace();
			}
			try {

				id = tf.getText();
				pw = pf.getText();

				os.writeBytes("" + (id) + "\n");
				os.writeBytes("" + (pw) + "\n");
				System.out.println(id);
				System.out.println(pw);

				reply_id = is.readLine();
				reply_pw = is.readLine();
				reply_gold = is.readLine();
				reply_exp = is.readLine();
				reply_Item1 = is.readLine();
				reply_Item2 = is.readLine();
				reply_Item3 = is.readLine();
				reply_Item4 = is.readLine();

//				System.out.print(reply_id);
//				System.out.print(reply_pw);
//				System.out.println(reply_gold);
//				System.out.println(reply_exp);

				if (id.equals(reply_id) && pw.equals(reply_pw)) {
					Body2.gold.setText("골드 : " + reply_gold);
					Body2.exp.setText("경험치 : " + reply_exp);
					Body2.item1.setText("Item1 : " + reply_Item1);
					Body2.item2.setText("Item2 : " + reply_Item2);
					Body2.item3.setText("Item3 : " + reply_Item3);
					Body2.item4.setText("Item4 : " + reply_Item4);
					ch.change("picture2");

				}
				
				is.close();
				os.close();
				myClient.close();

			} catch (UnknownHostException uhe) {
				uhe.printStackTrace();
			} catch (IOException error) {
				error.printStackTrace();
			}

		}
	}

}
