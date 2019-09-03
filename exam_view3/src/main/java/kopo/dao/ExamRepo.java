package kopo.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import kopo.domain.ExamRIO;
@Repository
public class ExamRepo {
	private static final Logger logger = LoggerFactory.getLogger(ExamRepo.class);
		
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public void createDB() throws Exception{
		logger.info("DAO--- createDB");
		String query="create table examtable(name varchar(20), studentid int not null primary key, kor int, eng int, mat int) DEFAULT CHARSET=utf8";
		jdbcTemplate.execute(query);
	}
	
	public void dropDB() throws Exception{
		logger.info("DAO--- dropDB");
		String query="drop table examtable";
		jdbcTemplate.execute(query);
	}
	
	public int insert(ExamRIO e)throws Exception {
			logger.info("DAO insertDB");
			return jdbcTemplate.update("insert into examtable(name,studentid,kor,eng,mat) values(?,?,?,?,?)"
					,e.getName(),e.getStudentid(),e.getKor(),e.getEng(),e.getMat());
	}
	
	public void update(ExamRIO e) throws Exception{
			logger.info("DAO updateDB");
						
			String query="update examtable set name=?,studentid=?,kor=?,eng=?,math=? where studentid=?;";
			jdbcTemplate.update(query,e.getName(), e.getStudentid(), e.getKor(), e.getEng(),e.getMat(), e.getStudentid());
			
	}
	public void delete(ExamRIO e) throws Exception{
			String query="delete from examtable where studentid=?";
			jdbcTemplate.update(query,e.getStudentid());
	}
	
	public List<ExamRIO> getAllRecord()throws Exception{
		logger.info("DAO getAllRecords");
		List<ExamRIO> results=jdbcTemplate.query("select * from examtable",
			new RowMapper<ExamRIO>() {
			@Override			
			public ExamRIO mapRow(ResultSet rs, int rowNum) throws SQLException{
				ExamRIO e = new ExamRIO();
				e.setName(rs.getString("name"));
				e.setStudentid(rs.getInt("studentid"));
				e.setKor(rs.getInt("kor"));
				e.setEng(rs.getInt("eng"));
				e.setMat(rs.getInt("mat"));
				
				return e;
			}
		});
		return results;
	}
	public ExamRIO getRecordById(int id) throws Exception{
		List<ExamRIO> results=jdbcTemplate.query("select * from examtable where studentid=?",
			new RowMapper<ExamRIO>() {
			@Override
			public ExamRIO mapRow(ResultSet rs, int rowNum) throws SQLException{
				ExamRIO e = new ExamRIO();
				e.setName(rs.getString("name"));
				e.setStudentid(rs.getInt("studentid"));
				e.setKor(rs.getInt("kor"));
				e.setEng(rs.getInt("eng"));
				e.setMat(rs.getInt("mat"));
				
				return e;
			}
		},id);
		return results.isEmpty()? null:results.get(0);
	}
}
