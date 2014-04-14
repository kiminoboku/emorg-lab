/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.kiminoboku.netbeans.settings;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import javax.swing.JComponent;
import org.netbeans.spi.options.OptionsPanelController;
import org.openide.util.HelpCtx;
import org.openide.util.Lookup;

/**
 * This is not yet final so I'm not commenting it. It's only proove of concept that settings panel in NetBeans RCP can be done
 */
@OptionsPanelController.SubRegistration(
        displayName = "#AdvancedOption_DisplayName_RemoteControl",
        keywords = "#AdvancedOption_Keywords_RemoteControl",
        keywordsCategory = "Advanced/RemoteControl"
)
@org.openide.util.NbBundle.Messages({"AdvancedOption_DisplayName_RemoteControl=Remote control", "AdvancedOption_Keywords_RemoteControl=remote control"})
public final class RemoteControlOptionsPanelController extends OptionsPanelController {

    private RemoteControlPanel panel;
    private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);
    private boolean changed;

    public void update() {
        getPanel().load();
        changed = false;
    }

    public void applyChanges() {
        getPanel().store();
        changed = false;
    }

    public void cancel() {
        // need not do anything special, if no changes have been persisted yet
    }

    public boolean isValid() {
        return getPanel().valid();
    }

    public boolean isChanged() {
        return changed;
    }

    public HelpCtx getHelpCtx() {
        return null; // new HelpCtx("...ID") if you have a help set
    }

    public JComponent getComponent(Lookup masterLookup) {
        return getPanel();
    }

    public void addPropertyChangeListener(PropertyChangeListener l) {
        pcs.addPropertyChangeListener(l);
    }

    public void removePropertyChangeListener(PropertyChangeListener l) {
        pcs.removePropertyChangeListener(l);
    }

    private RemoteControlPanel getPanel() {
        if (panel == null) {
            panel = new RemoteControlPanel(this);
        }
        return panel;
    }

    void changed() {
        if (!changed) {
            changed = true;
            pcs.firePropertyChange(OptionsPanelController.PROP_CHANGED, false, true);
        }
        pcs.firePropertyChange(OptionsPanelController.PROP_VALID, null, null);
    }

}
