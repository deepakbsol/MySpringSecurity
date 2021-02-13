package com.custome.security.config;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CustomeUserDetailsDaoImp implements CustomeUserDetailsDao{

	@Autowired
	private EntityManager entityManager;
	@Override
	public UserForValidation getUserForValidation(String username) {
		Session session = entityManager.unwrap(Session.class);
		Query<UserForValidation> createQuery = session.createQuery("from UserForValidation where username=:username");
		createQuery.setParameter("username", username);
		UserForValidation user = createQuery.getSingleResult();
		return user;
	}

}
