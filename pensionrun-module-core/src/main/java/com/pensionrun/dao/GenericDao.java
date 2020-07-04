package com.pensionrun.dao;

import java.util.List;

import javax.persistence.criteria.Predicate;

import com.pensionrun.vo.SearchVo;

public interface GenericDao<T> {	
	public T readById(Object id);
	public T create(T type);
	public T update(T type);
	public void delete(T type);   
	public List<T> readAll(); 
	public List<T> wherePredicate(Predicate predicate,Integer page,Integer unit);
	
}
