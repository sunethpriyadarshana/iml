package hibernate;
// Generated Aug 4, 2017 12:48:05 AM by Hibernate Tools 3.6.0


import java.util.HashSet;
import java.util.Set;

/**
 * Zone generated by hbm2java
 */
public class Zone  implements java.io.Serializable {


     private Integer idzone;
     private Branch branch;
     private String zone;
     private int status;
     private Set towns = new HashSet(0);

    public Zone() {
    }

	
    public Zone(Branch branch, String zone, int status) {
        this.branch = branch;
        this.zone = zone;
        this.status = status;
    }
    public Zone(Branch branch, String zone, int status, Set towns) {
       this.branch = branch;
       this.zone = zone;
       this.status = status;
       this.towns = towns;
    }
   
    public Integer getIdzone() {
        return this.idzone;
    }
    
    public void setIdzone(Integer idzone) {
        this.idzone = idzone;
    }
    public Branch getBranch() {
        return this.branch;
    }
    
    public void setBranch(Branch branch) {
        this.branch = branch;
    }
    public String getZone() {
        return this.zone;
    }
    
    public void setZone(String zone) {
        this.zone = zone;
    }
    public int getStatus() {
        return this.status;
    }
    
    public void setStatus(int status) {
        this.status = status;
    }
    public Set getTowns() {
        return this.towns;
    }
    
    public void setTowns(Set towns) {
        this.towns = towns;
    }




}

