package org.web.app;

 
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference; 
import org.glassfish.jersey.client.ClientConfig;

import com.app.model.BoardGame;
import com.app.model.Image;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URI;
import java.util.List;
import javax.servlet.annotation.WebServlet;


@WebServlet("/allgames")
public class AllGames extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {


        ClientConfig config = new ClientConfig();

        Client client = ClientBuilder.newClient(config);

        WebTarget target = client.target(getBaseURI());


        String jsonResponse =
                target.path("webapi").path("boardgames").
                        request(). //send a request
                        accept(MediaType.APPLICATION_JSON). //specify the media type of the response
                        get(String.class); // use the get method and return the response as a string

        System.out.println(jsonResponse);

        ObjectMapper objectMapper = new ObjectMapper(); // This object is from the jackson library

        List<BoardGame> gameList = objectMapper.readValue(jsonResponse, new TypeReference<List<BoardGame>>(){});

        PrintWriter out = response.getWriter();

        for(BoardGame game : gameList) 
        {
            out.print("<tr>");
            out.print("<td><a href=listing?id=" + game.getGid() + ">" + game.getName() + "</a></td>");
            out.print("<td>" + game.getPrice() + "</td>");
            out.print("<td>" + game.getMinP() + "-" + game.getMaxP() + "</td>");
            out.print("<td>" + game.getPlay_time() + " minutes</td>");
            out.print("<td>" + game.getAge() + "+</td>");
            
            //send request for first image of this game
            String imageJsonResponse =
                target.path("webapi").path("images").path("getFirstImage").path(String.valueOf(game.getGid())).
                        request(). //send a request
                        accept(MediaType.APPLICATION_JSON). //specify the media type of the response
                        get(String.class); // use the get method and return the response as a string
            
            Image image = objectMapper.readValue(imageJsonResponse, new TypeReference<Image>(){});
            
            
            out.print("<td><a href=listing?id=" + game.getGid() + "><img class=\"tableImage\" src = \"" + image.getPath() + 
                    "\" alt = \"" + game.getName() + 
                    "\" OnMouseOver = \"previewImage(this)\"" +  
                    "OnMouseOut = \"revertImage(this)\"" + "</a></td");

            out.print("</tr>");
        }


    }

    private static URI getBaseURI() {

        //Change the URL here to make the client point to your service.
        return UriBuilder.fromUri("http://localhost:8084/BoardNoMoreP4/").build();
    }


}