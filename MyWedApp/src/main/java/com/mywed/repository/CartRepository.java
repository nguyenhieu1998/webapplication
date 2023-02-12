package com.mywed.repository;

import com.mywed.model.CartItems;

import java.util.Collection;

public interface CartRepository {

    void add(CartItems items);

    void remove(int id);

    CartItems update(int id, int qty);

    void clear();

    Collection<CartItems> getAllItem();

    int getCount();

    double getAmount();
}
//    Đây là một mẫu của các phương thức được sử dụng để quản lý giỏ hàng.
//        Phương thức add() được sử dụng để thêm một mục vào giỏ hàng,
//        phương thức remove() được sử dụng để xóa một mục khỏi giỏ hàng,
//        phương thức update() được sử dụng để cập nhật số lượng của một mục,
//        phương thức clear() được sử dụng để xóa toàn bộ giỏ hàng,
//        phương thức getAllItem() được sử dụng để lấy tất cả các mục trong giỏ hàng,
//        phương thức getCount() được sử dụng để lấy số lượng mục trong giỏ hàng và
//        phương thức getAmount() được sử dụng để lấy tổng số tiền của giỏ hàng.