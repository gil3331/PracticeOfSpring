package kopo.exam.hibernate.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.internal.SessionImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import kopo.exam.hibernate.domain.ExamRIO;

@Transactional
@Repository
public class ExamRepoImpl implements ExamRepo{
	
	@Autowired
	private SessionFactory sessionFactory;
	
	private static final Logger logger = LoggerFactory.getLogger(ExamRepoImpl.class);
		
	private Session getSession() {
		//return sessionFactory.openSession();\
		logger.info("getSession().start");
		Session ss=null;
		try {
			ss=sessionFactory.getCurrentSession();
		}catch(org.hibernate.HibernateException he) {
			ss=sessionFactory.openSession();
		}
		return ss;
	}
	
	@Override
	public Long count() {
		logger.info("count().start");
		String hql = "select count(*) from ExamRIO";//<-이게 쿼리냐?!
		Query query = getSession().createQuery(hql);
		Long totalCount = (Long) query.uniqueResult();
		return totalCount;
	}
	
	@Override
	public ExamRIO selectOne(long id) {
		String hql = "From ExamRIO e where e.studentid="+id;
		Query query = getSession().createQuery(hql);
		return (ExamRIO) query.uniqueResult();
		//return (User)query.list().get(0);
		//return (Users)getSession().get(Users.class,id);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<ExamRIO> selectAll(){
		String hql = "FROM ExamRIO";
		Query query = getSession().createQuery(hql);
		return query.list();
		//return getSession().createCriteria(ExamRIO.class).list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<ExamRIO> selectAllByPagination(int page, int itemSizePerPage){
		String hql = "FROM ExamRIO ORDER BY studentid";
		Query query = getSession().createQuery(hql);
		query.setFirstResult((page -1)*itemSizePerPage);
		query.setMaxResults(itemSizePerPage);
		return query.list();
	}
	
	@Override
	public int createOne(ExamRIO exam) {
		return (int) getSession().save(exam);
		
	}
	
	@Override
	public void updateOne(ExamRIO exam) {
		getSession().saveOrUpdate(exam);
	}
	
	@Override
	public void deleteOne(ExamRIO exam) {
		getSession().delete(exam);
	}
	
	@Override
	public int deleteAll() {
		String hql = "DELETE FROM ExamRIO";
		Query query = getSession().createQuery(hql);
		return query.executeUpdate();
	}
	
	@Override
	public void createDB(){
		Statement stmt;
		try {
			stmt=((SessionImpl)getSession()).connection().createStatement();
			stmt.execute("create table examtable(name varchar(20), studentid int not null primary key,"
			+"kor int, eng int, mat int) DEFAULT CHARSET=utf8;");
			stmt.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void dropDB(){
		Statement stmt;
		try {
			stmt=((SessionImpl)getSession()).connection().createStatement();
			stmt.execute("drop table examtable;");
			stmt.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
	