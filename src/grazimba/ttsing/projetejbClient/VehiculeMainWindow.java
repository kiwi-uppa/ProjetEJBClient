/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package grazimba.ttsing.projetejbClient;

import grazimba.ttsing.projetejb.Crisis;
import grazimba.ttsing.projetejb.Route;
import grazimba.ttsing.projetejb.Routeplan;
import grazimba.ttsing.projetejb.Timeoutlog;
import grazimba.ttsing.projetejb.Vehicule;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Date;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.LayoutStyle;
import javax.swing.SwingConstants;

/**
 *
 * @author Guillaume
 */
public class VehiculeMainWindow extends JFrame{
    
    /*
     * VehiculeMainWindow constructor
     */
    public VehiculeMainWindow() {
        
        initComponents();
        
        pack();
        
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                ProjetEJBClient.getCont().SetVehiculeInUse(_currentVehicule, false);
                ProjetEJBClient.getCont().ExitQuery();
            }
        });
    }
    
    /*
     * VehiculeMainWindow thread launch
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
     * VehiculeMainWindow gui's update
     */
    public void RessourcesUpdated() {
        if(_currentVehicule!=null && !_currentVehicule.equals("")) {
            _labelId.setText(_currentVehicule);
            
            Route routeOfVehi = ProjetEJBClient.getCont().getRouteOfVehi(_currentVehicule);
            Vehicule currentVehi = ProjetEJBClient.getCont().getVehiculesById(_currentVehicule);
            _textAreaDesc.setText(null);
            if(routeOfVehi==null) {
                _textAreaDesc.append("Vehicule position : " + currentVehi.getPosition() + "\r\n" + "\r\n");
                _textAreaDesc.append("Crisis : " + "n/c" + "\r\n" + "\r\n");
                _textAreaDesc.append("Position : " + "n/c" + "\r\n" + "\r\n");
                _textAreaDesc.append("Beginning time : " + "n/c" + "\r\n" + "\r\n");
                _textAreaDesc.append("Route : " + "n/c" + "\r\n" + "\r\n");
                _textAreaDesc.append("Timer : " + "n/c" + "\r\n" + "\r\n");
                _textAreaDesc.append("ETA : " + "n/c");
                
                if(currentVehi.getPosition().equals("Station")){
                    _buttonERTL.setEnabled(false);
                    _buttonStation.setEnabled(false);
                    _buttonERTS.setEnabled(false);
                    _buttonAL.setEnabled(false);
                }
                if(currentVehi.getPosition().equals("ERTS")){
                    _buttonERTL.setEnabled(false);
                    _buttonStation.setEnabled(true);
                    _buttonERTS.setEnabled(false);
                    _buttonAL.setEnabled(false);
                }
                if(currentVehi.getPosition().equals("AL")){
                    _buttonERTL.setEnabled(false);
                    _buttonStation.setEnabled(false);
                    _buttonERTS.setEnabled(true);
                    _buttonAL.setEnabled(false);
                }
                if(currentVehi.getPosition().equals("ERTL")){
                    _buttonERTL.setEnabled(false);
                    _buttonStation.setEnabled(false);
                    _buttonERTS.setEnabled(true);
                    _buttonAL.setEnabled(false);
                }
            }
            else {
                Crisis crisisOfVehi = ProjetEJBClient.getCont().getCrisisByID(routeOfVehi.getRoutePK().getIdcrisis());
                Timeoutlog tolOfCrisis = ProjetEJBClient.getCont().getTolOfCrisis(crisisOfVehi.getIdcrisis());
                Routeplan rpOfCrisis = ProjetEJBClient.getCont().getRoutePlanOfCrisis(crisisOfVehi.getIdcrisis());
                
                _textAreaDesc.append("Vehicule position : " + currentVehi.getPosition() + "\r\n" + "\r\n");
                _textAreaDesc.append("Crise : " + crisisOfVehi.getIdcrisis() + "\r\n" + "\r\n");
                _textAreaDesc.append("Position : " + crisisOfVehi.getLongitude() + ";" + crisisOfVehi.getLatitude() + "\r\n" + "\r\n");
                _textAreaDesc.append("Beginning time : " + crisisOfVehi.getT() + "\r\n" + "\r\n");
                if(rpOfCrisis.getNomroute()==null || rpOfCrisis.getNomroute().equals(""))
                    _textAreaDesc.append("Route : " + "n/c" + "\r\n" + "\r\n");
                else {
                    if(rpOfCrisis.getComfirm().equals("t"))
                        _textAreaDesc.append("Route : " + rpOfCrisis.getNomroute() + " (comfirm)" + "\r\n" + "\r\n");
                    else
                        _textAreaDesc.append("Route : " + rpOfCrisis.getNomroute() + " (uncomfirm)" + "\r\n" + "\r\n");
                }
                if(tolOfCrisis != null) {
                    Date d = new Date();
                    float timer = tolOfCrisis.getD().getTime() - d.getTime();
                    timer /= 60000;
                    if(timer>0)
                        _textAreaDesc.append("Timer : " + timer + " minutes" + "\r\n" + "\r\n");
                    else
                        _textAreaDesc.append("Timer : " + "elapsed" + "\r\n" + "\r\n");
                }
                
                if(currentVehi.getEta()==null)
                    ProjetEJBClient.getCont().setVehiculeETA(_currentVehicule);
                Date d = new Date();
                long eta = currentVehi.getEta().getTime() - d.getTime();
                eta /= 60000;
                if(eta>=0)
                    _textAreaDesc.append("ETA : " + eta + "minutes");
                else
                    _textAreaDesc.append("ETA : " + "elapsed");
                
                if(currentVehi.getPosition().equals("Station")){
                    _buttonERTL.setEnabled(true);
                    _buttonStation.setEnabled(false);
                    _buttonERTS.setEnabled(false);
                    _buttonAL.setEnabled(false);
                }
                if(currentVehi.getPosition().equals("ERTS")){
                    _buttonERTL.setEnabled(true);
                    _buttonStation.setEnabled(false);
                    _buttonERTS.setEnabled(false);
                    _buttonAL.setEnabled(false);
                }
                if(currentVehi.getPosition().equals("AL")){
                    _buttonERTL.setEnabled(false);
                    _buttonStation.setEnabled(false);
                    _buttonERTS.setEnabled(false);
                    _buttonAL.setEnabled(false);
                }
                if(currentVehi.getPosition().equals("ERTL")){
                    _buttonERTL.setEnabled(false);
                    _buttonStation.setEnabled(false);
                    _buttonERTS.setEnabled(false);
                    _buttonAL.setEnabled(true);
                }
            }
        }
        else {
            _buttonAL.setEnabled(false);
            _buttonERTL.setEnabled(false);
            _buttonERTS.setEnabled(false);
            _buttonStation.setEnabled(false);
            Object[] possibleValues = ProjetEJBClient.getCont().getFreeVehiculesIds().toArray();
            if(possibleValues.length <= 0) {
                JOptionPane.showMessageDialog(this, "No vehicules avalaible", "No vehicules", JOptionPane.ERROR_MESSAGE);
                ProjetEJBClient.getCont().SetVehiculeInUse(_currentVehicule, false);
                ProjetEJBClient.getCont().ExitQuery();
            }
            else {
                while(_currentVehicule==null || _currentVehicule.equals(""))
                    _currentVehicule = (String) JOptionPane.showInputDialog(this, "Choose a vehicule : ", "Vehicule selection", JOptionPane.QUESTION_MESSAGE, null, possibleValues, possibleValues[0]);
                ProjetEJBClient.getCont().SetVehiculeInUse(_currentVehicule, true);
                RessourcesUpdated();
            }
        }
    }
    
    /*
     * Function asking user the ETA of a vehicule
     */
    public String getETA() {
        return JOptionPane.showInputDialog(this,"Estimated time to arrival :");
    }
    
    /*
     * Procedure displaying an error message
     */
    public void ErrorMessage(String m) {
        JOptionPane.showMessageDialog(this, m, "Error", JOptionPane.ERROR_MESSAGE);
    }
    
    /*
     * Gui's initialization
     */
    private void initComponents() {
        setTitle("Vehicule Client");
        _currentVehicule = new String("");
    
        _buttonQuit = new JButton("Quit");
        _buttonStation = new JButton("Station");
        _buttonERTS = new JButton("ERTS");
        _buttonAL = new JButton("AL");
        _buttonERTL = new JButton("ERTL");

        _labelTitle = new JLabel("Vehicule");
        _labelTitle.setFont(new java.awt.Font("Ubuntu", 1, 24));
        _labelId = new JLabel("id");
        _labelVehiPos = new JLabel("Vehicule's position : ");

        _textAreaDesc = new JTextArea();
        _textAreaDesc.setEditable(false);
        _scrollPaneDesc = new JScrollPane();
        _scrollPaneDesc.setViewportView(_textAreaDesc);
        _scrollPaneDesc.setMinimumSize(new Dimension(200, 200));
        
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        
        _buttonQuit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                jButtonQuitActionPerformed(evt);
            }
        });
        
        _buttonStation.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                jButtonStationActionPerformed(evt);
            }
        });
        
        _buttonERTS.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                jButtonERTSActionPerformed(evt);
            }
        });
        
        _buttonAL.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                jButtonALActionPerformed(evt);
            }
        });
        
        _buttonERTL.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                jButtonERTLActionPerformed(evt);
            }
        });
        
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        
        layout.setHorizontalGroup(
                layout.createSequentialGroup()
                    .addGroup(layout.createParallelGroup()
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(_labelTitle)
                            .addComponent(_labelId))
                        .addComponent(_scrollPaneDesc)
                        .addComponent(_labelVehiPos)
                        .addGroup(layout.createSequentialGroup()
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                                .addComponent(_buttonStation)
                                .addComponent(_buttonERTS))
                            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                                .addComponent(_buttonAL)
                                .addComponent(_buttonERTL))
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        )
                        .addComponent(_buttonQuit)
                    )
        );
        layout.linkSize(SwingConstants.HORIZONTAL, _buttonStation, _buttonERTS, _buttonAL, _buttonERTL,_buttonQuit);
        layout.setVerticalGroup(
                layout.createSequentialGroup()
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(_labelTitle)
                        .addComponent(_labelId))
                    .addComponent(_scrollPaneDesc)
                    .addComponent(_labelVehiPos)
                    .addGroup(layout.createParallelGroup()
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(_buttonStation)
                            .addComponent(_buttonERTS))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(_buttonAL)
                            .addComponent(_buttonERTL))
                    )
                    .addComponent(_buttonQuit)
        );
    }
    
    /*
     * Quit Button Action
     */
    private void jButtonQuitActionPerformed(ActionEvent evt) {
        Object[] options = { "OK", "CANCEL" };
        if(JOptionPane.showOptionDialog(null, "Do you want to quit?", "Quit", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[0]) == JOptionPane.OK_OPTION) {
            ProjetEJBClient.getCont().SetVehiculeInUse(_currentVehicule, false);
            ProjetEJBClient.getCont().ExitQuery();
        }
    }
    
    /*
     * Station Button Action
     */
    private void jButtonStationActionPerformed(ActionEvent evt) {
        ProjetEJBClient.getCont().setVehiculePosition(_currentVehicule, 0);
        ProjetEJBClient.getCont().UpdateRessources();
    }
    
    /*
     * ERTS Button Action
     */
    private void jButtonERTSActionPerformed(ActionEvent evt) {
        ProjetEJBClient.getCont().setVehiculePosition(_currentVehicule, 3);
        ProjetEJBClient.getCont().UpdateRessources();
    }
    
    /*
     * AL Button Action
     */
    private void jButtonALActionPerformed(ActionEvent evt) {
        ProjetEJBClient.getCont().setVehiculePosition(_currentVehicule, 2);
        ProjetEJBClient.getCont().UpdateRessources();
    }
    
    /*
     * ERTL Button Action
     */
    private void jButtonERTLActionPerformed(ActionEvent evt) {
        ProjetEJBClient.getCont().setVehiculePosition(_currentVehicule, 1);
        ProjetEJBClient.getCont().UpdateRessources();
    }
    
    /**
     * VehiculeMainWindow Attributes
     */
    
    String _currentVehicule;
    
    JButton _buttonQuit;
    JButton _buttonStation;
    JButton _buttonERTS;
    JButton _buttonAL;
    JButton _buttonERTL;
    
    JLabel _labelTitle;
    JLabel _labelId;
    JLabel _labelVehiPos;
    
    JTextArea _textAreaDesc;
    JScrollPane _scrollPaneDesc;
}
