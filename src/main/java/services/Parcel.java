/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import contoller.PermissionChecker;
import extention.BaseResponse;
import hibernate.Delivery;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import utilities.Database;

@Path("/parcel")
public class Parcel {

    //search parcel
    /**
     *
     * @param key
     * @param refNumber
     * @param pkgType
     * @param fromAddress
     * @param toAddress
     * @param status
     * @return
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getParcels(
            @FormParam("key") String key,
            @FormParam("ref_number") String refNumber,
            @FormParam("pkg_type") String pkgType,
            @FormParam("from_address") String fromAddress,
            @FormParam("to_address") String toAddress,
            @FormParam("delivery_status") String status) {

        BaseResponse response = new BaseResponse();
        PermissionChecker permissionChecker = new PermissionChecker(key);
        if (key != null && permissionChecker.isPermissionGranted()) {

            if (refNumber != null && !refNumber.equals("") || pkgType != null && !pkgType.equals("") || fromAddress != null && !fromAddress.equals("") || toAddress != null && !toAddress.equals("") || status != null && !status.equals("")) {
                try {

                    Session session = Database.getSession();
                    Set< hibernate.Parcel> parcelResult = new HashSet<>();
                    Criteria criteria = session.createCriteria(hibernate.Parcel.class);
                    if (refNumber != null) {
                        criteria.add(Restrictions.eq("refNumber", refNumber)).list();
                    }
                    if (status != null) {
                        hibernate.DeliveryStatus deliveryStatus = (hibernate.DeliveryStatus) session.createCriteria(hibernate.DeliveryStatus.class).add(Restrictions.eq("status", status)).uniqueResult();
                        if (deliveryStatus != null) {
                            criteria.add(Restrictions.eq("deliveryStatus", deliveryStatus)).list();
                        }
                    }
                    if (fromAddress != null) {
                        hibernate.Address fromAddressResult = (hibernate.Address) session.createCriteria(hibernate.Address.class).add(Restrictions.eq("address", fromAddress)).list().get(0);
                        criteria.add(Restrictions.eq("addressByFrom", fromAddressResult));
                    }
                    if (toAddress != null) {
                        hibernate.Address toAddressResult = (hibernate.Address) session.createCriteria(hibernate.Address.class).add(Restrictions.eq("address", toAddress)).list().get(0);
                        criteria.add(Restrictions.eq("addressByTo", toAddressResult));
                    }
                    if (pkgType != null) {
                        hibernate.PackageType packageTypeResult = (hibernate.PackageType) session.createCriteria(hibernate.PackageType.class).add(Restrictions.eq("", pkgType)).uniqueResult();
                        criteria.add(Restrictions.eq("packageType", packageTypeResult));
                    }

                    parcelResult.addAll(criteria.list());

                    response.setMsg_body(parcelResult);
                    response.setStatus("success");
                    return Response.status(Response.Status.OK).entity(response).build();

                } catch (HibernateException e) {
                    response.setErr_body("Something went wrong. please try again.");
                    response.setStatus("error");
                    return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(response).build();
                }
            } else {
                response.setErr_body("Invalid parameters found.");
                response.setStatus("error");
                return Response.status(Response.Status.NOT_ACCEPTABLE).entity(response).build();
            }
        } else {
            response.setErr_body("Invalid access key");
            response.setStatus("error");
            return Response.status(Response.Status.UNAUTHORIZED).entity(response).build();
        }
    }

    //add new parcel
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response addNewParcel(
            @FormParam("key") String key,
            @FormParam("fromAddressId") int fromAddressId,
            @FormParam("toAddressId") int toAddressId,
            @FormParam("weight") float weight,
            @FormParam("refNumber") String refNumber,
            @FormParam("packageType") String packageType,
            @FormParam("width") float width,
            @FormParam("height") float height,
            @FormParam("length") float length) {

        BaseResponse response = new BaseResponse();
        PermissionChecker permissionChecker = new PermissionChecker(key);
        if (key != null && permissionChecker.isPermissionGranted()) {

            if (fromAddressId != 0 && toAddressId != 0 && weight != 0 && packageType != null && !packageType.isEmpty()) {
                try {
                    Session session = Database.getSession();
                    hibernate.Address fromAddress = (hibernate.Address) session.load(hibernate.Address.class, fromAddressId);
                    if (fromAddress != null) {
                        hibernate.Address toAddress = (hibernate.Address) session.load(hibernate.Address.class, toAddressId);
                        if (toAddress != null) {
                            hibernate.PackageType packageTypeResult = (hibernate.PackageType) session.createCriteria(hibernate.PackageType.class).add(Restrictions.eq("packageType", packageType)).uniqueResult();
                            if (packageTypeResult != null) {

                                hibernate.DeliveryStatus deliveryDtatusResult = (hibernate.DeliveryStatus) session.createCriteria(hibernate.DeliveryStatus.class).add(Restrictions.eq("status", "Pending Pickup")).uniqueResult();
                                if (deliveryDtatusResult != null) {
                                    Transaction transaction = session.beginTransaction();

                                    hibernate.Parcel parcel = new hibernate.Parcel();
                                    parcel.setAddressByFrom(fromAddress);
                                    parcel.setAddressByTo(toAddress);
                                    parcel.setWeight(weight);
                                    parcel.setHeight(height);
                                    parcel.setLength(length);
                                    parcel.setDate(new Date());
                                    parcel.setDeliveryStatus(deliveryDtatusResult);
                                    parcel.setRefNumber(refNumber);
                                    parcel.setPackageType(packageTypeResult);
                                    session.save(parcel);

                                    hibernate.DeliveryStatus pendingDeliveryStatus = (hibernate.DeliveryStatus) session.createCriteria(hibernate.DeliveryStatus.class).add(Restrictions.eq("status", "Processing")).uniqueResult();
                                    Delivery delivery = new hibernate.Delivery(pendingDeliveryStatus, parcel, parcel.getAddressByFrom(), new Date());
                                    delivery.setIsAccept(false);
                                    session.save(delivery);

                                    transaction.commit();

                                    response.setMsg_body(parcel);
                                    response.setStatus("success");
                                    return Response.status(Response.Status.OK).entity(response).build();
                                } else {
                                    response.setErr_body("Invalid status found.");
                                    response.setStatus("error");
                                    return Response.status(Response.Status.NOT_ACCEPTABLE).entity(response).build();
                                }
                            } else {
                                response.setErr_body("Invalid Type found.");
                                response.setStatus("error");
                                return Response.status(Response.Status.NOT_ACCEPTABLE).entity(response).build();
                            }
                        } else {
                            response.setErr_body("Invalid address found.");
                            response.setStatus("error");
                            return Response.status(Response.Status.NOT_ACCEPTABLE).entity(response).build();
                        }
                    } else {
                        response.setErr_body("Invalid address found.");
                        response.setStatus("error");
                        return Response.status(Response.Status.NOT_ACCEPTABLE).entity(response).build();
                    }
                } catch (HibernateException e) {
                    response.setErr_body("Something went wrong. please try again.");
                    response.setStatus("error");
                    return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(response).build();
                }
            } else {
                response.setErr_body("Invalid parameters found.");
                response.setStatus("error");
                return Response.status(Response.Status.NOT_ACCEPTABLE).entity(response).build();
            }
        } else {
            response.setErr_body("Invalid access key");
            response.setStatus("error");
            return Response.status(Response.Status.UNAUTHORIZED).entity(response).build();
        }
    }

    //deliver parcel
    @POST
    @Path("{parcelId}/deliver")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deliverParcel(
            @FormParam("key") String key,
            @PathParam("parcelId") int parcelId,
            @FormParam("toAddressId") int toAddressId) {

        BaseResponse response = new BaseResponse();
        PermissionChecker permissionChecker = new PermissionChecker(key);
        if (key != null && permissionChecker.isPermissionGranted()) {
            if (parcelId != 0 && toAddressId != 0) {
                try {

                    Session session = Database.getSession();
                    hibernate.Address addressResult = (hibernate.Address) session.load(hibernate.Address.class, toAddressId);
                    if (addressResult != null) {
                        hibernate.Parcel parcelResult = (hibernate.Parcel) session.load(hibernate.Parcel.class, parcelId);
                        if (parcelResult != null) {

                            Set<hibernate.Delivery> deliveries = parcelResult.getDeliveries();
                            for (Delivery delivery : deliveries) {
                                if (delivery.getDeliveryStatus().getStatus().equals("Delivering")) {

                                    hibernate.DeliveryStatus deliveryStatusResult = (hibernate.DeliveryStatus) session.createCriteria(hibernate.DeliveryStatus.class).add(Restrictions.eq("status", "Delivered")).uniqueResult();

                                    Transaction transaction = session.beginTransaction();

                                    delivery.setAddressByToAddress(addressResult);
                                    delivery.setDeliveryStatus(deliveryStatusResult);
                                    delivery.setToDateTime(new Date());
                                    delivery.setIsAccept(false);

                                    session.update(delivery);
                                    transaction.commit();
                                }
                            }
                            response.setErr_body("Successfully Delivered.");
                            response.setStatus("success");
                            return Response.status(Response.Status.OK).entity(response).build();
                        } else {
                            response.setErr_body("No such parcel found.");
                            response.setStatus("error");
                            return Response.status(Response.Status.NOT_FOUND).entity(response).build();
                        }
                    } else {
                        response.setErr_body("No such address found.");
                        response.setStatus("error");
                        return Response.status(Response.Status.NOT_FOUND).entity(response).build();
                    }

                } catch (HibernateException e) {
                    response.setErr_body("Something went wrong. please try again.");
                    response.setStatus("error");
                    return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(response).build();
                }
            } else {
                response.setErr_body("Invalid parameters found.");
                response.setStatus("error");
                return Response.status(Response.Status.NOT_ACCEPTABLE).entity(response).build();
            }
        } else {
            response.setErr_body("Invalid access key");
            response.setStatus("error");
            return Response.status(Response.Status.UNAUTHORIZED).entity(response).build();
        }
    }

//accept parcel by receiver
    @POST
    @Path("{parcelId}/delivery/accept")
    @Produces(MediaType.APPLICATION_JSON)
    public Response acceptDeliveredParcel(
            @FormParam("key") String key,
            @PathParam("parcelId") int parcelId,
            @FormParam("isCustomer") boolean isCustomer) {

        BaseResponse response = new BaseResponse();
        PermissionChecker permissionChecker = new PermissionChecker(key);
        if (key != null && permissionChecker.isPermissionGranted()) {
            if (parcelId != 0) {
                try {

                    Session session = Database.getSession();

                    hibernate.Parcel parcelResult = (hibernate.Parcel) session.load(hibernate.Parcel.class, parcelId);
                    if (parcelResult != null) {
                        Transaction transaction = session.beginTransaction();
                        Set<hibernate.Delivery> deliveries = parcelResult.getDeliveries();
                        hibernate.Delivery oldDelivery = null;

                        for (Delivery delivery : deliveries) {
                            if (!delivery.getIsAccept()) {
                                oldDelivery = delivery;
                                delivery.setIsAccept(true);
                                session.update(delivery);
                            }
                        }
                        if (oldDelivery != null) {
                            if (!isCustomer) {
                                hibernate.DeliveryStatus pendingDeliveryStatus = (hibernate.DeliveryStatus) session.createCriteria(hibernate.DeliveryStatus.class).add(Restrictions.eq("status", "Delivering")).uniqueResult();
                                Delivery delivery = new hibernate.Delivery(pendingDeliveryStatus, oldDelivery.getParcel(), oldDelivery.getAddressByToAddress(), new Date());
                                delivery.setIsAccept(false);
                                session.save(delivery);
                            }
                            transaction.commit();
                            response.setErr_body("Accepted Delivery");
                            response.setStatus("success");
                            return Response.status(Response.Status.OK).entity(response).build();
                        } else {
                            response.setErr_body("No pending delivery found.");
                            response.setStatus("error");
                            return Response.status(Response.Status.NOT_FOUND).entity(response).build();
                        }

                    } else {
                        response.setErr_body("No such parcel found.");
                        response.setStatus("error");
                        return Response.status(Response.Status.NOT_FOUND).entity(response).build();
                    }
                } catch (HibernateException e) {
                    response.setErr_body("Something went wrong. please try again.");
                    response.setStatus("error");
                    return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(response).build();
                }
            } else {
                response.setErr_body("Invalid parameters found.");
                response.setStatus("error");
                return Response.status(Response.Status.NOT_ACCEPTABLE).entity(response).build();
            }
        } else {
            response.setErr_body("Invalid access key");
            response.setStatus("error");
            return Response.status(Response.Status.UNAUTHORIZED).entity(response).build();
        }
    }

//accept parcel by rider to delivery
//assign parcel to rider
//update location
//delete parcel
}
