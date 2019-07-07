package com.app.service;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;

import com.app.model.Order;

public class OrderService {

//	private static WebTarget resource = ClientBuilder.newBuilder().build()
//			.target("http://localhost/BoardNoMoreP4/webapi/orders");
	
	public static Order makeOrder(int gid, int quantity, String fname, String lname, String phone_num,
                                      String address, String city, String state, int zip, String delivery,
                                      String credit) 
        {
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
                
		return order;
	}

}
