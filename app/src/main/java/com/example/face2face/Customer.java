package com.example.face2face;

import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

public class Customer {
    public static ArrayList<Customer> customerArrayList = new ArrayList<>();
    public static String CUSTOMER_EDIT_EXTRA = "customerEdit";
    private String first_name;
    private int id;
    private String last_name;
    private ArrayList<Transformation> transformations;
    private Date deleted;

    public Customer(int id, String first_name, String last_name){
        this.first_name = first_name;
        this.last_name = last_name;
        this.deleted = null;
        this.id = id;
        this.transformations = new ArrayList<Transformation>();
    }

    public Customer(int id, String first_name, String last_name, Date deleted){
        this.first_name = first_name;
        this.last_name = last_name;
        this.deleted = deleted;
        this.id = id;
        this.transformations = new ArrayList<Transformation>();
    }

    public int getId() {
        return id;
    }

    public Date getDeleted() {
        return deleted;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public static Customer getCustomerForID(int passedCustomerID) {
        for (Customer customer : customerArrayList){
            if(customer.getId() == passedCustomerID)
                return customer;
        }
        return null;
    }

    public static void setCustomerArrayList(ArrayList<Customer> customerArrayList) {
        Customer.customerArrayList = customerArrayList;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public void setDeleted(Date deleted) {
        this.deleted = deleted;
    }

    public static ArrayList<Customer> nonDeleteCustomers(){
        ArrayList<Customer> nonDeleted = new ArrayList<>();
        for(Customer customer : customerArrayList){
            if(customer.getDeleted() == null){
                nonDeleted.add(customer);
            }
        }
        return nonDeleted;
    }
}

