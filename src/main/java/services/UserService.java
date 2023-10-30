package services;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.transaction.Transactional;

import entity.User;

/**
 * Session Bean implementation class UserService
 */
@Stateless
@LocalBean
@TransactionManagement(TransactionManagementType.CONTAINER)
public class UserService implements UserServiceLocal {
	
	public static EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory("primary");//
	public static EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void createUser(User user) {
    	em.persist(user);
    }

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void updateUser(User user) {
    	User userToUpdate = (User)em.find(User.class, user.getId());
    	userToUpdate.setName(user.getName());
    	userToUpdate.setPassword(user.getPassword());
    }
	
    public void deleteUser(User user) {
    	em.remove(user);
    }
	
    public List<User> getAll() {
    	return em.createQuery("Select t from " + User.class.getSimpleName() + " t").getResultList();
    }
}
