/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.kiminoboku.netbeans.research.log;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import java.awt.event.ActionEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.jdesktop.swingx.treetable.AbstractTreeTableModel;
import org.netbeans.api.settings.ConvertAsProperties;
import org.openide.awt.ActionID;
import org.openide.awt.Actions;
import org.openide.util.NbBundle.Messages;
import org.openide.windows.TopComponent;
import pl.kiminoboku.emorg.domain.entities.OperationLog;
import pl.kiminoboku.emorg.domain.entities.Research;
import pl.kiminoboku.emorg.domain.entities.ResearchLog;
import pl.kiminoboku.emorg.service.ServiceFactory;

/**
 * Top component which displays something.
 */
@ConvertAsProperties(
        dtd = "-//pl.kiminoboku.netbeans.research.log//ResearchLog//EN",
        autostore = false)
@TopComponent.Description(
        preferredID = "ResearchLogTopComponent",
        //iconBase="SET/PATH/TO/ICON/HERE",
        persistenceType = TopComponent.PERSISTENCE_NEVER)
@TopComponent.Registration(mode = "output", openAtStartup = false)
@ActionID(category = ResearchLogTopComponent.ACTION_CATEGORY, id = ResearchLogTopComponent.ACTION_ID)
//@ActionReference(path = "Menu/Window" /*, position = 333 */)
@TopComponent.OpenActionRegistration(
        displayName = "#CTL_ResearchLogAction")
@Messages({
    "CTL_ResearchLogAction=ResearchLog",
    "CTL_ResearchLogTopComponent=ResearchLog Window",
    "HINT_ResearchLogTopComponent=This is a ResearchLog window"
})
public final class ResearchLogTopComponent extends TopComponent {

    /**
     * Component action category
     */
    public static final String ACTION_CATEGORY = "Window";
    
    private static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

    /**
     * Component action id
     */
    public static final String ACTION_ID = "pl.kiminoboku.netbeans.research.log.ResearchLogTopComponent";

    private static final Queue<Integer> logIdsQueue = Lists.newLinkedList();

    private static final Map<Integer, TopComponent> registeredLogOutputs = Maps.newHashMap();

    public static void open(ActionEvent evt, Integer researchId) {
        if (registeredLogOutputs.containsKey(researchId)) {
            registeredLogOutputs.get(researchId).requestActive();
        } else {
            logIdsQueue.add(researchId);
            Actions.forID(ACTION_CATEGORY, ACTION_ID).actionPerformed(evt);
        }
    }
    private Integer researchId;

    private List<ResearchLog> logs;

    private AbstractTreeTableModel treeTableModel = new AbstractTreeTableModel(new Object()) {
        @Override
        public int getColumnCount() {
            return Column.values().length;
        }

        @Override
        public String getColumnName(int column) {
            return Column.values()[column].name;
        }

        @Override
        public Object getValueAt(Object o, int i) {
            if (o instanceof ResearchLog) {
                return Column.values()[i].getValue((ResearchLog) o);
            } else if (o instanceof OperationLog) {
                return Column.values()[i].getValue((OperationLog) o);
            } else {
                return null;
            }
        }

        @Override
        public Object getChild(Object parent, int index) {
            if (parent instanceof ResearchLog) {
                return ((ResearchLog) parent).getOperationLogs().get(index);
            } else {
                return logs.get(index);
            }
        }

        @Override
        public int getChildCount(Object parent) {
            if (parent instanceof ResearchLog) {
                return ((ResearchLog) parent).getOperationLogs().size();
            } else if (parent instanceof OperationLog) {
                return 0;
            } else {
                return logs.size();
            }
        }

        @Override
        public int getIndexOfChild(Object parent, Object child) {
            if (child instanceof ResearchLog) {
                return logs.indexOf(child);
            } else {
                return ((ResearchLog) parent).getOperationLogs().indexOf(child);
            }
        }

        @Override
        public boolean isCellEditable(Object node, int column) {
            return false;
        }

        @Override
        public boolean isLeaf(Object node) {
            return (node instanceof OperationLog);
        }
    };

    public ResearchLogTopComponent() {
        initComponents();
        setName(Bundle.CTL_ResearchLogTopComponent());
        setToolTipText(Bundle.HINT_ResearchLogTopComponent());

        researchId = logIdsQueue.poll();
        Research research = ServiceFactory.getResearchService().findById(researchId);
        logs = ServiceFactory.getResearchLogService().findAllByResearchId(researchId);
        jXTreeTable1.setTreeTableModel(treeTableModel);
        jXTreeTable1.setRootVisible(false);
        setName("Log: " + research.getName());
    }

    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT
     * modify this code. The content of this method is always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jXTreeTable1 = new org.jdesktop.swingx.JXTreeTable();
        jButton1 = new javax.swing.JButton();

        jScrollPane1.setViewportView(jXTreeTable1);

        org.openide.awt.Mnemonics.setLocalizedText(jButton1, org.openide.util.NbBundle.getMessage(ResearchLogTopComponent.class, "ResearchLogTopComponent.jButton1.text")); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 297, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 275, Short.MAX_VALUE)
                        .addGap(14, 14, 14))))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        JFileChooser jFileChooser = new JFileChooser();
        jFileChooser.setAcceptAllFileFilterUsed(false);
        jFileChooser.setApproveButtonText("Save");
        jFileChooser.setDialogTitle("Save as csv");
        jFileChooser.setDialogType(JFileChooser.SAVE_DIALOG);
        jFileChooser.setFileFilter(new FileNameExtensionFilter("CSV File", "csv"));
        jFileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        jFileChooser.setMultiSelectionEnabled(false);
        int result = jFileChooser.showSaveDialog(null);
        if(result == JFileChooser.APPROVE_OPTION) {
            File savedCsv = jFileChooser.getSelectedFile();
            exportData(savedCsv);
        }
        
    }//GEN-LAST:event_jButton1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JScrollPane jScrollPane1;
    private org.jdesktop.swingx.JXTreeTable jXTreeTable1;
    // End of variables declaration//GEN-END:variables

    @Override
    public void componentOpened() {
        registeredLogOutputs.put(researchId, this);
    }

    @Override
    public void componentClosed() {
        registeredLogOutputs.remove(researchId);
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

    private void exportData(File savedCsv) {
        try {
            try(BufferedWriter writer = new BufferedWriter(new FileWriter(savedCsv))) {
                writer.write("Event;Time;Description\n");
                for(ResearchLog researchLog : logs) {
                    String event = Column.EVENT.getValue(researchLog).toString();
                    String time = DATE_FORMAT.format(researchLog.getResearchStartTime());
                    String description = Column.DESCRIPTION.getValue(researchLog).toString();
                    writeDataLine(writer, event, time, description);
                    
                    for(OperationLog operationLog : researchLog.getOperationLogs()) {
                        event = Column.EVENT.getValue(operationLog).toString();
                        time = DATE_FORMAT.format(operationLog.getOperationTime());
                        description = operationLog.getLogMessage();
                        writeDataLine(writer, event, time, description);
                    }
                }
                
                JOptionPane.showMessageDialog(null, "File saved");
            }
        } catch(IOException ex) {
            throw new RuntimeException(ex);
        }
    }
    
    
    private void writeDataLine(BufferedWriter writer, String event, String time, String description) throws IOException {
        String message = MessageFormat.format("{0};{1};{2}\n", event, time, description);
        writer.write(message);
    }

    public static enum Column {

        EVENT("Event"),
        TIME("Time"),
        DESCRIPTION("Description");

        private Column(String name) {
            this.name = name;
        }
        private String name;

        private Object getValue(ResearchLog researchLog) {
            switch (this) {
                case DESCRIPTION:
                case EVENT:
                    return "Research started";
                case TIME:
                    return researchLog.getResearchStartTime();
                default:
                    throw new AssertionError(this);
            }
        }

        private Object getValue(OperationLog operationLog) {
            switch (this) {
                case DESCRIPTION:
                    return operationLog.getLogMessage();
                case EVENT:
                    return operationLog.getOperationType();
                case TIME:
                    return operationLog.getOperationTime();
                default:
                    throw new AssertionError(this);
            }
        }
    }
}
