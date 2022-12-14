package com.tfs.demo.tfs_crud_demo.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.aspectj.weaver.ast.Or;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "restaurant")
public class Restaurant {

    @Id
    @Column(name = "restaurant_id")
    private String restaurantId;

    @Column(name = "restaurant_location")
    private String restaurantLocation;

    @Column(name = "restaurant_name")
    private String restaurantName;

    @Column(name = "restaurant_phone_number")
    private String restaurantNumber;

    @OneToMany(mappedBy = "theRestaurant",cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    @JsonManagedReference(value = "restaurant-staff")
    private List<Staff> staffList;

    //TODO need fixes
//    @OneToMany(mappedBy = "theRestaurantOrder",cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
//    @JsonManagedReference(value = "restaurant-order")
//    private List<Order> orderList;

    @Column(name = "status")
    private boolean status;

    public Restaurant(){

    }

    public Restaurant(String restaurantId, String restaurantLocation, String restaurantName, String restaurantNumber, List<Staff> staffList, boolean status) {
        this.restaurantId = restaurantId;
        this.restaurantLocation = restaurantLocation;
        this.restaurantName = restaurantName;
        this.restaurantNumber = restaurantNumber;
        this.staffList = staffList;
        this.status = status;
    }

    public String getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(String restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getRestaurantLocation() {
        return restaurantLocation;
    }

    public void setRestaurantLocation(String restaurantLocation) {
        this.restaurantLocation = restaurantLocation;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public String getRestaurantNumber() {
        return restaurantNumber;
    }

    public void setRestaurantNumber(String restaurantNumber) {
        this.restaurantNumber = restaurantNumber;
    }

    public List<Staff> getStaffList() {
        return staffList;
    }

    public void setStaffList(List<Staff> staffList) {
        this.staffList = staffList;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "restaurantId='" + restaurantId + '\'' +
                ", restaurantLocation='" + restaurantLocation + '\'' +
                ", restaurantName='" + restaurantName + '\'' +
                ", restaurantNumber='" + restaurantNumber + '\'' +
                ", staffList=" + staffList +
//                ", orderList=" + orderList +
                ", status=" + status +
                '}';
    }

    public void addStaff(Staff theStaff){
        if(staffList == null){
            staffList = new ArrayList<>();
        }
        staffList.add(theStaff);
    }

//    public void addOrder(Order theOrder){
//        if(orderList == null){
//            orderList = new ArrayList<>();
//        }
//        orderList.add(theOrder);
//    }

}
