package com.mywed;

import com.mywed.dto.OrderdetailDTO;
import com.mywed.model.Orderdetail;
import com.mywed.model.Products;
import com.mywed.model.ProductsNotFoundException;
import com.mywed.repository.OrderPosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class PosOrderController {
    @Autowired
    OrderPosService service;
    // hàm hiển thị danh sách các đơn hàng
    // Sử dụng 1 model DTo để lấy lấy thông tin cần thiết của đơn hàng đã order thành công
    @GetMapping("/table-data-oder")
    public String showProductList(Model model){
        List<OrderdetailDTO> list = service.getAllOrder();
        model.addAttribute("listProduct", list);
        return "table-data-oder";
    }
//    @GetMapping("/table-data-oder/new")
//    public String showNewForm(Model model){
//        model.addAttribute("edit", new Orderdetail());
//        model.addAttribute("pageTitle", "Add New product");
//        return "form-edit-san-pham";
//    }

    @PostMapping("/table-data-oder/save")
    public String saveOrder(Orderdetail orderdetail, RedirectAttributes ra){
        service.save(orderdetail);
        ra.addFlashAttribute("message", "the orderdetail have been save");
        return "redirect:/table-data-oder";
    }

//    @GetMapping("/table-data-oder/edit/{id}")
//    public String showEditForm(@PathVariable("id") Integer id, Model model, RedirectAttributes ra){
//        try{
//            Orderdetail orderdetail = service.get(id);
//            model.addAttribute("orderdetail", orderdetail);
//            return "form-edit-don-hang";
//        }catch (ProductsNotFoundException e) {
//            ra.addFlashAttribute("message", e.getMessage());
//            return "redirect:/table-data-oder";
//        }
//    }
    // hàm xoá đơn hàng
    @GetMapping("/table-data-oder/delete/{id}")
    public String deleteOrder(@PathVariable("id") Integer id, RedirectAttributes ra){
        try{
            service.delete(id);
            ra.addFlashAttribute("message", "the orderdetail have been delete");
        } catch (ProductsNotFoundException e){
            ra.addFlashAttribute("message", e.getMessage());
        }
        return "redirect:/table-data-oder";
    }
}
