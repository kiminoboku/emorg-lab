/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.kiminoboku.netbeans.research;

import com.google.common.collect.Lists;
import java.util.List;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.table.DefaultTableModel;
import org.netbeans.api.settings.ConvertAsProperties;
import org.openide.DialogDisplayer;
import org.openide.NotifyDescriptor;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.util.NbBundle;
import org.openide.util.NbBundle.Messages;
import org.openide.windows.TopComponent;
import org.openide.windows.WindowManager;
import pl.kiminoboku.emorg.domain.entities.Research;
import pl.kiminoboku.emorg.service.ServiceFactory;
import pl.kiminoboku.netbeans.research.edit.EditResearchTopComponent;

/**
 * Top component which displays something.
 */
@ConvertAsProperties(
        dtd = "-//pl.kiminoboku.netbeans.research//ManageResearch//EN",
        autostore = false)
@TopComponent.Description(
        preferredID = "ManageResearchTopComponent",
        //iconBase="SET/PATH/TO/ICON/HERE",
        persistenceType = TopComponent.PERSISTENCE_ALWAYS)
@TopComponent.Registration(mode = "properties", openAtStartup = false)
@ActionID(category = "Window", id = "pl.kiminoboku.netbeans.research.ManageResearchTopComponent")
@ActionReference(path = "Menu/Window" /*, position = 333 */)
@TopComponent.OpenActionRegistration(
        displayName = "#CTL_ManageResearchAction",
        preferredID = "ManageResearchTopComponent")
@Messages({
    "CTL_ManageResearchAction=Researches",
    "CTL_ManageResearchTopComponent=Researches",
    "HINT_ManageResearchTopComponent=Saved researches"
})
public final class ManageResearchTopComponent extends TopComponent {

    public static ManageResearchTopComponent getInstance() {
        return (ManageResearchTopComponent) WindowManager.getDefault().findTopComponent("ManageResearchTopComponent");
    }
    private DefaultTableModel defaultTableModel;

    public ManageResearchTopComponent() {
        initComponents();
        setName(Bundle.CTL_ManageResearchTopComponent());
        setToolTipText(Bundle.HINT_ManageResearchTopComponent());
    }

    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT
     * modify this code. The content of this method is always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        addJButton = new javax.swing.JButton();
        editJButton = new javax.swing.JButton();
        copyJButton = new javax.swing.JButton();
        deleteJButton = new javax.swing.JButton();

        jScrollPane1.setViewportView(jTable1);

        org.openide.awt.Mnemonics.setLocalizedText(addJButton, org.openide.util.NbBundle.getMessage(ManageResearchTopComponent.class, "ManageResearchTopComponent.addJButton.text")); // NOI18N
        addJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addJButtonActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(editJButton, org.openide.util.NbBundle.getMessage(ManageResearchTopComponent.class, "ManageResearchTopComponent.editJButton.text")); // NOI18N
        editJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editJButtonActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(copyJButton, org.openide.util.NbBundle.getMessage(ManageResearchTopComponent.class, "ManageResearchTopComponent.copyJButton.text")); // NOI18N
        copyJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                copyJButtonActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(deleteJButton, org.openide.util.NbBundle.getMessage(ManageResearchTopComponent.class, "ManageResearchTopComponent.deleteJButton.text")); // NOI18N
        deleteJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteJButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 343, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(addJButton)
                    .addComponent(editJButton)
                    .addComponent(copyJButton)
                    .addComponent(deleteJButton))
                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {addJButton, copyJButton, deleteJButton, editJButton});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(addJButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(editJButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(copyJButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(deleteJButton)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 275, Short.MAX_VALUE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void addJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addJButtonActionPerformed
        EditResearchTopComponent.openNew(evt);
    }//GEN-LAST:event_addJButtonActionPerformed

    private void editJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editJButtonActionPerformed
        if (jTable1.getSelectionModel().isSelectionEmpty()) {
            DialogDisplayer.getDefault().notify(new NotifyDescriptor.Message(NbBundle.getMessage(ManageResearchTopComponent.class, "ManageResearchTopComponent.validate.editExactlyOneRow")));
        } else if (jTable1.getSelectionModel().getMaxSelectionIndex() - jTable1.getSelectionModel().getMinSelectionIndex() > 0) {
            DialogDisplayer.getDefault().notify(new NotifyDescriptor.Message(NbBundle.getMessage(ManageResearchTopComponent.class, "ManageResearchTopComponent.validate.editExactlyOneRow")));
        } else {
            DialogDisplayer.getDefault().notify(new NotifyDescriptor.Message("Invoke edit logic here")); //TODO invoke edit logic here
        }
    }//GEN-LAST:event_editJButtonActionPerformed

    private void copyJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_copyJButtonActionPerformed
        if (jTable1.getSelectionModel().isSelectionEmpty()) {
            DialogDisplayer.getDefault().notify(new NotifyDescriptor.Message(NbBundle.getMessage(ManageResearchTopComponent.class, "ManageResearchTopComponent.validate.copyExactlyOneRow")));
        } else if (jTable1.getSelectionModel().getMaxSelectionIndex() - jTable1.getSelectionModel().getMinSelectionIndex() > 0) {
            DialogDisplayer.getDefault().notify(new NotifyDescriptor.Message(NbBundle.getMessage(ManageResearchTopComponent.class, "ManageResearchTopComponent.validate.copyExactlyOneRow")));
        } else {
            DialogDisplayer.getDefault().notify(new NotifyDescriptor.Message("Invoke copy logic here")); //TODO invoke copy logic here
        }
    }//GEN-LAST:event_copyJButtonActionPerformed

    private void deleteJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteJButtonActionPerformed
        if (jTable1.getSelectionModel().isSelectionEmpty()) {
            DialogDisplayer.getDefault().notify(new NotifyDescriptor.Message(NbBundle.getMessage(ManageResearchTopComponent.class, "ManageResearchTopComponent.validate.removeAtLeastOneRow")));
        } else {
            String message = NbBundle.getMessage(ManageResearchTopComponent.class, "ManageResearchTopComponent.removeQuestion");
            Object answer = DialogDisplayer.getDefault().notify(new NotifyDescriptor.Confirmation(message, NotifyDescriptor.YES_NO_OPTION));
            if (answer == NotifyDescriptor.YES_OPTION) {
                DialogDisplayer.getDefault().notify(new NotifyDescriptor.Message("Invoke remove logic here")); //TODO invoke remove logic here
            }
        }
    }//GEN-LAST:event_deleteJButtonActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addJButton;
    private javax.swing.JButton copyJButton;
    private javax.swing.JButton deleteJButton;
    private javax.swing.JButton editJButton;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables

    @Override
    public void componentOpened() {
        initTable();
        refreshResearches();
    }

    @Override
    public void componentClosed() {
        // TODO add custom code on component closing
    }

    void writeProperties(java.util.Properties p) {
        // better to version settings since initial version as advocated at
        // http://wiki.apidesign.org/wiki/PropertyFiles
        p.setProperty("version", "1.0");
        // TODO store your settings
    }

    void readProperties(java.util.Properties p) {
        String version = p.getProperty("version");
        // TODO read your settings according to their version
    }

    private void initTable() {
        defaultTableModel = new DefaultTableModel(Column.columnNames(), 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        jTable1.setModel(defaultTableModel);
        jTable1.setAutoCreateRowSorter(true);
        jTable1.getRowSorter().setSortKeys(Lists.newArrayList(new RowSorter.SortKey(Column.NAME.number, SortOrder.ASCENDING)));
    }

    private Object[] researchToRowData(Research research) {
        Object[] ret = new Object[Column.values().length];
        ret[0] = research.getId();
        ret[1] = research.getName();
        ret[2] = research.getDescription();
        return ret;
    }

    public void refreshResearches() {
        defaultTableModel.setRowCount(0);
        List<Research> researches = ServiceFactory.getResearchDAOService().findAll();
        for (Research research : researches) {
            Object[] rowData = researchToRowData(research);
            defaultTableModel.addRow(rowData);
        }
    }

    private static enum Column {

        ID(NbBundle.getMessage(ManageResearchTopComponent.class, "ManageResearchTopComponent.column.id")),
        NAME(NbBundle.getMessage(ManageResearchTopComponent.class, "ManageResearchTopComponent.column.name")),
        DESCRIPTION(NbBundle.getMessage(ManageResearchTopComponent.class, "ManageResearchTopComponent.column.description"));

        private static int counter = 0;

        private static int nextNumber() {
            return counter++;
        }

        private static String[] columnNames() {
            List<String> ret = Lists.newArrayList();
            for (Column c : Lists.newArrayList(values())) {
                ret.add(c.name);
            }
            return ret.toArray(new String[0]);
        }

        private Column(String name) {
            this.number = nextNumber();
            this.name = name;
        }
        private int number;

        private String name;

    }
}
