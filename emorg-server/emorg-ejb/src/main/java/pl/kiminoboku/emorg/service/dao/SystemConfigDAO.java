/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.kiminoboku.emorg.service.dao;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import pl.kiminoboku.emorg.cdi.SystemConfig;
import pl.kiminoboku.emorg.domain.entities.Config;

/**
 * Service responsible for managing system configuration
 *
 * @author Radek
 */
@Stateless
@LocalBean
public class SystemConfigDAO {

    @PersistenceContext
    private EntityManager entityManager;

    /**
     * Returns and produces system configuration entity. Also creates and persists one
     * with default parameter values if there isn't any in persistent storage.
     *
     * @return
     */
    @Produces
    @SystemConfig
    @RequestScoped
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Config getSystemConfig() {
        //find our entity
        Config ret = entityManager.find(Config.class, Integer.valueOf(1));
        //there is no config entity, create default and persist
        if (ret == null) {
            ret = new Config();
            entityManager.persist(ret);
        }
        //return config entity
        return ret;
    }
}
