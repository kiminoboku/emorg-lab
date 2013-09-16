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
import pl.kiminoboku.emorg.service.dao.RecentCommandDAO;
import pl.kiminoboku.emorg.domain.entities.RecentCommand;
import pl.kiminoboku.emorg.domain.entities.Config;

/**
 *
 * @author Radek
 */
@Stateless
@LocalBean
public class RecentCommandUpdateService {

    @Inject
    @SystemConfig
    Config config;

    @Inject
    RecentCommandDAO recentCommandDAO;

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void addCommand(String command) {
        RecentCommand existingCommand = recentCommandDAO.findByCommand(command);
        if (existingCommand != null) {
            existingCommand.setLastInvocation(new Date());
            recentCommandDAO.merge(existingCommand);
        } else {
            int recentCommandCount = recentCommandDAO.getCommandCount();
            if (recentCommandCount >= config.getMaxRecentCommandsCount()) {
                int oldestCommandsToDropCount = recentCommandCount - config.getMaxRecentCommandsCount() + 1;
                List<RecentCommand> commandsToDrop = recentCommandDAO.findOldestCommands(oldestCommandsToDropCount);
                for (RecentCommand recentCommand : commandsToDrop) {
                    recentCommandDAO.remove(recentCommand);
                }
            }

            RecentCommand newCommand = new RecentCommand(command);
            recentCommandDAO.persist(newCommand);
        }
    }
}
