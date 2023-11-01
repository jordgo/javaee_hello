package services;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.jboss.logging.Logger;

import entity.User;

public class LoginService {
	private static final Logger LOGGER = Logger.getLogger(LoginService.class);

//	@PersistenceContext(unitName = "primary")
//	private static EntityManager entityManager; 
	
	public static EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory("primary");//
	public static EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
	
	
	public static User getUser(String name, String password) {
		
        try {
            Query query = em.createQuery("select u from User u where u.name = :name and u.password = :password", User.class);
            query.setParameter("name", name);
            query.setParameter("password", password);
            
            User user = (User)query.getSingleResult();
            LOGGER.info("From db: "+user.toString());
            return user;
            
        } catch (NoResultException e) {
            return null;
        }
	}

}
