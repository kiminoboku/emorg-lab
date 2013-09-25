/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.kiminoboku.emorg.faces.controller.application.actions;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import pl.kiminoboku.emorg.faces.util.FacesMessageUtil;
import pl.kiminoboku.emorg.service.RecentCommandUpdateService;
import pl.kiminoboku.i18n.ResourceBundleSafe;

/**
 * Application controller responsible for handling running remote commands view
 * requests.
 *
 * @author Radek
 */
@Named
@RequestScoped
public class RunCommandApplicationController {

    /**
     * System messages (l10n)
     */
    @Inject
    ResourceBundleSafe resourceBundle;

    /**
     * Service responsible for managing recent commands cache
     */
    @Inject
    RecentCommandUpdateService recentCommandUpdateService;

    /**
     * Command to invoke
     */
    String command;

    /**
     * Returns command to invoke
     *
     * @return
     */
    public String getCommand() {
        return command;
    }

    /**
     * Sets command to invoke
     *
     * @param command
     */
    public void setCommand(String command) {
        this.command = command;
    }

    /**
     * Handles "run remote command" view request
     */
    public void executeCommand() {
        //TODO run the command

        //register recent command in cache
        recentCommandUpdateService.addCommand(command);
        //add success notification
        FacesMessageUtil.addInfoMessage(resourceBundle.getString("uruchom.program.uruchomiono"));
    }
}
