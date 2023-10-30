package services;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.QueryHint;

import org.jboss.logging.Logger;

import entity.User;
import servlets.LoginServlet;

public class LoginService {
	private static final Logger LOGGER = Logger.getLogger(LoginService.class);

//	@PersistenceContext(unitName = "primary")
//	private static EntityManager entityManager; 
	
	public static EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory("primary");//
	public static EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
	
	
	public static String getUser(String name, String password) {
		
        try {
            Query query = em.createNativeQuery("select * from user_auth u where u.name = ? and u.password = ?");
            query.setParameter(1, name);
            query.setParameter(2, password);
            
            List<Object[]> list =  query.getResultList();
            Object[] listData = {(Object)"empty"};
            
            for (int i=0; i<list.size(); i++) {
            	listData = list.get(i);
                System.out.println("111111111111 "+listData[0]+"  "+listData[1]+"  "+listData[2]);	
            }
            return listData[0].toString();
//        	return em.find(User.class, 2);
        } catch (NoResultException e) {
            return null;
        }
	}

}
