/*
SQLyog Ultimate - MySQL GUI v8.2 
MySQL - 5.5.31 : Database - citypak
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`citypak` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `citypak`;

/*Table structure for table `account` */

DROP TABLE IF EXISTS `account`;

CREATE TABLE `account` (
  `idaccount` int(11) NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `balance` double NOT NULL,
  `credit_limit` double NOT NULL,
  `status` int(11) NOT NULL,
  `id_user` int(11) NOT NULL,
  PRIMARY KEY (`idaccount`),
  KEY `fk_account_user1_idx` (`id_user`),
  CONSTRAINT `fk_account_user1` FOREIGN KEY (`id_user`) REFERENCES `user` (`iduser`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `account` */

/*Table structure for table `action` */

DROP TABLE IF EXISTS `action`;

CREATE TABLE `action` (
  `idaction` int(11) NOT NULL AUTO_INCREMENT,
  `action` varchar(150) NOT NULL,
  `status` int(11) NOT NULL,
  PRIMARY KEY (`idaction`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `action` */

/*Table structure for table `address` */

DROP TABLE IF EXISTS `address`;

CREATE TABLE `address` (
  `idaddress` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `address` varchar(255) NOT NULL,
  `id_town` int(11) NOT NULL,
  `latitude` mediumtext,
  `longitude` mediumtext,
  `status` int(11) NOT NULL,
  `contact_number` varchar(45) NOT NULL,
  PRIMARY KEY (`idaddress`),
  UNIQUE KEY `address_UNIQUE` (`address`,`name`),
  KEY `fk_address_town1_idx` (`id_town`),
  CONSTRAINT `fk_address_town1` FOREIGN KEY (`id_town`) REFERENCES `town` (`idtown`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `address` */

/*Table structure for table `branch` */

DROP TABLE IF EXISTS `branch`;

CREATE TABLE `branch` (
  `idbranch` int(11) NOT NULL AUTO_INCREMENT,
  `branch` varchar(255) NOT NULL,
  `status` int(11) NOT NULL,
  PRIMARY KEY (`idbranch`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `branch` */

/*Table structure for table `delivery` */

DROP TABLE IF EXISTS `delivery`;

CREATE TABLE `delivery` (
  `iddelivery` int(11) NOT NULL AUTO_INCREMENT,
  `id_package` int(11) NOT NULL,
  `from_user` int(11) NOT NULL,
  `to_user` int(11) DEFAULT NULL,
  `from_date_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `to_date_time` timestamp NULL DEFAULT NULL,
  `delivery_status` int(11) NOT NULL,
  PRIMARY KEY (`iddelivery`),
  KEY `fk_delivery_delivery_status1_idx` (`delivery_status`),
  KEY `fk_delivery_package1_idx` (`id_package`),
  KEY `fk_delivery_user1_idx` (`from_user`),
  KEY `fk_delivery_user2_idx` (`to_user`),
  CONSTRAINT `fk_delivery_delivery_status1` FOREIGN KEY (`delivery_status`) REFERENCES `delivery_status` (`iddelivery_status`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_delivery_package1` FOREIGN KEY (`id_package`) REFERENCES `package` (`idpackage`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_delivery_user1` FOREIGN KEY (`from_user`) REFERENCES `user` (`iduser`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_delivery_user2` FOREIGN KEY (`to_user`) REFERENCES `user` (`iduser`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `delivery` */

/*Table structure for table `delivery_status` */

DROP TABLE IF EXISTS `delivery_status`;

CREATE TABLE `delivery_status` (
  `iddelivery_status` int(11) NOT NULL AUTO_INCREMENT,
  `status` varchar(45) NOT NULL,
  PRIMARY KEY (`iddelivery_status`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

/*Data for the table `delivery_status` */

insert  into `delivery_status`(`iddelivery_status`,`status`) values (1,'Penging Pickup'),(2,'Delivering'),(3,'Delivered');

/*Table structure for table `device` */

DROP TABLE IF EXISTS `device`;

CREATE TABLE `device` (
  `iddevice` int(11) NOT NULL AUTO_INCREMENT,
  `model` varchar(100) NOT NULL,
  `brand` varchar(45) NOT NULL,
  `status` int(11) NOT NULL,
  `udid` varchar(45) NOT NULL,
  PRIMARY KEY (`iddevice`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `device` */

/*Table structure for table `distance` */

DROP TABLE IF EXISTS `distance`;

CREATE TABLE `distance` (
  `iddistance` int(11) NOT NULL AUTO_INCREMENT,
  `distance_type` varchar(45) NOT NULL,
  `status` int(11) NOT NULL,
  PRIMARY KEY (`iddistance`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

/*Data for the table `distance` */

insert  into `distance`(`iddistance`,`distance_type`,`status`) values (1,'In Branch',1),(2,'Inter-Branches',1);

/*Table structure for table `package` */

DROP TABLE IF EXISTS `package`;

CREATE TABLE `package` (
  `idpackage` int(11) NOT NULL AUTO_INCREMENT,
  `from` int(11) NOT NULL,
  `to` int(11) NOT NULL,
  `weight` float NOT NULL,
  `height` float NOT NULL,
  `length` float NOT NULL,
  `width` float NOT NULL,
  `date` datetime NOT NULL,
  `delivery_status` int(11) NOT NULL,
  `ref_number` varchar(45) DEFAULT NULL,
  `currunt_location_longitude` mediumtext NOT NULL,
  `currunt_location_latitude` mediumtext NOT NULL,
  `package_type` int(11) NOT NULL,
  PRIMARY KEY (`idpackage`),
  KEY `fk_package_address1_idx` (`from`),
  KEY `fk_package_address2_idx` (`to`),
  KEY `fk_package_package_type1_idx` (`package_type`),
  KEY `fk_package_delivery_status1` (`delivery_status`),
  CONSTRAINT `fk_package_address1` FOREIGN KEY (`from`) REFERENCES `address` (`idaddress`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_package_address2` FOREIGN KEY (`to`) REFERENCES `address` (`idaddress`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_package_package_type1` FOREIGN KEY (`package_type`) REFERENCES `package_type` (`idpackage_type`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_package_delivery_status1` FOREIGN KEY (`delivery_status`) REFERENCES `delivery_status` (`iddelivery_status`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `package` */

/*Table structure for table `package_type` */

DROP TABLE IF EXISTS `package_type`;

CREATE TABLE `package_type` (
  `idpackage_type` int(11) NOT NULL AUTO_INCREMENT,
  `package_type` varchar(45) NOT NULL,
  `status` int(11) NOT NULL,
  PRIMARY KEY (`idpackage_type`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

/*Data for the table `package_type` */

insert  into `package_type`(`idpackage_type`,`package_type`,`status`) values (1,'Parcel',1),(2,'Documents',1),(3,'Card',1);

/*Table structure for table `rate_sheet` */

DROP TABLE IF EXISTS `rate_sheet`;

CREATE TABLE `rate_sheet` (
  `idrate_sheet` int(11) NOT NULL AUTO_INCREMENT,
  `distance` int(11) NOT NULL,
  `parcel_type` int(11) NOT NULL,
  `weight_type` int(11) NOT NULL,
  `amount` double NOT NULL,
  PRIMARY KEY (`idrate_sheet`),
  KEY `fk_rate_sheet_package_type1_idx` (`parcel_type`),
  KEY `fk_rate_sheet_distance1_idx` (`distance`),
  KEY `fk_rate_sheet_weight_type1` (`weight_type`),
  CONSTRAINT `fk_rate_sheet_package_type1` FOREIGN KEY (`parcel_type`) REFERENCES `package_type` (`idpackage_type`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_rate_sheet_distance1` FOREIGN KEY (`distance`) REFERENCES `distance` (`iddistance`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_rate_sheet_weight_type1` FOREIGN KEY (`weight_type`) REFERENCES `weight_type` (`idweight_type`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `rate_sheet` */

/*Table structure for table `town` */

DROP TABLE IF EXISTS `town`;

CREATE TABLE `town` (
  `idtown` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `id_zone` int(11) NOT NULL,
  `status` int(11) NOT NULL,
  `longitude` mediumtext NOT NULL,
  `latitude` mediumtext NOT NULL,
  PRIMARY KEY (`idtown`),
  KEY `fk_town_zone1_idx` (`id_zone`),
  CONSTRAINT `fk_town_zone1` FOREIGN KEY (`id_zone`) REFERENCES `zone` (`idzone`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `town` */

/*Table structure for table `transaction` */

DROP TABLE IF EXISTS `transaction`;

CREATE TABLE `transaction` (
  `idtransaction` int(11) NOT NULL AUTO_INCREMENT,
  `id_account` int(11) NOT NULL,
  `id_package` int(11) NOT NULL,
  `description` varchar(255) NOT NULL,
  `amount` double NOT NULL,
  `status` int(11) NOT NULL,
  `idtransaction_type` int(11) NOT NULL,
  `date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`idtransaction`),
  KEY `fk_transaction_account1_idx` (`id_account`),
  KEY `fk_transaction_package1_idx` (`id_package`),
  KEY `fk_transaction_transaction_type1_idx` (`idtransaction_type`),
  CONSTRAINT `fk_transaction_account1` FOREIGN KEY (`id_account`) REFERENCES `account` (`idaccount`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_transaction_package1` FOREIGN KEY (`id_package`) REFERENCES `package` (`idpackage`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_transaction_transaction_type1` FOREIGN KEY (`idtransaction_type`) REFERENCES `transaction_type` (`idtransaction_type`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `transaction` */

/*Table structure for table `transaction_type` */

DROP TABLE IF EXISTS `transaction_type`;

CREATE TABLE `transaction_type` (
  `idtransaction_type` int(11) NOT NULL AUTO_INCREMENT,
  `transaction_type` varchar(25) NOT NULL,
  PRIMARY KEY (`idtransaction_type`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

/*Data for the table `transaction_type` */

insert  into `transaction_type`(`idtransaction_type`,`transaction_type`) values (1,'Bill'),(2,'Payment');

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `iduser` int(11) NOT NULL AUTO_INCREMENT,
  `nic` varchar(45) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `status` int(11) NOT NULL,
  `id_user_role` int(11) NOT NULL,
  `username` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `api_key` varchar(45) NOT NULL,
  `email` varchar(100) DEFAULT NULL,
  `reset_token` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`iduser`),
  UNIQUE KEY `username_UNIQUE` (`username`),
  UNIQUE KEY `key_UNIQUE` (`api_key`),
  KEY `fk_user_user_role1_idx` (`id_user_role`),
  CONSTRAINT `fk_user_user_role1` FOREIGN KEY (`id_user_role`) REFERENCES `user_role` (`iduser_role`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

/*Data for the table `user` */

insert  into `user`(`iduser`,`nic`,`name`,`status`,`id_user_role`,`username`,`password`,`api_key`,`email`,`reset_token`) values (1,'000000000V','Super Admin',1,1,'admin','admin','00000000000000000000000000000000','admin@citypak.com',NULL);

/*Table structure for table `user_has_address` */

DROP TABLE IF EXISTS `user_has_address`;

CREATE TABLE `user_has_address` (
  `iduser_has_address` int(11) NOT NULL AUTO_INCREMENT,
  `user_iduser` int(11) NOT NULL,
  `address_idaddress` int(11) NOT NULL,
  `default` tinyint(1) NOT NULL,
  PRIMARY KEY (`iduser_has_address`),
  KEY `fk_user_has_address_address1_idx` (`address_idaddress`),
  KEY `fk_user_has_address_user1_idx` (`user_iduser`),
  CONSTRAINT `fk_user_has_address_user1` FOREIGN KEY (`user_iduser`) REFERENCES `user` (`iduser`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_has_address_address1` FOREIGN KEY (`address_idaddress`) REFERENCES `address` (`idaddress`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `user_has_address` */

/*Table structure for table `user_has_device` */

DROP TABLE IF EXISTS `user_has_device`;

CREATE TABLE `user_has_device` (
  `user_iduser` int(11) NOT NULL,
  `device_iddevice` int(11) NOT NULL,
  `iduser_has_device` int(11) NOT NULL AUTO_INCREMENT,
  `start_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `end_date` timestamp NULL DEFAULT NULL,
  `status` int(11) NOT NULL,
  PRIMARY KEY (`iduser_has_device`),
  KEY `fk_user_has_device_device1_idx` (`device_iddevice`),
  KEY `fk_user_has_device_user1_idx` (`user_iduser`),
  CONSTRAINT `fk_user_has_device_user1` FOREIGN KEY (`user_iduser`) REFERENCES `user` (`iduser`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_has_device_device1` FOREIGN KEY (`device_iddevice`) REFERENCES `device` (`iddevice`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `user_has_device` */

/*Table structure for table `user_has_rate_sheet` */

DROP TABLE IF EXISTS `user_has_rate_sheet`;

CREATE TABLE `user_has_rate_sheet` (
  `iduser_has_rate_sheet` int(11) NOT NULL AUTO_INCREMENT,
  `id_user` int(11) NOT NULL,
  `id_rate_sheet` int(11) NOT NULL,
  `state` int(11) NOT NULL,
  PRIMARY KEY (`iduser_has_rate_sheet`),
  KEY `fk_user_has_rate_sheet_rate_sheet1_idx` (`id_rate_sheet`),
  KEY `fk_user_has_rate_sheet_user1_idx` (`id_user`),
  CONSTRAINT `fk_user_has_rate_sheet_rate_sheet1` FOREIGN KEY (`id_rate_sheet`) REFERENCES `rate_sheet` (`idrate_sheet`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_has_rate_sheet_user1` FOREIGN KEY (`id_user`) REFERENCES `user` (`iduser`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `user_has_rate_sheet` */

/*Table structure for table `user_has_vehicle` */

DROP TABLE IF EXISTS `user_has_vehicle`;

CREATE TABLE `user_has_vehicle` (
  `user_iduser` int(11) NOT NULL,
  `vehicle_idvehicle` int(11) NOT NULL,
  `status` int(11) NOT NULL,
  `start_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `end_date` timestamp NULL DEFAULT NULL,
  `iduser_has_vehicle` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`iduser_has_vehicle`),
  KEY `fk_user_has_vehicle_vehicle1_idx` (`vehicle_idvehicle`),
  KEY `fk_user_has_vehicle_user1_idx` (`user_iduser`),
  CONSTRAINT `fk_user_has_vehicle_user1` FOREIGN KEY (`user_iduser`) REFERENCES `user` (`iduser`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_has_vehicle_vehicle1` FOREIGN KEY (`vehicle_idvehicle`) REFERENCES `vehicle` (`idvehicle`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `user_has_vehicle` */

/*Table structure for table `user_role` */

DROP TABLE IF EXISTS `user_role`;

CREATE TABLE `user_role` (
  `iduser_role` int(11) NOT NULL AUTO_INCREMENT,
  `role` varchar(45) NOT NULL,
  `status` int(11) NOT NULL,
  PRIMARY KEY (`iduser_role`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

/*Data for the table `user_role` */

insert  into `user_role`(`iduser_role`,`role`,`status`) values (1,'Admin',1),(2,'Driver',1),(3,'Office Crew',1),(4,'Customer',1);

/*Table structure for table `user_role_has_action` */

DROP TABLE IF EXISTS `user_role_has_action`;

CREATE TABLE `user_role_has_action` (
  `user_role_iduser_role` int(11) NOT NULL,
  `action_idaction` int(11) NOT NULL,
  `status` int(11) NOT NULL,
  PRIMARY KEY (`user_role_iduser_role`,`action_idaction`),
  KEY `fk_user_role_has_action_action1_idx` (`action_idaction`),
  KEY `fk_user_role_has_action_user_role1_idx` (`user_role_iduser_role`),
  CONSTRAINT `fk_user_role_has_action_user_role1` FOREIGN KEY (`user_role_iduser_role`) REFERENCES `user_role` (`iduser_role`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_role_has_action_action1` FOREIGN KEY (`action_idaction`) REFERENCES `action` (`idaction`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `user_role_has_action` */

/*Table structure for table `vehicle` */

DROP TABLE IF EXISTS `vehicle`;

CREATE TABLE `vehicle` (
  `idvehicle` int(11) NOT NULL AUTO_INCREMENT,
  `vehicle_number` varchar(45) NOT NULL,
  `status` int(11) NOT NULL,
  `vehicle_type` int(11) NOT NULL,
  `licence_upto` date NOT NULL,
  `insuarance_upto` date NOT NULL,
  PRIMARY KEY (`idvehicle`),
  KEY `fk_vehicle_vehicle_type1_idx` (`vehicle_type`),
  CONSTRAINT `fk_vehicle_vehicle_type1` FOREIGN KEY (`vehicle_type`) REFERENCES `vehicle_type` (`idvehicle_type`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `vehicle` */

/*Table structure for table `vehicle_type` */

DROP TABLE IF EXISTS `vehicle_type`;

CREATE TABLE `vehicle_type` (
  `idvehicle_type` int(11) NOT NULL AUTO_INCREMENT,
  `type` varchar(45) NOT NULL,
  `status` int(11) NOT NULL,
  PRIMARY KEY (`idvehicle_type`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

/*Data for the table `vehicle_type` */

insert  into `vehicle_type`(`idvehicle_type`,`type`,`status`) values (1,'Car',1),(2,'Van',1),(3,'Motorcycle',1),(4,'Threeweeler',1),(5,'Lorry',1);

/*Table structure for table `weight_config` */

DROP TABLE IF EXISTS `weight_config`;

CREATE TABLE `weight_config` (
  `idweight_config` int(11) NOT NULL AUTO_INCREMENT,
  `id_weight_type` int(11) NOT NULL,
  `value` int(11) NOT NULL,
  PRIMARY KEY (`idweight_config`),
  KEY `fk_weight_config_weight_type1` (`id_weight_type`),
  CONSTRAINT `fk_weight_config_weight_type1` FOREIGN KEY (`id_weight_type`) REFERENCES `weight_type` (`idweight_type`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `weight_config` */

/*Table structure for table `weight_type` */

DROP TABLE IF EXISTS `weight_type`;

CREATE TABLE `weight_type` (
  `idweight_type` int(11) NOT NULL AUTO_INCREMENT,
  `weight_type` varchar(45) NOT NULL,
  `status` int(11) NOT NULL,
  PRIMARY KEY (`idweight_type`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

/*Data for the table `weight_type` */

insert  into `weight_type`(`idweight_type`,`weight_type`,`status`) values (1,'Initial',1),(2,'Additional',1);

/*Table structure for table `zone` */

DROP TABLE IF EXISTS `zone`;

CREATE TABLE `zone` (
  `idzone` int(11) NOT NULL AUTO_INCREMENT,
  `zone` varchar(255) NOT NULL,
  `id_branch` int(11) NOT NULL,
  `status` int(11) NOT NULL,
  PRIMARY KEY (`idzone`),
  KEY `fk_zone_branch_idx` (`id_branch`),
  CONSTRAINT `fk_zone_branch` FOREIGN KEY (`id_branch`) REFERENCES `branch` (`idbranch`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `zone` */

/*Table structure for table `package_details_view` */

DROP TABLE IF EXISTS `package_details_view`;

/*!50001 DROP VIEW IF EXISTS `package_details_view` */;
/*!50001 DROP TABLE IF EXISTS `package_details_view` */;

/*!50001 CREATE TABLE  `package_details_view`(
 `idpackage` int(11) ,
 `ref_number` varchar(45) ,
 `currunt_location_latitude` mediumtext ,
 `currunt_location_longitude` mediumtext ,
 `weight` float ,
 `delivery_status` varchar(45) ,
 `package_type` varchar(45) ,
 `to_name` varchar(255) ,
 `to_address` varchar(255) ,
 `to_contact_number` varchar(45) ,
 `to_latitude` mediumtext ,
 `to_logitude` mediumtext ,
 `to_user_id` int(11) ,
 `to_town` varchar(255) ,
 `to_zone` varchar(255) ,
 `to_branch` varchar(255) ,
 `from_name` varchar(255) ,
 `from_address` varchar(255) ,
 `from_contact_number` varchar(45) ,
 `from_latitude` mediumtext ,
 `from_logitude` mediumtext ,
 `from_user_id` int(11) ,
 `from_town` varchar(255) ,
 `from_zone` varchar(255) ,
 `from_branch` varchar(255) 
)*/;

/*Table structure for table `user_has_account_view` */

DROP TABLE IF EXISTS `user_has_account_view`;

/*!50001 DROP VIEW IF EXISTS `user_has_account_view` */;
/*!50001 DROP TABLE IF EXISTS `user_has_account_view` */;

/*!50001 CREATE TABLE  `user_has_account_view`(
 `iduser` int(11) ,
 `name` varchar(255) ,
 `email` varchar(100) ,
 `status` int(11) ,
 `balance` double ,
 `credit_limit` double ,
 `account_status` int(11) ,
 `transaction_date` timestamp ,
 `transaction_amount` double ,
 `transaction_description` varchar(255) ,
 `transaction_status` int(11) ,
 `idtransaction` int(11) ,
 `transaction_type` varchar(25) 
)*/;

/*Table structure for table `user_has_address_view` */

DROP TABLE IF EXISTS `user_has_address_view`;

/*!50001 DROP VIEW IF EXISTS `user_has_address_view` */;
/*!50001 DROP TABLE IF EXISTS `user_has_address_view` */;

/*!50001 CREATE TABLE  `user_has_address_view`(
 `iduser` int(11) ,
 `name` varchar(255) ,
 `email` varchar(100) ,
 `status` int(11) ,
 `is_default` tinyint(1) ,
 `address` varchar(255) ,
 `contact_name` varchar(255) ,
 `contact_number` varchar(45) ,
 `address_status` int(11) ,
 `address_lat` mediumtext ,
 `address_long` mediumtext ,
 `town_name` varchar(255) ,
 `town_status` int(11) ,
 `zone` varchar(255) ,
 `zone_status` int(11) ,
 `branch` varchar(255) ,
 `branch_status` int(11) 
)*/;

/*Table structure for table `user_has_devices_view` */

DROP TABLE IF EXISTS `user_has_devices_view`;

/*!50001 DROP VIEW IF EXISTS `user_has_devices_view` */;
/*!50001 DROP TABLE IF EXISTS `user_has_devices_view` */;

/*!50001 CREATE TABLE  `user_has_devices_view`(
 `iduser` int(11) ,
 `name` varchar(255) ,
 `email` varchar(100) ,
 `status` int(11) ,
 `device_start_date` timestamp ,
 `device_end_date` timestamp ,
 `user_has_device_status` int(11) ,
 `brand` varchar(45) ,
 `model` varchar(100) ,
 `udid` varchar(45) ,
 `device_status` int(11) 
)*/;

/*Table structure for table `user_has_previlages_view` */

DROP TABLE IF EXISTS `user_has_previlages_view`;

/*!50001 DROP VIEW IF EXISTS `user_has_previlages_view` */;
/*!50001 DROP TABLE IF EXISTS `user_has_previlages_view` */;

/*!50001 CREATE TABLE  `user_has_previlages_view`(
 `iduser` int(11) ,
 `name` varchar(255) ,
 `nic` varchar(45) ,
 `user_status` int(11) ,
 `api_key` varchar(45) ,
 `role` varchar(45) ,
 `user_role_status` int(11) ,
 `privilage_status` int(11) ,
 `action` varchar(150) ,
 `action_status` int(11) 
)*/;

/*Table structure for table `user_has_rate_sheet_view` */

DROP TABLE IF EXISTS `user_has_rate_sheet_view`;

/*!50001 DROP VIEW IF EXISTS `user_has_rate_sheet_view` */;
/*!50001 DROP TABLE IF EXISTS `user_has_rate_sheet_view` */;

/*!50001 CREATE TABLE  `user_has_rate_sheet_view`(
 `iduser` int(11) ,
 `name` varchar(255) ,
 `email` varchar(100) ,
 `status` int(11) ,
 `rate_sheet_status` int(11) ,
 `amount` double ,
 `distance_type` varchar(45) ,
 `distance_type_status` int(11) ,
 `weight_type` varchar(45) ,
 `weight_type_status` int(11) ,
 `package_type` varchar(45) ,
 `package_type_status` int(11) 
)*/;

/*Table structure for table `user_has_vehicle_view` */

DROP TABLE IF EXISTS `user_has_vehicle_view`;

/*!50001 DROP VIEW IF EXISTS `user_has_vehicle_view` */;
/*!50001 DROP TABLE IF EXISTS `user_has_vehicle_view` */;

/*!50001 CREATE TABLE  `user_has_vehicle_view`(
 `iduser` int(11) ,
 `name` varchar(255) ,
 `email` varchar(100) ,
 `status` int(11) ,
 `vehicle_start_at` timestamp ,
 `vehicle_end_at` timestamp ,
 `user_has_vehicle_status` int(11) ,
 `vehicle_number` varchar(45) ,
 `vehicle_status` int(11) ,
 `vehicle_type` varchar(45) ,
 `vehicle_type_status` int(11) 
)*/;

/*View structure for view package_details_view */

/*!50001 DROP TABLE IF EXISTS `package_details_view` */;
/*!50001 DROP VIEW IF EXISTS `package_details_view` */;

/*!50001 CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `package_details_view` AS select `package`.`idpackage` AS `idpackage`,`package`.`ref_number` AS `ref_number`,`package`.`currunt_location_latitude` AS `currunt_location_latitude`,`package`.`currunt_location_longitude` AS `currunt_location_longitude`,`package`.`weight` AS `weight`,`delivery_status`.`status` AS `delivery_status`,`package_type`.`package_type` AS `package_type`,`address`.`name` AS `to_name`,`address`.`address` AS `to_address`,`address`.`contact_number` AS `to_contact_number`,`address`.`latitude` AS `to_latitude`,`address`.`longitude` AS `to_logitude`,`user`.`iduser` AS `to_user_id`,`town`.`name` AS `to_town`,`zone`.`zone` AS `to_zone`,`branch`.`branch` AS `to_branch`,`from_address`.`name` AS `from_name`,`from_address`.`address` AS `from_address`,`from_address`.`contact_number` AS `from_contact_number`,`from_address`.`latitude` AS `from_latitude`,`from_address`.`longitude` AS `from_logitude`,`from_user`.`iduser` AS `from_user_id`,`from_town`.`name` AS `from_town`,`from_zone`.`zone` AS `from_zone`,`from_branch`.`branch` AS `from_branch` from ((((((((((((((`package` left join `delivery_status` on((`package`.`delivery_status` = `delivery_status`.`iddelivery_status`))) left join `address` on((`package`.`to` = `address`.`idaddress`))) left join `address` `from_address` on((`package`.`from` = `from_address`.`idaddress`))) left join `user_has_address` on((`user_has_address`.`address_idaddress` = `address`.`idaddress`))) left join `town` on((`address`.`id_town` = `town`.`idtown`))) left join `user` on((`user_has_address`.`user_iduser` = `user`.`iduser`))) left join `user_has_address` `from_user_has_address` on((`from_user_has_address`.`address_idaddress` = `from_address`.`idaddress`))) left join `town` `from_town` on((`from_address`.`id_town` = `from_town`.`idtown`))) left join `user` `from_user` on((`from_user_has_address`.`user_iduser` = `from_user`.`iduser`))) left join `zone` on((`town`.`id_zone` = `zone`.`idzone`))) left join `branch` on((`zone`.`id_branch` = `branch`.`idbranch`))) left join `zone` `from_zone` on((`from_town`.`id_zone` = `from_zone`.`idzone`))) left join `branch` `from_branch` on((`from_zone`.`id_branch` = `from_branch`.`idbranch`))) left join `package_type` on((`package`.`package_type` = `package_type`.`idpackage_type`))) */;

/*View structure for view user_has_account_view */

/*!50001 DROP TABLE IF EXISTS `user_has_account_view` */;
/*!50001 DROP VIEW IF EXISTS `user_has_account_view` */;

/*!50001 CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `user_has_account_view` AS select `user`.`iduser` AS `iduser`,`user`.`name` AS `name`,`user`.`email` AS `email`,`user`.`status` AS `status`,`account`.`balance` AS `balance`,`account`.`credit_limit` AS `credit_limit`,`account`.`status` AS `account_status`,`transaction`.`date` AS `transaction_date`,`transaction`.`amount` AS `transaction_amount`,`transaction`.`description` AS `transaction_description`,`transaction`.`status` AS `transaction_status`,`transaction`.`idtransaction` AS `idtransaction`,`transaction_type`.`transaction_type` AS `transaction_type` from (((`user` left join `account` on((`account`.`id_user` = `user`.`iduser`))) left join `transaction` on((`transaction`.`id_account` = `account`.`idaccount`))) left join `transaction_type` on((`transaction`.`idtransaction_type` = `transaction_type`.`idtransaction_type`))) order by `user`.`iduser` */;

/*View structure for view user_has_address_view */

/*!50001 DROP TABLE IF EXISTS `user_has_address_view` */;
/*!50001 DROP VIEW IF EXISTS `user_has_address_view` */;

/*!50001 CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `user_has_address_view` AS select `user`.`iduser` AS `iduser`,`user`.`name` AS `name`,`user`.`email` AS `email`,`user`.`status` AS `status`,`user_has_address`.`default` AS `is_default`,`address`.`address` AS `address`,`address`.`name` AS `contact_name`,`address`.`contact_number` AS `contact_number`,`address`.`status` AS `address_status`,`address`.`latitude` AS `address_lat`,`address`.`longitude` AS `address_long`,`town`.`name` AS `town_name`,`town`.`status` AS `town_status`,`zone`.`zone` AS `zone`,`zone`.`status` AS `zone_status`,`branch`.`branch` AS `branch`,`branch`.`status` AS `branch_status` from (((((`user` left join `user_has_address` on((`user_has_address`.`user_iduser` = `user`.`iduser`))) left join `address` on((`user_has_address`.`address_idaddress` = `address`.`idaddress`))) left join `town` on((`address`.`id_town` = `town`.`idtown`))) left join `zone` on((`town`.`id_zone` = `zone`.`idzone`))) left join `branch` on((`zone`.`id_branch` = `branch`.`idbranch`))) order by `user`.`iduser` */;

/*View structure for view user_has_devices_view */

/*!50001 DROP TABLE IF EXISTS `user_has_devices_view` */;
/*!50001 DROP VIEW IF EXISTS `user_has_devices_view` */;

/*!50001 CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `user_has_devices_view` AS select `user`.`iduser` AS `iduser`,`user`.`name` AS `name`,`user`.`email` AS `email`,`user`.`status` AS `status`,`user_has_device`.`start_date` AS `device_start_date`,`user_has_device`.`end_date` AS `device_end_date`,`user_has_device`.`status` AS `user_has_device_status`,`device`.`brand` AS `brand`,`device`.`model` AS `model`,`device`.`udid` AS `udid`,`device`.`status` AS `device_status` from ((`user` left join `user_has_device` on((`user_has_device`.`user_iduser` = `user`.`iduser`))) left join `device` on((`user_has_device`.`device_iddevice` = `device`.`iddevice`))) order by `user`.`iduser` */;

/*View structure for view user_has_previlages_view */

/*!50001 DROP TABLE IF EXISTS `user_has_previlages_view` */;
/*!50001 DROP VIEW IF EXISTS `user_has_previlages_view` */;

/*!50001 CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `user_has_previlages_view` AS select `user`.`iduser` AS `iduser`,`user`.`name` AS `name`,`user`.`nic` AS `nic`,`user`.`status` AS `user_status`,`user`.`api_key` AS `api_key`,`user_role`.`role` AS `role`,`user_role`.`status` AS `user_role_status`,`user_role_has_action`.`status` AS `privilage_status`,`action`.`action` AS `action`,`action`.`status` AS `action_status` from (((`user` left join `user_role` on((`user`.`id_user_role` = `user_role`.`iduser_role`))) left join `user_role_has_action` on((`user_role_has_action`.`user_role_iduser_role` = `user_role`.`iduser_role`))) left join `action` on((`user_role_has_action`.`action_idaction` = `action`.`idaction`))) order by `user`.`iduser` */;

/*View structure for view user_has_rate_sheet_view */

/*!50001 DROP TABLE IF EXISTS `user_has_rate_sheet_view` */;
/*!50001 DROP VIEW IF EXISTS `user_has_rate_sheet_view` */;

/*!50001 CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `user_has_rate_sheet_view` AS select `user`.`iduser` AS `iduser`,`user`.`name` AS `name`,`user`.`email` AS `email`,`user`.`status` AS `status`,`user_has_rate_sheet`.`state` AS `rate_sheet_status`,`rate_sheet`.`amount` AS `amount`,`distance`.`distance_type` AS `distance_type`,`distance`.`status` AS `distance_type_status`,`weight_type`.`weight_type` AS `weight_type`,`weight_type`.`status` AS `weight_type_status`,`package_type`.`package_type` AS `package_type`,`package_type`.`status` AS `package_type_status` from (((((`user` left join `user_has_rate_sheet` on((`user_has_rate_sheet`.`id_user` = `user`.`iduser`))) left join `rate_sheet` on((`user_has_rate_sheet`.`id_rate_sheet` = `rate_sheet`.`idrate_sheet`))) left join `package_type` on((`rate_sheet`.`parcel_type` = `package_type`.`idpackage_type`))) left join `weight_type` on((`rate_sheet`.`weight_type` = `weight_type`.`idweight_type`))) left join `distance` on((`rate_sheet`.`distance` = `distance`.`iddistance`))) order by `user`.`iduser` */;

/*View structure for view user_has_vehicle_view */

/*!50001 DROP TABLE IF EXISTS `user_has_vehicle_view` */;
/*!50001 DROP VIEW IF EXISTS `user_has_vehicle_view` */;

/*!50001 CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `user_has_vehicle_view` AS select `user`.`iduser` AS `iduser`,`user`.`name` AS `name`,`user`.`email` AS `email`,`user`.`status` AS `status`,`user_has_vehicle`.`start_date` AS `vehicle_start_at`,`user_has_vehicle`.`end_date` AS `vehicle_end_at`,`user_has_vehicle`.`status` AS `user_has_vehicle_status`,`vehicle`.`vehicle_number` AS `vehicle_number`,`vehicle`.`status` AS `vehicle_status`,`vehicle_type`.`type` AS `vehicle_type`,`vehicle_type`.`status` AS `vehicle_type_status` from (((`user` left join `user_has_vehicle` on((`user_has_vehicle`.`user_iduser` = `user`.`iduser`))) left join `vehicle` on((`user_has_vehicle`.`vehicle_idvehicle` = `vehicle`.`idvehicle`))) left join `vehicle_type` on((`vehicle`.`vehicle_type` = `vehicle_type`.`idvehicle_type`))) order by `user`.`iduser` */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
