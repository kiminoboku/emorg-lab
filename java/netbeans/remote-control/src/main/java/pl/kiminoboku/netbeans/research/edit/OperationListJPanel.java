/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.kiminoboku.netbeans.research.edit;

import com.google.common.collect.Lists;
import java.util.List;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import pl.kiminoboku.emorg.domain.entities.operation.AbstractOperation;
import pl.kiminoboku.netbeans.components.operation.OperationRowJPanel;

/**
 *
 * @author Radek
 */
public class OperationListJPanel extends JPanel {

    private List<AbstractOperation> operations = Lists.newArrayList();

    private List<OperationRowJPanel> operationRows = Lists.newArrayList();

    public OperationListJPanel() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    }
}
