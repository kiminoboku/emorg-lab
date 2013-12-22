/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pl.kiminoboku.emorg.service.persistence;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Radek
 */
public class EntityManagerFactoryService {
    private final EntityManagerFactory entityManagerFactory;
    private final EntityManager entityManager;

    public EntityManagerFactoryService() {
        entityManagerFactory = Persistence.createEntityManagerFactory("emorgPU");
        entityManager = entityManagerFactory.createEntityManager();
    }
    
    public EntityManager getEntityManager() {
        return entityManager;
    }
    
    public void close() {
        entityManager.close();
        entityManagerFactory.close();
    }
}
