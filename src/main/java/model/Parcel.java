/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.Date;

public class Parcel {

    private int packageId;
    private float weight;
    private float height;
    private float length;
    private float width;
    private float longitude;
    private float latitiude;
    private Date date;
    private String deliveryState;
    private String refaranceNumber;
    private String parcelType;
    private Address fromAddress;
    private Address toAddress;
    private ArrayList<Deliveries> deliveries;

    /**
     * @return the packageId
     */
    public int getPackageId() {
        return packageId;
    }

    /**
     * @param packageId the packageId to set
     */
    public void setPackageId(int packageId) {
        this.packageId = packageId;
    }

    /**
     * @return the weight
     */
    public float getWeight() {
        return weight;
    }

    /**
     * @param weight the weight to set
     */
    public void setWeight(float weight) {
        this.weight = weight;
    }

    /**
     * @return the height
     */
    public float getHeight() {
        return height;
    }

    /**
     * @param height the height to set
     */
    public void setHeight(float height) {
        this.height = height;
    }

    /**
     * @return the length
     */
    public float getLength() {
        return length;
    }

    /**
     * @param length the length to set
     */
    public void setLength(float length) {
        this.length = length;
    }

    /**
     * @return the width
     */
    public float getWidth() {
        return width;
    }

    /**
     * @param width the width to set
     */
    public void setWidth(float width) {
        this.width = width;
    }

    /**
     * @return the longitude
     */
    public float getLongitude() {
        return longitude;
    }

    /**
     * @param longitude the longitude to set
     */
    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    /**
     * @return the latitiude
     */
    public float getLatitiude() {
        return latitiude;
    }

    /**
     * @param latitiude the latitiude to set
     */
    public void setLatitiude(float latitiude) {
        this.latitiude = latitiude;
    }

    /**
     * @return the date
     */
    public Date getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * @return the deliveryState
     */
    public String getDeliveryState() {
        return deliveryState;
    }

    /**
     * @param deliveryState the deliveryState to set
     */
    public void setDeliveryState(String deliveryState) {
        this.deliveryState = deliveryState;
    }

    /**
     * @return the refaranceNumber
     */
    public String getRefaranceNumber() {
        return refaranceNumber;
    }

    /**
     * @param refaranceNumber the refaranceNumber to set
     */
    public void setRefaranceNumber(String refaranceNumber) {
        this.refaranceNumber = refaranceNumber;
    }

    /**
     * @return the parcelType
     */
    public String getParcelType() {
        return parcelType;
    }

    /**
     * @param parcelType the parcelType to set
     */
    public void setParcelType(String parcelType) {
        this.parcelType = parcelType;
    }

    /**
     * @return the fromAddress
     */
    public Address getFromAddress() {
        return fromAddress;
    }

    /**
     * @param fromAddress the fromAddress to set
     */
    public void setFromAddress(Address fromAddress) {
        this.fromAddress = fromAddress;
    }

    /**
     * @return the toAddress
     */
    public Address getToAddress() {
        return toAddress;
    }

    /**
     * @param toAddress the toAddress to set
     */
    public void setToAddress(Address toAddress) {
        this.toAddress = toAddress;
    }

    /**
     * @return the deliveries
     */
    public ArrayList<Deliveries> getDeliveries() {
        return deliveries;
    }

    /**
     * @param deliveries the deliveries to set
     */
    public void setDeliveries(ArrayList<Deliveries> deliveries) {
        this.deliveries = deliveries;
    }

    private static class Deliveries {

        private Date toDate;
        private Date fromDtae;
        private Address.User fromUser;
        private Address.User toUser;
        private String status;
        
        public Deliveries() {
        }

        /**
         * @return the toDate
         */
        public Date getToDate() {
            return toDate;
        }

        /**
         * @param toDate the toDate to set
         */
        public void setToDate(Date toDate) {
            this.toDate = toDate;
        }

        /**
         * @return the fromDtae
         */
        public Date getFromDtae() {
            return fromDtae;
        }

        /**
         * @param fromDtae the fromDtae to set
         */
        public void setFromDtae(Date fromDtae) {
            this.fromDtae = fromDtae;
        }

        /**
         * @return the fromUser
         */
        public Address.User getFromUser() {
            return fromUser;
        }

        /**
         * @param fromUser the fromUser to set
         */
        public void setFromUser(Address.User fromUser) {
            this.fromUser = fromUser;
        }

        /**
         * @return the toUser
         */
        public Address.User getToUser() {
            return toUser;
        }

        /**
         * @param toUser the toUser to set
         */
        public void setToUser(Address.User toUser) {
            this.toUser = toUser;
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
}
