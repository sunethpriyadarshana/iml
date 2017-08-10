package hibernate;
// Generated Aug 4, 2017 12:48:05 AM by Hibernate Tools 3.6.0


import java.util.HashSet;
import java.util.Set;

/**
 * Action generated by hbm2java
 */
public class Action  implements java.io.Serializable {


     private Integer idaction;
     private String action;
     private int status;
     private Set userRoleHasActions = new HashSet(0);

    public Action() {
    }

	
    public Action(String action, int status) {
        this.action = action;
        this.status = status;
    }
    public Action(String action, int status, Set userRoleHasActions) {
       this.action = action;
       this.status = status;
       this.userRoleHasActions = userRoleHasActions;
    }
   
    public Integer getIdaction() {
        return this.idaction;
    }
    
    public void setIdaction(Integer idaction) {
        this.idaction = idaction;
    }
    public String getAction() {
        return this.action;
    }
    
    public void setAction(String action) {
        this.action = action;
    }
    public int getStatus() {
        return this.status;
    }
    
    public void setStatus(int status) {
        this.status = status;
    }
    public Set getUserRoleHasActions() {
        return this.userRoleHasActions;
    }
    
    public void setUserRoleHasActions(Set userRoleHasActions) {
        this.userRoleHasActions = userRoleHasActions;
    }




}


