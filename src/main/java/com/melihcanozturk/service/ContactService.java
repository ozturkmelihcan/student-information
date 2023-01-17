package com.melihcanozturk.service;



import com.melihcanozturk.entity.Contact;

import com.melihcanozturk.repository.ContactDao;

public class ContactService {

	private ContactDao contactDao = new ContactDao();

	public void create(String address1, String address2, String phone1, String phone2) {
		Contact contact = new Contact(address1, address2, phone1, phone2);
		contactDao.create(contact);
	}

	public Contact updateContact(long parseLong, String text, String text2, String text3, String text4) {
		
		Contact contact = contactDao.find(parseLong);
		contact.setAddress1(text);
		contact.setAddress2(text2);
		contact.setPhoneNumber1(text3);
		contact.setPhoneNumber2(text4);
		contactDao.update(parseLong, contact);
		return contact;
	}
	
	
}
