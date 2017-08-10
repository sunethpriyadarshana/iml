/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import contoller.DatabaseConnector;
import contoller.KeyGenarator;
import contoller.PermissionChecker;
import extention.BaseResponse;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import model.LoginResponse;

@Path("/login")
public class Login {

    /**
     *
     * @param username
     * @param password
     * @return
     */
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(@FormParam("username") String username, @FormParam("password") String password) {
        BaseResponse response = new BaseResponse();
        if (username != null && !username.equals("") && !username.isEmpty() && password != null && !password.equals("") && !password.isEmpty() && password.length() > 7) {
            try {
                String query = "SELECT * FROM user_login_view WHERE username='" + username + "' AND password='" + password + "'";
                ResultSet result = new DatabaseConnector().search(query);
                LoginResponse loginResponse = new LoginResponse();
                if (result.next()) {

                    if ("active".equals(result.getString("user_status").toLowerCase())) {

                        String apiKey = new KeyGenarator().getApiKey(result.getInt("iduser") + new Date().toString());

                        String updateQuery = "Update `user` u SET u.api_key='" + apiKey + "' WHERE u.iduser='" + result.getString("iduser") + "'";
                        new DatabaseConnector().update(updateQuery);

                        loginResponse.setUser_id(result.getInt("iduser"));
                        loginResponse.setToken(apiKey);
                        loginResponse.setEmail(result.getString("name"));
                        loginResponse.setEmail(result.getString("email"));
                        loginResponse.setNic(result.getString("nic"));
                        loginResponse.setRole(result.getString("role"));
                        loginResponse.setStatus(result.getString("user_status"));

                        response.setMsg_body(loginResponse);
                        response.setStatus("success");
                        return Response.status(Response.Status.OK).entity(response).build();
                    } else {
                        response.setMsg_body("User " + result.getString("user_status").toLowerCase());
                        response.setStatus("error");
                        return Response.status(Response.Status.OK).entity(response).build();
                    }
                } else {

                    response.setErr_body("User not found");
                    response.setStatus("error");
                    return Response.status(Response.Status.NOT_FOUND).entity(response).build();

                }
            } catch (SQLException ex) {
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, "Sql Exception.. database connection failed", ex);
                response.setErr_body("Something went wrong.Please try again.");
                response.setStatus("error");
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(response).build();
            }
        } else {
            response.setErr_body("No valid parameters found.");
            response.setStatus("error");
            return Response.status(Response.Status.NOT_ACCEPTABLE).entity(response).build();
        }
    }

    /**
     *
     * @param email
     * @return 
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response resetPasswordRequest(@FormParam("email") String email) {
        BaseResponse response = new BaseResponse();
        if (email != null && !email.equals("") && !email.isEmpty()) {
            try {
                String query = "UPDATE `user` SET api_key='' ,reset_token='',status=(SELECT iduser_status FROM user_status WHERE status='Password Reseted') WHERE email='" + email + "'";
                boolean update = new DatabaseConnector().update(query);
                if (update) {
                    response.setMsg_body("Please check your email for reset code.");
                    response.setStatus("success");
                    return Response.status(Response.Status.OK).entity(response).build();
                } else {
                    throw new SQLException();
                }
            } catch (SQLException ex) {
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, "Sql Exception.. database connection failed", ex);
                response.setErr_body("Something went wrong.Please try again.");
                response.setStatus("error");
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(response).build();
            }
        } else {
            response.setErr_body("No valid parameters found.");
            response.setStatus("error");
            return Response.status(Response.Status.NOT_ACCEPTABLE).entity(response).build();
        }
    }

    /**
     *
     * @param resetCode
     * @param password
     * @return UserModel
     */
    @PUT
    @Path("/{userId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response resetPassword(@FormParam("resetCode") String resetCode, @FormParam("password") String password) {

        BaseResponse response = new BaseResponse();
        if (resetCode != null && !resetCode.equals("") && !resetCode.isEmpty() && password != null && !password.equals("") && !password.isEmpty() && password.length() > 7) {
            try {
                String query = "UPDATE `user` SET password='" + password + "',reset_token='' WHERE reset_token='" + resetCode + "'";
                boolean update = new DatabaseConnector().update(query);
                if (update) {
                    response.setMsg_body("password resetted.");
                    response.setStatus("success");
                    return Response.status(Response.Status.OK).entity(response).build();
                } else {
                    response.setErr_body("User not found");
                    response.setStatus("error");
                    return Response.status(Response.Status.NOT_FOUND).entity(response).build();
                }
            } catch (SQLException ex) {
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, "Sql Exception.. database connection failed", ex);
                response.setErr_body("Something went wrong.Please try again.");
                response.setStatus("error");
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(response).build();
            }
        } else {
            response.setErr_body("No valid parameters found.");
            response.setStatus("error");
            return Response.status(Response.Status.NOT_ACCEPTABLE).entity(response).build();
        }
    }

    /**
     *
     * @param key
     * @param password
     * @return
     */
    @PUT
    @Path("/change/{userId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response changePassword(@FormParam("key") String key, @FormParam("password") String password) {
        BaseResponse response = new BaseResponse();
        PermissionChecker permissionChecker = new PermissionChecker(key);
        if (key != null && permissionChecker.isPermissionGranted()) {
            if (password != null && !password.equals("") && !password.isEmpty() && password.length() > 7) {
                try {
                    String query = "UPDATE `user` SET password='" + password + "', api_key ='' WHERE iduser='" + permissionChecker.getUserId() + "'";
                    boolean update = new DatabaseConnector().update(query);
                    if (update) {
                        response.setMsg_body("password resetted.");
                        response.setStatus("success");
                        return Response.status(Response.Status.OK).entity(response).build();
                    } else {
                        response.setErr_body("User not found");
                        response.setStatus("error");
                        return Response.status(Response.Status.NOT_FOUND).entity(response).build();
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(Login.class.getName()).log(Level.SEVERE, "Sql Exception.. database connection failed", ex);
                    response.setErr_body("Something went wrong.Please try again.");
                    response.setStatus("error");
                    return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(response).build();
                }
            } else {
                response.setErr_body("No valid parameters found.");
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
