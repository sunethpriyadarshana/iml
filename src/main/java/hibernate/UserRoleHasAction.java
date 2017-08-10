package hibernate;
// Generated Aug 4, 2017 12:48:05 AM by Hibernate Tools 3.6.0



/**
 * UserRoleHasAction generated by hbm2java
 */
public class UserRoleHasAction  implements java.io.Serializable {


     private UserRoleHasActionId id;
     private UserRole userRole;
     private Action action;
     private int status;

    public UserRoleHasAction() {
    }

    public UserRoleHasAction(UserRoleHasActionId id, UserRole userRole, Action action, int status) {
       this.id = id;
       this.userRole = userRole;
       this.action = action;
       this.status = status;
    }
   
    public UserRoleHasActionId getId() {
        return this.id;
    }
    
    public void setId(UserRoleHasActionId id) {
        this.id = id;
    }
    public UserRole getUserRole() {
        return this.userRole;
    }
    
    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }
    public Action getAction() {
        return this.action;
    }
    
    public void setAction(Action action) {
        this.action = action;
    }
    public int getStatus() {
        return this.status;
    }
    
    public void setStatus(int status) {
        this.status = status;
    }




}

