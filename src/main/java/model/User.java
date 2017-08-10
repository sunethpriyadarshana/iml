/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import java.util.ArrayList;

public class User {
    
    private int userId;
    private String nic;
    private String name;
    private String email;
    private String role;
    private String status;
    private ArrayList<Vehicle> vehicles;
    private ArrayList<Address> addresses;
    private ArrayList<Device> devices;
    private RateSheet rates;

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

    /**
     * @return the vehicles
     */
    public ArrayList<Vehicle> getVehicles() {
        return vehicles;
    }

    /**
     * @param vehicles the vehicles to set
     */
    public void setVehicles(ArrayList<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }

    /**
     * @return the addresses
     */
    public ArrayList<Address> getAddresses() {
        return addresses;
    }

    /**
     * @param addresses the addresses to set
     */
    public void setAddresses(ArrayList<Address> addresses) {
        this.addresses = addresses;
    }

    /**
     * @return the devices
     */
    public ArrayList<Device> getDevices() {
        return devices;
    }

    /**
     * @param devices the devices to set
     */
    public void setDevices(ArrayList<Device> devices) {
        this.devices = devices;
    }

    /**
     * @return the rates
     */
    public RateSheet getRates() {
        return rates;
    }

    /**
     * @param rates the rates to set
     */
    public void setRates(RateSheet rates) {
        this.rates = rates;
    }

    /**
     * @return the role
     */
    public String getRole() {
        return role;
    }

    /**
     * @param role the role to set
     */
    public void setRole(String role) {
        this.role = role;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }
    
}
