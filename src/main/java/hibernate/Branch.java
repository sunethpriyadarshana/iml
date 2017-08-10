package hibernate;
// Generated Aug 4, 2017 12:48:05 AM by Hibernate Tools 3.6.0


import java.util.HashSet;
import java.util.Set;

/**
 * Branch generated by hbm2java
 */
public class Branch  implements java.io.Serializable {


     private Integer idbranch;
     private String branch;
     private int status;
     private Set zones = new HashSet(0);

    public Branch() {
    }

	
    public Branch(String branch, int status) {
        this.branch = branch;
        this.status = status;
    }
    public Branch(String branch, int status, Set zones) {
       this.branch = branch;
       this.status = status;
       this.zones = zones;
    }
   
    public Integer getIdbranch() {
        return this.idbranch;
    }
    
    public void setIdbranch(Integer idbranch) {
        this.idbranch = idbranch;
    }
    public String getBranch() {
        return this.branch;
    }
    
    public void setBranch(String branch) {
        this.branch = branch;
    }
    public int getStatus() {
        return this.status;
    }
    
    public void setStatus(int status) {
        this.status = status;
    }
    public Set getZones() {
        return this.zones;
    }
    
    public void setZones(Set zones) {
        this.zones = zones;
    }




}


