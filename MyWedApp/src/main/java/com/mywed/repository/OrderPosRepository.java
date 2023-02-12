package com.mywed.repository;

import com.mywed.model.Orderdetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OrderPosRepository extends JpaRepository<Orderdetail, Integer> {

    List<Orderdetail> findAll();

    public Long countById(Integer id);
}
