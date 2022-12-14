package com.tfs.demo.tfs_crud_demo.rest;

import com.tfs.demo.tfs_crud_demo.entity.Food;
import com.tfs.demo.tfs_crud_demo.entity.Region;
import com.tfs.demo.tfs_crud_demo.service.FoodService;
import com.tfs.demo.tfs_crud_demo.service.RegionService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class RegionRestController {
    private RegionService regionService;
    private FoodService foodService;

    @Autowired
    public RegionRestController(RegionService theRegionService, FoodService theFoodService){
        regionService = theRegionService;
        foodService = theFoodService;
    }

    @GetMapping("/regions")
    @ApiOperation("Return list of all regions")
    public List<Region> getAllRegion(){
        return regionService.getAllRegions();
    }

    @GetMapping("/regions/{regionId}")
    @ApiOperation("Return region based on regionId")
    public Region getRegionById(@PathVariable String regionId){
        Region theRegion = regionService.getRegionById(regionId);
        if(theRegion == null){
            throw new RuntimeException("Region with id - " +regionId + " not found!");
        }
        return theRegion;
    }

    @PostMapping("/regions/{foodId}TO{regionId}")
    @ApiOperation("Add food to region (need 2 path variable: foodId & regionId)")
    public String addFoodToRegion(@PathVariable String regionId,@PathVariable int foodId){
        Food theFood = foodService.getFoodById(foodId);
        Region theRegion = regionService.getRegionById(regionId);
        if(theRegion==null){
            throw new RuntimeException("Region with id - " +regionId + " not found!");
        }
        theRegion.addFood(theFood);
        theFood.setTheRegion(theRegion);
        regionService.saveRegion(theRegion);
        foodService.saveFood(theFood);

        return "Add food: " +theFood.getFoodName() + " to region: " +theRegion.getRegion_name() + " successfull";
    }

}
