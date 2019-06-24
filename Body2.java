//Body2.java

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import java.io.*;
import java.net.*;

public class Body2 extends JPanel {
	static final int PORT=3012;
	Socket myClient;
	BufferedReader is=null;
	DataOutputStream os=null;
	private JButton button;
	private JButton map1, map2, map3, map4;
	private static JTextArea log;
	private JScrollPane scroll;
	public static JLabel gold, exp, item1, item2, item3, item4;
	private JLabel getitem;
	private PictureChange ch;
	static int num;
	static String text;
	static boolean judge1 = true;
	static boolean judge2 = true;
	static boolean judge3 = true;
	static boolean judge4 = true;
	static int current_gold = 0, current_exp = 0;
	static int current_item1 = 0, current_item2 = 0, current_item3 = 0, current_item4 = 0;
	private ImageIcon img;

	public Body2(PictureChange temp) throws ClassNotFoundException {
		try {

			myClient=new Socket("localhost", PORT);
			
			File file=new File("gamedata.txt");
			FileReader fr=new FileReader(file);
			
			
			int reply_gold = (int)fr.read();
			int reply_exp = (int)fr.read();
			int reply_Item1 = (int)fr.read();
			int reply_Item2 = (int)fr.read();
			int reply_Item3 =(int)fr.read();
			int reply_Item4 = (int)fr.read();
			
			System.out.print(reply_gold);
			System.out.print("\n");
			System.out.print(reply_exp);
			System.out.print("\n");
			System.out.print(reply_Item1);
			System.out.print("\n");
			System.out.print(reply_Item2);
			System.out.print("\n");
			System.out.print(reply_Item3);
			System.out.print("\n");
			System.out.print(reply_Item4);
			
			fr.close();
		} catch (UnknownHostException uhe) {
			uhe.printStackTrace();
		} catch (IOException error) {
			error.printStackTrace();
		} 
		setLayout(null);
		ch = temp;

		
		
		
		setPreferredSize(new Dimension(800, 700));
		img = new ImageIcon("C:/Users/MELEE/eclipse-workspace/TP/src/TP/Background2.gif");

		log = new JTextArea();
		log.setFont(new Font("Serif", Font.PLAIN, 14));
		log.setDisabledTextColor(Color.black);
		log.disable();
		scroll = new JScrollPane(log);
		scroll.setLocation(50, 50);
		scroll.setSize(400, 450);
		add(scroll);

		exp = new JLabel("경험치 : ");
		exp.setBounds(500, 50, 130, 30);
		exp.setForeground(Color.magenta); // ID 글자색(핑크)
		exp.setFont(new Font("Serif", Font.BOLD, 15));
		add(exp);

		gold = new JLabel("골드 : ");
		gold.setBounds(650, 50, 130, 30);
		gold.setForeground(Color.ORANGE); // ID 글자색(핑크)
		gold.setFont(new Font("Serif", Font.BOLD, 15));
		add(gold);

		getitem = new JLabel("획득 아이템 : ");
		getitem.setBounds(500, 100, 100, 30);
		getitem.setForeground(Color.PINK); // ID 글자색(핑크)
		getitem.setFont(new Font("Serif", Font.BOLD, 15));
		add(getitem);

		item1 = new JLabel("Item1 : 0");
		item1.setBounds(500, 150, 100, 30);
		item1.setForeground(Color.cyan); // ID 글자색(핑크)
		item1.setFont(new Font("Serif", Font.BOLD, 15));
		add(item1);

		item2 = new JLabel("Item2 : 0");
		item2.setBounds(500, 200, 100, 30);
		item2.setForeground(Color.cyan); // ID 글자색(핑크)
		item2.setFont(new Font("Serif", Font.BOLD, 15));
		add(item2);

		item3 = new JLabel("Item3 : 0");
		item3.setBounds(500, 250, 100, 30);
		item3.setForeground(Color.cyan); // ID 글자색(핑크)
		item3.setFont(new Font("Serif", Font.BOLD, 15));
		add(item3);

		item4 = new JLabel("Item4 : 0");
		item4.setBounds(500, 300, 100, 30);
		item4.setForeground(Color.cyan); // ID 글자색(핑크)
		item4.setFont(new Font("Serif", Font.BOLD, 15));
		add(item4);

		button = new JButton("게임종료");
		button.setSize(150, 75);
		button.setLocation(300, 600);
		button.addActionListener(new ChangeListener());
		add(button);

		map1 = new JButton("맵1");
		map1.setSize(70, 50);
		map1.setLocation(500, 350);
		map1.addActionListener(new Map1Listener());
		add(map1);

		map2 = new JButton("맵2");
		map2.setSize(70, 50);
		map2.setLocation(600, 350);
		map2.addActionListener(new Map2Listener());
		add(map2);

		map3 = new JButton("맵3");
		map3.setSize(70, 50);
		map3.setLocation(500, 450);
		map3.addActionListener(new Map3Listener());
		add(map3);

		map4 = new JButton("맵4");
		map4.setSize(70, 50);
		map4.setLocation(600, 450);
		map4.addActionListener(new Map4Listener());
		add(map4);
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(img.getImage(), 0, 0, this);
	}

	static void addLog(Object text) {
		log.append(text + "\n");
		log.setCaretPosition(log.getDocument().getLength());
	}

	private class ChangeListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			ch.change("picture1");
			map1.setEnabled(true);
			map2.setEnabled(true);
			map3.setEnabled(true);
			map4.setEnabled(true);
			log.setText(null);
			judge1 = false;
			judge2 = false;
			judge3 = false;
			judge4 = false;
			gold.setText("골드 : 0");
			exp.setText("경험치 : 0");
			item1.setText("Item1 : 0");
			item2.setText("Item2 : 0");
			item3.setText("Item3 : 0");
			item4.setText("Item4 : 0");
			current_gold = 0;
			current_exp = 0;
			current_item1 = 0;
			current_item2 = 0;
			current_item3 = 0;
			current_item4 = 0;
		}
	}

	private class Map1Listener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			num = 1;
			Map1 main = new Map1();
			main.start();
			map1.setEnabled(false);
			map2.setEnabled(true);
			map3.setEnabled(true);
			map4.setEnabled(true);
		}
	}

	private class Map2Listener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			num = 2;
			Map2 main = new Map2();
			main.start();
			map1.setEnabled(true);
			map2.setEnabled(false);
			map3.setEnabled(true);
			map4.setEnabled(true);
		}
	}

	private class Map3Listener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			num = 3;
			Map3 main = new Map3();
			main.start();
			map1.setEnabled(true);
			map2.setEnabled(true);
			map3.setEnabled(false);
			map4.setEnabled(true);
		}
	}

	private class Map4Listener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			num = 4;
			Map4 main = new Map4();
			main.start();
			map1.setEnabled(true);
			map2.setEnabled(true);
			map3.setEnabled(true);
			map4.setEnabled(false);
		}
	}
}

class Map1 extends Thread {
	Random r = new Random();
	private int map1_gold, map1_exp;
	static int item1 = 0;

	public Map1() {

	}

	public synchronized void run() {
		Body2.addLog("Map1에 입장하였습니다");
		while (Body2.num == 1) {
			if (!Body2.judge1) {
				Body2.judge1 = true;
				Body2.judge2 = true;
				Body2.judge3 = true;
				Body2.judge4 = true;
				try {
					wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			}
			map1_gold = r.nextInt(10) + 1;
			map1_exp = r.nextInt(10) + 1;
			Body2.current_gold += map1_gold;
			Body2.current_exp += map1_exp;
			if (r.nextInt(10) >= 2) {
				Body2.addLog(map1_gold + "gold, " + map1_exp + "exp를 획득하였습니다.");
			} else {
				item1++;
				Body2.current_item1 = item1;
				Body2.addLog(map1_gold + "gold, " + map1_exp + "exp, item1을 1개 획득하였습니다.");
			}
			Body2.gold.setText("골드 : " + Body2.current_gold);
			Body2.exp.setText("경험치 : " + Body2.current_exp);
			Body2.item1.setText("Item1 : " + Body2.current_item1);
			try {
				sleep(1000);
			} catch (Exception e) {
				System.out.println(e);
			}
		}
	}
}

class Map2 extends Thread {
	Random r = new Random();
	private int map2_gold, map2_exp;
	static int item2 = 0;

	public Map2() {

	}

	public synchronized void run() {
		Body2.addLog("Map2에 입장하였습니다");
		while (Body2.num == 2) {
			if (!Body2.judge2) {
				Body2.judge1 = true;
				Body2.judge2 = true;
				Body2.judge3 = true;
				Body2.judge4 = true;
				try {
					wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			}
			map2_gold = r.nextInt(90) + 11;
			map2_exp = r.nextInt(90) + 11;
			Body2.current_gold += map2_gold;
			Body2.current_exp += map2_exp;
			if (r.nextInt(10) >= 2) {
				Body2.addLog(map2_gold + "gold, " + map2_exp + "exp를 획득하였습니다.");
			} else {
				item2++;
				Body2.current_item2 = item2;
				Body2.addLog(map2_gold + "gold, " + map2_exp + "exp, item2을 1개 획득하였습니다.");
			}
			Body2.gold.setText("골드 : " + Body2.current_gold);
			Body2.exp.setText("경험치 : " + Body2.current_exp);
			Body2.item2.setText("Item2 : " + Body2.current_item2);
			try {
				sleep(1000);
			} catch (Exception e) {
				System.out.println(e);
			}
		}
	}
}

class Map3 extends Thread {
	Random r = new Random();
	int map3_gold, map3_exp;
	static int item3 = 0;

	public Map3() {

	}

	public synchronized void run() {
		Body2.addLog("Map3에 입장하였습니다");
		while (Body2.num == 3) {
			if (!Body2.judge3) {
				Body2.judge1 = true;
				Body2.judge2 = true;
				Body2.judge3 = true;
				Body2.judge4 = true;
				try {
					wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			}
			map3_gold = r.nextInt(900) + 101;
			map3_exp = r.nextInt(900) + 101;
			Body2.current_gold += map3_gold;
			Body2.current_exp += map3_exp;
			if (r.nextInt(10) >= 2) {
				Body2.addLog(map3_gold + "gold, " + map3_exp + "exp를 획득하였습니다.");
			} else {
				item3++;
				Body2.current_item3 = item3;
				Body2.addLog(map3_gold + "gold, " + map3_exp + "exp, item3을 1개 획득하였습니다.");
			}
			Body2.gold.setText("골드 : " + Body2.current_gold);
			Body2.exp.setText("경험치 : " + Body2.current_exp);
			Body2.item3.setText("Item3 : " + Body2.current_item3);
			try {
				sleep(1000);
			} catch (Exception e) {
				System.out.println(e);
			}
		}
	}
}

class Map4 extends Thread {
	Random r = new Random();
	private int map4_gold, map4_exp;
	static int item4 = 0;

	public Map4() {

	}

	public synchronized void run() {
		Body2.addLog("Map4에 입장하였습니다");
		while (Body2.num == 4) {
			if (!Body2.judge4) {
				Body2.judge1 = true;
				Body2.judge2 = true;
				Body2.judge3 = true;
				Body2.judge4 = true;
				try {
					wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			}
			map4_gold = r.nextInt(9000) + 1001;
			map4_exp = r.nextInt(9000) + 1001;
			Body2.current_gold += map4_gold;
			Body2.current_exp += map4_exp;
			if (r.nextInt(10) >= 2) {
				Body2.addLog(map4_gold + "gold, " + map4_exp + "exp를 획득하였습니다.");
			} else {
				item4++;
				Body2.current_item4 = item4;
				Body2.addLog(map4_gold + "gold, " + map4_exp + "exp, item4을 1개 획득하였습니다.");
			}
			Body2.gold.setText("골드 : " + Body2.current_gold);
			Body2.exp.setText("경험치 : " + Body2.current_exp);
			Body2.item4.setText("Item4 : " + Body2.current_item4);
			try {
				sleep(1000);
			} catch (Exception e) {
				System.out.println(e);
			}
		}
	}
}