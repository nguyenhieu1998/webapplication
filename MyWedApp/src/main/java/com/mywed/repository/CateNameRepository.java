package com.mywed.repository;

import com.mywed.model.CateName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CateNameRepository extends CrudRepository<CateName, Integer> {

}
