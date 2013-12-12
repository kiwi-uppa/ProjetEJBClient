/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package grazimba.ttsing.projetejbClient;

import grazimba.ttsing.projetejb.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
/**
 *
 * @author grazimba & ttsing
 */
public class Ressources {
    
    private List<Crisis> crises;
    private List<Route> routes;
    private List<Routeplan> toutePlan;
    private List<Timeoutlog> tol;
    private List<Vehicule> vehicule;
    
    private javax.naming.Context _jndi_context;
    
    private VehiculeFacadeRemote _vf;
    private CrisisFacadeRemote _crise;
    private TimeoutlogFacadeRemote _tl;
    private RouteplanFacadeRemote _rp;
    private RouteFacadeRemote _rt;
    
    public Ressources()
    {
        try
        {
            _jndi_context = new javax.naming.InitialContext();
            
            _vf = (VehiculeFacadeRemote) _jndi_context.lookup("ejb/VehiculeFacade");
            _crise = (CrisisFacadeRemote) _jndi_context.lookup("ejb/CrisisFacade");
            _tl = (TimeoutlogFacadeRemote) _jndi_context.lookup("ejb/TimeoutlogFacade");
            _rp = (RouteplanFacadeRemote) _jndi_context.lookup("ejb/RouteplanFacade");
            _rt = (RouteFacadeRemote) _jndi_context.lookup("ejb/RouteFacade");
            
            System.out.println("context ok");
        }
        catch (Throwable t)
        {
            t.printStackTrace();
            System.exit(1);
        }
    }
    
    /**
     * @desc Call by ThreadMAJ, update ressources from db
     */
    public void UpdateRessources() {
        setCrises(_crise.findAll());
        setVehicules(_vf.findAll());
        setTols(_tl.findAll());
        setRoutes(_rt.findAll());
        setToutePlans(_rp.findAll());
        ProjetEJBClient.getCont().RessourcesUpdated();
    }
    
    public void AddCrise(Crisis c, Timeoutlog t, Routeplan rt) {
        _crise.create(c);
        if(t != null)
            _tl.create(t);
        _rp.create(rt);
        UpdateRessources();
    }
    
    public void AddRoute(Route r){
        _rt.create(r);
        Routeplan tmpRP = _rp.find(r.getRoutePK().getIdcrisis());
        if(ProjetEJBClient.getTypeProgram() == PROGRAM_CLIENT.FIRE)
            tmpRP.setNbfirevehicule(tmpRP.getNbfirevehicule()+1);
        else
            tmpRP.setNbpolicevehicule(tmpRP.getNbpolicevehicule()+1);
        _rp.edit(tmpRP);
        UpdateRessources();
    }
    
    public void RemoveRoute(Route r){
        _rt.remove(r);
        Routeplan tmpRP = _rp.find(r.getRoutePK().getIdcrisis());
        if(ProjetEJBClient.getTypeProgram() == PROGRAM_CLIENT.FIRE)
            tmpRP.setNbfirevehicule(tmpRP.getNbfirevehicule()-1);
        else
            tmpRP.setNbpolicevehicule(tmpRP.getNbpolicevehicule()-1);
        _rp.edit(tmpRP);
        UpdateRessources();
    }
    
    public void EditRouteplan(String s){
        Routeplan tmpRP = _rp.find(s);
        tmpRP.setNomroute(ProjetEJBClient.getCont().getRouteName());
        _rp.edit(tmpRP);
        UpdateRessources();
    }
    
    public void ComfirmRouteplan(String s){
        Routeplan tmpRP = _rp.find(s);
        tmpRP.setComfirm("t");
        _rp.edit(tmpRP);
        UpdateRessources();
    }
    
    public void DeclineRouteplan(String s){
        Routeplan tmpRP = _rp.find(s);
        tmpRP.setComfirm("f");
        tmpRP.setNomroute(null);
        _rp.edit(tmpRP);
        UpdateRessources();
    }
    
    public void setCriseClosed(String s) {
        Date d = new Date();
        Crisis tmpC = _crise.find(s);
        
        Timeoutlog tmpTL = _tl.find(s);
        if(tmpTL != null) {
            if(d.getTime() > tmpTL.getD().getTime())
            {
                if(ProjetEJBClient.getTypeProgram() == PROGRAM_CLIENT.POLICE)
                    tmpTL.setPscreason(ProjetEJBClient.getCont().getReasons());
                if(ProjetEJBClient.getTypeProgram() == PROGRAM_CLIENT.FIRE)
                    tmpTL.setFscreason(ProjetEJBClient.getCont().getReasons());
                _tl.edit(tmpTL);
            }
        }
        
        tmpC.setStatut("Closed");
        _crise.edit(tmpC);
        
        for(int i=0; i<routes.size(); i++) {
            if(routes.get(i).getRoutePK().getIdcrisis().equals(tmpC.getIdcrisis()))
                _rt.remove(routes.get(i));
        }
        
        Routeplan tmpRP = _rp.find(tmpC.getIdcrisis());
        tmpRP.setNbfirevehicule(0);
        tmpRP.setNbpolicevehicule(0);
        _rp.edit(tmpRP);
    }
    
    public List<Crisis> getActiveCrisis() {
        List<Crisis> crisesActives = new ArrayList<Crisis>();
        for(int i=0; i<crises.size(); i++) {
            if(crises.get(i).getStatut().equals("Active"))
                crisesActives.add(crises.get(i));
        }
        return crisesActives;
    }
    
    public Timeoutlog getTolOf(String id){
        for(int i=0; i<tol.size(); i++) {
            if(tol.get(i).getIdcrisis().equals(id))
                return tol.get(i);
        }
        return null;
    }
    
    public List<Vehicule> getVehiculesOf(String id) {
        List<Vehicule> listVehi = new ArrayList<Vehicule>();
        for(int i=0; i<routes.size(); i++) {
            if(routes.get(i).getRoutePK().getIdcrisis().equals(id)) {
                for(int j=0; j<vehicule.size(); j++) {
                    if(vehicule.get(j).getIdvehicule().equals(routes.get(i).getRoutePK().getIdvehicule()))
                        listVehi.add(vehicule.get(j));
                }
            }
        }
        if(listVehi.size()>0)
            return listVehi;
        else
            return null;
    }
    
    public Routeplan getRoutePlanOf(String id) {
        return _rp.find(id);
    }
    
    public List<Vehicule> getVehiculesForCrisis() {
        List<Vehicule> vehiForCrisis = getVehicules();
        for(int i=0; i<vehiForCrisis.size(); i++) {
            if(ProjetEJBClient.getTypeProgram() == PROGRAM_CLIENT.FIRE) {
                if(vehiForCrisis.get(i).getType().equals("Police") || inUse(vehiForCrisis.get(i).getIdvehicule())) {
                    vehiForCrisis.remove(i);
                    i--;
                }
            }
            if(ProjetEJBClient.getTypeProgram() == PROGRAM_CLIENT.POLICE) {
                if(vehiForCrisis.get(i).getType().equals("Fire") || inUse(vehiForCrisis.get(i).getIdvehicule())) {
                    vehiForCrisis.remove(i);
                    i--;
                }
            }
        }
        return vehiForCrisis;
    }
    
    private boolean inUse(String id) {
        for(int i=0; i<routes.size(); i++) {
            if(routes.get(i).getRoutePK().getIdvehicule().equals(id))
                return true;
        }
        return false;
    }

    
    /**
     * Crisis
     * /
    
    /**
     * @return the crises
     */
    public List<Crisis> getCrises() {
        return crises;
    }

    /**
     * @param crises the crises to set
     */
    private void setCrises(List<Crisis> crises) {
        this.crises = crises;
    }
    
    /**
     * @return the crise at i
     */
    public Crisis getCrise(int i) {
        return crises.get(i);
    }
    
    /**
     * @param crise the crise to set
     */
    private void setCrise(Crisis crise) {
        crises.add(crise);
    }
    
    
    /**
     * Route
     */
    
    

    /**
     * @return the routes
     */
    public List<Route> getRoutes() {
        return routes;
    }

    /**
     * @param routes the routes to set
     */
    private void setRoutes(List<Route> routes) {
        this.routes = routes;
    }
    
    /**
     * @return the route at i
     */
    public Route getRoute(int i) {
        return routes.get(i);
    }

    /**
     * @param route the route to set
     */
    private void setRoute(Route route) {
        this.routes.add(route);
    }
    
    
    
    /**
     * RoutePlan
     */

    
    
    /**
     * @return the toutePlans
     */
    public List<Routeplan> getToutePlans() {
        return toutePlan;
    }

    /**
     * @param toutePlan the toutePlans to set
     */
    private void setToutePlans(List<Routeplan> toutePlan) {
        this.toutePlan = toutePlan;
    }
    
    /**
     * @return the toutePlan at i
     */
    public Routeplan getToutePlan(int i) {
        return toutePlan.get(i);
    }

    /**
     * @param toutePlan the toutePlan to set
     */
    private void setToutePlan(Routeplan toutePlan) {
        this.toutePlan.add(toutePlan);
    }
    
    
    
    /**
     * TimeOutLog
     */

    
    
    /**
     * @return the tols
     */
    public List<Timeoutlog> getTols() {
        return tol;
    }

    /**
     * @param tol the tols to set
     */
    private void setTols(List<Timeoutlog> tol) {
        this.tol = tol;
    }
    
    /**
     * @return the tol at i
     */
    public Timeoutlog getTol(int i) {
        return tol.get(i);
    }

    /**
     * @param tol the tol to set
     */
    private void setTol(Timeoutlog tol) {
        this.tol.add(tol);
    }
    
    
    
    /**
     * Vehicule
     */

    
    
    /**
     * @return the vehicules
     */
    public List<Vehicule> getVehicules() {
        return vehicule;
    }

    /**
     * @param vehicule the vehicules to set
     */
    private void setVehicules(List<Vehicule> vehicule) {
        this.vehicule = vehicule;
    }
    
    /**
     * @return the vehicule at i
     */
    public Vehicule getVehicule(int i) {
        return vehicule.get(i);
    }

    /**
     * @param vehicule the vehicule to set
     */
    private void setVehicule(Vehicule vehicule) {
        this.vehicule.add(vehicule);
    }
    
}
