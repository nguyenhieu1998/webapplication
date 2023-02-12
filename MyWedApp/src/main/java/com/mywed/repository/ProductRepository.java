package com.mywed.repository;

import com.mywed.model.Products;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
//Đây là một mẫu của interface ProductRepository được mở rộng từ CrudRepository.
//        Nó bao gồm một phương thức findByMotor() để thực hiện truy vấn trên cơ sở dữ liệu để tìm kiếm các
//        sản phẩm theo motor.
@Repository
public interface ProductRepository extends CrudRepository<Products, Integer> {
    Page<Products> findByMotor (String motor, Pageable pageable);

}
