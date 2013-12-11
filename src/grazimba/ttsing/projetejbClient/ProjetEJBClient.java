/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package grazimba.ttsing.projetejbClient;

import javax.swing.JFrame;

enum PROGRAM_CLIENT {FIRE, POLICE, VEHICULE, CREATE_VEHICULES};


/**
 *
 * @author grazimba & ttsing
 */
public class ProjetEJBClient {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            if(args.length != 1)
                throw new Exception("Arguments count invalid");
            switch (args[0]) {
                
                case "0" :
                    _typeProgram = PROGRAM_CLIENT.POLICE;
                    _tMAJ = new ThreadMAJ();
                    _cont = new Controller();
                    _mw = new MainWindow();
                    _ressource = new Ressources();
                    break;
                    
                case "1" :
                    _typeProgram = PROGRAM_CLIENT.FIRE;
                    _tMAJ = new ThreadMAJ();
                    _cont = new Controller();
                    _mw = new MainWindow();
                    _ressource = new Ressources();
                    break;
                    
                case "2" :
                    _typeProgram = PROGRAM_CLIENT.VEHICULE;
                    _tMAJ = new ThreadMAJ();
                    _cont = new Controller();
                    //_vmw = new VehiculeMainWindow();
                    _ressource = new Ressources();
                    break;
                    
                case "3" :
                    _typeProgram = PROGRAM_CLIENT.CREATE_VEHICULES;
                    _tMAJ = new ThreadMAJ();
                    _cont = new Controller();
                    //_cvmw = new CreateVehiculeMainWindow();
                    _ressource = new Ressources();
                    break;
                    
                default :
                    throw new Exception("Invalid arguments : waiting for {0, 1, 2, 3}");
            }
        }
        catch(Exception e) {
            System.err.println(e);
            System.exit(1);
        }
        
    }
    
    public static MainWindow getMainWindow() {
        return _mw;
    }
    
//TODO
//    public static VehiculeMainWindow getVehiculeMainWindow() {
//        return _vmw;
//    }
//    
//    public static CreateVehiculeMainWindow getCreateVehiculeMainWindow() {
//        return _cvmw;
//    }
    
    public static Ressources getRessource() {
        return _ressource;
    }
    
    public static Controller getCont() {
        return _cont;
    }
    
    public static ThreadMAJ getThreadMAJ() {
        return _tMAJ;
    }

    
    public static PROGRAM_CLIENT getTypeProgram() {
        return _typeProgram;
    }
    
    
    /**
     * Main attributes
     */
    private static MainWindow _mw;
    //TO CREATE
    //private static VehiculeMainWindow _vmw;
    //private static CreateVehiculeMainWindow _cvmw;
    private static Ressources _ressource;
    private static ThreadMAJ _tMAJ;
    private static Controller _cont;
    private static PROGRAM_CLIENT _typeProgram;
}