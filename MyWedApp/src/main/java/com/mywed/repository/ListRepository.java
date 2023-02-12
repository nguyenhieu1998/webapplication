package com.mywed.repository;

import com.mywed.model.Products;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ListRepository extends JpaRepository<Products, Integer> {

    @Override
    Page<Products> findAll(Pageable pageable);
}
