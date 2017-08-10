package hibernate;
// Generated Aug 4, 2017 12:48:05 AM by Hibernate Tools 3.6.0



/**
 * UserHasAddress generated by hbm2java
 */
public class UserHasAddress  implements java.io.Serializable {


     private Integer iduserHasAddress;
     private User user;
     private Address address;
     private boolean default_;

    public UserHasAddress() {
    }

    public UserHasAddress(User user, Address address, boolean default_) {
       this.user = user;
       this.address = address;
       this.default_ = default_;
    }
   
    public Integer getIduserHasAddress() {
        return this.iduserHasAddress;
    }
    
    public void setIduserHasAddress(Integer iduserHasAddress) {
        this.iduserHasAddress = iduserHasAddress;
    }
    public User getUser() {
        return this.user;
    }
    
    public void setUser(User user) {
        this.user = user;
    }
    public Address getAddress() {
        return this.address;
    }
    
    public void setAddress(Address address) {
        this.address = address;
    }
    public boolean isDefault_() {
        return this.default_;
    }
    
    public void setDefault_(boolean default_) {
        this.default_ = default_;
    }




}


