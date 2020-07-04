package com.pensionrun.dao;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.pensionrun.vo.SearchVo;

public abstract class GenericDaoImpl<T> implements GenericDao<T> {

	@PersistenceContext
	private EntityManager em;

	private final Class<T> entityType;

	@SuppressWarnings("unchecked")
	public GenericDaoImpl() {
		Type t = getClass().getGenericSuperclass();
		ParameterizedType pt = (ParameterizedType) t;
		entityType = (Class<T>) pt.getActualTypeArguments()[0];
	}

	public T readById(Object id) {
		return em.find(entityType, id);
	}

	public T create(T entity) {
		em.persist(entity);
		return entity;
	}

	public T update(T entity) {
		return em.merge(entity);
	}

	public void delete(T entity) {
		em.remove(entity);
	}


	public List<T> readAll() 
	{
		Query q = em.createQuery("select x from " + entityType.getSimpleName() + " x");        
		return (List<T>) q.getResultList();
	}

	public List<T> wherePredicate(Predicate predicate,Integer page,Integer unit){
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<T> cq = cb.createQuery(entityType);
		if(page==0||unit==0){
			return em.createQuery(cq.where(predicate)).getResultList();
		}
		return em.createQuery(cq.where(predicate))
				.setFirstResult(page*(unit))
				.setMaxResults(unit)
				.getResultList();
	}

}
