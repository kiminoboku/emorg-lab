/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.kiminoboku.emorg.ejb.recent.command;

import java.util.Date;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Observes;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;
import pl.kiminoboku.emorg.cdi.SystemConfig;
import pl.kiminoboku.emorg.ejb.dao.RecentCommandDAOBean;
import pl.kiminoboku.emorg.domain.entities.RecentCommand;
import pl.kiminoboku.emorg.domain.entities.Config;

/**
 *
 * @author Radek
 */
@Stateless
@LocalBean
public class RecentCommandRegisterBean {

    @Inject
    @SystemConfig
    private Config config;

    @Inject
    private RecentCommandDAOBean recentCommandDAOBean;

    @Produces
    @Named("recentCommands")
    public List<RecentCommand> getRecentCommands() {
        return recentCommandDAOBean.findAllSortAlphabetically();
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void addCommand(String command) {
        RecentCommand existingCommand = recentCommandDAOBean.findByCommand(command);
        if(existingCommand != null) {
            existingCommand.setLastInvocation(new Date());
            recentCommandDAOBean.merge(existingCommand);
        } else {
            int recentCommandCount = recentCommandDAOBean.getCommandCount();
            if (recentCommandCount >= config.getMaxRecentCommandsCount()) {
                int oldestCommandsToDropCount = recentCommandCount - config.getMaxRecentCommandsCount() + 1;
                List<RecentCommand> commandsToDrop = recentCommandDAOBean.findOldestCommands(oldestCommandsToDropCount);
                for (RecentCommand recentCommand : commandsToDrop) {
                    recentCommandDAOBean.remove(recentCommand);
                }
            }

            RecentCommand newCommand = new RecentCommand(command);
            recentCommandDAOBean.persist(newCommand);
        }
    }
}
