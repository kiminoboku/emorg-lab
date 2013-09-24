/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.kiminoboku.emorg.test.integration;

import org.glassfish.embeddable.GlassFishException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import pl.kiminoboku.test.util.GlassFishUtil;

/**
 * Abstract integration test with glassfish
 *
 * @author Radek
 */
public abstract class GlassFishTest {

    @BeforeClass
    public static void setUpClass() {
        GlassFishUtil.start();
    }

    @AfterClass
    public static void tearDownClass() throws GlassFishException {
        GlassFishUtil.stop();
    }

    @Before
    public void setUpTest() {
        GlassFishUtil.deployApp();
    }

    @After
    public void tearDownTest() {
        GlassFishUtil.undeployApp();
    }

    public static <T> T getBean(Class<T> beanClass) {
        return GlassFishUtil.lookup(beanClass);
    }
}
