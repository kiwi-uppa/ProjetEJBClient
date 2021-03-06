/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package grazimba.ttsing.projetejbClient;


import grazimba.ttsing.projetejb.*;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Date;
import java.util.List;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;


/**
 *
 * @author grazimba & ttsing
 * 
 * 
 */
public final class MainWindow extends javax.swing.JFrame {

    /**
     * Creates new form MainWindow
     */
    public MainWindow() {
        if(ProjetEJBClient.getTypeProgram() == PROGRAM_CLIENT.POLICE)
            this.setTitle("Police Station");
        else
            this.setTitle("Fire Station");
        
        initComponents();
        
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                ProjetEJBClient.getCont().ExitQuery();
            }
        });
    }
    
    /*
     * Init Component of the MainwWidow
     */

    private void initComponents() {

        jLabelTitle = new javax.swing.JLabel();
        jButtonQuit = new javax.swing.JButton();
        jComboBoxCrisis = new javax.swing.JComboBox();
        jButtonAddCrise = new javax.swing.JButton();
        jButtonCloseCrise = new javax.swing.JButton();
        jScrollPaneVehicules = new javax.swing.JScrollPane();
        jTableVehicules = new javax.swing.JTable();
        jLabelVehicules = new javax.swing.JLabel();
        jLabelDescription = new javax.swing.JLabel();
        jScrollPaneDescription = new javax.swing.JScrollPane();
        jTextAreaDescription = new javax.swing.JTextArea();
        jButtonAddVoiture = new javax.swing.JButton();
        jButtonRemoveVoiture = new javax.swing.JButton();
        jButtonAddRoute = new javax.swing.JButton();
        jButtonConfRoute = new javax.swing.JButton();
        jButtonDeclineRoute = new javax.swing.JButton();
        
        _currentCrisis = new Integer(-1);
        _updating = false;

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        
        jComboBoxCrisis.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
                if(!_updating)
                    CrisisComboBoxItemChanged(evt);
            }
        });

        jLabelTitle.setFont(new java.awt.Font("Ubuntu", 1, 24)); // NOI18N
        jLabelTitle.setHorizontalAlignment(SwingConstants.LEFT);
        jLabelTitle.setText("");

        jButtonQuit.setText("Quit");
        jButtonQuit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                jButtonQuitActionPerformed(evt);
            }
        });
        
        jButtonAddCrise.setText("Add Crisis");
        jButtonAddCrise.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                jButtonAddCrisePerformed(evt);
            }
        });
        
        jButtonCloseCrise.setText("Close Crisis");
        jButtonCloseCrise.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCloseCrisePerformed(evt);
            }
        });
        
        jTableVehicules.setModel(new MyTableModel(new Object [][] {}));
        jTableVehicules.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        jScrollPaneVehicules.setViewportView(jTableVehicules);
        jScrollPaneVehicules.setSize(jScrollPaneVehicules.getSize().width, 200);
        jScrollPaneVehicules.setMinimumSize(new Dimension(200, 200));

        jLabelVehicules.setText("Vehicules :");

        jLabelDescription.setText("Description :");

        jTextAreaDescription.setEditable(false);
        jScrollPaneDescription.setViewportView(jTextAreaDescription);
        jScrollPaneDescription.setMinimumSize(new Dimension(200, 200));

        jButtonAddVoiture.setText("Add Vehicule");
        jButtonAddVoiture.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                jButtonAddVoiturePerformed(e);
            }
        });

        jButtonRemoveVoiture.setText("Remove Vehicule");
        jButtonRemoveVoiture.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                jButtonRemoveVoiturePerformed(e);
            }
        });
        
        jButtonAddRoute.setText("Add Route");
        jButtonAddRoute.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                jButtonAddRoutePerformed(e);
            }
            
        });
        
        jButtonConfRoute.setText("Confirm Route");
        jButtonConfRoute.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                jButtonComfirmRoutePerformed(e);
            }
            
        });
        
        jButtonDeclineRoute.setText("Decline Route");
        jButtonDeclineRoute.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                jButtonDeclineRoutePerformed(e);
            }
            
        });
        
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        
        layout.setHorizontalGroup(
                layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelTitle)
                    .addComponent(jComboBoxCrisis)
                    .addComponent(jLabelDescription)
                    .addComponent(jScrollPaneDescription)
                    .addComponent(jLabelVehicules)
                    .addComponent(jScrollPaneVehicules))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                    .addComponent(jButtonAddCrise)
                    .addComponent(jButtonCloseCrise)
                    .addComponent(jButtonAddRoute)
                    .addComponent(jButtonConfRoute)
                    .addComponent(jButtonDeclineRoute)
                    .addComponent(jButtonAddVoiture)
                    .addComponent(jButtonRemoveVoiture)
                    .addComponent(jButtonQuit))
        );
        layout.linkSize(SwingConstants.HORIZONTAL, jButtonAddCrise, jButtonAddVoiture, jButtonRemoveVoiture, jButtonQuit,jButtonAddRoute,jButtonConfRoute,jButtonDeclineRoute, jButtonCloseCrise);
        layout.setVerticalGroup(
                layout.createSequentialGroup()
                    .addComponent(jLabelTitle)
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(jComboBoxCrisis)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jButtonAddCrise)
                            .addComponent(jButtonCloseCrise)))
                    .addComponent(jLabelDescription)
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPaneDescription)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jButtonAddRoute)
                            .addComponent(jButtonConfRoute)
                            .addComponent(jButtonDeclineRoute)))
                    .addComponent(jLabelVehicules)
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPaneVehicules)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jButtonAddVoiture)
                            .addComponent(jButtonRemoveVoiture)))
                    .addComponent(jButtonQuit)
        );

        if(ProjetEJBClient.getTypeProgram() == PROGRAM_CLIENT.POLICE)
        jLabelTitle.setText("Police Station");
        else
        jLabelTitle.setText("Fire Station");

        pack();
    }
    
    /*
     * Quit Button Action
     */
    private void jButtonQuitActionPerformed(ActionEvent evt) {
        Object[] options = { "OK", "CANCEL" };
        if(JOptionPane.showOptionDialog(null, "Do you want to quit?", "Quit", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[0]) == JOptionPane.OK_OPTION) {
            ProjetEJBClient.getCont().ExitQuery();
        }
    }
    
    /*
     * AddCrisis Button Action
     */
    private void jButtonAddCrisePerformed(ActionEvent evt) {
        AddCrisisFrame acf = new AddCrisisFrame();
        ProjetEJBClient.getCont().UpdateRessources();
    }
    
    /*
     * CloseCrisis Button Action
     */
    private void jButtonCloseCrisePerformed(java.awt.event.ActionEvent evt) {
        ProjetEJBClient.getCont().setCriseClosed(jComboBoxCrisis.getSelectedItem().toString());
        ProjetEJBClient.getCont().UpdateRessources();
    }

    /*
     * AddVehicule Button Action
     */
    private void jButtonAddVoiturePerformed(ActionEvent evt){
        AddVoitureFrame avf = new AddVoitureFrame(jComboBoxCrisis.getSelectedItem().toString());
        ProjetEJBClient.getCont().UpdateRessources();
    }
    
    /*
     * On vérifie si la tableau contient et des lignes et si oui on supprime le vehicule selectionne
     */
    private void jButtonRemoveVoiturePerformed(ActionEvent evt){
       if(jTableVehicules.getSelectedRow() >= 0){
          String idV = jTableVehicules.getValueAt(jTableVehicules.getSelectedRow(), 0).toString();
          ProjetEJBClient.getCont().RemoveRouteByVehicule(idV);
          ProjetEJBClient.getCont().UpdateRessources();
      }
      else{
          JOptionPane.showMessageDialog(this, "No row selected", " Selection Error ", JOptionPane.ERROR_MESSAGE);
      }
    }
    
    /*
     * AddRoute Button Action
     */
    private void jButtonAddRoutePerformed(ActionEvent evt){
        ProjetEJBClient.getCont().EditRouteplan(jComboBoxCrisis.getSelectedItem().toString());
    }
    
    /*
     * ComfirmRoute Button Action
     */
    private void jButtonComfirmRoutePerformed(ActionEvent evt){
        ProjetEJBClient.getCont().ComfirmRouteplan(jComboBoxCrisis.getSelectedItem().toString());
    }
    
    /*
     * DeclineRoute Button Action
     */
    private void jButtonDeclineRoutePerformed(ActionEvent evt){
        ProjetEJBClient.getCont().DeclineRouteplan(jComboBoxCrisis.getSelectedItem().toString());
    }
    
    /*
     * ComboBox Item Changed
     */
    private void CrisisComboBoxItemChanged(ActionEvent evt) {
        _currentCrisis = jComboBoxCrisis.getSelectedIndex();
        RessourcesUpdated();
    }
    
    /*
     * Gui's thread launch
     */
    public void launch() {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                setVisible(true);
            }
        });
    }
    
    /*
     * Updating gui procedure
     */
    public void RessourcesUpdated(){
        _updating = true;
        
        //Get list of active crisis
        List<Crisis> crisesActives = ProjetEJBClient.getCont().getActiveCrisis();
        
        //Update ComboBox of crisis
        Integer current = _currentCrisis;
        jComboBoxCrisis.removeAllItems();
        for(int i = 0; i<crisesActives.size(); i++) {
            jComboBoxCrisis.addItem(crisesActives.get(i).getIdcrisis());
        }
        //Check if the current selected index is in bounds
        _currentCrisis = current;
        if(_currentCrisis == -1 && _currentCrisis < crisesActives.size())
            _currentCrisis = 0;
        if(_currentCrisis >= crisesActives.size())
            _currentCrisis = 0;
        if(crisesActives.size() <= 0)
            _currentCrisis = -1;
        jComboBoxCrisis.setSelectedIndex(_currentCrisis);
        
        
        //Set the Table and the TextArea empty
        jTextAreaDescription.setText(null);
        jTableVehicules.setModel(new MyTableModel(new Object [0][0]));
        //If there is an active crisis
        if(crisesActives.size()>0) {
            
            
            //Write position and beginning date in textarea
            jTextAreaDescription.append("Position : " + crisesActives.get(_currentCrisis).getLongitude() + "; " + crisesActives.get(_currentCrisis).getLatitude() + "\r\n" + "\r\n");
            jTextAreaDescription.append("Beginnig time : " + crisesActives.get(_currentCrisis).getT() + "\r\n" + "\r\n");
            
            //Get the timeoutlog of the current selected crisis
            Timeoutlog currentTol = ProjetEJBClient.getCont().getTolOfCrisis(crisesActives.get(_currentCrisis).getIdcrisis());
            //If there is one we get the timer and calculate the remaining time to display it on the text area
            if(currentTol != null) {
                Date cd = new Date();
                float minutes = currentTol.getD().getTime() - cd.getTime();
                minutes /= 60000;
                if(minutes > 0)
                    jTextAreaDescription.append("Timer : " + minutes + " minutes" + "\r\n" + "\r\n");
                else
                    jTextAreaDescription.append("Timer : " + "elapsed"  + "\r\n" + "\r\n");
            }
            //Get the routeplan of the current crisis to display it in the textarea
            Routeplan currentRP = ProjetEJBClient.getCont().getRoutePlanOfCrisis(crisesActives.get(_currentCrisis).getIdcrisis());
            if(currentRP.getNomroute() != null)
                if(currentRP.getComfirm().equals("t"))
                    jTextAreaDescription.append("Name route : " + currentRP.getNomroute()  + " (comfirm)" +"\r\n" + "\r\n");
                else
                    jTextAreaDescription.append("Name route : " + currentRP.getNomroute()  + " (uncomfirm)"  + "\r\n" + "\r\n");
            else
                jTextAreaDescription.append("Name route : " + "n/c"  + "\r\n" + "\r\n");
            
            //Display the description of the crisis in the textarea
            jTextAreaDescription.append("Description : " + crisesActives.get(_currentCrisis).getDescription());
            //Get list of vehicules of the current crisis
            List<Vehicule> vehiculesOfCrisis = ProjetEJBClient.getCont().getVehiculesOfCrisis(crisesActives.get(_currentCrisis).getIdcrisis());
            //And fill the table with it
            if(vehiculesOfCrisis.size()>0) {
                Object[][] data = new Object[vehiculesOfCrisis.size()][4];
                
                for(int i=0; i<vehiculesOfCrisis.size(); i++) {
                    data[i][0] = vehiculesOfCrisis.get(i).getIdvehicule();
                    if(vehiculesOfCrisis.get(i).getEta()!=null) {
                    Long eta = new Long(vehiculesOfCrisis.get(i).getEta().getTime()/60000);
                    eta -= new Date().getTime();
                    if(eta>=0)
                        data[i][1] = eta.toString() + " minutes";
                    else
                        data[i][1] = "elapsed";
                    }
                    else
                        data[i][1] = null;
                    
                    data[i][2] = vehiculesOfCrisis.get(i).getPosition();
                    data[i][3] = vehiculesOfCrisis.get(i).getType();
                }
                jTableVehicules.setModel(new MyTableModel(data));
            }
            //Enabling buttons
            jButtonCloseCrise.setEnabled(true);
            jButtonAddVoiture.setEnabled(true);
            
            if(vehiculesOfCrisis != null)
                jButtonRemoveVoiture.setEnabled(true);
            else
                jButtonRemoveVoiture.setEnabled(false);
            
            if(ProjetEJBClient.getTypeProgram() == PROGRAM_CLIENT.FIRE) {
                jButtonAddRoute.setEnabled(false);
                if(currentRP.getNomroute() != null && currentRP.getComfirm().equals("f")) {
                    jButtonConfRoute.setEnabled(true);
                    jButtonDeclineRoute.setEnabled(true);
                }
                else {
                    jButtonConfRoute.setEnabled(false);
                    jButtonDeclineRoute.setEnabled(false);
                }
            }
            else {
                jButtonConfRoute.setEnabled(false);
                jButtonDeclineRoute.setEnabled(false);
                if(currentRP.getNomroute() != null)
                    jButtonAddRoute.setEnabled(false);
                else
                    jButtonAddRoute.setEnabled(true);
            }
        }
        else {
            //Disable all button exept button Quit & AddCrise
            jButtonCloseCrise.setEnabled(false);
            jButtonAddVoiture.setEnabled(false);
            jButtonRemoveVoiture.setEnabled(false);
            jButtonAddRoute.setEnabled(false);
            jButtonConfRoute.setEnabled(false);
            jButtonDeclineRoute.setEnabled(false);
        }
        
        _updating = false;
    }
    
    /*
     * Function returning the combobox
     */
    public JComboBox getComboBoxCrisis(){
        return jComboBoxCrisis;
    }
    
    /*
     * Function asking reasons why timer elapsed
     */
    public String getReasons() {
        return JOptionPane.showInputDialog("Reasons why timer elapsed :");
    }
    
    /*
     * Function asking the name of the route
     */
    public String getRouteName() {
        return JOptionPane.showInputDialog("Route Name :");
    }
    
    /*
     * Procedure displaying an error message
     */
    public void ErrorMessage(String m) {
        JOptionPane.showMessageDialog(this, m, "Error", JOptionPane.ERROR_MESSAGE);
    }
    
    /*
     * MainWindow attributes
     */
    private javax.swing.JButton jButtonQuit;
    private javax.swing.JButton jButtonAddCrise;
    private javax.swing.JButton jButtonCloseCrise;
    private javax.swing.JButton jButtonAddVoiture;
    private javax.swing.JButton jButtonRemoveVoiture;
    private javax.swing.JButton jButtonAddRoute;
    private javax.swing.JButton jButtonConfRoute;
    private javax.swing.JButton jButtonDeclineRoute;
    private javax.swing.JComboBox jComboBoxCrisis;
    private javax.swing.JLabel jLabelTitle;
    private javax.swing.JLabel jLabelVehicules;
    private javax.swing.JLabel jLabelDescription;
    private javax.swing.JScrollPane jScrollPaneVehicules;
    private javax.swing.JScrollPane jScrollPaneDescription;
    private javax.swing.JTable jTableVehicules;
    private javax.swing.JTextArea jTextAreaDescription;

    
    private Integer _currentCrisis;
    private static boolean _updating;
}


/*
 * Model for jTable
 */
class MyTableModel extends AbstractTableModel {
    
    String[] columnNames = {"ID Vehicule", "Estimated Time to Arrival", "Position", "Type"};
    
    Object[][] data;
    
    public MyTableModel(Object[][] objs) {
        super();
        data = objs;
    }

    @Override
    public int getRowCount() {
        return data.length;
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int i, int i1) {
        return data[i][i1];
    }
    
    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }
    
    @Override
    public boolean isCellEditable(int row, int column) {
       return false;
    }
}