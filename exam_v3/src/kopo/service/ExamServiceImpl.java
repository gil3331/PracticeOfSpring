package kopo.service;

import java.util.ArrayList;
import java.util.List;

import kopo.dao.ExamRepo;
import kopo.domain.ExamRIO;
import kopo.dto.ExamSIO;

public class ExamServiceImpl implements ExamService {

	@Override
	public void createDB() {
		// TODO Auto-generated method stub
		ExamRepo.createDB();
	}

	@Override
	public void dropDB() {
		// TODO Auto-generated method stub
		ExamRepo.dropDB();
	}

	@Override
	public void allsetDB() {
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
	public ExamSIO selectOne(int id) {
		// TODO Auto-generated method stub
		ExamRIO exam = ExamRepo.getRecordById(id);
		return new ExamSIO(
				exam.getName(),exam.getStudentid(),exam.getKor(),exam.getEng(),exam.getMat()
				);
	}

	@Override
	public List<ExamSIO> selectAll() {
		// TODO Auto-generated method stub
		List<ExamRIO> exams = ExamRepo.getAllRecord();
		List<ExamSIO> examScores = new ArrayList<ExamSIO>();
		for (ExamRIO exam : exams){
			examScores.add(new ExamSIO(
					exam.getName(),exam.getStudentid(),exam.getKor(),exam.getEng(),exam.getMat())
					);
		}
		return examScores;
	}

	@Override
	public List<ExamSIO> selectAllByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insert(ExamSIO examScore) {
		// TODO Auto-generated method stub
		return ExamRepo.insert(
				new ExamRIO(examScore.getName(),examScore.getStudentid(),
								examScore.getKor(),examScore.getEng(),examScore.getMat())
				);
	}

	@Override
	public int update(int id, ExamSIO examScore) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(int id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(ExamSIO examScore) {
		// TODO Auto-generated method stub
		return 0;
	}

}
