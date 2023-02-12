package com.mywed;

import com.mywed.dto.OrderdetailDTO;
import com.mywed.model.Orderdetail;
import com.mywed.model.Products;
import com.mywed.model.ProductsNotFoundException;
import com.mywed.repository.OrderPosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
//Đoạn code này là một lớp dịch vụ có tên là OrderPosService.
//        Nó có một phương thức getAllOrder() để lấy tất cả các đơn đặt hàng từ cơ sở dữ liệu,
//        một phương thức convertEntityToDto() để chuyển đổi các đối tượng đặt hàng thành đối tượng DTO,
//        một phương thức save() để lưu các đối tượng đặt hàng vào cơ sở dữ liệu, một phương thức get()
//        để lấy một đối tượng đặt hàng cụ thể và một phương thức delete() để xóa một đối tượng đặt hàng cụ thể.
// chức năng cho trang admin để quản lý đơn hàng đã order
@Service
public class OrderPosService {
    @Autowired
    private OrderPosRepository repo;

    public List<OrderdetailDTO> getAllOrder(){
        return repo.findAll().stream().map(this::convertEntityToDto).collect(Collectors.toList());
    }

    private OrderdetailDTO convertEntityToDto(Orderdetail orderdetail){
        OrderdetailDTO orderdetailDTO = new OrderdetailDTO();
        orderdetailDTO.setId(orderdetail.getId());
        orderdetailDTO.setFirtsName(orderdetail.getFirtsName());
        orderdetailDTO.setName(orderdetail.getProducts().getName());
        orderdetailDTO.setTotalCate(orderdetail.getTotalCate());
        orderdetailDTO.setTotalOrder(orderdetail.getTotalOrder());
        orderdetailDTO.setAddress(orderdetail.getAddress());
        orderdetailDTO.setPhone(orderdetail.getPhone());
        return orderdetailDTO;
    }

    public void save(Orderdetail orderdetail) {
        repo.save(orderdetail);
    }

    public Orderdetail get(Integer id) throws ProductsNotFoundException{
        Optional<Orderdetail> result = repo.findById(id);
        if(result.isPresent()){
            return result.get();
        }
        throw new ProductsNotFoundException("Could not find any orderdetail with ID"+ id);
    }

    public void delete(Integer id) throws ProductsNotFoundException {
        Long count = repo.countById(id);
        if(count == null || count == 0){
            throw new ProductsNotFoundException("Could not find any orderdetail with ID"+ id);
        }
    }
}
