package com.pensionrun.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.pensionrun.entity.Theme;

@Repository
public class ThemeDaoImpl extends GenericDaoImpl<Theme> implements ThemeDao{

	@PersistenceContext
	private EntityManager em;
	


}
