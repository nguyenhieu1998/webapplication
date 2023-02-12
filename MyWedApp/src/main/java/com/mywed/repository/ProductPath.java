package com.mywed.repository;

import com.mywed.model.ProductPicture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductPath extends JpaRepository<ProductPicture, Integer> {

//    @Query(value = "select file_path from product_picture inner join categories on product_picture.product_id = categories.id")
//    List<ProductPicture> path(int id);
    @Query(value = "select * from db_motor.product_picture where product_id = ?1",nativeQuery = true)
    public List<ProductPicture> path(int productId);
}
