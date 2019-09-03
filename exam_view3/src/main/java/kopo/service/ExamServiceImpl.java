package kopo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kopo.dao.ExamRepo;
import kopo.domain.ExamRIO;
import kopo.dto.ExamSIO;
@Service
public class ExamServiceImpl implements ExamService {
	@Autowired
	private ExamRepo dao;
		
	@Override
	public void createDB() throws Exception{

		dao.createDB();
	}

	@Override
	public void dropDB() throws Exception{
		// TODO Auto-generated method stub
		dao.dropDB();
	}

	@Override
	public void allsetDB() throws Exception{
		// TODO Auto-generated method stub
		this.insert(new ExamSIO("나연",209901,91,100,95));
		this.insert(new ExamSIO("정연",209902,90,90,100));
		this.insert(new ExamSIO("모모",209903,85,80,95));
		this.insert(new ExamSIO("사나",209904,75,100,85));
		this.insert(new ExamSIO("지영",209905,85,70,75));
		this.insert(new ExamSIO("미나",209906,95,80,95));
		this.insert(new ExamSIO("다현",209907,85,100,85));
		this.insert(new ExamSIO("채영",209908,75,100,65));
		this.insert(new ExamSIO("쯔위",209909,85,80,95));
	}

	@Override
	public ExamSIO selectOne(int id) throws Exception{
		// TODO Auto-generated method stub
		ExamRIO exam = null;
		try {
			exam = dao.getRecordById(id);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return new ExamSIO(
				exam.getName(),exam.getStudentid(),exam.getKor(),exam.getEng(),exam.getMat()
				);
	}

	@Override
	public List<ExamSIO> selectAll()throws Exception {
		// TODO Auto-generated method stub
		List<ExamRIO> exams = null;
		try {
			exams = dao.getAllRecord();
		}catch (Exception e) {
			e.printStackTrace();
		}
		List<ExamSIO> examScores = new ArrayList<ExamSIO>();
		for (int i=0; i<exams.size();i++){
			examScores.add(new ExamSIO(
					exams.get(i).getName(),
					exams.get(i).getStudentid(),
					exams.get(i).getKor(),
					exams.get(i).getEng(),
					exams.get(i).getMat()
					));
		}
		return examScores;
	}

	@Override
	public List<ExamSIO> selectAllByName(String name)throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insert(ExamSIO examScore) throws Exception{
		// TODO Auto-generated method stub
		try {
		dao.insert(
				new ExamRIO(examScore.getName(),examScore.getStudentid(),
								examScore.getKor(),examScore.getEng(),examScore.getMat())
				);
		
	}catch(Exception e) {
		e.printStackTrace();
	}
}
	@Override
	public void update(int id, ExamSIO examScore)throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(int id) throws Exception{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(ExamSIO examScore) throws Exception{
		// TODO Auto-generated method stub
		
	}

}
