package org.web.app;
 
import com.app.model.BoardGame;
import com.app.model.Image;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URI;
import java.util.List;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.glassfish.jersey.client.ClientConfig;


@WebServlet("/listing")
public class ListingServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {


        ClientConfig config = new ClientConfig();
        Client client = ClientBuilder.newClient(config);
        WebTarget target = client.target(getBaseURI());
        
        String gameId = request.getParameter("id");


        String jsonResponse =
                target.path("webapi").path("boardgames").path(gameId).
                        request(). //send a request
                        accept(MediaType.APPLICATION_JSON). //specify the media type of the response
                        get(String.class); // use the get method and return the response as a string
        
        //Get json of all images from this gameId
        String imagesJsonResponse =
                target.path("webapi").path("images").path("getGamesImages").path(gameId).
                        request(). //send a request
                        accept(MediaType.APPLICATION_JSON). //specify the media type of the response
                        get(String.class); // use the get method and return the response as a string

        System.out.println(jsonResponse);

        ObjectMapper objectMapper = new ObjectMapper(); // This object is from the jackson library

        PrintWriter out = response.getWriter();
        BoardGame game = objectMapper.readValue(jsonResponse, new TypeReference<BoardGame>(){});
        List<Image> images = objectMapper.readValue(imagesJsonResponse, new TypeReference<List<Image>>(){});
        
        
        
        
        
        out.print("<html><body>");
        
            makeMetaData(out, game);
            makeNavBar(out);
        
            out.print("<div class=gridContainer>");

                makeSlideShow(out, images);
                makeProductInfoContainer(out, game);
                makeFormContainer(out, game);
            
            out.print("</div>");
            
            initializeSlideShow(out);
        
        
        out.print("</body></html>");


    }

    private static URI getBaseURI() {

        //Change the URL here to make the client point to your service.
        return UriBuilder.fromUri("http://localhost:8084/BoardNoMoreP4/").build();
    }
    
   
    
    private static void makeMetaData(PrintWriter out, BoardGame game) throws IOException
    {
        
        out.print("<title>" + game.getName() + " | Board No More</title>\n" +
        "    <meta charset=\"UTF-8\">\n" +
        "    <meta name='viewport' content='width=device-width, initial-scale=1.0'>\n" +
        "    <link rel=\"stylesheet\" href=\"styles.css\">\n" +
        "\n" +
        "    <script src=\"javascripts/slideshow.js\"></script>\n" +
        "    <script type = \"text/JavaScript\" src = \"javascripts/getPlace.js\"> </script>\n" +
        "    <script type = \"text/JavaScript\" src = \"javascripts/getTotal.js\"> </script>\n" +
        "    <script src='lib/d3.js'></script>");
    }
    
    private static void makeNavBar(PrintWriter out) throws IOException
    {
        
        out.print("<div class='topnav'><a href='index.jsp'>Home</a><a href='about.html'>About</a></div>");
    }
    
    private static void makeSlideShow(PrintWriter out, List<Image> images) throws IOException
    {
        
        out.print("<div class=\"slideShowContainer\">");
        
        
            for (Image image: images)
            {
                out.print("<img class='slideshow' src=" + image.getPath() + ">");
            }
        
            out.print("<button class = \"back\" OnClick=\"updateSlide(-1)\">&#10094;</button>");
            out.print("<button class = \"next\" OnClick=\"updateSlide(+1)\">&#10095;</button>");
        
        out.print("</div>");
    }
    
    private static void makeProductInfoContainer(PrintWriter out, BoardGame game) throws IOException
    {
        out.print("<div class = \"productInfoContainer\">\n" +
        "        <h2>\n" +
        "          " + game.getName() + "\n" +
        "        </h2>\n" +
        "\n" +
        "        <table>\n" +
        "          <tr>\n" +
        "            <th>Price</th>\n" +
        "            <th>Players</th>\n" +
        "            <th>Playing Time</th>\n" +
        "            <th>Age</th>\n" +
        "          </tr>\n" +
        "          <tr>\n" +
        "            <td>" + game.getPrice() + "</td>\n" +
        "            <td>" + game.getMinP() + "-" + game.getMaxP() + "</td>\n" +
        "            <td>" + game.getPlay_time() + " Min</td>\n" +
        "            <td>" + game.getAge() + "+</td>\n" +
        "          </tr>\n" +
        "        </table>\n" +
        "\n" +
        "        <p>\n" +
            game.getDescription() + "\n" +
        "        </p>\n" +
        "      </div>");
        
    }
    
    private static void makeFormContainer(PrintWriter out, BoardGame game) throws IOException
    {
        out.print("<div class = \"formContainer\">\n" +
        "        <b>Purchase Below</b>\n" +
        "        <br>\n" +
        "\n" +
        "        <form name=\"purchaseForm\" action=\"orders\"\n" +
        "          method = \"post\">\n" +
        "\n" +
        "          Board Game:\n" +
        "           <option name = \"boardgame\">" + game.getName() + "</option>\n" +
        "\n" +
        "          </select>\n" +
        "          <input name=\"gameId\" type=\"hidden\" value=" + game.getGid() + ">\n" +
        "          <br>\n" +
        "\n" +
        "          Quantity:\n" +
        "          <input type=\"text\" name=\"quantity\" onblur=\"getTotal(this.value)\" required/>\n" +
        "          <br>\n" +
        "\n" +
        "          First Name:\n" +
        "          <input type=\"text\" name=\"firstname\" required/>\n" +
        "          <br>\n" +
        "\n" +
        "          Last Name:\n" +
        "          <input type=\"text\" name=\"lastname\" required/>\n" +
        "          <br>\n" +
        "\n" +
        "          Phone Number:\n" +
        "          <input type=\"tel\" name=\"phone\" pattern=\"[0-9]{3}-[0-9]{3}-[0-9]{4}\"\n" +
        "            placeholder = \"Format: 123-456-7890\" required/>\n" +
        "          <br>\n" +
        "\n" +
        "          Address:\n" +
        "          <input type=\"text\" name=\"address\" placeholder = \"Street address\" required/>\n" +
        "          <br>\n" +
        "\n" +
        "          Zip:\n" +
        "          <input type=\"text\" name=\"zip\" pattern=\"[0-9]{5}\"\n" +
        "            placeholder = \"Format: 12345\" onblur=\"getPlace(this.value)\" required/>\n" +
        "          <br>\n" +
        "\n" +
        "          City:\n" +
        "          <input type=\"text\" name=\"city\" required/>\n" +
        "          <br>\n" +
        "\n" +
        "          State/Province/Region:\n" +
        "          <input type=\"text\" name=\"state\" required/>\n" +
        "          <br>\n" +
        "\n" +
        "          Delivery Option:\n" +
        "          <select name=\"delivery\">\n" +
        "            <option selected value=\"standard\">$5.00 Standard Shipping</option>\n" +
        "            <option value=\"ground\">$3.00 6-day Ground Shipping</option>\n" +
        "            <option value=\"expedited\">$8.00 2-day Expedited Shipping</option>\n" +
        "            <option value=\"overnight\">$12.00 Overnight Shipping</option>\n" +
        "          </select>\n" +
        "          <br>\n" +
        "\n" +
        "          Credit Card Number:\n" +
        "          <input type=\"text\" name=\"credit\" pattern=\"[0-9]{4}-[0-9]{4}-[0-9]{4}-[0-9]{4}\"\n" +
        "            placeholder = \"Format: 1234-5678-9012-3456\" required/>\n" +
        "          <br>\n" +
        "\n" +
        "          <input type=\"submit\" value=\"Purchase\"/>\n" +
        "          <p><b>TOTAL:</b></p>\n" +
        "          <p id=\"total\"></p>\n" +
        "\n" +
        "\n" +
        "        </form>");
        
    }
    
        
     
     private static void initializeSlideShow(PrintWriter out) throws IOException
    {
        out.print("<script>\n" +
        "\n" +
        "        var slideIndex = 0;\n" +
        "        displaySlide(slideIndex);\n" +
        "\n" +
        "    </script>");
    }


}