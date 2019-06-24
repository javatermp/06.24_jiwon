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
	BufferedReader is=null;
	DataOutputStream os=null;
	private JButton button;
	private PictureChange ch;
	private ImageIcon img;
	private JLabel la1, la2;
	private JTextField tf;
	private JPasswordField pf;
	private String id = null, pw = null;
	
	public Body1(PictureChange temp) {
		try {

			
			myClient = new Socket("localhost", PORT);

			is = new BufferedReader(new InputStreamReader(myClient.getInputStream()));
			os = new DataOutputStream(myClient.getOutputStream());

		} catch (UnknownHostException uhe) {
			uhe.printStackTrace();
		} catch (IOException error) {
			error.printStackTrace();
		}
		
		setLayout(null);
		ch = temp;

		la1 = new JLabel("ID");
		la2 = new JLabel("PW");
		tf = new JTextField();
		pf = new JPasswordField();

		setPreferredSize(new Dimension(500, 300));
		img = new ImageIcon("C:/Users/MELEE/eclipse-workspace/TP/src/TP/Background1.gif");
		ImageIcon normalIcon = new ImageIcon("C:/Users/Gram11/eclipse-workspace/Termproject/src/login1.png"); // 누르기전
		ImageIcon rolloverIcon = new ImageIcon("C:/Users/Gram11/eclipse-workspace/Termproject/src/login2.png"); // 눌렀을 때
		ImageIcon pressedIcon = new ImageIcon("C:/Users/Gram11/eclipse-workspace/Termproject/src/login2.png");
		
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
	

	public class ChangeListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			
				try {
				id = tf.getText();
				pw = pf.getText();
				
				os.writeBytes(""+(id)+"\n");
				os.writeBytes(""+(pw)+"\n");
				System.out.println(id);
				System.out.println(pw);
				
				String reply_id = is.readLine();
				String reply_pw=is.readLine();
				System.out.print(reply_id);
				System.out.print(reply_pw);
				if (id.equals(reply_id) && pw.equals(reply_pw)) {
					ch.change("picture2");
					os.close();
					is.close();
					}


//				myClient.close();
				}catch (UnknownHostException uhe) {
					uhe.printStackTrace();
				} catch (IOException error) {
					error.printStackTrace();
				} 


			

		}
	}
	

	


}

