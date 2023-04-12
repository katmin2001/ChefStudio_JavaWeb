package com.devpro.javaweb.services;

import org.springframework.stereotype.Service;

import com.devpro.javaweb.model.Role;
@Service
public class RoleService extends BaseService<Role>{
	@Override
	protected Class<Role> clazz() {
		return Role.class;
	}
}
