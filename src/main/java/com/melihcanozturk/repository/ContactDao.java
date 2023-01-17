package com.melihcanozturk.repository;

import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.Session;

import com.melihcanozturk.entity.Contact;

public class ContactDao implements ICrud<Contact> {

	@Override
	public void create(Contact entity) {
		Session session = null;
		try {
			session = databaseConnectionHibernate();
			session.getTransaction().begin();
			session.save(entity);
			session.getTransaction().commit();
			System.out.println("Contact information has been successfully...");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@Override
	public void delete(long id) {
		Session session = null;
		try {
			Contact contact = find(id);
			if (contact != null) {
				session = databaseConnectionHibernate();
				session.getTransaction().begin();
				session.remove(contact);
				session.getTransaction().commit();
				System.out.println("Contact details have been successfully deleted from the database.");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println("Contact information can't be deleted from the database.");
		} finally {
			session.close();
		}

	}

	@Override
	public void update(long id,Contact entity) {
		Session session = null;
		try {
			Contact contact = find(id);
			if (contact != null) {
				contact.setId(entity.getId());
				contact.setPhoneNumber1(entity.getPhoneNumber1());
				contact.setPhoneNumber2(entity.getPhoneNumber2());
				contact.setAddress1(entity.getAddress1());
				contact.setAddress2(entity.getAddress2());

				session = databaseConnectionHibernate();
				session.getTransaction().begin();
				session.update(contact);
				session.getTransaction().commit();
				System.out.println("Contact information success...");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println("Some problem occured while updating contact to DB.");
		} finally {
			session.close();
		}

	}

	@Override
	public List<Contact> listAll() {
		Session session = null;
		session = databaseConnectionHibernate();
		String query = "select contact from Contact as contact";
		TypedQuery<Contact> typedQuery = session.createQuery(query, Contact.class);
		List<Contact> contactList = typedQuery.getResultList();

		return contactList;
	}

	@Override
	public Contact find(long id) {
		Session session = databaseConnectionHibernate();
		Contact contactList;
		try {
			contactList = session.find(Contact.class, id);
			if (contactList != null) {
				System.out.println("Contact found : " + contactList);
				return contactList;
			} else {
				System.out.println("Contact details not found");
				return null;
			}
		} catch (Exception e) {
			System.out.println("Some problem occured while finding yonetmen to DB");
		} finally {
			session.close();
		}
		return null;
	}

}
