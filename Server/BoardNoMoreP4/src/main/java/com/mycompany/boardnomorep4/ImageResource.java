package com.mycompany.boardnomorep4;

import com.mycompany.boardnomorep4.model.Image;
import com.mycompany.boardnomorep4.service.ImageService;

import java.util.List;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


/**
 * Root resource (exposed at "images" path)
 */
@Path("/images")
public class ImageResource {

    /**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     */
    
    
    //This method represents an endpoint with the URL /images/{id} and a GET request ( Note that {id} is a placeholder for a path parameter)
    @Path("{id}")
    @GET
    @Produces( { MediaType.APPLICATION_JSON }) //This provides only JSON responses
    public Response getImageById(@PathParam("id") int id/* The {id} placeholder parameter is resolved */) {
        //invokes the DB method which will fetch a image_list item object by id
        Image image = ImageService.getImageById(id);

        //Respond with a 404 if there is no such Image item for the id provided
        if(image == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        //Respond with a 200 OK if you have a Image object to return as response
        return Response.ok(image).build();
    }
    
    //This method represents an endpoint with the URL /images/getFirstImage/{gid} and a GET request 
    //( Note that {id} is a placeholder for a path parameter)
    @Path("/getFirstImage/{gid}")
    @GET
    @Produces( { MediaType.APPLICATION_JSON }) //This provides only JSON responses
    public Response getFirstImageByGid(@PathParam("gid") int gid/* The {gid} placeholder parameter is resolved */) {
        //invokes the DB method which will fetch a image_list item object by id
        Image image = ImageService.getFirstImageByGid(gid);

        //Respond with a 404 if there is no such Image item for the id provided
        if(image == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        //Respond with a 200 OK if you have a Image object to return as response
        return Response.ok(image).build();
    }
    
    //This method represents an endpoint with the URL /images/getGamesImages/{gid} and a GET request 
    //( Note that {gid} is a placeholder for a path parameter)
    @Path("/getGamesImages/{gid}")
    @GET
    @Produces( { MediaType.APPLICATION_JSON }) //This provides only JSON responses
    public Response getAllImagesByGid(@PathParam("gid") int gid/* The {gid} placeholder parameter is resolved */) {
        //invokes the DB method which will fetch a image_list item object by id
        List<Image> images = ImageService.getAllImagesByGid(gid);

        //Respond with a 404 if there is no such Image item for the id provided
        if(images == null || images.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        //Respond with a 200 OK if you have a Image object to return as response
        return Response.ok(images).build();
    }
    
    //This method represents an endpoint with the URL /images and a GET request.
    // Since there is no @PathParam mentioned, the /images as a relative path and a GET request will invoke this method.
    @GET
    @Produces( { MediaType.APPLICATION_JSON })
    public Response getAllImages() {
        
        List<Image> imageList = ImageService.getAllImages();

        if(imageList == null || imageList.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        return Response.ok(imageList).build();
    }

    //This method represents an endpoint with the URL /images and a POST request.
    // Since there is no @PathParam mentioned, the /images as a relative path and a POST request will invoke this method.
    @POST
    @Consumes({MediaType.APPLICATION_JSON}) //This method accepts a request of the JSON type
    public Response addImage(Image image) {

        //The Image object here is automatically constructed from the json request. Jersey is so cool!
        if(ImageService.AddImage(image)) {
            return Response.ok().entity("Image Added Successfully").build();
        }

        // Return an Internal Server error because something wrong happened. This should never be executed
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();


    }

    //Similar to the method above.
    @POST
    @Consumes({MediaType.APPLICATION_FORM_URLENCODED}) //This method accepts form parameters.
    //If you were to send a POST through a form submit, this method would be called.
    public Response addImage(@FormParam("gid") int gid,
                            @FormParam("path") String path) 
    {
        Image image = new Image();
        
        image.setGid(gid);
        image.setPath(path);

        System.out.println(image);

        if(ImageService.AddImage(image)) {
            return Response.ok().entity("Image Added Successfully").build();
        }

        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();


    }

    //This method represents a PUT request where the id is provided as a path parameter and the request body is provided in JSON
    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Response updateImage(@PathParam("id") int id, Image image) {

        // Retrieve the image that you will need to change
        Image retrievedImage = ImageService.getImageById(id);

        if(retrievedImage == null) {
            //If not found then respond with a 404 response.
            return Response.status(Response.Status.NOT_FOUND).
                    entity("We could not find the requested resource").build();
        }

        // This is the image_object retrieved from the json request sent.
        image.setGid(id);        

        //This calls the JDBC method which in turn calls the the UPDATE SQL command
        if(ImageService.updateImage(image)) {

            return Response.ok().entity(image).build();
        }

        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();


    }

    //This method represents a DELETE request where the id is provided as a path parameter and the request body is provided in JSON
    @DELETE
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_FORM_URLENCODED, MediaType.APPLICATION_JSON})
    public Response deleteImage(@PathParam("id") int id) {

        //Retrieve the Image_object that you want to delete.
        Image retrievedImage = ImageService.getImageById(id);

        if(retrievedImage == null) {
            //If not found throw a 404
            return Response.status(Response.Status.NOT_FOUND).
                    entity("We could not find the requested resource").build();
        }

        // This calls the JDBC method which in turn calls the DELETE SQL command.
        if(ImageService.deleteImage(retrievedImage)) {
            return Response.ok().entity("Image Deleted Successfully").build();
        }

        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();


    }
}
