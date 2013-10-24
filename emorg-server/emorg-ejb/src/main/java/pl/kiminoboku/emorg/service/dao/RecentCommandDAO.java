/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.kiminoboku.emorg.service.dao;

import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.enterprise.inject.Produces;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import pl.kiminoboku.emorg.domain.entities.RecentCommand;
import pl.kiminoboku.emorg.service.RecentCommandUpdateService;

/**
 * Service responsible for RecentCommand entity persistence.
 *
 * @author Radek
 */
@Stateless
@LocalBean
public class RecentCommandDAO {

    @PersistenceContext
    EntityManager entityManager;

    /**
     * Returns and produces all recent commands in cache.
     *
     * @return
     */
    @Produces
    @Named("recentCommandsAll")
    public List<RecentCommand> findAllSortAlphabetically() {
        return entityManager.createQuery("SELECT rc FROM RecentCommand rc ORDER BY UPPER(rc.command)").getResultList();
    }

    /**
     * Returns all recent commands count
     *
     * @return
     */
    public int getCommandsCount() {
        long c = (Long) entityManager.createQuery("SELECT COUNT(rc) FROM RecentCommand rc").getSingleResult();
        return (int) c;
    }

    /**
     * Returns least recently used commands but no more than given number.
     *
     * @param limit returned list size limit
     * @return
     */
    public List<RecentCommand> findOldestCommands(int limit) {
        return entityManager.createQuery("SELECT rc FROM RecentCommand rc ORDER BY rc.lastInvocation").setMaxResults(limit).getResultList();
    }

    /**
     * Removes given recent command
     *
     * @param recentCommand
     */
    public void remove(RecentCommand recentCommand) {
        entityManager.remove(recentCommand);
    }

    /**
     * Persist given recent command. Important! This method just persists command in
     * persistent storage. If you want cache manipulation logic (limiting cache size and
     * removing least used commands) you should check
     * {@link RecentCommandUpdateService}.
     *
     * @param newCommand
     * @see RecentCommandUpdateService
     */
    public void persist(RecentCommand newCommand) {
        entityManager.persist(newCommand);
    }

    /**
     * Finds and returns RecentCommand object for given command content or {@code null}
     * if there isn't any.
     *
     * @param command command content we wan't to find RecentCommand for.
     * @return
     */
    public RecentCommand findByCommand(String command) {
        try {
            return (RecentCommand) entityManager.createQuery("SELECT rc FROM RecentCommand rc WHERE rc.command = :command").setParameter("command", command).getSingleResult();
        } catch (NoResultException ex) {
            return null;
        }
    }

    /**
     * Merges given RecentCommand
     *
     * @param existingCommand
     */
    public void merge(RecentCommand existingCommand) {
        entityManager.merge(existingCommand);
    }
}
