/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.kiminoboku.netbeans.research.edit;

import com.google.common.collect.Lists;
import java.awt.Dialog;
import java.awt.Rectangle;
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
        OperationTypeUI chosenOperationType = chooseOperationType();
        if (chosenOperationType != null) {
            AbstractOperation operationToAdd;

            if (chosenOperationType.getDefaultOperation() != null) {
                operationToAdd = chosenOperationType.getDefaultOperation();
            } else {
                operationToAdd = createOperationFromOperationType(chosenOperationType);
            }


            if (operationToAdd != null) {
                addNewOperation(index, chosenOperationType, operationToAdd);
            }
        }
    }

    private OperationTypeUI chooseOperationType() {
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
        return chooseOperationJPanel.getChosenOperationType();
    }

    private static AbstractOperation createOperationFromOperationType(OperationTypeUI operationTypeUI) {
        final JPanel editPanel = operationTypeUI.createOperationEditPanel();
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
        return ((OperationCreator) editPanel).createOperation();
    }

    public void addNewOperation(int index, OperationTypeUI operationTypeUI, AbstractOperation operationToAdd) {
        remove(addButton);

        operations.add(index, operationToAdd);
        final OperationRowJPanel operationRowJPanel = new OperationRowJPanel(index + 1, operationTypeUI, operationToAdd.getDescription(), operationTypeUI.getDefaultOperation() == null);
        operationRowJPanel.addAboveButtonActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addAboveGivenOperationRowJPanel(operationRowJPanel);
            }
        });
        operationRowJPanel.addBelowButtonActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addBelowGivenOperationRowJPanel(operationRowJPanel);
            }
        });
        operationRows.add(index, operationRowJPanel);
        add(operationRowJPanel, index);
        reindexRows();
        scrollRectToVisible(new Rectangle(0, index * getRowHeight(), getWidth(), getRowHeight()));

        revalidate();
    }

    private int getRowHeight() {
        if (operationRows.isEmpty()) {
            return 0;
        } else {
            return operationRows.get(0).getHeight();
        }
    }

    private void addAboveGivenOperationRowJPanel(OperationRowJPanel rowToBeFollowing) {
        int index = operationRows.indexOf(rowToBeFollowing);
        addNewOperation(index);
    }

    private void addBelowGivenOperationRowJPanel(OperationRowJPanel rowToBePrevious) {
        int index = operationRows.indexOf(rowToBePrevious);
        addNewOperation(index + 1);
    }

    private void reindexRows() {
        for (int i = 0; i < operationRows.size(); ++i) {
            operationRows.get(i).setNumberLabelText(String.valueOf(i + 1));
        }
    }
}
