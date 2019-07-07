package com.mycompany.boardnomorep4;

import com.mycompany.boardnomorep4.model.BoardGame;
import com.mycompany.boardnomorep4.service.BoardGameService;

import java.util.List;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


/**
 * Root resource (exposed at "boardgames" path)
 */
@Path("/boardgames")
public class BoardGameResource {

    /**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     */
    
    
    //This method represents an endpoint with the URL /boardGames/{id} and a GET request ( Note that {id} is a placeholder for a path parameter)
    @Path("{id}")
    @GET
    @Produces( { MediaType.APPLICATION_JSON }) //This provides only JSON responses
    public Response getBoardGameById(@PathParam("id") int id/* The {id} placeholder parameter is resolved */) {
        //invokes the DB method which will fetch a boardGame_list item object by id
        BoardGame boardGame = BoardGameService.getBoardGameById(id);

        //Respond with a 404 if there is no such BoardGame item for the id provided
        if(boardGame == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        //Respond with a 200 OK if you have a BoardGame object to return as response
        return Response.ok(boardGame).build();
    }
    
    //This method represents an endpoint with the URL /boardGames and a GET request.
    // Since there is no @PathParam mentioned, the /boardGames as a relative path and a GET request will invoke this method.
    @GET
    @Produces( { MediaType.APPLICATION_JSON })
    public Response getAllBoardGames() {
        
        List<BoardGame> boardGameList = BoardGameService.getAllBoardGames();

        if(boardGameList == null || boardGameList.isEmpty()) {

        }

        return Response.ok(boardGameList).build();
    }

    //This method represents an endpoint with the URL /boardGames and a POST request.
    // Since there is no @PathParam mentioned, the /boardGames as a relative path and a POST request will invoke this method.
    @POST
    @Consumes({MediaType.APPLICATION_JSON}) //This method accepts a request of the JSON type
    public Response addGame(BoardGame game) {

        //The BoardGame object here is automatically constructed from the json request. Jersey is so cool!
        if(BoardGameService.AddGame(game)) {
            return Response.ok().entity("BoardGame Added Successfully").build();
        }

        // Return an Internal Server error because something wrong happened. This should never be executed
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();


    }

    //Similar to the method above.
    @POST
    @Consumes({MediaType.APPLICATION_FORM_URLENCODED}) //This method accepts form parameters.
    //If you were to send a POST through a form submit, this method would be called.
    public Response addGame(@FormParam("name") String name,
                            @FormParam("price") int price,
                            @FormParam("minP") int minP,
                            @FormParam("maxP") int maxP,
                            @FormParam("play_time") int play_time,
                            @FormParam("age") int age,
                            @FormParam("description") String description) {
        BoardGame boardGame = new BoardGame();
        
        boardGame.setName(name);
        boardGame.setPrice(price);
        boardGame.setMinP(minP);
        boardGame.setMaxP(maxP);
        boardGame.setPlay_time(play_time);
        boardGame.setAge(age);
        boardGame.setDescription(description); 

        System.out.println(boardGame);

        if(BoardGameService.AddGame(boardGame)) {
            return Response.ok().entity("BoardGame Added Successfully").build();
        }

        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();


    }

    //This method represents a PUT request where the id is provided as a path parameter and the request body is provided in JSON
    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Response updateGame(@PathParam("id") int id, BoardGame boardGame) {

        // Retrieve the game that you will need to change
        BoardGame retrievedGame = BoardGameService.getBoardGameById(id);

        if(retrievedGame == null) {
            //If not found then respond with a 404 response.
            return Response.status(Response.Status.NOT_FOUND).
                    entity("We could not find the requested resource").build();
        }

        // This is the boardGame_object retrieved from the json request sent.
        boardGame.setGid(id);        

        //This calls the JDBC method which in turn calls the the UPDATE SQL command
        if(BoardGameService.updateGame(boardGame)) {

            return Response.ok().entity(boardGame).build();
        }

        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();


    }

    //This method represents a DELETE request where the id is provided as a path parameter and the request body is provided in JSON
    @DELETE
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_FORM_URLENCODED, MediaType.APPLICATION_JSON})
    public Response deleteGame(@PathParam("id") int id) {

        //Retrieve the BoardGame_object that you want to delete.
        BoardGame retrievedGame = BoardGameService.getBoardGameById(id);

        if(retrievedGame == null) {
            //If not found throw a 404
            return Response.status(Response.Status.NOT_FOUND).
                    entity("We could not find the requested resource").build();
        }

        // This calls the JDBC method which in turn calls the DELETE SQL command.
        if(BoardGameService.deleteGame(retrievedGame)) {
            return Response.ok().entity("BoardGame Deleted Successfully").build();
        }

        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();


    }
}
