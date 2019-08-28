<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.*,javax.sql.*,java.io.*" %>
<%@ page import="kopo.domain.*,kopo.dao.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>데이터 Insert</title>
</head>
<body>
	<h3>데이터 Insert</h3>
	<hr>
	<%
		try {
			ExamRepo.insert(new ExamRIO("나연",209901,95,100,95));
			ExamRepo.insert(new ExamRIO("정연",209902,90,90,100));
			ExamRepo.insert(new ExamRIO("모모",209903,85,80,95));
			ExamRepo.insert(new ExamRIO("사나",209904,75,100,85));
			ExamRepo.insert(new ExamRIO("지영",209905,85,70,75));
			ExamRepo.insert(new ExamRIO("미나",209906,95,80,95));
			ExamRepo.insert(new ExamRIO("다현",209907,85,100,85));
			ExamRepo.insert(new ExamRIO("채영",209908,75,100,65));
			ExamRepo.insert(new ExamRIO("쯔위",209909,85,80,95));
		} catch(Exception e) {
	        out.println("테이블 생성중 에러발생."+e);
	    }
	%>
	
</body>
</html>