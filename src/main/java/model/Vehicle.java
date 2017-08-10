/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Date;

public class Vehicle {

    private Date vehicleStartDate;
    private Date vehicleEndDate;
    private VehicleDetails vehicleDetails;
    private int status;

    /**
     * @return the vehicleStartDate
     */
    public Date getVehicleStartDate() {
        return vehicleStartDate;
    }

    /**
     * @param vehicleStartDate the vehicleStartDate to set
     */
    public void setVehicleStartDate(Date vehicleStartDate) {
        this.vehicleStartDate = vehicleStartDate;
    }

    /**
     * @return the vehicleEndDate
     */
    public Date getVehicleEndDate() {
        return vehicleEndDate;
    }

    /**
     * @param vehicleEndDate the vehicleEndDate to set
     */
    public void setVehicleEndDate(Date vehicleEndDate) {
        this.vehicleEndDate = vehicleEndDate;
    }

    /**
     * @return the vehicleDetails
     */
    public VehicleDetails getVehicleDetails() {
        return vehicleDetails;
    }

    /**
     * @param vehicleDetails the vehicleDetails to set
     */
    public void setVehicleDetails(VehicleDetails vehicleDetails) {
        this.vehicleDetails = vehicleDetails;
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

    public class VehicleDetails {

        private int idVehicle;
        private String vehicle_number;
        private String vehicle_type;
        private Date licence_upto;
        private Date insuarance_upto;
        private int status;

        /**
         * @return the idVehicle
         */
        public int getIdVehicle() {
            return idVehicle;
        }

        /**
         * @param idVehicle the idVehicle to set
         */
        public void setIdVehicle(int idVehicle) {
            this.idVehicle = idVehicle;
        }

        /**
         * @return the vehicle_number
         */
        public String getVehicle_number() {
            return vehicle_number;
        }

        /**
         * @param vehicle_number the vehicle_number to set
         */
        public void setVehicle_number(String vehicle_number) {
            this.vehicle_number = vehicle_number;
        }

        /**
         * @return the vehicle_type
         */
        public String getVehicle_type() {
            return vehicle_type;
        }

        /**
         * @param vehicle_type the vehicle_type to set
         */
        public void setVehicle_type(String vehicle_type) {
            this.vehicle_type = vehicle_type;
        }

        /**
         * @return the licence_upto
         */
        public Date getLicence_upto() {
            return licence_upto;
        }

        /**
         * @param licence_upto the licence_upto to set
         */
        public void setLicence_upto(Date licence_upto) {
            this.licence_upto = licence_upto;
        }

        /**
         * @return the insuarance_upto
         */
        public Date getInsuarance_upto() {
            return insuarance_upto;
        }

        /**
         * @param insuarance_upto the insuarance_upto to set
         */
        public void setInsuarance_upto(Date insuarance_upto) {
            this.insuarance_upto = insuarance_upto;
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
}
