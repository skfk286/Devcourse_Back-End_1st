package com.ycjung.shopping.model.repository;

import com.ycjung.shopping.model.dto.ShopProductDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ShopProductRepository {
    List<ShopProductDTO> selectAllProducts();


}
