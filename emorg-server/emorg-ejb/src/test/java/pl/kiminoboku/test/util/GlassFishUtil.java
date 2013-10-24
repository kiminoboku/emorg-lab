/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.kiminoboku.test.util;

import java.io.File;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.glassfish.embeddable.GlassFish;
import org.glassfish.embeddable.GlassFishException;
import org.glassfish.embeddable.GlassFishRuntime;

/**
 * Integration tests util class responsible for managing embedded glassfish instance.
 *
 * @author Radek
 */
public class GlassFishUtil {

    private static GlassFish glassFish;

    private static InitialContext initialContext;

    public static void start() {
        try {
            Class.forName("org.postgresql.ds.PGSimpleDataSource");
            glassFish = GlassFishRuntime.bootstrap().newGlassFish();
            glassFish.start();
            glassFish.getCommandRunner().run("add-resources", "./src/test/setup/glassfish-resources.xml");

            initialContext = new InitialContext();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    public static void stop() {
        try {
            glassFish.stop();
            glassFish.dispose();
        } catch (GlassFishException ex) {
            throw new RuntimeException(ex);
        }
    }

    public static <T> T lookup(Class<T> beanClass) {
        try {
            String jndi = "java:global/classes/" + beanClass.getSimpleName();
            return (T) initialContext.lookup(jndi);
        } catch (NamingException ex) {
            throw new RuntimeException(ex);
        }
    }

    public static void deployApp() {
        try {
            glassFish.getDeployer().deploy(new File("./target/classes"));
        } catch (GlassFishException ex) {
            throw new RuntimeException(ex);
        }
    }

    public static void undeployApp() {
        try {
            glassFish.getDeployer().undeploy(glassFish.getDeployer().getDeployedApplications().iterator().next());
        } catch (GlassFishException ex) {
            throw new RuntimeException(ex);
        }
    }
}
