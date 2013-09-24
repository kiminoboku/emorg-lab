/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.kiminoboku.emorg.service;

import java.util.Date;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import pl.kiminoboku.emorg.cdi.SystemConfig;
import pl.kiminoboku.emorg.domain.entities.Config;
import pl.kiminoboku.emorg.domain.entities.RecentCommand;
import pl.kiminoboku.emorg.service.dao.RecentCommandDAO;

/**
 * Service responsible for managing recent commands cache.
 *
 * @author Radek
 */
@Stateless
@LocalBean
public class RecentCommandUpdateService {

    /**
     * Injected system config. Needed so we can know how many recent commands we are
     * supposed to hold until we drop least recently used.
     */
    @Inject
    @SystemConfig
    Config config;

    @Inject
    RecentCommandDAO recentCommandDAO;

    /**
     * Adds given command to recent commands cache. If recent commands cache size
     * exceeds it's maximum capacity (set in system config) least recently used command
     * will be dropped to make place for given one.
     *
     * @param command command to be added to cache
     * @throws NullPointerException if {@code command == null}
     * @throws IllegalArgumentException if {@code command.trim.isEmpty()}
     */
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void addCommand(String command) {
        if (command == null) {
            throw new NullPointerException();
        }
        //to prevent cache cheating with whitespaces
        command = command.trim();
        if (command.isEmpty()) {
            throw new IllegalArgumentException();
        }
        //check if we have this command in cache already
        RecentCommand existingCommand = recentCommandDAO.findByCommand(command);
        //we actualy have given command in cache, update last invocation and merge entity
        if (existingCommand != null) {
            existingCommand.setLastInvocation(new Date());
            recentCommandDAO.merge(existingCommand);
        } else { //the command is brand new
            //check commands count
            int recentCommandCount = recentCommandDAO.getCommandsCount();
            //if commands cache size exceeds its maximum
            if (recentCommandCount >= config.getMaxRecentCommandsCount()) {
                //count how many commands to drop (we can be dropping more than just one, we have to consider if system config maxCount got lower)
                int oldestCommandsToDropCount = recentCommandCount - config.getMaxRecentCommandsCount() + 1; //plus one for new command
                //take least recently used comands
                List<RecentCommand> commandsToDrop = recentCommandDAO.findOldestCommands(oldestCommandsToDropCount);
                //drop commands
                for (RecentCommand recentCommand : commandsToDrop) {
                    recentCommandDAO.remove(recentCommand);
                }
            }

            //create and persist new command
            RecentCommand newCommand = new RecentCommand(command);
            recentCommandDAO.persist(newCommand);
        }
    }
}
