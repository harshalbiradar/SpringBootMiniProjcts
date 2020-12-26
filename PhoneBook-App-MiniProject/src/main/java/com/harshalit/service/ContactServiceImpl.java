package com.harshalit.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.harshalit.entity.Contact;
import com.harshalit.repository.ContactRepository;

@Service
public class ContactServiceImpl implements ContactService {

	@Autowired
	private ContactRepository contactRepository;
	
	@Override
	public boolean saveContact(Contact contact) {
		contact.setContactIsActive("Y");
		Contact savedContact = contactRepository.save(contact);
		return savedContact.getContactId()!= null;
	}

	@Override
	public List<Contact> getAllContacts() {
		return contactRepository.findByContactIsActive("Y");
	}

	@Override
	public boolean updateContact(Integer contactId) {
		contactRepository.findById(contactId);
		return false;
	}

	@Override
	public boolean deleteContact(Integer contactId) {
		// TODO Auto-generated method stub
		return false;
	}

}
