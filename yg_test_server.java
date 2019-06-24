package Term;

import java.io.*;
import java.net.*;

public class test_server {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			int i = 1; // 서버로 접속된 클라이언트의 개수를 나타내기 위한 변수
			ServerSocket s = new ServerSocket(8189); // 앞의 문제와 다르게 포트 8199로 생성한다. 그리고 서버에 접속할 때마다 새로운 시도를 하게 한다.			
			for (;;) {
				Socket incoming = s.accept();
				Runnable task = new ThreadedEchoHandler2(incoming, i); // Runnable과 관련된 클래스 객체 생성
				Thread t = new Thread(task); // 이 객체 변수를 쓰레드로 만든다.
				t.start(); // 쓰레드 시작
				i++; // 다음 클라이언트의 접속을 위해서 개수를 1 늘려준다.
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}

}

class ThreadedEchoHandler2 implements Runnable {
	public ThreadedEchoHandler2(Socket i, int c) {
		incoming = i;
		num = c;
	}

	public void run() {
		example(); // 쓰레드가 실행되면, example() 메소드가 실행되게 한다.
	}
	
	public synchronized void example() {
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(incoming.getInputStream()));
			PrintWriter out = new PrintWriter(incoming.getOutputStream(), true);
			
			boolean done = false;
			
			while (!done) {				
				String str = in.readLine();
				int number = Integer.parseInt(str.split(" ")[0]);
				String ck = str.split(" ")[1];
				counter++; // 클라이언트들로부터 받아온 메시지의 총 횟수를 나타내기 위함
//				int number = Integer.parseInt(str);
				
				if(str == null) {
					done = true;
				} else {
					System.out.println(number + ", " + ck + " : " + num + " >> server1");
					
					out.println("From server:Echo: " + number);
					
				}
			}
			
			in.close();
			out.close();
			
			incoming.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	private Socket incoming;
	private int num; // 보낸 클라이언트의 번호를 알게 하기 위한 변수
	private static int counter = 0;
}
