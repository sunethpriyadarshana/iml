/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

public class Address {

    private int id;
    private String name;
    private String address;
    private String zone;
    private String branch;
    private long latitude;
    private long longitude;
    private String contact_number;
    private Town town;
    private User user;

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return the latitude
     */
    public long getLatitude() {
        return latitude;
    }

    /**
     * @param latitude the latitude to set
     */
    public void setLatitude(long latitude) {
        this.latitude = latitude;
    }

    /**
     * @return the longitude
     */
    public long getLongitude() {
        return longitude;
    }

    /**
     * @param longitude the longitude to set
     */
    public void setLongitude(long longitude) {
        this.longitude = longitude;
    }

    /**
     * @return the contact_number
     */
    public String getContact_number() {
        return contact_number;
    }

    /**
     * @param contact_number the contact_number to set
     */
    public void setContact_number(String contact_number) {
        this.contact_number = contact_number;
    }

    /**
     * @return the zone
     */
    public String getZone() {
        return zone;
    }

    /**
     * @param zone the zone to set
     */
    public void setZone(String zone) {
        this.zone = zone;
    }

    /**
     * @return the branch
     */
    public String getBranch() {
        return branch;
    }

    /**
     * @param branch the branch to set
     */
    public void setBranch(String branch) {
        this.branch = branch;
    }

    /**
     * @return the town
     */
    public Town getTown() {
        return town;
    }

    /**
     * @param town the town to set
     */
    public void setTown(Town town) {
        this.town = town;
    }

    /**
     * @return the user
     */
    public User getUser() {
        return user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    public class Town {

        private int tiwnId;
        private String name;
        private Long longitude;
        private Long latitiude;
        private int status;
        private Zone zone;

        public Town() {
        }

        /**
         * @return the tiwnId
         */
        public int getTiwnId() {
            return tiwnId;
        }

        /**
         * @param tiwnId the tiwnId to set
         */
        public void setTiwnId(int tiwnId) {
            this.tiwnId = tiwnId;
        }

        /**
         * @return the name
         */
        public String getName() {
            return name;
        }

        /**
         * @param name the name to set
         */
        public void setName(String name) {
            this.name = name;
        }

        /**
         * @return the longitude
         */
        public Long getLongitude() {
            return longitude;
        }

        /**
         * @param longitude the longitude to set
         */
        public void setLongitude(Long longitude) {
            this.longitude = longitude;
        }

        /**
         * @return the latitiude
         */
        public Long getLatitiude() {
            return latitiude;
        }

        /**
         * @param latitiude the latitiude to set
         */
        public void setLatitiude(Long latitiude) {
            this.latitiude = latitiude;
        }

        /**
         * @return the status
         */
        public int getStatus() {
            return status;
        }

        /**
         * @param status the status to set
         */
        public void setStatus(int status) {
            this.status = status;
        }

        /**
         * @return the zone
         */
        public Zone getZone() {
            return zone;
        }

        /**
         * @param zone the zone to set
         */
        public void setZone(Zone zone) {
            this.zone = zone;
        }
    }

    public class Zone {

        private int zoneId;
        private String zone;
        private int status;
        private Branch branch;

        public Zone() {
        }

        /**
         * @return the zoneId
         */
        public int getZoneId() {
            return zoneId;
        }

        /**
         * @param zoneId the zoneId to set
         */
        public void setZoneId(int zoneId) {
            this.zoneId = zoneId;
        }

        /**
         * @return the zone
         */
        public String getZone() {
            return zone;
        }

        /**
         * @param zone the zone to set
         */
        public void setZone(String zone) {
            this.zone = zone;
        }

        /**
         * @return the status
         */
        public int getStatus() {
            return status;
        }

        /**
         * @param status the status to set
         */
        public void setStatus(int status) {
            this.status = status;
        }

        /**
         * @return the branch
         */
        public Branch getBranch() {
            return branch;
        }

        /**
         * @param branch the branch to set
         */
        public void setBranch(Branch branch) {
            this.branch = branch;
        }
    }

    public class Branch {

        private int branchId;
        private String branchName;
        private int status;

        public Branch() {
        }

        /**
         * @return the branchId
         */
        public int getBranchId() {
            return branchId;
        }

        /**
         * @param branchId the branchId to set
         */
        public void setBranchId(int branchId) {
            this.branchId = branchId;
        }

        /**
         * @return the branchName
         */
        public String getBranchName() {
            return branchName;
        }

        /**
         * @param branchName the branchName to set
         */
        public void setBranchName(String branchName) {
            this.branchName = branchName;
        }

        /**
         * @return the status
         */
        public int getStatus() {
            return status;
        }

        /**
         * @param status the status to set
         */
        public void setStatus(int status) {
            this.status = status;
        }
    }

    public class User {

        private int userId;
        private String nic;
        private String name;
        private String email;

        public User() {
        }

        /**
         * @return the userId
         */
        public int getUserId() {
            return userId;
        }

        /**
         * @param userId the userId to set
         */
        public void setUserId(int userId) {
            this.userId = userId;
        }

        /**
         * @return the nic
         */
        public String getNic() {
            return nic;
        }

        /**
         * @param nic the nic to set
         */
        public void setNic(String nic) {
            this.nic = nic;
        }

        /**
         * @return the name
         */
        public String getName() {
            return name;
        }

        /**
         * @param name the name to set
         */
        public void setName(String name) {
            this.name = name;
        }

        /**
         * @return the email
         */
        public String getEmail() {
            return email;
        }

        /**
         * @param email the email to set
         */
        public void setEmail(String email) {
            this.email = email;
        }
    }
}
