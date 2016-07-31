package me.roneythomas.inventoryapp;


/**
 * Created by roneythomas on 2016-07-22.
 */
public class Inventory {

    private String name;
    private String quantity;
    private String price;
    private String uri;
    private String phone;

    public Inventory() {

    }

    public Inventory(String name, String quantity, String price, String uri, String phone) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.uri = uri;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
