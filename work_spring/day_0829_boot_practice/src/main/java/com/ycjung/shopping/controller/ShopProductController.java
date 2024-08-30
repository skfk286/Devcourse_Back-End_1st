package com.ycjung.shopping.controller;

import com.ycjung.shopping.model.dto.ShopProductDTO;
import com.ycjung.shopping.model.service.ShopProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/product")
public class ShopProductController {

    @Autowired
    private ShopProductService shopProductService;

    @GetMapping("/list") // 조회
    public String getProducts(Model model) {
        System.out.println("getProducts()..");

        model.addAttribute("products", shopProductService.selectAllProducts());

        return "list";
    }

    @GetMapping("/read") // 상세조회
    public String getProduct() {

        return "view";
    }
}
