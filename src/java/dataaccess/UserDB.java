package dataaccess;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import models.*;

/**
 *
 * @author Alex Tompkins - 821984
 */
public class UserDB {
    
    public List<User> getAll() {
        
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        try { 
            
            List<User> allUsers = em.createNamedQuery("User.findAll", User.class).getResultList();
            return allUsers;
        } finally { 
            em.close();
        }
    } 
    
    
    public User get(String email) {
        
        EntityManager em = DBUtil.getEmFactory().createEntityManager(); 
        try { 
        
            User user = em.find(User.class, email); 
            return user; 
        } finally { 
            
            em.close(); 
        } 
    } 
    
    
    public void insert(User user) { 
        
        EntityManager em = DBUtil.getEmFactory().createEntityManager(); 
        EntityTransaction transaction = em.getTransaction(); 
        
        try { 
            
            Role role = user.getRole(); 
            
            transaction.begin(); 
            em.persist(user); 
            //em.merge(role); 
            transaction.commit();
        } catch (Exception e) { 
            
            transaction.rollback(); 
        } finally { 
            
            em.close(); 
        } 
    } 
    
    
    public void update(User user) { 
    
        EntityManager em = DBUtil.getEmFactory().createEntityManager(); 
        EntityTransaction transaction = em.getTransaction(); 
        
        try { 
          
            transaction.begin(); 
            em.merge(user); 
            transaction.commit(); 
        } catch (Exception e) { 
            
            transaction.rollback(); 
        } finally { 
            
            em.close();
        } 
    } 
    
    
    public void delete(User user) { 
        
        EntityManager em = DBUtil.getEmFactory().createEntityManager(); 
        EntityTransaction transaction = em.getTransaction(); 
        
        try { 
            
            Role role = user.getRole(); 
            transaction.begin(); 
            em.remove( em.merge(user) ); 
            em.merge(role); 
            transaction.commit(); 
        } catch (Exception e) { 
            
            transaction.rollback(); 
        } finally { 
            
            em.close(); 
        } 
    } 
}


