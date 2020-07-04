package com.pensionrun.dao;

import com.pensionrun.entity.Admin;

public interface AdminDao extends GenericDao<Admin>{
	Admin readByEmail(String email);
}
