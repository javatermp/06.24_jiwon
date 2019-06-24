
//game.java

import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.*;

public class game {



	public static void main(String[] args) throws UnknownHostException, IOException, ClassNotFoundException {
		// TODO Auto-generated method stub
		

		
		PictureChange ch = new PictureChange();
		
		ch.setTitle("Game");
		ch.panel1 = new Body1(ch);

		ch.panel2 = new Body2(ch);

		ch.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ch.getContentPane().add(ch.panel1);
		ch.pack();
		ch.setResizable(false);
		ch.setVisible(true);
	}



}

class PictureChange extends JFrame {

	Body1 panel1 = null;
	Body2 panel2 = null;

	public void change(String name) {

		if (name.equals("picture1")) {
			getContentPane().removeAll();
			getContentPane().add(panel1);
			pack();
			setResizable(false);
			setVisible(true);
			revalidate();
			repaint();
		} else {
			getContentPane().removeAll();
			getContentPane().add(panel2);
			pack();
			setResizable(false);
			setVisible(true);
			revalidate();
			repaint();
		}
	}
}