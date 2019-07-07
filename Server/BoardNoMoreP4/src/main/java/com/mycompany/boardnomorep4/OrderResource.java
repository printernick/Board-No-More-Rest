package com.mycompany.boardnomorep4;

import com.mycompany.boardnomorep4.model.Order;
import com.mycompany.boardnomorep4.service.OrderService;

import java.util.List;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


/**
 * Root resource (exposed at "orders" path)
 */
@Path("/orders")
public class OrderResource {

    /**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     */
    
    
    //This method represents an endpoint with the URL /orders/{id} and a GET request ( Note that {id} is a placeholder for a path parameter)
    @Path("{id}")
    @GET
    @Produces( { MediaType.APPLICATION_JSON }) //This provides only JSON responses
    public Response getOrderById(@PathParam("id") int id/* The {id} placeholder parameter is resolved */) {
        //invokes the DB method which will fetch a order_list item object by id
        Order order = OrderService.getOrderById(id);

        //Respond with a 404 if there is no such Order item for the id provided
        if(order == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        //Respond with a 200 OK if you have a Order object to return as response
        return Response.ok(order).build();
    }
    
    //This method represents an endpoint with the URL /orders and a GET request.
    // Since there is no @PathParam mentioned, the /orders as a relative path and a GET request will invoke this method.
    @GET
    @Produces( { MediaType.APPLICATION_JSON })
    public Response getAllOrders() {
        
        List<Order> orderList = OrderService.getAllOrders();

        if(orderList == null || orderList.isEmpty()) {

        }

        return Response.ok(orderList).build();
    }

    //This method represents an endpoint with the URL /orders and a POST request.
    // Since there is no @PathParam mentioned, the /orders as a relative path and a POST request will invoke this method.
    @POST
    @Consumes({MediaType.APPLICATION_JSON}) //This method accepts a request of the JSON type
    public Response addOrder(Order order) {

        //The Order object here is automatically constructed from the json request. Jersey is so cool!
        if(OrderService.AddOrder(order)) {
            return Response.ok().entity("Order Added Successfully").build();
        }

        // Return an Internal Server error because something wrong happened. This should never be executed
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();


    }

    //Similar to the method above.
    @POST
    @Consumes({MediaType.APPLICATION_FORM_URLENCODED}) //This method accepts form parameters.
    //If you were to send a POST through a form submit, this method would be called.
    public Response addOrder(@FormParam("gid") int gid,
                            @FormParam("quantity") int quantity,
                            @FormParam("fname") String fname,
                            @FormParam("lname") String lname,
                            @FormParam("phone_num") String phone_num,
                            @FormParam("address") String address,
                            @FormParam("city") String city,
                            @FormParam("state") String state,
                            @FormParam("zip") int zip,
                            @FormParam("delivery") String delivery,
                            @FormParam("credit") String credit) {
        Order order = new Order();
        
        order.setGid(gid);
        order.setQuantity(quantity);
        order.setFname(fname);
        order.setLname(lname);
        order.setPhone_num(phone_num);
        order.setAddress(address);
        order.setCity(city);
        order.setState(state);
        order.setZip(zip);
        order.setDelivery(delivery);
        order.setCredit(credit); 

        System.out.println(order);

        if(OrderService.AddOrder(order)) {
            return Response.ok().entity("Order Added Successfully").build();
        }

        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();


    }

    //This method represents a PUT request where the id is provided as a path parameter and the request body is provided in JSON
    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Response updateOrder(@PathParam("id") int id, Order order) {

        // Retrieve the order that you will need to change
        Order retrievedOrder = OrderService.getOrderById(id);

        if(retrievedOrder == null) {
            //If not found then respond with a 404 response.
            return Response.status(Response.Status.NOT_FOUND).
                    entity("We could not find the requested resource").build();
        }

        // This is the order_object retrieved from the json request sent.
        order.setGid(id);        

        //This calls the JDBC method which in turn calls the the UPDATE SQL command
        if(OrderService.updateOrder(order)) {

            return Response.ok().entity(order).build();
        }

        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();


    }

    //This method represents a DELETE request where the id is provided as a path parameter and the request body is provided in JSON
    @DELETE
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_FORM_URLENCODED, MediaType.APPLICATION_JSON})
    public Response deleteOrder(@PathParam("id") int id) {

        //Retrieve the Order_object that you want to delete.
        Order retrievedOrder = OrderService.getOrderById(id);

        if(retrievedOrder == null) {
            //If not found throw a 404
            return Response.status(Response.Status.NOT_FOUND).
                    entity("We could not find the requested resource").build();
        }

        // This calls the JDBC method which in turn calls the DELETE SQL command.
        if(OrderService.deleteOrder(retrievedOrder)) {
            return Response.ok().entity("Order Deleted Successfully").build();
        }

        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();


    }
}
