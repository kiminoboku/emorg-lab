/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.kiminoboku.emorg.ejb.dao;

import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import pl.kiminoboku.emorg.domain.entities.RecentCommand;

/**
 *
 * @author Radek
 */
@Stateless
@LocalBean
public class RecentCommandDAOBean {

    @PersistenceContext
    private EntityManager entityManager;

    public List<RecentCommand> findAllSortAlphabetically() {
        return entityManager.createQuery("SELECT rc FROM RecentCommand rc ORDER BY UPPER(rc.command)").getResultList();
    }

    public int getCommandCount() {
        long c = (Long)entityManager.createQuery("SELECT COUNT(rc) FROM RecentCommand rc").getSingleResult();
        return (int)c;
    }

    public List<RecentCommand> findOldestCommands(int limit) {
        return entityManager.createQuery("SELECT rc FROM RecentCommand rc ORDER BY rc.lastInvocation").setMaxResults(limit).getResultList();
    }

    public void remove(RecentCommand recentCommand) {
        entityManager.remove(recentCommand);
    }

    public void persist(RecentCommand newCommand) {
        entityManager.persist(newCommand);
    }

    public RecentCommand findByCommand(String command) {
        try {
            return (RecentCommand)entityManager.createQuery("SELECT rc FROM RecentCommand rc WHERE rc.command = :command").setParameter("command", command).getSingleResult();
        } catch(NoResultException ex) {
            return null;
        }
    }

    public void merge(RecentCommand existingCommand) {
        entityManager.merge(existingCommand);
    }
}
