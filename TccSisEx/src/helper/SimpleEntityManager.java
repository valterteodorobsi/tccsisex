/**
 * 
 */
package helper;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

/**
 * SisEx - Sistema de Gerenciamento de Exames
 * @project	SisEx
 * @package	br.com.sisex.helper
 * @class	SimpleEntityManager.java
 * @author	Marco Quadros (hanrellz - marco.quadros@mqsistemas.com.br) 
 * @version 1.0
 * 
 */
public class SimpleEntityManager {

	@PersistenceContext(name="SisExPU")
	private EntityManager entityManager;
    private EntityManagerFactory factory;
     
    public SimpleEntityManager(EntityManagerFactory factory) {
        this.factory = factory;
        this.entityManager = factory.createEntityManager();
    }
     
    public SimpleEntityManager(){
        factory = Persistence.createEntityManagerFactory("SisExPU");
        this.entityManager = factory.createEntityManager();
    }
 
    public void beginTransaction(){
        entityManager.getTransaction().begin();
    }
     
    public void commit(){
        entityManager.getTransaction().commit();
    }
    
    public void close(){
        entityManager.close();
        factory.close();
    }
     
    public void rollBack(){
        entityManager.getTransaction().rollback();
    }
     
    public EntityManager getEntityManager(){
        return entityManager;
    }
	
}
