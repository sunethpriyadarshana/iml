/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
//import utilities.Database;

@Path("/test")
public class test {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response callGetMethod() {
        //hibernate.Address list = (hibernate.Address) Database.getSession().load(hibernate.Address.class,1);
       return Response.status(200).entity("Test API is called").build();
        
	
    }

}
