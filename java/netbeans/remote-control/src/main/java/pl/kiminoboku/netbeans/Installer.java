/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.kiminoboku.netbeans;

import org.openide.modules.ModuleInstall;
import pl.kiminoboku.emorg.service.ServiceFactory;

public class Installer extends ModuleInstall {

    @Override
    public boolean closing() {
        ServiceFactory.getEntityManagerFactoryService().close();
        ServiceFactory.getResourceManagerService().stop();
        return true;
    }

    
    @Override
    public void restored() {
        ServiceFactory.getEntityManagerFactoryService().getEntityManager();
        ServiceFactory.getResourceManagerService().start();
    }

}
