package kopo.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import kopo.domain.ExamRIO;

public class ExamRepo {
	public static Connection getConnection() {
		Connection conn = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://35.189.155.41:3306/kopo15?characterEncoding=UTF-8&serverTimezone=UTC", "root", "asdf1234");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}
	public static int createDB() {
		try {
		Connection conn = getConnection();
		Statement stmt = conn.createStatement();
		stmt.executeUpdate(
				"create table examtable(name varchar(20), studentid int not null primary key, kor int, eng int, mat int) DEFAULT CHARSET=utf8;");
		stmt.close();
		conn.close();	
		
		}catch(Exception e) {
			
		}
		return 0;
	}
	public static int dropDB() {
		try {
			Connection conn = getConnection();
			Statement stmt = conn.createStatement();
			stmt.executeUpdate(
					"drop table examtable;");
			stmt.close();
			conn.close();
		} catch(Exception e) {
	        
	    }
		return 0;
	}
	public static int insert(ExamRIO e) {
		int status=0;
		try {
			Connection conn = getConnection();
			PreparedStatement ps = conn.prepareStatement(			
					"insert into examtable(name,studentid,kor,eng,mat) values(?,?,?,?,?);");
			ps.setString(1, e.getName());
			ps.setInt(2, e.getStudentid());
			ps.setInt(3, e.getKor());
			ps.setInt(4, e.getEng());
			ps.setInt(5, e.getMat());
			status=ps.executeUpdate();
			ps.close();
			conn.close();
		} catch(Exception u) {System.out.println(u);}
		return status;
	}
	public static int update(ExamRIO e) {
		int status=0;
		try {
			Connection conn = getConnection();
			PreparedStatement ps = conn.prepareStatement(			
					"update examtable set name=?,studentid=?,kor=?,eng=?,math=? where studentid=?;");
			ps.setString(1, e.getName());
			ps.setInt(2, e.getStudentid());
			ps.setInt(3, e.getKor());
			ps.setInt(4, e.getEng());
			ps.setInt(5, e.getMat());
			ps.setInt(6, e.getStudentid());
			status=ps.executeUpdate();
			ps.close();
			conn.close();	
		}catch(Exception u) {System.out.println(u);}
		return status;
	}
	public static int delete(ExamRIO e) {
		int status=0;
		try {
			Connection conn = getConnection();
			PreparedStatement ps = conn.prepareStatement(			
					"delete from examtable where studentid=?");
			ps.setInt(1, e.getStudentid());			
			status=ps.executeUpdate();
			ps.close();
			conn.close();	
		}catch(Exception u) {System.out.println(u);}
		return status;
	}
	public static List<ExamRIO> getAllRecord(){
		List<ExamRIO> list=new ArrayList<ExamRIO>();
		try {
			Connection conn = getConnection();
			PreparedStatement ps = conn.prepareStatement("select * from examtable;");
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				ExamRIO e = new ExamRIO();
				e.setName(rs.getString("name"));
				e.setStudentid(rs.getInt("studentid"));
				e.setKor(rs.getInt("kor"));
				e.setEng(rs.getInt("eng"));
				e.setMat(rs.getInt("mat"));
				list.add(e);
			}
			rs.close();
			ps.close();
			conn.close();	
		}catch(Exception u) {System.out.println(u);}
		return list;
	}
	public static ExamRIO getRecordById(int id) {
		ExamRIO e = new ExamRIO();
		try {
			Connection conn = getConnection();
			PreparedStatement ps = conn.prepareStatement("select * from examtable where studentid=?;");
			ps.setInt(1,id);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				e.setName(rs.getString("name"));
				e.setStudentid(rs.getInt("studentid"));
				e.setKor(rs.getInt("kor"));
				e.setEng(rs.getInt("eng"));
				e.setMat(rs.getInt("mat"));
				
			}
			rs.close();
			ps.close();
			conn.close();
		}catch(Exception u) {System.out.println(u);}
		return e;
	}
}
