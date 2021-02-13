package com.spring.security.config;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
@Repository
public class AuthDaoLayer implements AuthUserDao{

	@Autowired
	private EntityManager entityManager;
	@Override
	public UserEntity getUserEntity(String username) {
		System.out.println("username1-->"+username);
		Session session = entityManager.unwrap(Session.class);
		Query<UserEntity> createQuery = session.createQuery("from UserEntity where username=:username");
		createQuery.setParameter("username", username);
		UserEntity user = createQuery.getSingleResult();
		System.out.println("user-->"+user);
		return user;
	}

}
