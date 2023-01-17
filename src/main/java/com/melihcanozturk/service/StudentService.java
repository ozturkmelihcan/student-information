package com.melihcanozturk.service;

import java.util.List;

import com.melihcanozturk.entity.Contact;
import com.melihcanozturk.entity.Student;
import com.melihcanozturk.repository.StudentDao;

public class StudentService {

	private StudentDao studentDao = new StudentDao();

	public void create(Student s) {

		studentDao.create(s);
	}

	public List<Student> getAll() {
		return studentDao.getAll();
	}

	public Student findId(long select) {
		
		return studentDao.find(select);
	}

	public void delete(String id) {
		studentDao.delete(Long.parseLong(id));
		
	}

	public List<Student> getByName(String firstName) {
		List<Student>list = null;
		try {
			list = studentDao.getByName(firstName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
		
	}
	
	public List<Student> getByLastName(String lastName) {
		List<Student>list = null;
		try {
			list = studentDao.getByLastName(lastName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
		
	}

	public List<Student> getByEmail(String email) {
		List<Student>list = null;
		try {
			list = studentDao.getByEmail(email);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

//	public void update(long id,String firstName, String lastName, String email, String gender, String address1, String address2,
//			String phone1, String phone2) {
//		Student student ;
//		try {
//			studentDao.update(id,student);
//		} catch (Exception e) {
//			e.printStackTrace();
//			System.out.println("failed to update...");
//		}
//	}

	public void update(long parseLong, String text, String text2, String text3, String valueOf, Contact contact) {
		Student student = studentDao.find(parseLong);
		
		student.setFirstName(text);
		student.setLastName(text2);
		student.setEmail(text3);
		student.setGender(valueOf);
		student.setContact(contact);
		studentDao.update(parseLong, student);
		
	}

//	public void update(long id,String firstName, String lastName, String email) {		// nesneyı fındById de cagır  Daodaki
//		Student student = new Student(firstName,lastName,email);
//		studentDao.update( student);
//	}

	
}