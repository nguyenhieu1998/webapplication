package com.mywed;

import com.mywed.model.CartItems;
import com.mywed.model.CateName;
import com.mywed.model.Order;
import com.mywed.model.Orderdetail;
import com.mywed.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller

public class CheckoutController {

    @Autowired
    CheckoutRepository repo;// lưu thông tin checkout
    @Autowired
    CartRepository cart; // lấy danh sách Sp mà kh thanh toán
    @Autowired
    OrderdetailRepository orderRepo; // thông tin chi tiết đơn hàng
    @Autowired
    FindOrderRepository findRepo; // tìm thông tin hoá đơn đã thanh toán
    // @GetMapping("/checkout/new") phẩn hiển thị danh sách sp, tổng tiền của 1 đơn hàng muốn thanh toán
    // và nơi yêu cầu user nhập những thông tin cần thiết để đặt hàng.
    @Autowired
    CateNameRepository cate;
    @GetMapping("/checkout/new")
    public String orderForm(Model model){
        model.addAttribute("checkout", new Order());
        model.addAttribute("total", cart.getAmount());
        model.addAttribute("itemCart", cart.getAllItem());
        Iterable<CateName> list1 = cate.findAll();
        model.addAttribute("list1", list1);
        return "checkout";
    }

    // sau đã nhập đầy dủ thông tin cá nhân cần thiết để đặt hàng
    // @PostMapping("/checkout/save") sữ tiến hành lưu trữ các thông tin của user truyền vào trong DB
    // sau đó tiến hành tạo 1 hoá đơn chí tiết gồm có thông tin user, SP order, số lượng và số tiền cần thanh toán, ngày đặt hàng
    // rồi hiện thị các hoá đơn đã order thành công lên trang order-succsess
    @PostMapping("/checkout/save")
    public String getCheckOut(Order order, ModelMap modelMap){
        LocalDateTime date = LocalDateTime.now();// phương thức lấy ngày tháng năm
        String getDate = date.toString();// chuyển đổi kiểu dữ liệu ngày tháng năm sang kiểu chuỗi
        order.setOrderDay(getDate);
        repo.save(order);
        try{
            List<Orderdetail>list = new ArrayList<>();
            for(CartItems items : cart.getAllItem()){
                Orderdetail orderdetail = new Orderdetail();
                orderdetail.setFirtsName(order.getFirtsName());
                orderdetail.setLastName(order.getLastName());
                orderdetail.setPhone(order.getPhone());
                orderdetail.setAddress(order.getAddress1());
                orderdetail.setOrderId(order.getId());
                orderdetail.setProductId(items.getProductId());
                orderdetail.setTotalCate(items.getQuantity());
                orderdetail.setTotalOrder((int) cart.getAmount());
                orderdetail.setOrderDay(getDate);
                orderRepo.save(orderdetail);
                list.add(orderdetail);
            }
            Optional<Orderdetail> find = findRepo.findByFirtsName(order.getFirtsName());
            modelMap.addAttribute("find", find.get());
            modelMap.addAttribute("itemCart", cart.getAllItem());
            modelMap.addAttribute("totalitems", cart.getCount());
        } catch (Exception e){
            e.printStackTrace();
        }
        return "order-success";
    }
}
