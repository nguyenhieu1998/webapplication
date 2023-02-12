package com.mywed.repository;

import com.mywed.model.Products;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DetailRepository extends CrudRepository<Products, Integer> {
    List<Products> findByMotor (String motor);
}
