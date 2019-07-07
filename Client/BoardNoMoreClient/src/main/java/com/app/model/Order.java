package com.app.model;


import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

//You will need to create a Java Object. Jersey uses these to contruct requests and responses.

@XmlRootElement
public class Order {
    @XmlElement
    private int oid;
    
    @XmlElement
    private int gid;
    
    @XmlElement
    private int quantity;
    
    @XmlElement
    private String fname;
    
    @XmlElement
    private String lname;
    
    @XmlElement
    private String phone_num;
    
    @XmlElement
    private String address;
    
    @XmlElement
    private String city;
    
    @XmlElement
    private String state;
    
    @XmlElement
    private int zip;
    
    @XmlElement
    private String delivery;
    
    @XmlElement
    private String credit;

    /**
     * @return the oid
     */
    public int getOid() {
        return oid;
    }

    /**
     * @param oid the oid to set
     */
    public void setOid(int oid) {
        this.oid = oid;
    }

    /**
     * @return the gid
     */
    public int getGid() {
        return gid;
    }

    /**
     * @param gid the gid to set
     */
    public void setGid(int gid) {
        this.gid = gid;
    }

    /**
     * @return the quantity
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * @param quantity the quantity to set
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * @return the fname
     */
    public String getFname() {
        return fname;
    }

    /**
     * @param fname the fname to set
     */
    public void setFname(String fname) {
        this.fname = fname;
    }

    /**
     * @return the lname
     */
    public String getLname() {
        return lname;
    }

    /**
     * @param lname the lname to set
     */
    public void setLname(String lname) {
        this.lname = lname;
    }

    /**
     * @return the phone_num
     */
    public String getPhone_num() {
        return phone_num;
    }

    /**
     * @param phone_num the phone_num to set
     */
    public void setPhone_num(String phone_num) {
        this.phone_num = phone_num;
    }

    /**
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return the city
     */
    public String getCity() {
        return city;
    }

    /**
     * @param city the city to set
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * @return the state
     */
    public String getState() {
        return state;
    }

    /**
     * @param state the state to set
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * @return the zip
     */
    public int getZip() {
        return zip;
    }

    /**
     * @param zip the zip to set
     */
    public void setZip(int zip) {
        this.zip = zip;
    }

    /**
     * @return the delivery
     */
    public String getDelivery() {
        return delivery;
    }

    /**
     * @param delivery the delivery to set
     */
    public void setDelivery(String delivery) {
        this.delivery = delivery;
    }

    /**
     * @return the credit
     */
    public String getCredit() {
        return credit;
    }

    /**
     * @param credit the credit to set
     */
    public void setCredit(String credit) {
        this.credit = credit;
    }
    
    @Override
    public String toString() 
    {
        
        return "{\"gid\": \"" + gid + "\", \"quantity\": \"" + quantity +
        "\", \"fname\": \"" + fname + "\", \"lname\": \"" + lname + "\", \"phone_num\": \"" + phone_num +
        "\", \"address\": \"" + address + "\", \"city\": \"" + city + "\", \"state\": \"" + state +
        "\", \"zip\": \"" + zip + "\", \"delivery\": \"" + delivery + "\", \"credit\": \"" + credit
         + "\"}";
//        String orderId = null;
//        if (oid != 0)
//        {
//            orderId = String.valueOf(oid);
//        }
//        return \"{oid=\" + orderId + \", gid=\" + gid + \", quantity=\" + quantity +
//                \", fname=\" + fname + \", lname=\" + lname + \", phone_num=\" + phone_num +
//                \", address=\" + address + \", city=\" + city + \", state=\" + state +
//                \", zip=\" + zip + \", delivery=\" + delivery + \", credit=\" + credit
//                 + \"}\";
    }
   
}