/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.kiminoboku.netbeans.research.edit;

import com.google.common.collect.Lists;
import java.awt.Dialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.validation.ValidationException;
import org.openide.DialogDescriptor;
import org.openide.DialogDisplayer;
import org.openide.NotifyDescriptor;
import pl.kiminoboku.emorg.domain.entities.operation.AbstractOperation;
import pl.kiminoboku.netbeans.ValidateMe;
import pl.kiminoboku.netbeans.components.operation.ChooseOperationJPanel;
import pl.kiminoboku.netbeans.components.operation.OperationCreator;
import pl.kiminoboku.netbeans.components.operation.OperationRowJPanel;
import pl.kiminoboku.netbeans.components.operation.OperationTypeUI;

/**
 *
 * @author Radek
 */
public class OperationListJPanel extends JPanel {

    private static final String BACKGROUND = "List.background";

    private static final String SELECTION_BACKGROUND = "List.selectionBackground";

    private JButton addButton;

    private List<AbstractOperation> operations = Lists.newArrayList();

    private List<OperationRowJPanel> operationRows = Lists.newArrayList();

    public OperationListJPanel() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        initAddButton();
    }

    private void initAddButton() {
        addButton = new JButton("Add operation");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addNewOperation(0);
            }
        });
        add(addButton);
    }

    public void addNewOperation(int index) {
        ChooseOperationJPanel chooseOperationJPanel = new ChooseOperationJPanel();
        DialogDescriptor chooseOperationDialogDescriptor = new DialogDescriptor(chooseOperationJPanel, "Choose operation type");
        chooseOperationDialogDescriptor.setOptions(new Object[]{DialogDescriptor.CANCEL_OPTION});
        chooseOperationDialogDescriptor.setClosingOptions(new Object[]{DialogDescriptor.CANCEL_OPTION});
        chooseOperationDialogDescriptor.setMessageType(DialogDescriptor.QUESTION_MESSAGE);
        final Dialog chooseOperationTypeDialog = DialogDisplayer.getDefault().createDialog(chooseOperationDialogDescriptor);
        chooseOperationJPanel.addChooseButtonsActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                chooseOperationTypeDialog.dispose();
            }
        });
        chooseOperationTypeDialog.setVisible(true);
        if (chooseOperationJPanel.getChosenOperationType() != null) {
            OperationTypeUI chosenOperationType = chooseOperationJPanel.getChosenOperationType();
            AbstractOperation operationToAdd;

            if (chosenOperationType.getDefaultOperation() != null) {
                operationToAdd = chosenOperationType.getDefaultOperation();
            } else {
                final JPanel editPanel = chosenOperationType.createOperationEditPanel();
                DialogDescriptor editDialogDescriptor = new DialogDescriptor(editPanel, "Provide operation details");
                editDialogDescriptor.setMessageType(DialogDescriptor.QUESTION_MESSAGE);
                editDialogDescriptor.setAdditionalOptions(new Object[]{DialogDescriptor.OK_OPTION});
                editDialogDescriptor.setClosingOptions(new Object[]{DialogDescriptor.CANCEL_OPTION});
                editDialogDescriptor.setValue(DialogDescriptor.CANCEL_OPTION);
                final Dialog editDialog = DialogDisplayer.getDefault().createDialog(editDialogDescriptor);
                editDialogDescriptor.setButtonListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource().equals(DialogDescriptor.OK_OPTION)) {
                            if (editPanel instanceof ValidateMe) {
                                try {
                                    ((ValidateMe) editPanel).isDataValid();
                                    editDialog.dispose();
                                } catch (ValidationException ex) {
                                    DialogDisplayer.getDefault().notify(new NotifyDescriptor.Message(ex.getMessage(), NotifyDescriptor.WARNING_MESSAGE));
                                }
                            } else {
                                editDialog.dispose();
                            }
                        }
                    }
                });
                editDialog.setVisible(true);
                operationToAdd = ((OperationCreator) editPanel).createOperation();
            }


            if (operationToAdd != null) {
                addNewOperation(index, chosenOperationType, operationToAdd);
            }
        }
    }

    public void addNewOperation(int index, OperationTypeUI operationTypeUI, AbstractOperation operationToAdd) {
        remove(addButton);

        operations.add(operationToAdd);
        OperationRowJPanel operationRowJPanel = new OperationRowJPanel(index + 1, operationTypeUI, operationToAdd.getDescription(), operationTypeUI.getDefaultOperation() == null);
        operationRows.add(operationRowJPanel);
        add(operationRowJPanel, index);

        revalidate();
    }
}
