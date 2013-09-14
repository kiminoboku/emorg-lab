/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.kiminoboku.emorg.ejb.dao;

import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import pl.kiminoboku.emorg.cdi.SystemConfig;
import pl.kiminoboku.emorg.entities.Config;

/**
 *
 * @author Radek
 */
@Stateless
@LocalBean
public class SystemConfigDAOBean {
    
    @PersistenceContext
    private EntityManager entityManager;
    
    @Produces
    @SystemConfig
    @RequestScoped
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Config getSystemConfig() {
        Config ret = entityManager.find(Config.class, Integer.valueOf(1));
        if(ret == null) {
            ret = new Config();
            entityManager.persist(ret);
        }
        return ret;
    }
}
