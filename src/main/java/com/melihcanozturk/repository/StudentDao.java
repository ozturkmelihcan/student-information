package com.melihcanozturk.repository;

import java.util.List;
import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.melihcanozturk.entity.Student;

public class StudentDao implements ICrud<Student> {

	private Session session;
	private Transaction transaction;

	private void openTransaction() {

		session = databaseConnectionHibernate();
		transaction = session.beginTransaction();

	}

	private void accessTransaction() {
		transaction.commit();
		session.close();
	}

	private void errorTransaction() {
		if (transaction != null) {
			transaction.rollback();
		}

	}

	@Override
	public void create(Student t) {
		try {
			openTransaction();
			session.save(t);
			accessTransaction();
		} catch (Exception e) {
			errorTransaction();
		}
	}

	@Override
	public void delete(long id) {
		Session session = null;
		try {
			Student student = find(id);
			if (student != null) {
				session = databaseConnectionHibernate();
				session.getTransaction().begin();
				session.remove(student);
				session.getTransaction().commit();
				System.out.println("Student information have been successfully deleted from the database.");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println("Student information can't be deleted from the database.");
		} finally {
			session.close();
		}

	}

	@Override
	public void update(long id, Student entity) {
		Session session = null;
		try {
			Student student = find(id);
			if (student != null) {
				student.setId(entity.getId());
				student.setFirstName(entity.getFirstName());
				student.setLastName(entity.getLastName());
				student.setEmail(entity.getEmail());
				student.setContact(entity.getContact());

				student.setGender(entity.getGender());
				session = databaseConnectionHibernate();
				session.getTransaction().begin();
				session.update(student);
				session.getTransaction().commit();
				System.out.println("Student information success...");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println("Some problem occured while updating student to DB.");
		} finally {
			session.close();
		}

	}

	@Override
	public List<Student> listAll() {
		Session session = null;
		session = databaseConnectionHibernate();
		String query = "select student from Student as student";
		TypedQuery<Student> typedQuery = session.createQuery(query, Student.class);
		List<Student> studentList = typedQuery.getResultList();

		return studentList;
	}

	@Override
	public Student find(long id) {
		Session session = databaseConnectionHibernate();
		Student studentList;
		try {
			studentList = session.find(Student.class, id);
			if (studentList != null) {
				System.out.println("Student found : " + studentList);
				return studentList;
			} else {
				System.out.println("Student information not found");
				return null;
			}
		} catch (Exception e) {
			System.out.println("Some problem occured while finding student to DB");
		} finally {
			session.close();
		}
		return null;
	}

	public List<Student> getAll() {

		List<Student> list = null;
		try (Session session = databaseConnectionHibernate()) {
			list = session.createQuery("from Student", Student.class).list();
		} catch (Exception e) {
		}
		return list;
	}

	public List<Student> getByName(String firstName) {
		Transaction transaction = null;
		List<Student> list = null;
		try (Session session = databaseConnectionHibernate()) {
			transaction = session.beginTransaction();
			list = session
					.createQuery("select student from Student as student where student.firstName = '" + firstName + "'",
							Student.class)
					.list();
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
		}
		return list;
	}

	public List<Student> getByLastName(String lastName) {
		Transaction transaction = null;
		List<Student> list = null;
		try (Session session = databaseConnectionHibernate()) {
			transaction = session.beginTransaction();
			list = session
					.createQuery("select student from Student as student where student.lastName = '" + lastName + "'",
							Student.class)
					.list();
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
		}
		return list;
	}

	public List<Student> getByEmail(String email) {
		Transaction transaction = null;
		List<Student> list = null;
		try (Session session = databaseConnectionHibernate()) {
			transaction = session.beginTransaction();
			list = session.createQuery("select student from Student as student where student.email = '" + email + "'",
					Student.class).list();
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
		}
		return list;

	}

	public void update(Student student) {
		Session session = null;
		try {
			Student student1 = session.find(Student.class, student.getId());
			if (student1 != null) {
				student1.setId(student.getId());
				student1.setFirstName(student.getFirstName());
				student1.setLastName(student.getLastName());
				student1.setGender(student.getGender());
				student1.setContact(student.getContact());
				student1.setEmail(student.getEmail());
				session = databaseConnectionHibernate();
				session.getTransaction().begin();
				session.update(student1);
				session.getTransaction().commit();
				System.out.println("Student information success...");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println("Some problem occured while updating student to DB.");
		} finally {
			session.close();
		}

	}
}
