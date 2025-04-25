package com.lal.app.repo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.lal.app.model.Student;

@Repository
public class StudentRepo {

	private JdbcTemplate jdbc;

	public void save(Student s) {

		String sql = "insert into student (rollno, name, marks) values (?,?,?)";

		int rows = jdbc.update(sql, s.getRollNo(), s.getName(), s.getMarks());

		System.out.println(rows + " effected");

	}

	public JdbcTemplate getJdbc() {
		return jdbc;
	}

	@Autowired
	public void setJdbc(JdbcTemplate jdbc) {
		this.jdbc = jdbc;
	}

	public List<Student> findAll() {
		String sql = "select * from student";
		return jdbc.query(sql, (rs, rowNum) -> {
			Student s = new Student();
			s.setRollNo(rs.getInt("rollno"));
			s.setName(rs.getString("name"));
			s.setMarks(rs.getInt("marks"));
			return s;
		});
	}

}
