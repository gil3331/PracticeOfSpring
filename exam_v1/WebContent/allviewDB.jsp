<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.*,javax.sql.*,java.io.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h3>전체 데이터</h3>
	<hr>
	<%
	try {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection conn = DriverManager.getConnection("jdbc:mysql://192.168.23.115:3306/kopo15?characterEncoding=UTF-8&serverTimezone=UTC", "root", "asdf1234");
		Statement stmt = conn.createStatement();
		ResultSet rset = stmt.executeQuery("select * from examtable;");
	%>	
	<table cellspacing=1 width=600 border=1>
	<tr>
		<td width=50><p align=center>이름</p></td>
		<td width=50><p align=center>학번</p></td>
		<td width=50><p align=center>국어</p></td>		
		<td width=50><p align=center>영어</p></td>		
		<td width=50><p align=center>수학</p></td>
	</tr>
	<%      
    	while (rset.next()) { 
            out.println("<tr>");
            out.println("<td width=50><p align=center>"+
                    "<a href='oneviewDB.jsp?studentid="+Integer.toString(rset.getInt(2))+"'>"+
                    rset.getString(1)+"</a></p></td>");
            out.println("<td width=50><p align=center>"+
            "<a href='oneviewDB.jsp?studentid="+Integer.toString(rset.getInt(2))+"'>"+
            Integer.toString(rset.getInt(2))+"</a></p></td>");
            out.println("<td width=50><p align=center>"+Integer.toString(rset.getInt(3))+"</p></td>");
            out.println("<td width=50><p align=center>"+Integer.toString(rset.getInt(4))+"</p></td>");
            out.println("<td width=50><p align=center>"+Integer.toString(rset.getInt(5))+"</p></td>");
            out.println("<tr>");
    	} 
		rset.close(); 
	    stmt.close();
		conn.close();	
	} catch(Exception e) {
        out.println("테이블 생성중 에러발생."+e);
    }
	%>
</body>
</html>