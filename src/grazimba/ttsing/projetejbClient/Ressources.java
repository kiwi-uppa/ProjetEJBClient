/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package grazimba.ttsing.projetejbClient;

import grazimba.ttsing.projetejb.*;
import java.util.List;
/**
 *
 * @author grazimba
 */
public class Ressources {
    
    private List<Crisis> crises;
    private List<Route> routes;
    private List<Routeplan> toutePlan;
    private List<Timeoutlog> tol;
    private List<Vehicule> vehicule;

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
    public void setCrises(List<Crisis> crises) {
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
    public void setCrise(Crisis crise) {
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
    public void setRoutes(List<Route> routes) {
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
    public void setRoute(Route route) {
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
    public void setToutePlans(List<Routeplan> toutePlan) {
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
    public void setToutePlan(Routeplan toutePlan) {
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
    public void setTols(List<Timeoutlog> tol) {
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
    public void setTol(Timeoutlog tol) {
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
    public void setVehicules(List<Vehicule> vehicule) {
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
    public void setVehicule(Vehicule vehicule) {
        this.vehicule.add(vehicule);
    }
    
}