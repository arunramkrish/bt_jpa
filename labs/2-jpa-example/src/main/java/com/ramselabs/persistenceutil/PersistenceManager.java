package com.ramselabs.persistenceutil;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * @author Ravikant
 *
 */
public enum PersistenceManager {
  INSTANCE;
  
  private EntityManagerFactory emFactory;
  
  private PersistenceManager() {
    emFactory = Persistence.createEntityManagerFactory("jpa-example");
  }
  
  public EntityManager getEntityManager() {
    return emFactory.createEntityManager();
  }
  
  public void close() {
    emFactory.close();
  }
}
