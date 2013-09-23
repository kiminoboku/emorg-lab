/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.kiminoboku.i18n;

import java.text.MessageFormat;
import java.util.ResourceBundle;

/**
 *
 * @author Radek
 */
public class ResourceBundleSafe {

    ResourceBundle delegate;

    public ResourceBundleSafe(ResourceBundle delegate) {
        this.delegate = delegate;
    }

    public String getString(String key) {
        if (delegate.containsKey(key)) {
            return delegate.getString(key);
        } else {
            return new StringBuilder("???").append(key).append("???").toString();
        }
    }

    public String getString(String key, Object... arguments) {
        String message = delegate.getString(key);
        return MessageFormat.format(message, arguments);
    }
}
