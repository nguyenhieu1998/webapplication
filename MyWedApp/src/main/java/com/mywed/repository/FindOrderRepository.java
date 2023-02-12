package com.mywed.repository;

import com.mywed.model.Orderdetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FindOrderRepository extends JpaRepository<Orderdetail, String> {

    Optional<Orderdetail> findByFirtsName(String firtsName);
}
