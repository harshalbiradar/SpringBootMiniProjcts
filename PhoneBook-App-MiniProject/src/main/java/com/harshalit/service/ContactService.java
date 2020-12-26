package com.harshalit.service;

import java.util.List;

import com.harshalit.entity.Contact;



public interface ContactService {

	public boolean saveContact(Contact contact);
	public List<Contact> getAllContacts();
	public boolean updateContact(Integer contactId);
	public boolean deleteContact(Integer contactId);
	
	
	
	
}
