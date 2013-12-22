package pl.kiminoboku.emorg.service;

import org.restlet.Component;
import org.restlet.data.Protocol;
import pl.kiminoboku.emorg.service.web.ResearchOrderResource;
import pl.kiminoboku.emorg.service.web.XsdResource;

/**
 * @author Radek
 */
public class ResourceManagerService {

    private Component component;
    private boolean on;

    public void start() {
        if (!on) {
            try {
                component = new Component();
                component.getServers().add(Protocol.HTTP, 8080);
                component.getDefaultHost().attach("/", ResearchOrderResource.class);
                component.getDefaultHost().attach("/xsd", XsdResource.class);
                component.start();
                on = true;
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    public void stop() {
        if (on) {
            on = false;
            try {
                component.stop();
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        }
    }
}
