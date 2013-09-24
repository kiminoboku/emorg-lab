/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.kiminoboku.emorg.cdi;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.inject.Inject;
import javax.inject.Qualifier;
import pl.kiminoboku.emorg.domain.entities.Config;
import pl.kiminoboku.emorg.service.dao.SystemConfigDAO;

/**
 * Qualifier for injecting system configuration entity into beans. Produced by
 * {@link SystemConfigDAO}. Example usage:<br/>
 * <code><pre>
 * class MyService {
 *     &#064;Inject
 *     &#064;SystemConfig
 *     Config systemConfig; //default system config
 *
 *     void myMethod() {
 *         Foo setting = systemConfig.getDefaultFoo();
 *         ...
 *     }
 * }
 * </pre></code>
 *
 * @author Radek
 * @see Inject
 * @see Config
 * @see Qualifier
 */
@Qualifier
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.TYPE})
public @interface SystemConfig {
}
