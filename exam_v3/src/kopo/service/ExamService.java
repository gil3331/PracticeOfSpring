package kopo.service;

import java.util.List;

import kopo.dto.ExamSIO;

public interface ExamService {
	void createDB();
	void dropDB();
	
	void allsetDB();
	
	ExamSIO selectOne(int id);
	List<ExamSIO> selectAll();
	List<ExamSIO> selectAllByName(String name);
	
	int insert(ExamSIO examScore);
	int update(int id, ExamSIO examScore);
	int delete(int id);
	int delete(ExamSIO examScore);
}
