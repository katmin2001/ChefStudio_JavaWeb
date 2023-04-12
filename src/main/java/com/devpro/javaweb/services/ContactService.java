package com.devpro.javaweb.services;

import org.springframework.stereotype.Service;

import com.devpro.javaweb.model.Contact;

@Service
public class ContactService extends BaseService<Contact>{

	@Override
	protected Class<Contact> clazz() {
		// TODO Auto-generated method stub
		return Contact.class;
	}

}
