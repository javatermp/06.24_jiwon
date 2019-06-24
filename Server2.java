
import java.io.*;

import java.net.*;
import java.sql.*;

class Server2 {
	static final int PORT = 3011;
	private static final String DRIVER = "com.mysql.jdbc.Driver";
	private static final String URL = "jdbc:mysql://localhost:3306/personal_info";
	private static final String USER = "root";
	private static final String PASS = "123";
	private static Connection conn;
	private static Statement stmt;
	private static ResultSet rs;
	private static int gold_DB;
	private static int exp_DB;
	private static int Item1_DB;
	private static int Item2_DB;
	private static int Item3_DB;
	private static int Item4_DB;
	
	public static void main(String[] args) {
		ServerSocket myService = null;
		Socket serviceClient = null;
		BufferedReader is = null;
		DataOutputStream os = null;
	
		try {
			conn = DriverManager.getConnection(URL, USER, PASS);
			stmt = conn.createStatement();
			System.out.println("DB Conneted");

			myService = new ServerSocket(PORT);
			System.out.println("Server running at " + PORT);
			serviceClient = myService.accept();
			System.out.println("클라이언트 on");

			is = new BufferedReader(new InputStreamReader(serviceClient.getInputStream()));
			os = new DataOutputStream(serviceClient.getOutputStream());

			String id = is.readLine();
			String pw = is.readLine();
			
			System.out.println(id);

			String sql = "select * from personal_info where id='"+(id)+"'";
			System.out.println(sql);
			rs = stmt.executeQuery(sql);
			System.out.println(rs);
			
			while(rs.next()) {
				gold_DB=rs.getInt("gold");
				exp_DB=rs.getInt("exp");
				Item1_DB=rs.getInt("Item1");
				Item2_DB=rs.getInt("Item2");
				Item3_DB=rs.getInt("Item3");
				Item4_DB=rs.getInt("Item4");
			}

//			while (rs.next()) {
//				if (rs.getString("id").equals(id) && rs.getString("pwd").equals(pw)) {
//					id_DB = rs.getString("id");
//					pw_DB = rs.getString("pwd");
//				}
//			}
//			System.out.println(id_DB);
//			System.out.println(pw_DB);
//
//			os.writeBytes("" + (id_DB) + "\n");
//			os.writeBytes("" + (pw_DB) + "\n");
			
			
			os.writeBytes(""+(gold_DB)+"\n");
			os.writeBytes(""+(exp_DB)+"\n");
			os.writeBytes(""+(Item1_DB)+"\n");
			os.writeBytes(""+(Item2_DB)+"\n");
			os.writeBytes(""+(Item3_DB)+"\n");
			os.writeBytes(""+(Item4_DB)+"\n");
			
			String total_gold=is.readLine();
			String total_exp=is.readLine();
			String total_item1=is.readLine();
			String total_item2=is.readLine();
			String total_item3=is.readLine();
			String total_item4=is.readLine();
			System.out.println(total_gold);
			
			int total_gold_conv=Integer.parseInt(total_gold);
			int total_exp_conv=Integer.parseInt(total_exp);
			int total_item1_conv=Integer.parseInt(total_item1);
			int total_item2_conv=Integer.parseInt(total_item2);
			int total_item3_conv=Integer.parseInt(total_item3);
			int total_item4_conv=Integer.parseInt(total_item4);
			
			sql = "update personal_info set gold=" + (total_gold_conv) + ", exp="+(total_exp_conv)+", Item1="+(total_item1_conv)+", Item2="+(total_item2_conv)+", Item3="+(total_item3_conv)+", Item4="+(total_item4_conv)+" where id='"+(id)+"'"; 
            System.out.println(sql);
            stmt.executeUpdate(sql);
			
//			os.close();
//			is.close();
//			serviceClient.close();

		} catch (IOException error) {
			error.printStackTrace();
		} catch (Exception e2) {
			System.out.println("actionPerformed err : " + e2);
		} finally {

			try {
				if (rs != null)
					rs.close();
				if (stmt != null)
					stmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception e2) {

			}

		}

	}
}
