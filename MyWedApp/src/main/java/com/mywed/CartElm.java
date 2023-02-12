package com.mywed;

import com.mywed.model.CartItems;
import com.mywed.repository.CartRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@SessionScope
@Service
public class CartElm implements CartRepository {
//    tạo một lớp CartElm để quản lý giỏ hàng của một người dùng.
//    Lớp này kế thừa từ lớp CartRepository và có một thuộc tính là một từ điển được gọi là maps.
//    Nó có nhiều phương thức để thêm, xoá, cập nhật và lấy thông tin của các mặt hàng trong giỏ hàng.
//    Phương thức getAmount() được sử dụng để tính toán tổng số tiền của giỏ hàng.
    Map<Integer, CartItems> maps = new HashMap<>();// danh sách giỏ hàng
    @Override
    public void add(CartItems item){
        CartItems carItem = maps.get(item.getProductId());
        if(carItem == null){
            maps.put(item.getProductId(),item);
        } else {
            carItem.setQuantity(carItem.getQuantity() + 1);
        }
    }

    @Override
    public void remove(int id){
        maps.remove(id);
    }

    @Override
    public CartItems update(int id, int qty){
        CartItems carItem = maps.get(id);
        carItem.setQuantity(qty);
        return carItem;
    }

    @Override
    public void clear(){
        maps.clear();
    }

    @Override
    public Collection<CartItems> getAllItem(){
        return maps.values();
    }

    @Override
    public int getCount(){
        return maps.values().size();
    }
//    @Override
    public double getAmount(){
        return maps.values().stream().mapToDouble(item -> item.getQuantity() * item.getPrice()).sum();
    }
}
