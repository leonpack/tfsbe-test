package com.tfs.demo.tfs_crud_demo.rest;

import com.tfs.demo.tfs_crud_demo.entity.Promotion;
import com.tfs.demo.tfs_crud_demo.service.PromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PromotionRestController {

    private PromotionService promotionService;

    @Autowired
    public PromotionRestController(PromotionService thePromotionService){
        promotionService = thePromotionService;
    }

    @GetMapping("/promotions")
    public List<Promotion> getAllPromotion(){
        return promotionService.getAllPromotions();
    }

    @GetMapping("/promotions/{promotionCode}")
    public Promotion getPromotionByCode(@PathVariable String promotionCode){
        Promotion thePromotion = promotionService.getPromotionByCode(promotionCode);

        if(thePromotion==null){
            throw new RuntimeException("Promotion code - " +promotionCode + " not found");
        }

        return thePromotion;
    }

    @PostMapping("/promotions")
    public String addNewPromotion(@RequestBody Promotion thePromotion){
        if(!promotionService.checkDuplicatePromotionCode(thePromotion.getPromotionCode())){
            return "This promotion code - " +thePromotion.getPromotionCode() + " already in the list";
        }
        promotionService.savePromotion(thePromotion);
        return "Saved new promotion successful";
    }

    @PutMapping("/promotions")
    public Promotion updatePromotion(@RequestBody Promotion thePromotion){
        promotionService.savePromotion(thePromotion);
        return thePromotion;
    }

    @DeleteMapping("/promotions/{promotionCode}")
    public String disablePromotion(@PathVariable String promotionCode){
        Promotion thePromotion = promotionService.getPromotionByCode(promotionCode);
        if(thePromotion == null){
            throw new RuntimeException("Promotion code - " +promotionCode + " not found!!");
        }
        thePromotion.setStatus(false);
        promotionService.savePromotion(thePromotion);
        return "Disable promotion " +promotionCode + " successful!";
    }
}