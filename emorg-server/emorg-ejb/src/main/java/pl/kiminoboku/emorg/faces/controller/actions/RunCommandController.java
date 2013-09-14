/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.kiminoboku.emorg.faces.controller.actions;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import pl.kiminoboku.emorg.ejb.recent.command.RecentCommandRegisterBean;
import pl.kiminoboku.emorg.faces.util.FacesMessageUtil;
import pl.kiminoboku.i18n.ResourceBundleSafe;

/**
 *
 * @author Radek
 */
@Named
@RequestScoped
public class RunCommandController {
    
    @Inject
    private ResourceBundleSafe resourceBundle;
    
    @Inject
    private RecentCommandRegisterBean recentCommandRegisterBean;
    
    private String command;

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }
    
    public void executeCommand() {
        recentCommandRegisterBean.addCommand(command);
        FacesMessageUtil.addInfoMessage(resourceBundle.getString("uruchom.program.uruchomiono"));
    }
}
