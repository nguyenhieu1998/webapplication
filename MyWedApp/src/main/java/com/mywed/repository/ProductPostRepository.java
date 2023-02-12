package com.mywed.repository;

import com.mywed.model.Products;
import org.springframework.data.repository.CrudRepository;

public interface ProductPostRepository extends CrudRepository<Products, Integer> {
    public Long countById(Integer id);
}
