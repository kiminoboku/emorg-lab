/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.kiminoboku.i18n;

import java.text.MessageFormat;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * Util class responsible for safely accesing resource bundle
 *
 * @author Radek
 * @see #getString(java.lang.String)
 */
public class ResourceBundleSafe {

    /**
     * Actual resource bundle that holds messages
     */
    ResourceBundle delegate;

    /**
     * Creates new object decorating given resource bundle. Delegate isn't copied in any
     * way so changes within this object will be reflected in created object.
     *
     * @param delegate
     */
    public ResourceBundleSafe(ResourceBundle delegate) {
        this.delegate = delegate;
    }

    /**
     * Returns message for given key or {@code "???" + key + "???"} value if given key
     * is not present in resource instead of throwing {@link MissingResourceException}
     *
     * @param key key that we search message for
     * @return message value or {@code "???" + key + "???"} if key is not present in
     * delegated resource bundle
     */
    public String getString(String key) {
        if (delegate.containsKey(key)) {
            return delegate.getString(key);
        } else {
            return new StringBuilder("???").append(key).append("???").toString();
        }
    }

    /**
     * Returns message for given key formatted with given arguments. Returns
     * {@code "???" + key + "???"} if given key is not present in delegated resource
     * bundle
     *
     * @param key key that we search message for
     * @param arguments format arguments
     * @return formatted message
     * @see MessageFormat#format(java.lang.String, java.lang.Object[])
     */
    public String getString(String key, Object... arguments) {
        String message = getString(key);
        return MessageFormat.format(message, arguments);
    }
}
