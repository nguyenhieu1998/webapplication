package com.mywed.model;

import com.mywed.repository.ProductPostRepository;
import com.mywed.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductPosService {
    @Autowired
    private ProductPostRepository repo;

//    tạo ra một lớp sản phẩm với các phương thức sau: listAll() để lấy danh sách tất cả các sản phẩm;
//    save() để lưu sản phẩm; get() để lấy sản phẩm theo ID; và delete() để xóa sản phẩm theo ID.
    public List<Products> listAll(){
        return (List<Products>) repo.findAll();
    }

    public void save(Products products){
        repo.save(products);
    }

    public Products get(Integer id) throws ProductsNotFoundException{
        Optional<Products> result = repo.findById(id);
        if(result.isPresent()){
            return result.get();
        }
        throw new ProductsNotFoundException("Could not find any product with ID" + id);
    }
    public void delete(Integer id) throws ProductsNotFoundException {
        Long count = repo.countById(id);
        if(count == null || count == 0){
            throw new ProductsNotFoundException("Could not find any product with ID" + id);
        }
        repo.deleteById(id);
    }
}
