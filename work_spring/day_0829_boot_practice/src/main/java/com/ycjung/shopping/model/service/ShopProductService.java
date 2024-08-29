package com.ycjung.shopping.model.service;

import com.ycjung.shopping.model.dto.ShopProductDTO;
import com.ycjung.shopping.model.repository.ShopProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShopProductService {

    @Autowired
    private ShopProductRepository shopProductRepository;

    public List<ShopProductDTO> selectAllProducts() {
        return shopProductRepository.selectAllProducts();
    }
}
