/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pl.kiminoboku.netbeans.components.operation;

import java.util.Objects;
import javax.validation.ValidationException;
import org.apache.commons.lang3.StringUtils;
import pl.kiminoboku.emorg.domain.entities.operation.TextMessageOperation;
import pl.kiminoboku.netbeans.ValidateMe;

/**
 *
 * @author Radek
 */
public class EditTextMessageOperationJPanel extends javax.swing.JPanel implements OperationCreator<TextMessageOperation>, ValidateMe {
    
    private TextMessageOperation operation;
    
    /**
     * Creates new form EditTextMessageOperationJPanel
     */
    public EditTextMessageOperationJPanel() {
        initComponents();
    }
    
    public EditTextMessageOperationJPanel(TextMessageOperation toEdit) {
        Objects.requireNonNull(toEdit);
        initComponents();
        operation = toEdit;
        contentTextArea.setText(operation.getMessageContent());
        titleField.setText(operation.getMessageTitle());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        titleLabel = new javax.swing.JLabel();
        titleField = new javax.swing.JTextField();
        contentLabel = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        contentTextArea = new javax.swing.JTextArea();

        org.openide.awt.Mnemonics.setLocalizedText(titleLabel, org.openide.util.NbBundle.getMessage(EditTextMessageOperationJPanel.class, "EditTextMessageOperationJPanel.titleLabel.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(contentLabel, org.openide.util.NbBundle.getMessage(EditTextMessageOperationJPanel.class, "EditTextMessageOperationJPanel.contentLabel.text")); // NOI18N

        contentTextArea.setColumns(20);
        contentTextArea.setLineWrap(true);
        contentTextArea.setRows(5);
        contentTextArea.setWrapStyleWord(true);
        jScrollPane1.setViewportView(contentTextArea);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(contentLabel)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(titleLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(titleField)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(titleLabel)
                    .addComponent(titleField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(contentLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 232, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel contentLabel;
    private javax.swing.JTextArea contentTextArea;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField titleField;
    private javax.swing.JLabel titleLabel;
    // End of variables declaration//GEN-END:variables

    @Override
    public TextMessageOperation createOperation() {
        if(operation == null) {
            operation = new TextMessageOperation();
        }
        String messageTitle = titleField.getText();
        operation.setMessageTitle(messageTitle);
        
        String messageContent = contentTextArea.getText();
        operation.setMessageContent(messageContent);
        
        String description = contentTextArea.getText();
        operation.setDescription(description);
        
        return operation;
    }

    @Override
    public void isDataValid() {
        if(StringUtils.isEmpty(contentTextArea.getText())) {
            throw new ValidationException("EditTextMessageOperationJPanel.contentCannotBeEmpty");
        }
    }
}