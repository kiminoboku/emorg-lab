/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.kiminoboku.netbeans.research;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.openide.DialogDescriptor;
import org.openide.DialogDisplayer;
import org.openide.NotifyDescriptor;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionRegistration;
import org.openide.util.NbBundle;
import org.openide.util.NbBundle.Messages;
import pl.kiminoboku.emorg.service.ServiceFactory;

@ActionID(
        category = "Tools",
        id = "pl.kiminoboku.netbeans.research.ExecuteResearchAction")
@ActionRegistration(
        displayName = "#CTL_ExecuteResearchAction")
@ActionReference(path = "Menu/Tools", position = 150)
@Messages("CTL_ExecuteResearchAction=Execute research")
public final class ExecuteResearchAction implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        if (ServiceFactory.getResearchService().countAll() == 0) {
            String message = NbBundle.getMessage(ExecuteResearchAction.class, "ExecuteResearchAction.noResearches");
            DialogDisplayer.getDefault().notify(new NotifyDescriptor.Message(message));
        } else {
            ExecuteResearchListJPanel executeResearchListJPanel = new ExecuteResearchListJPanel();
            DialogDescriptor dialogDescriptor = new DialogDescriptor(executeResearchListJPanel, null);
            dialogDescriptor.setMessageType(DialogDescriptor.QUESTION_MESSAGE);
            dialogDescriptor.setModal(true);
            dialogDescriptor.setOptionType(DialogDescriptor.OK_CANCEL_OPTION);
            DialogDisplayer.getDefault().createDialog(dialogDescriptor).setVisible(true);
            if (dialogDescriptor.getValue().equals(DialogDescriptor.OK_OPTION)) {
                ServiceFactory.getResearchService().executeResearch(executeResearchListJPanel.getSelectedResearchId());
            }
        }
    }
}
