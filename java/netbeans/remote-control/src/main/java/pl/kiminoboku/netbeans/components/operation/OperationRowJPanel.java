/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.kiminoboku.netbeans.components.operation;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.event.ActionListener;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import org.openide.util.ImageUtilities;

/**
 * Panel responsible for representation of single operation in edit list
 *
 * @author Radek
 */
public final class OperationRowJPanel extends javax.swing.JPanel {

    /**
     * Creates new OperationRowJPanel
     */
    public OperationRowJPanel() {
        initComponents();
    }

    /**
     * Creates new OperationRowJPanel with given order number, operation type and description.
     *
     * @param orderNumber order number of created row
     * @param operationType operation type of created row
     * @param description description of created row
     * @param settingsEnabled determines if settings button of created row is supposed to be enabled
     */
    public OperationRowJPanel(int orderNumber, OperationTypeUI operationType, String description, boolean settingsEnabled) {
        this();
        numberLabel.setText(String.valueOf(orderNumber));
        String iconPath = operationType.getIconPath();
        ImageIcon imageIcon = ImageUtilities.loadImageIcon(iconPath, false);
        setIcon(imageIcon);
        descriptionLabel.setText(description);
        settingsButton.setEnabled(settingsEnabled);
        repaint();
    }

    /**
     * Adds action listener to "Add above" button
     *
     * @param l action listener
     */
    public void addAboveButtonActionListener(ActionListener l) {
        addAboveButton.addActionListener(l);
    }

    /**
     * Adds action listener to "Add below" button
     *
     * @param l action listener
     */
    public void addBelowButtonActionListener(ActionListener l) {
        addBelowButton.addActionListener(l);
    }

    /**
     * Adds action listener to "Remove me" button
     *
     * @param l
     */
    public void addRemoveButtonActionListener(ActionListener l) {
        removeButton.addActionListener(l);
    }

    /**
     * Returns description label text
     *
     * @return description label text
     */
    public String getDescriptionLabelText() {
        return descriptionLabel.getText();
    }

    /**
     * Sets description label text
     *
     * @param descriptionLabelText new description label text
     */
    public void setDescriptionLabelText(String descriptionLabelText) {
        descriptionLabel.setText(descriptionLabelText);
    }

    /**
     * Returns number label text
     *
     * @return number label text
     */
    public String getNumberLabelText() {
        return numberLabel.getText();
    }

    /**
     * Sets number label text
     *
     * @param numberLabelText number label text
     */
    public void setNumberLabelText(String numberLabelText) {
        numberLabel.setText(numberLabelText);
    }

    /**
     * Returns operation icon
     *
     * @return operation icon
     */
    public Icon getIcon() {
        return operationIcon.getIcon();
    }

    /**
     * Sets operation icon
     *
     * @param icon operation icon
     */
    public void setIcon(Icon icon) {
        operationIcon.setIcon(icon);
    }

    @Override
    public void setBackground(Color bg) {
        super.setBackground(bg);
        setBackground(bg, getComponents());
    }

    /**
     * Sets background color in given components
     *
     * @param bg color
     * @param components components
     */
    private void setBackground(Color bg, Component[] components) {
        for (Component c : components) {
            c.setBackground(bg);
            if (c instanceof Container) {
                setBackground(bg, ((Container) c).getComponents());
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT
     * modify this code. The content of this method is always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        numberLabel = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        operationIcon = new pl.kiminoboku.netbeans.components.IconComponent();
        descriptionLabel = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        settingsButton = new javax.swing.JButton();
        removeButton = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        addAboveButton = new javax.swing.JButton();
        addBelowButton = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();

        setMaximumSize(new java.awt.Dimension(32767, 73));
        setMinimumSize(new java.awt.Dimension(506, 73));

        org.openide.awt.Mnemonics.setLocalizedText(numberLabel, org.openide.util.NbBundle.getMessage(OperationRowJPanel.class, "OperationRowJPanel.numberLabel.text")); // NOI18N
        numberLabel.setMaximumSize(new java.awt.Dimension(18, 14));
        numberLabel.setMinimumSize(new java.awt.Dimension(18, 14));
        numberLabel.setPreferredSize(new java.awt.Dimension(18, 14));

        jPanel1.setLayout(new java.awt.GridBagLayout());

        operationIcon.setPreferredSize(new java.awt.Dimension(32, 32));
        jPanel1.add(operationIcon, new java.awt.GridBagConstraints());

        descriptionLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        org.openide.awt.Mnemonics.setLocalizedText(descriptionLabel, org.openide.util.NbBundle.getMessage(OperationRowJPanel.class, "OperationRowJPanel.descriptionLabel.text")); // NOI18N

        jPanel2.setLayout(new javax.swing.BoxLayout(jPanel2, javax.swing.BoxLayout.LINE_AXIS));

        org.openide.awt.Mnemonics.setLocalizedText(settingsButton, org.openide.util.NbBundle.getMessage(OperationRowJPanel.class, "OperationRowJPanel.settingsButton.text")); // NOI18N
        jPanel2.add(settingsButton);

        org.openide.awt.Mnemonics.setLocalizedText(removeButton, org.openide.util.NbBundle.getMessage(OperationRowJPanel.class, "OperationRowJPanel.removeButton.text")); // NOI18N
        jPanel2.add(removeButton);

        jPanel3.setLayout(new javax.swing.BoxLayout(jPanel3, javax.swing.BoxLayout.Y_AXIS));

        org.openide.awt.Mnemonics.setLocalizedText(addAboveButton, org.openide.util.NbBundle.getMessage(OperationRowJPanel.class, "OperationRowJPanel.addAboveButton.text")); // NOI18N
        jPanel3.add(addAboveButton);

        org.openide.awt.Mnemonics.setLocalizedText(addBelowButton, org.openide.util.NbBundle.getMessage(OperationRowJPanel.class, "OperationRowJPanel.addBelowButton.text")); // NOI18N
        jPanel3.add(addBelowButton);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(numberLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(descriptionLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 125, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addComponent(jSeparator1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(descriptionLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(numberLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addAboveButton;
    private javax.swing.JButton addBelowButton;
    private javax.swing.JLabel descriptionLabel;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel numberLabel;
    private pl.kiminoboku.netbeans.components.IconComponent operationIcon;
    private javax.swing.JButton removeButton;
    private javax.swing.JButton settingsButton;
    // End of variables declaration//GEN-END:variables
}