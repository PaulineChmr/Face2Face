package com.imt.face2face;

import java.util.ArrayList;
import java.util.UUID;

public class Customer {
    private String first_name;
    private UUID id;
    private String last_name;
    private ArrayList<Transformation> transformations;

    public Customer(String first_name, String last_name){
        this.first_name = first_name;
        this.last_name = last_name;
        this.id = UUID.randomUUID();
        this.transformations = new ArrayList<Transformation>();
    }
}
