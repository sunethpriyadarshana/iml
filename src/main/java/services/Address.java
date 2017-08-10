/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import contoller.DatabaseConnector;
import contoller.PermissionChecker;
import extention.BaseResponse;
import hibernate.Town;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import utilities.Database;

@Path("/address")
public class Address {

    /**
     *
     * @param key
     * @param isDefault
     * @param address
     * @param contactName
     * @param contactNumber
     * @param town
     * @param zone
     * @param branch
     * @return
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAddressList(
            @FormParam("key") String key,
            @FormParam("is_default") boolean isDefault,
            @FormParam("address") String address,
            @FormParam("contact_name") String contactName,
            @FormParam("contact_number") String contactNumber,
            @FormParam("town") String town,
            @FormParam("zone") String zone,
            @FormParam("branch") String branch) {

        BaseResponse response = new BaseResponse();
        if (key != null && new PermissionChecker(key).isPermissionGranted()) {
            try {
                boolean isFirstWhereClauseAdded = false;
                boolean isWhereClauseAdded = false;
                String query = "SELECT * FROM user_has_address_view ";
                if (isDefault) {
                    if (!isWhereClauseAdded) {
                        query += "WHERE ";
                        isWhereClauseAdded = true;
                    }
                    if (isFirstWhereClauseAdded) {
                        query += "AND ";
                    }
                    query += "is_default LIKE '%" + isDefault + "%'";
                    isFirstWhereClauseAdded = true;
                }
                if (address != null) {
                    if (!isWhereClauseAdded) {
                        query += "WHERE ";
                        isWhereClauseAdded = true;
                    }
                    if (isFirstWhereClauseAdded) {
                        query += "AND ";
                    }
                    query += "address LIKE '%" + address + "%'";
                    isFirstWhereClauseAdded = true;
                }
                if (contactName != null) {
                    if (!isWhereClauseAdded) {
                        query += "WHERE ";
                        isWhereClauseAdded = true;
                    }
                    if (isFirstWhereClauseAdded) {
                        query += "AND ";
                    }
                    query += "contact_name LIKE '%" + contactName + "%'";
                    isFirstWhereClauseAdded = true;
                }
                if (contactNumber != null) {
                    if (!isWhereClauseAdded) {
                        query += "WHERE ";
                        isWhereClauseAdded = true;
                    }
                    if (isFirstWhereClauseAdded) {
                        query += "AND ";
                    }
                    query += "contact_number LIKE '%" + contactNumber + "%'";
                    isFirstWhereClauseAdded = true;
                }
                if (town != null) {
                    if (!isWhereClauseAdded) {
                        query += "WHERE ";
                        isWhereClauseAdded = true;
                    }
                    if (isFirstWhereClauseAdded) {
                        query += "AND ";
                    }
                    query += "town_name LIKE '%" + town + "%'";
                    isFirstWhereClauseAdded = true;
                }
                if (zone != null) {
                    if (!isWhereClauseAdded) {
                        query += "WHERE ";
                        isWhereClauseAdded = true;
                    }
                    if (isFirstWhereClauseAdded) {
                        query += "AND ";
                    }
                    query += "`zone` LIKE '%" + zone + "%'";
                    isFirstWhereClauseAdded = true;
                }
                if (branch != null) {
                    if (!isWhereClauseAdded) {
                        query += "WHERE ";
                        isWhereClauseAdded = true;
                    }
                    if (isFirstWhereClauseAdded) {
                        query += "AND ";
                    }
                    query += "branch LIKE '%" + branch + "%'";
                    isFirstWhereClauseAdded = true;
                }

                ResultSet result = new DatabaseConnector().search(query);
                model.User userModel = new model.User();
                if (result.next()) {

                    response.setMsg_body(userModel);
                    response.setStatus("success");
                    return Response.status(Response.Status.OK).entity(response).build();
                } else {
                    response.setMsg_body("No results found");
                    response.setStatus("error");
                    return Response.status(Response.Status.NOT_FOUND).entity(response).build();
                }
            } catch (SQLException ex) {
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
                response.setErr_body(ex);
                response.setStatus("error");
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(response).build();
            }
        } else {
            response.setErr_body("Invalid access key");
            response.setStatus("error");
            return Response.status(Response.Status.UNAUTHORIZED).entity(response).build();
        }
    }

    //add new address
    /**
     *
     * @param key
     * @param userId
     * @param isDefault
     * @param name
     * @param townName
     * @param address
     * @param latitude
     * @param longitude
     * @param contact
     * @return
     */
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response addNewAddress(
            @FormParam("key") String key,
            @FormParam("user_id") int userId,
            @FormParam("isDefault") boolean isDefault,
            @FormParam("name") String name,
            @FormParam("town") String townName,
            @FormParam("address") String address,
            @FormParam("latitude") long latitude,
            @FormParam("longitude") long longitude,
            @FormParam("contact") String contact) {

        BaseResponse response = new BaseResponse();
        PermissionChecker permissionChecker = new PermissionChecker(key);
        if (key != null && permissionChecker.isPermissionGranted()) {
            if (userId == 0) {
                userId = permissionChecker.getUserId();
            }
            if (name != null && !"".equals(name) && address != null && !"".equals(address) && contact != null && !"".equals(contact)) {
                try {

                    Session session = Database.getSession();
                    Transaction transaction = session.beginTransaction();

                    hibernate.User user = (hibernate.User) session.load(hibernate.User.class, userId);
                    if (user != null) {

                        hibernate.Town town = (hibernate.Town) session.createCriteria(hibernate.Town.class).add(Restrictions.eq("name", townName)).uniqueResult();
                        if (town != null) {

                            hibernate.Address addressResult = (hibernate.Address) session.createCriteria(hibernate.Address.class).add(Restrictions.eq("address", address)).uniqueResult();
                            if (addressResult == null) {

                                addressResult = new hibernate.Address(town, name, address, 1, contact);
                                session.save(addressResult);

                            }

                            hibernate.UserHasAddress userHasAddressResult = (hibernate.UserHasAddress) session.createCriteria(hibernate.UserHasAddress.class).add(Restrictions.eq("user", user)).add(Restrictions.eq("address", addressResult)).uniqueResult();
                            if (userHasAddressResult != null) {
                                userHasAddressResult.setDefault_(isDefault);
                            } else {
                                userHasAddressResult = new hibernate.UserHasAddress(user, addressResult, isDefault);
                                session.save(userHasAddressResult);
                            }

                            transaction.commit();

                            //create response object
                            model.Address responseObject = new model.Address();

                            model.Address.Town responseTown = new model.Address().getTown();
                            responseTown.setName(town.getName());
                            responseTown.setTiwnId(town.getIdtown());
                            responseTown.setStatus(town.getStatus());
                            responseTown.setLatitiude(Long.parseLong(town.getLatitude()));
                            responseTown.setLongitude(Long.parseLong(town.getLongitude()));

                            model.Address.User responseUser = new model.Address().getUser();
                            responseUser.setEmail(user.getEmail());
                            responseUser.setName(user.getName());
                            responseUser.setNic(user.getNic());
                            responseUser.setUserId(user.getIduser());

                            responseObject.setAddress(addressResult.getAddress());
                            responseObject.setId(addressResult.getIdaddress());
                            responseObject.setContact_number(addressResult.getContactNumber());
                            responseObject.setBranch(addressResult.getTown().getZone().getBranch().getBranch());
                            responseObject.setName(addressResult.getName());
                            responseObject.setTown(responseTown);
                            responseObject.setUser(responseUser);
                            responseObject.setZone(addressResult.getTown().getZone().getZone());

                            response.setMsg_body(responseObject);
                            response.setStatus("success");
                            return Response.status(Response.Status.OK).entity(response).build();

                        } else {
                            response.setMsg_body("No such city found.");
                            response.setStatus("error");
                            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(response).build();
                        }

                    } else {
                        response.setMsg_body("No such user found.");
                        response.setStatus("error");
                        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(response).build();
                    }

                } catch (HibernateException ex) {
                    Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
                    response.setErr_body("Internal server error.");
                    response.setStatus("error");
                    return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(response).build();
                }

            } else {
                response.setErr_body("Invalid parameter found");
                response.setStatus("error");
                return Response.status(Response.Status.NOT_ACCEPTABLE).entity(response).build();
            }

        } else {
            response.setErr_body("Invalid access key");
            response.setStatus("error");
            return Response.status(Response.Status.UNAUTHORIZED).entity(response).build();
        }
    }

    //find address
    /**
     *
     * @param key
     * @param addressId
     * @return
     */
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAddress(
            @FormParam("key") String key,
            @PathParam("id") int addressId) {

        BaseResponse response = new BaseResponse();
        PermissionChecker permissionChecker = new PermissionChecker(key);
        if (key != null && permissionChecker.isPermissionGranted()) {

            if (addressId != 0) {
                Session session = Database.getSession();
                hibernate.Address addressResult = (hibernate.Address) session.load(hibernate.Address.class, addressId);
                if (addressResult != null) {

                    Town town = addressResult.getTown();

                    //create response object
                    model.Address responseObject = new model.Address();

                    model.Address.Town responseTown = new model.Address().getTown();

                    responseTown.setName(town.getName());
                    responseTown.setTiwnId(town.getIdtown());
                    responseTown.setStatus(town.getStatus());
                    responseTown.setLatitiude(Long.parseLong(town.getLatitude()));
                    responseTown.setLongitude(Long.parseLong(town.getLongitude()));

                    responseObject.setAddress(addressResult.getAddress());
                    responseObject.setId(addressResult.getIdaddress());
                    responseObject.setContact_number(addressResult.getContactNumber());
                    responseObject.setBranch(addressResult.getTown().getZone().getBranch().getBranch());
                    responseObject.setName(addressResult.getName());
                    responseObject.setTown(responseTown);
                    responseObject.setZone(addressResult.getTown().getZone().getZone());

                    response.setErr_body(responseObject);
                    response.setStatus("success");
                    return Response.status(Response.Status.OK).entity(response).build();
                } else {
                    response.setErr_body("No such address found.");
                    response.setStatus("error");
                    return Response.status(Response.Status.NOT_FOUND).entity(response).build();
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

    //udate exsisting address
    /**
     *
     * @param key
     * @param addressId
     * @param address
     * @param contact
     * @param isDefault
     * @param latitude
     * @param longitude
     * @param townName
     * @return
     */
    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateAddress(
            @FormParam("key") String key,
            @PathParam("id") int addressId,
            @FormParam("address") int address,
            @FormParam("contact") int contact,
            @FormParam("isDefalut") boolean isDefault,
            @FormParam("latitude") long latitude,
            @FormParam("longitude") long longitude,
            @FormParam("town") String townName) {

        BaseResponse response = new BaseResponse();
        PermissionChecker permissionChecker = new PermissionChecker(key);
        if (key != null && permissionChecker.isPermissionGranted()) {

            Session session = Database.getSession();
            Transaction transaction = session.beginTransaction();
            hibernate.Address addressresult = (hibernate.Address) session.load(hibernate.Address.class, addressId);
            if (addressresult != null) {

                addressresult.setStatus(0);
                session.save(addressresult);
                transaction.commit();

                response.setErr_body("address deleted.");
                response.setStatus("success");
                return Response.status(Response.Status.OK).entity(response).build();

            } else {
                response.setErr_body("Invalid address");
                response.setStatus("error");
                return Response.status(Response.Status.NOT_ACCEPTABLE).entity(response).build();
            }
        } else {
            response.setErr_body("Invalid access key");
            response.setStatus("error");
            return Response.status(Response.Status.UNAUTHORIZED).entity(response).build();
        }
    }

    //delete address
    /**
     *
     * @param key
     * @param addressId
     * @return
     */
    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteAddress(
            @FormParam("key") String key,
            @PathParam("id") int addressId) {

        BaseResponse response = new BaseResponse();
        PermissionChecker permissionChecker = new PermissionChecker(key);
        if (key != null && permissionChecker.isPermissionGranted()) {

            Session session = Database.getSession();
            Transaction transaction = session.beginTransaction();
            hibernate.Address address = (hibernate.Address) session.load(hibernate.Address.class, addressId);
            if (address != null) {

                address.setStatus(0);
                session.save(address);
                transaction.commit();

                response.setErr_body("address deleted.");
                response.setStatus("success");
                return Response.status(Response.Status.OK).entity(response).build();

            } else {
                response.setErr_body("Invalid address");
                response.setStatus("error");
                return Response.status(Response.Status.NOT_ACCEPTABLE).entity(response).build();
            }
        } else {
            response.setErr_body("Invalid access key");
            response.setStatus("error");
            return Response.status(Response.Status.UNAUTHORIZED).entity(response).build();
        }
    }
}
