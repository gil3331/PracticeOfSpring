package kopo.exam.hibernate.service;

import java.util.List;

import kopo.exam.hibernate.dto.ExamSIO;

public interface ExamService {
	public void createDB()throws Exception;
	public void dropDB()throws Exception;
	
	public void allsetDB()throws Exception;
	
	public ExamSIO selectOne(int id)throws Exception;
	public List<ExamSIO> selectAll()throws Exception;
	public List<ExamSIO> selectAllByName(String name)throws Exception;
	
	public void insert(ExamSIO examScore)throws Exception;
	public void update(int id, ExamSIO examScore)throws Exception;
	public void delete(int id)throws Exception;
	public void delete(ExamSIO examScore)throws Exception;
}
