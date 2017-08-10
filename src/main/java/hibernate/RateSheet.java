package hibernate;
// Generated Aug 4, 2017 12:48:05 AM by Hibernate Tools 3.6.0


import java.util.HashSet;
import java.util.Set;

/**
 * RateSheet generated by hbm2java
 */
public class RateSheet  implements java.io.Serializable {


     private Integer idrateSheet;
     private WeightType weightType;
     private PackageType packageType;
     private Distance distance;
     private double amount;
     private Set userHasRateSheets = new HashSet(0);

    public RateSheet() {
    }

	
    public RateSheet(WeightType weightType, PackageType packageType, Distance distance, double amount) {
        this.weightType = weightType;
        this.packageType = packageType;
        this.distance = distance;
        this.amount = amount;
    }
    public RateSheet(WeightType weightType, PackageType packageType, Distance distance, double amount, Set userHasRateSheets) {
       this.weightType = weightType;
       this.packageType = packageType;
       this.distance = distance;
       this.amount = amount;
       this.userHasRateSheets = userHasRateSheets;
    }
   
    public Integer getIdrateSheet() {
        return this.idrateSheet;
    }
    
    public void setIdrateSheet(Integer idrateSheet) {
        this.idrateSheet = idrateSheet;
    }
    public WeightType getWeightType() {
        return this.weightType;
    }
    
    public void setWeightType(WeightType weightType) {
        this.weightType = weightType;
    }
    public PackageType getPackageType() {
        return this.packageType;
    }
    
    public void setPackageType(PackageType packageType) {
        this.packageType = packageType;
    }
    public Distance getDistance() {
        return this.distance;
    }
    
    public void setDistance(Distance distance) {
        this.distance = distance;
    }
    public double getAmount() {
        return this.amount;
    }
    
    public void setAmount(double amount) {
        this.amount = amount;
    }
    public Set getUserHasRateSheets() {
        return this.userHasRateSheets;
    }
    
    public void setUserHasRateSheets(Set userHasRateSheets) {
        this.userHasRateSheets = userHasRateSheets;
    }




}

