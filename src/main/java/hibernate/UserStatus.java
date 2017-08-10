package hibernate;
// Generated Aug 4, 2017 12:48:05 AM by Hibernate Tools 3.6.0


import java.util.HashSet;
import java.util.Set;

/**
 * UserStatus generated by hbm2java
 */
public class UserStatus  implements java.io.Serializable {


     private Integer iduserStatus;
     private String status;
     private Set users = new HashSet(0);

    public UserStatus() {
    }

	
    public UserStatus(String status) {
        this.status = status;
    }
    public UserStatus(String status, Set users) {
       this.status = status;
       this.users = users;
    }
   
    public Integer getIduserStatus() {
        return this.iduserStatus;
    }
    
    public void setIduserStatus(Integer iduserStatus) {
        this.iduserStatus = iduserStatus;
    }
    public String getStatus() {
        return this.status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    public Set getUsers() {
        return this.users;
    }
    
    public void setUsers(Set users) {
        this.users = users;
    }




}


