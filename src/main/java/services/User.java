/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import contoller.DatabaseConnector;
import contoller.PermissionChecker;
import extention.BaseResponse;
import hibernate.UserRole;
import hibernate.UserRoleHasAction;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.HashSet;
import java.util.Set;
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
import model.UserSearchResults;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import utilities.Database;

@Path("/user")
public class User {

    /**
     *
     * @param key
     * @param name
     * @param role
     * @param email
     * @param vehicle_type
     * @param vehicle_number
     * @param address
     * @param device_model
     * @param device_brand
     * @return
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserList(
            @FormParam("key") String key,
            @FormParam("name") String name,
            @FormParam("role") String role,
            @FormParam("email") String email,
            @FormParam("vehicle_type") String vehicle_type,
            @FormParam("vehicle_number") String vehicle_number,
            @FormParam("address") String address,
            @FormParam("device_model") String device_model,
            @FormParam("device_brand") String device_brand) {

        BaseResponse response = new BaseResponse();
        if (key != null && new PermissionChecker(key).isPermissionGranted()) {
            try {
                boolean isFirstWhereClauseAdded = false;
                boolean isWhereClauseAdded = false;
                String query = "SELECT u.* FROM user_details u ";
                if (vehicle_number != null) {
                    if (!isWhereClauseAdded) {
                        query += "WHERE ";
                        isWhereClauseAdded = true;
                    }
                    if (isFirstWhereClauseAdded) {
                        query += "AND ";
                    }
                    query += "u.vehicle_number LIKE '%" + vehicle_number + "%'";
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
                    query += "u.user_address LIKE '%" + address + "%'";
                    isFirstWhereClauseAdded = true;
                }
                if (device_model != null) {
                    if (!isWhereClauseAdded) {
                        query += "WHERE ";
                        isWhereClauseAdded = true;
                    }
                    if (isFirstWhereClauseAdded) {
                        query += "AND ";
                    }
                    query += "u.device_model LIKE '%" + device_model + "%'";
                    isFirstWhereClauseAdded = true;
                }
                if (device_brand != null) {
                    if (!isWhereClauseAdded) {
                        query += "WHERE ";
                        isWhereClauseAdded = true;
                    }
                    if (isFirstWhereClauseAdded) {
                        query += "AND ";
                    }
                    query += "u.device_brand LIKE '%" + device_brand + "%'";
                    isFirstWhereClauseAdded = true;
                }
                if (vehicle_type != null) {
                    if (!isWhereClauseAdded) {
                        query += "WHERE ";
                        isWhereClauseAdded = true;
                    }
                    if (isFirstWhereClauseAdded) {
                        query += "AND ";
                    }
                    query += "u.vehicle_type LIKE '%" + vehicle_type + "%'";
                    isFirstWhereClauseAdded = true;
                }
                if (role != null) {
                    if (!isWhereClauseAdded) {
                        query += "WHERE ";
                        isWhereClauseAdded = true;
                    }
                    if (isFirstWhereClauseAdded) {
                        query += "AND ";
                    }
                    query += "u.user_role LIKE '%" + role + "%'";
                    isFirstWhereClauseAdded = true;
                }
                if (email != null) {
                    if (!isWhereClauseAdded) {
                        query += "WHERE ";
                        isWhereClauseAdded = true;
                    }
                    if (isFirstWhereClauseAdded) {
                        query += "AND ";
                    }
                    query += "u.email LIKE '%" + email + "%'";
                    isFirstWhereClauseAdded = true;
                }
                if (name != null) {
                    if (!isWhereClauseAdded) {
                        query += "WHERE ";
                        isWhereClauseAdded = true;
                    }
                    if (isFirstWhereClauseAdded) {
                        query += "AND ";
                    }
                    query += "u.`name` LIKE '%" + name + "%'";
                    isFirstWhereClauseAdded = true;
                }

                ResultSet result = new DatabaseConnector().search(query);
                UserSearchResults userModel = new UserSearchResults();
                if (result.next()) {
                    userModel.setId(result.getInt("iduser"));
                    userModel.setName(result.getString("name"));
                    userModel.setStatus(result.getString("user_status"));
                    // userModel.setNic(result.getString("nic"));
                    // userModel.setRole(result.getString("role"));
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

    /**
     *
     * @param key
     * @param user_id
     * @return response
     */
    @GET
    @Path("/{user_id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserById(@FormParam("key") String key, @PathParam("user_id") String user_id) {
        BaseResponse response = new BaseResponse();
        if (key != null && new PermissionChecker(key).isPermissionGranted()) {
            if (user_id != null && "".equals(user_id)) {
                try {
                    String query = "select u.iduser,u.`name`,u.email,u.nic,u.`role`,u.role_status,u.user_status FROM user_login_view u WHERE u.iduser='" + user_id + "'";
                    ResultSet result = new DatabaseConnector().search(query);
                    User userModel = new User();
                    if (result.next()) {
//                        userModel.setId(BigInteger.valueOf(result.getInt("iduser")));
//                        userModel.setName(result.getString("name"));
//                        userModel.setStatus(result.getString("user_status"));
//                        userModel.setNic(result.getString("nic"));
//                        userModel.setEmail(result.getString("email"));
//                        userModel.setRole(result.getString("role"));
//                        userModel.setRole_status(result.getString("role_status"));
                        response.setMsg_body(userModel);

                        response.setMsg_body(response);
                        response.setStatus("success");
                        return Response.status(Response.Status.OK).entity(response).build();
                    } else {
                        response.setErr_body("user not found");
                        response.setStatus("failed");
                        return Response.status(Response.Status.NOT_FOUND).entity(response).build();
                    }
                } catch (SQLException ex) {
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

    /**
     *
     * @param key
     * @param role
     * @param name
     * @param email
     * @param password
     * @return
     */
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response registerUser(
            @FormParam("key") String key,
            @FormParam("role") String role,
            @FormParam("name") String name,
            @FormParam("email") String email,
            @FormParam("password") String password) {
        BaseResponse response = new BaseResponse();

        if (role == null || "".equals(role)) {
            role = "Customer";
        }
        boolean isPermissionGranted = false;
        if ("Customer".equals(role)) {
            isPermissionGranted = true;
        } else if (key != null && new PermissionChecker(key).isPermissionGranted()) {
            isPermissionGranted = true;
        }
        if (isPermissionGranted) {
            if (name != null && !"".equals(name) && email != null && !"".equals(email) && password != null && !"".equals(password)) {
                try {

                    String searchQuery = "SELECT u.iduser_role FROM user_role u WHERE u.`role`='" + role + "'";
                    ResultSet searchResult = new DatabaseConnector().search(searchQuery);
                    if (searchResult.next()) {

                        String userRoleId = searchResult.getString("iduser_role");

                        String statusQuery = "SELECT u.iduser_status FROM user_status u WHERE u.status='Not Activated'";
                        ResultSet statusResult = new DatabaseConnector().search(statusQuery);
                        if (statusResult.next()) {

                            String userStatus = statusResult.getString("iduser_status");

                            String query = "INSERT INTO `user` (`name`,email,username,password,`status`,id_user_role) VALUES ('" + name + "','" + email + "','" + email + "','" + password + "','" + userStatus + "','" + userRoleId + "')";
                            ResultSet result = new DatabaseConnector().insert(query);
                            if (result.next()) {

                                response.setErr_body("User registered. Please activate the user account.");
                                response.setStatus("success");
                                return Response.status(Response.Status.OK).entity(response).build();

                            } else {
                                throw new SQLException("insertion error");
                            }
                        } else {
                            throw new SQLException("insertion error");
                        }

                    } else {
                        response.setErr_body("Invalid user role found.");
                        response.setStatus("error");
                        return Response.status(Response.Status.NOT_ACCEPTABLE).entity(response).build();
                    }
                } catch (SQLIntegrityConstraintViolationException ex) {
                    Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
                    response.setErr_body("Already registered.");
                    response.setStatus("error");
                    return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(response).build();
                } catch (SQLException ex) {
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

    /**
     *
     * @param key
     * @param user_id
     * @param name
     * @return
     */
    @Path("/{user_id}")
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateUser(
            @FormParam("key") String key,
            @PathParam("user_id") String user_id,
            @FormParam("name") String name) {
        BaseResponse response = new BaseResponse();
        if (key != null && new PermissionChecker(key).isPermissionGranted()) {
            if (user_id != null && "".equals(user_id)) {
                try {

                    String query = "UPDATE `user` SET `name`='" + name + "'";
                    boolean result = new DatabaseConnector().update(query);

                    User responseUser = new User();

                    response.setMsg_body(response);
                    response.setStatus("success");
                    return Response.status(Response.Status.OK).entity(response).build();

                } catch (SQLException ex) {
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

    /**
     *
     * @param key
     * @param user_id
     * @return
     */
    @Path("/{user_id}")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteUser(
            @FormParam("key") String key,
            @PathParam("user_id") String user_id) {
        BaseResponse response = new BaseResponse();
        if (key != null && new PermissionChecker(key).isPermissionGranted()) {
            if (user_id != null && "".equals(user_id) && 1 != Integer.parseInt(user_id)) {
                try {

                    String query = "UPDATE `user` SET status=(SELECT iduser_status FROM user_status WHERE status ='Deleted') WHERE iduser='" + user_id + "'";
                    new DatabaseConnector().delete(query);

                    response.setMsg_body("User removed successfully.");
                    response.setStatus("success");
                    return Response.status(Response.Status.OK).entity(response).build();

                } catch (SQLException ex) {
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

    /**
     *
     * @param key
     * @param user_id
     * @return
     */
    @Path("/{user_id}/address")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserAddress(
            @FormParam("key") String key,
            @PathParam("user_id") int user_id) {
        BaseResponse response = new BaseResponse();
        PermissionChecker permissionChecker = new PermissionChecker(key);

        if (key != null && permissionChecker.isPermissionGranted()) {
            if (0 != user_id && 1 != user_id) {
            } else {
                user_id = permissionChecker.getUserId();
            }
            try {

                Session session = Database.getSession();
                hibernate.User user = (hibernate.User) session.load(hibernate.User.class, user_id);
                if (user != null) {
                    Set<hibernate.UserHasAddress> userHasAddresses = user.getUserHasAddresses();
                    if (userHasAddresses.size() > 0) {
                        Set<hibernate.Address> addressList = new HashSet<>();
                        for (hibernate.UserHasAddress userHasAddress : userHasAddresses) {
                            if (userHasAddress.getAddress().getStatus() == 1) {
                                addressList.add(userHasAddress.getAddress());
                            }
                        }
                        response.setMsg_body(addressList);
                        response.setStatus("success");
                        return Response.status(Response.Status.OK).entity(response).build();
                    } else {
                        response.setErr_body("user has no addresses");
                        response.setStatus("error");
                        return Response.status(Response.Status.NOT_FOUND).entity(response).build();
                    }
                } else {
                    response.setErr_body("User not found");
                    response.setStatus("error");
                    return Response.status(Response.Status.NOT_FOUND).entity(response).build();
                }
            } catch (HibernateException ex) {
                Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
                response.setErr_body("Internal server error.");
                response.setStatus("error");
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(response).build();
            }

        } else {
            response.setErr_body("Invalid access key");
            response.setStatus("error");
            return Response.status(Response.Status.UNAUTHORIZED).entity(response).build();
        }
    }

    /**
     *
     * @param key
     * @param user_id
     * @return
     */
    @Path("/{user_id}/device")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserDevices(
            @FormParam("key") String key,
            @PathParam("user_id") int user_id) {
        BaseResponse response = new BaseResponse();
        PermissionChecker permissionChecker = new PermissionChecker(key);

        if (key != null && permissionChecker.isPermissionGranted()) {
            if (0 != user_id && 1 != user_id) {
            } else {
                user_id = permissionChecker.getUserId();
            }
            try {
                Session session = Database.getSession();
                hibernate.User user = (hibernate.User) session.load(hibernate.User.class, user_id);
                if (user != null) {
                    Set<hibernate.UserHasDevice> userHasdevices = user.getUserHasDevices();
                    if (userHasdevices.size() > 0) {
                        Set<hibernate.Device> deviceList = new HashSet<>();
                        for (hibernate.UserHasDevice userHasDevice : userHasdevices) {
                            if (userHasDevice.getStatus() == 1) {
                                deviceList.add(userHasDevice.getDevice());
                            }
                        }
                        response.setMsg_body(deviceList);
                        response.setStatus("success");
                        return Response.status(Response.Status.OK).entity(response).build();
                    } else {
                        response.setErr_body("user has no devices");
                        response.setStatus("error");
                        return Response.status(Response.Status.NOT_FOUND).entity(response).build();
                    }
                } else {
                    response.setErr_body("User not found");
                    response.setStatus("error");
                    return Response.status(Response.Status.NOT_FOUND).entity(response).build();
                }
            } catch (HibernateException ex) {
                Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
                response.setErr_body("Internal server error.");
                response.setStatus("error");
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(response).build();
            }
        } else {
            response.setErr_body("Invalid access key");
            response.setStatus("error");
            return Response.status(Response.Status.UNAUTHORIZED).entity(response).build();
        }
    }

    /**
     *
     * @param key
     * @param user_id
     * @return
     */
    @Path("/{user_id}/vehicle")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserVehicles(
            @FormParam("key") String key,
            @PathParam("user_id") int user_id) {
        BaseResponse response = new BaseResponse();
        PermissionChecker permissionChecker = new PermissionChecker(key);

        if (key != null && permissionChecker.isPermissionGranted()) {
            if (0 != user_id && 1 != user_id) {
            } else {
                user_id = permissionChecker.getUserId();
            }
            try {
                Session session = Database.getSession();
                hibernate.User user = (hibernate.User) session.load(hibernate.User.class, user_id);
                if (user != null) {
                    Set<hibernate.UserHasVehicle> userHasVehicles = user.getUserHasVehicles();
                    if (userHasVehicles.size() > 0) {
                        Set<hibernate.Vehicle> vehicleList = new HashSet<>();
                        for (hibernate.UserHasVehicle userHasVehicle : userHasVehicles) {
                            if (userHasVehicle.getStatus() == 1) {
                                vehicleList.add(userHasVehicle.getVehicle());
                            }
                        }
                        response.setMsg_body(vehicleList);
                        response.setStatus("success");
                        return Response.status(Response.Status.OK).entity(response).build();
                    } else {
                        response.setErr_body("user has no devices");
                        response.setStatus("error");
                        return Response.status(Response.Status.NOT_FOUND).entity(response).build();
                    }
                } else {
                    response.setErr_body("User not found");
                    response.setStatus("error");
                    return Response.status(Response.Status.NOT_FOUND).entity(response).build();
                }
            } catch (HibernateException ex) {
                Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
                response.setErr_body("Internal server error.");
                response.setStatus("error");
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(response).build();
            }
        } else {
            response.setErr_body("Invalid access key");
            response.setStatus("error");
            return Response.status(Response.Status.UNAUTHORIZED).entity(response).build();
        }
    }

    /**
     *
     * @param key
     * @param user_id
     * @return
     */
    @Path("/{user_id}/permission")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserpermission(
            @FormParam("key") String key,
            @PathParam("user_id") int user_id) {
        BaseResponse response = new BaseResponse();
        PermissionChecker permissionChecker = new PermissionChecker(key);

        if (key != null && permissionChecker.isPermissionGranted()) {
            if (0 != user_id && 1 != user_id) {
            } else {
                user_id = permissionChecker.getUserId();
            }
            try {
                Session session = Database.getSession();
                hibernate.User user = (hibernate.User) session.load(hibernate.User.class, user_id);
                if (user != null) {
                    UserRole userRole = user.getUserRole();
                    if (userRole != null && userRole.getStatus() != 0) {
                        Set<hibernate.UserRoleHasAction> userRoleHasActions = userRole.getUserRoleHasActions();
                        if (userRoleHasActions.size() > 0) {
                            Set<hibernate.Action> actionList = new HashSet<>();
                            for (UserRoleHasAction userRoleHasAction : userRoleHasActions) {
                                if (userRoleHasAction.getStatus() == 1) {
                                    actionList.add(userRoleHasAction.getAction());
                                }
                            }
                            response.setMsg_body(actionList);
                            response.setStatus("success");
                            return Response.status(Response.Status.OK).entity(response).build();
                        } else {
                            response.setErr_body("User has no permissions to anything.");
                            response.setStatus("error");
                            return Response.status(Response.Status.UNAUTHORIZED).entity(response).build();
                        }
                    } else {
                        response.setErr_body("User has no permissions to anything.");
                        response.setStatus("error");
                        return Response.status(Response.Status.UNAUTHORIZED).entity(response).build();
                    }
                } else {
                    response.setErr_body("User not found");
                    response.setStatus("error");
                    return Response.status(Response.Status.NOT_FOUND).entity(response).build();
                }
            } catch (HibernateException ex) {
                Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
                response.setErr_body("Internal server error.");
                response.setStatus("error");
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(response).build();
            }
        } else {
            response.setErr_body("Invalid access key");
            response.setStatus("error");
            return Response.status(Response.Status.UNAUTHORIZED).entity(response).build();
        }
    }
}
