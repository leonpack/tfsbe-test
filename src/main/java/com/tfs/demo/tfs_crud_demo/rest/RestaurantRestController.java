package com.tfs.demo.tfs_crud_demo.rest;

import com.tfs.demo.tfs_crud_demo.entity.Restaurant;
import com.tfs.demo.tfs_crud_demo.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class RestaurantRestController {

    private RestaurantService restaurantService;

    @Autowired
    public RestaurantRestController(RestaurantService theRestaurantService){
        restaurantService = theRestaurantService;
    }

    @GetMapping("/restaurants")
    public List<Restaurant> getAllRestaurant(){
        return restaurantService.getAllRestaurants();
    }

    @GetMapping("/restaurants/{restaurantId}")
    public Restaurant getRestaurantById(@PathVariable String restaurantId){
        Restaurant theRestaurant = restaurantService.getRestaurantById(restaurantId);

        if(theRestaurant==null){
            throw new RuntimeException("Restaurant with id - " +restaurantId+" not found!");
        }
        return theRestaurant;
    }

    @PostMapping("/restaurants")
    public String AddNewRestaurant(@RequestBody Restaurant theRestaurant){
        if(!restaurantService.checkDuplicateId(theRestaurant.getRestaurantId())){
            return "Duplicate with id -" +theRestaurant.getRestaurantId() + " has been found!, please try again";
        }
        if(!restaurantService.checkDuplicateName(theRestaurant.getRestaurantName())){
            return "This restaurant name - " +theRestaurant.getRestaurantName() + " has been taken, please try again";
        }
        if(!restaurantService.checkDuplicatePhoneNumber(theRestaurant.getRestaurantNumber())){
            return "This restaurant phone number - " +theRestaurant.getRestaurantNumber() + " has been linked with another restaurant, please try again!";
        }
        restaurantService.saveRestaurant(theRestaurant);
        return "Saved - " +theRestaurant;
    }

    @PutMapping("/restaurants")
    public Restaurant updateRestaurant(@RequestBody Restaurant theRestaurant){
        restaurantService.saveRestaurant(theRestaurant);
        return theRestaurant;
    }

    @DeleteMapping("/restaurants/{restaurantId}")
    public String DisableRestaurant(@PathVariable String restaurantId){
        restaurantService.disableRestaurant(restaurantId);
        return "Disable restaurant with id - "+restaurantId + " completed!";
    }

}