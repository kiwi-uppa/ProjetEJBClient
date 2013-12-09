/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package grazimba.ttsing.projetejbClient;

import grazimba.ttsing.projetejb.Crisis;
import grazimba.ttsing.projetejb.Route;
import grazimba.ttsing.projetejb.RoutePK;
import grazimba.ttsing.projetejb.Vehicule;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigInteger;
import java.util.Random;
import java.util.Vector;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 *
 * @author Wandy
 */
public class AddVoitureFrame extends JFrame {
    
    String[] _vehiTab;
    
    /* Frame */
    private JPanel _contentPane;
    private GridLayout _layout;
    private JButton _okButton;
    private JButton _cancelButton;
    private JComboBox _vehiList;
    
    public AddVoitureFrame(){
        setTitle("Add Voiture");
        InitLayout();
    }
    
    private void InitLayout(){
        _contentPane = (JPanel) this.getContentPane();
        _layout = new GridLayout(2,2);
        _contentPane.setLayout(_layout);
        
        _okButton = new JButton("OK");
        _okButton.setPreferredSize(new Dimension(200,25));
        _cancelButton = new JButton("Cancel");
        _vehiList = new JComboBox();
        recupVehi();
        
        /* Listener */
        _okButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
                OKButtonActionPerformed(evt);
            }
        });
        
        _cancelButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
                CancelButtonActionPerformed(evt);
            }
        });
        
        _contentPane.add(new JLabel("Vehicule : "));
        _contentPane.add(_vehiList);
        
        _contentPane.add(_okButton);
        _contentPane.add(_cancelButton);
        
        pack();
        setVisible(true);
    }
    
    /** Check des conditions pour afficher des vehicules valide **/
    private void recupVehi(){
        for(int i = 0 ;  i < ProjetEJBClient.getRessource().getVehicules().size() ; i++ )
        {
            boolean find = false;
            for(int j = 0; j<ProjetEJBClient.getRessource().getRoutes().size(); j++)
                if(ProjetEJBClient.getRessource().getRoute(j).getRoutePK().getIdvehicule().equals(ProjetEJBClient.getRessource().getVehicule(i)))
                    find = true;
            
            if(!find) {
                if(ProjetEJBClient.getTypeProgram() == PROGRAM_CLIENT.POLICE) {
                    String tempPosition = ProjetEJBClient.getRessource().getVehicules().get(i).getPosition();
                    if((tempPosition.equals("Station") || tempPosition.equals("ERTS")) && ProjetEJBClient.getRessource().getVehicules().get(i).getType().equals("Police") )
                        _vehiList.addItem(ProjetEJBClient.getRessource().getVehicules().get(i).getIdvehicule().toString());
                }
                if(ProjetEJBClient.getTypeProgram() == PROGRAM_CLIENT.FIRE) {
                    String tempPosition = ProjetEJBClient.getRessource().getVehicules().get(i).getPosition();
                    if((tempPosition.equals("Station") || tempPosition.equals("ERTS")) && ProjetEJBClient.getRessource().getVehicules().get(i).getType().equals("Fire") )
                        _vehiList.addItem(ProjetEJBClient.getRessource().getVehicules().get(i).getIdvehicule().toString());
                } 
            }
        }
    }
    
    private void OKButtonActionPerformed(ActionEvent evt){
        String idcrisis =  ProjetEJBClient.getView().getComboBoxCrisis().getSelectedItem().toString();
        String idvehi = _vehiList.getSelectedItem().toString();
        boolean find=false;
        for(int i = 0 ; i < ProjetEJBClient.getRessource().getCrises().size() ; i++)
        {
            if(ProjetEJBClient.getRessource().getCrises().get(i).getIdcrisis().toString().compareTo(idcrisis) == 0)
            { 
                /* Check if our Routes containt something */
                if(ProjetEJBClient.getRessource().getRoutes().size() > 0)
                {
                    for(int j = 0 ; j < ProjetEJBClient.getRessource().getRoutes().size() ; j++){
                        /* Check if the vehicule is already used */
                        if(ProjetEJBClient.getRessource().getRoutes().get(j).getRoutePK().getIdvehicule().compareTo(idvehi) == 0 )
                            find=true; 
                    }
                }
                if(!find){
                    RoutePK rpk = new RoutePK(_vehiList.getSelectedItem().toString(),idcrisis);
                    Route r = new Route(rpk);
                    ProjetEJBClient.getCont().AddRoute(r);
                    this.dispose();
                }
                else
                    JOptionPane.showMessageDialog(this, "Le vehicule choisi est deja attribuer a une crise", " Erreur ", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    private void CancelButtonActionPerformed(ActionEvent evt) {
        this.dispose();
    }
}
