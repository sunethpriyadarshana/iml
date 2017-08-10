package hibernate;
// Generated Aug 4, 2017 12:48:05 AM by Hibernate Tools 3.6.0


import java.util.Date;

/**
 * UserHasDevice generated by hbm2java
 */
public class UserHasDevice  implements java.io.Serializable {


     private Integer iduserHasDevice;
     private Device device;
     private User user;
     private Date startDate;
     private Date endDate;
     private int status;

    public UserHasDevice() {
    }

	
    public UserHasDevice(Device device, User user, Date startDate, int status) {
        this.device = device;
        this.user = user;
        this.startDate = startDate;
        this.status = status;
    }
    public UserHasDevice(Device device, User user, Date startDate, Date endDate, int status) {
       this.device = device;
       this.user = user;
       this.startDate = startDate;
       this.endDate = endDate;
       this.status = status;
    }
   
    public Integer getIduserHasDevice() {
        return this.iduserHasDevice;
    }
    
    public void setIduserHasDevice(Integer iduserHasDevice) {
        this.iduserHasDevice = iduserHasDevice;
    }
    public Device getDevice() {
        return this.device;
    }
    
    public void setDevice(Device device) {
        this.device = device;
    }
    public User getUser() {
        return this.user;
    }
    
    public void setUser(User user) {
        this.user = user;
    }
    public Date getStartDate() {
        return this.startDate;
    }
    
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }
    public Date getEndDate() {
        return this.endDate;
    }
    
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
    public int getStatus() {
        return this.status;
    }
    
    public void setStatus(int status) {
        this.status = status;
    }




}

