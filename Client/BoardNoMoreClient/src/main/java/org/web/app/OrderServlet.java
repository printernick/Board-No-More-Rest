package org.web.app;
 
import com.app.model.Order;
import com.app.service.OrderService;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URI;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference; 
import org.glassfish.jersey.client.ClientConfig;


@WebServlet("/orders")
public class OrderServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
                String name = request.getParameter("boardgame");
        
                int gid = Integer.valueOf(request.getParameter("gameId"));
                int quantity = Integer.parseInt(request.getParameter("quantity"));
		String fname = request.getParameter("firstname");
		String lname = request.getParameter("lastname");
                String phone_num = request.getParameter("phone");
                String address = request.getParameter("address");
                String city = request.getParameter("city");
                String state = request.getParameter("state");
                int zip = Integer.parseInt(request.getParameter("zip"));
                String delivery = request.getParameter("delivery");
                String credit = request.getParameter("credit");
                
                Order order = OrderService.makeOrder(gid, quantity, fname, lname, phone_num, address, city, state, zip, delivery, credit);
                
                
                ClientConfig config = new ClientConfig();
                Client client = ClientBuilder.newClient(config);
                WebTarget target = client.target(getBaseURI());
                
                
                //add to database
                target.path("webapi").path("orders").request(MediaType.APPLICATION_JSON).post(Entity.entity(order.toString(), MediaType.APPLICATION_JSON));
                                
                PrintWriter out = response.getWriter();
                
                out.print("<html><body>");
        
                    makeMetaData(out);
                    makeNavBar(out);
                    makeHeaderConfirmation(out);
                    makeDetails(out, name, quantity, fname, lname, phone_num, address, city, state, zip, delivery);

                out.print("</body></html>");

                
                
	}

    private static URI getBaseURI() {

        //Change the URL here to make the client point to your service.
        return UriBuilder.fromUri("http://localhost:8084/BoardNoMoreP4/").build();
    }
    
   
    
    private static void makeMetaData(PrintWriter out) throws IOException
    {
        
        out.print("<title>Order Confirmation | Board No More</title>\n" +
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
    
    private static void makeHeaderConfirmation(PrintWriter out) throws IOException
    {
        
        out.print("<h1>Board No More Confirmation Page</h1>\n" +
        "\n" +
        "  <p>\n" +
        "    Thank you for shopping with Board No More!\n" +
        "    We have received your order and hope you find\n" +
        "    everything satisfactory.\n" +
        "    <br><br>\n" +
        "    You can find the details of your order below.\n" +
        "\n" +
        "  </p>");
    }
    
    private static void makeDetails(PrintWriter out, String name, int quantity, String fname, String lname, String phone_num,
                                      String address, String city, String state, int zip, String delivery) throws IOException
    {
        
        out.print("Board Game: " + name + "<br><br>" +
                "Quantity: " + quantity + "<br><br>" +
                "First name: " + fname + "<br><br>" +
                "Last name: " + lname + "<br><br>" +
                "Phone Number: " + phone_num + "<br><br>" +
                "Address: " + address + "<br><br>" +
                "City: " + city + "<br><br>" +
                "State: " + state + "<br><br>" +
                "Zip: " + zip + "<br><br>" +
                "Delivery: " + delivery);
    }
    
    
    
    

}