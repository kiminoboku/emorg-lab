/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.kiminoboku.netbeans.research;

import java.awt.Component;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;
import pl.kiminoboku.emorg.domain.entities.Research;
import pl.kiminoboku.emorg.service.ServiceFactory;

/**
 *
 * @author Radek
 */
public class ExecuteResearchListJPanel extends javax.swing.JPanel {

    /**
     * Creates new form ExecuteResearchListJPanel
     */
    public ExecuteResearchListJPanel() {
        initComponents();
        List<Research> researches = ServiceFactory.getResearchService().findAll();
        DefaultComboBoxModel<Research> comboBoxModel = new DefaultComboBoxModel<>(researches.toArray(new Research[0]));
        researchesComboBox.setModel(comboBoxModel);
        researchesComboBox.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                value = ((Research) value).getName();
                return super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            }
        });
        revalidate();
        repaint();
    }

    /**
     * Returns chosen research id
     * @return chosen research id
     */
    public Integer getSelectedResearchId() {
        return researchesComboBox.getItemAt(researchesComboBox.getSelectedIndex()).getId();
    }

    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT
     * modify this code. The content of this method is always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        researchesComboBox = new javax.swing.JComboBox<Research>();
        chooseResearchLabel = new javax.swing.JLabel();

        researchesComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        org.openide.awt.Mnemonics.setLocalizedText(chooseResearchLabel, org.openide.util.NbBundle.getMessage(ExecuteResearchListJPanel.class, "ExecuteResearchListJPanel.chooseResearchLabel.text")); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(chooseResearchLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(researchesComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(chooseResearchLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(researchesComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel chooseResearchLabel;
    private javax.swing.JComboBox<Research> researchesComboBox;
    // End of variables declaration//GEN-END:variables
}
