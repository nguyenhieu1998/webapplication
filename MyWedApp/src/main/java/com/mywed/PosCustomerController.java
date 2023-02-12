package com.mywed;

import com.mywed.model.Account;
import com.mywed.model.Products;
import com.mywed.model.ProductsNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class PosCustomerController {

    @Autowired
    AccountService service;
    // hàm hiển thị các danh sách tại khoản đk tạo từ DB liên view
    @GetMapping("/table-data-customer")
    public String showData(Model model){
        List<Account> list = service.listAll();
        model.addAttribute("list", list);
        return "table-data-customer";
    }
    // hàm hiển thị lên 1 form khi admin muốn tạo thêm 1 tài khoản nữa mà ko cần đăng ký
    @GetMapping("/table-data-customer/new")
    public String showNewForm(Model model){
        model.addAttribute("add", new Account());
        return "form-add-account";
    }
    // hàm xử lý lưu trữ các thông tin của 1 tài khoản khi admin thêm vào
    @PostMapping("/table-data-customer/save")
    public String saveAccount(Account account, RedirectAttributes ra){
        service.save(account);
        ra.addFlashAttribute("message", "The account has been saved successfully");
        return "redirect:/table-data-customer";
    }
    // hàm xử lý chỉnh sửa thông tin của 1 tài khoản
    @GetMapping("/table-data-customer/edit/{id}")
    public String showEditForm(@PathVariable("id") Integer id, Model model, RedirectAttributes ra){
        try{
            Account account = service.get(id);
            model.addAttribute("acc", account);
            model.addAttribute("pageTitle", "Edit account (ID: " + id +")");
            return "form-edit-customeracc";
        } catch (ProductsNotFoundException e){
            ra.addFlashAttribute("message", e.getMessage());
            return "redirect:/table-data-customer";
        }
    }
    // hàm xoá tài khoản
    @GetMapping("/table-data-customer/delete/{id}")
    public String deleteProduct(@PathVariable("id") Integer id, RedirectAttributes ra){
        try{
            service.delete(id);
            ra.addFlashAttribute("message", "The product ID " + id + " has been deleted.");
        } catch (ProductsNotFoundException e){
            ra.addFlashAttribute("message", e.getMessage());
        }
        return "redirect:/table-data-customer";
    }
}
