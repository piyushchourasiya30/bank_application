package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import oracle.jdbc.driver.OracleDriver;

public class Model {
	String url = "jdbc:oracle:thin:@//localhost:1521/XE";
	String user = "system";
	String pass = "system";
	String name;
	int accno;
	int balance;
	String userid;
	String password;
	String email;
	private Connection con;
	private PreparedStatement pstmt;
	private ResultSet res;
	int row;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAccno() {
		return accno;
	}

	public void setAccno(int accno) {
		this.accno = accno;
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Model() {
		try {
			DriverManager.registerDriver(new OracleDriver());
			con = DriverManager.getConnection(url, user, pass);
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

	public boolean login() throws SQLException

	{
		String sql = "SELECT ACCNO FROM BANK WHERE USERID =? AND PASSWORD=?";
		pstmt = con.prepareStatement(sql);
		pstmt.setString(1, userid);
		pstmt.setString(2, password);
		res = pstmt.executeQuery();
		if (res.next() == true) {
			accno = res.getInt("ACCNO");
			return true;
		} else {

			return false;
		}
	}

	public boolean checkBalance() throws SQLException {
		String sql = "SELECT BALANCE FROM BANK WHERE ACCNO=?";
		pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, accno);
		res = pstmt.executeQuery();
		if (res.next() == true) {
			balance = res.getInt("balance");
			return true;
		} else

			return false;

	}

	public boolean changePassword(String npwd) throws SQLException {
		String sql = "UPDATE BANK SET PASSWORD=?  WHERE ACCNO =? AND PASSWORD=?";
		pstmt = con.prepareStatement(sql);
		pstmt.setString(1, npwd);
		pstmt.setInt(2, accno);
		pstmt.setString(3, password);
		pstmt.executeUpdate();
		if (row == 0) {
			return false;
		} else {
			return true;
		}
	}

	public boolean transfer1(int amount) throws Exception {
		String sql = "UPDATE BANK SET BALANCE=BALANCE-? WHERE ACCNO=?";
		pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, amount);
		pstmt.setInt(2, accno);
		row = pstmt.executeUpdate();
		if (row == 0) {

			return false;
		} else {
			statement1(amount);
			return true;
		}
	}

	public boolean transfer2(int reaccno, int amount) throws Exception {
		String sql = "UPDATE BANK SET BALANCE=BALANCE+? WHERE ACCNO=?";
		pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, amount);
		pstmt.setInt(2, reaccno);
		row = pstmt.executeUpdate();
		if (row == 0) {

			return false;
		} else {
			statement2(amount, reaccno);
			return true;
		}
	}

	public void statement1(int amount) throws Exception {
		String sql = "INSERT INTO STATMENT(ACCNO,DEBIT) VALUES(?,?)";

		pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, accno);
		pstmt.setInt(2, amount);
		row = pstmt.executeUpdate();
		System.out.println("sucess");
	}

	public void statement2(int amount, int reaccno) throws Exception {
		String sql = "INSERT INTO STATMENT(ACCNO,CREDIT) VALUES(?,?)";

		pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, reaccno);
		pstmt.setInt(2, amount);
		row = pstmt.executeUpdate();
		System.out.println("sucess");
	}

	public ArrayList getDebit() throws SQLException {
		ArrayList<Integer> debit= new ArrayList<Integer>();
		String sql = "SELECT DEBIT FROM STATMENT WHERE ACCNO=?";
		pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, accno);
		res = pstmt.executeQuery();
		while (res.next()) {
			debit.add(res.getInt("debit"));

		}

		return debit;

	}

	public ArrayList getCredit() throws SQLException {
		ArrayList<Integer> credit = new ArrayList<Integer>();
		String sql = "SELECT CREDIT FROM STATMENT WHERE ACCNO=?";
		pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, accno);
		res = pstmt.executeQuery();
		while (res.next()) {
			credit.add(res.getInt("credit"));

		}

		return credit;

	}


}
