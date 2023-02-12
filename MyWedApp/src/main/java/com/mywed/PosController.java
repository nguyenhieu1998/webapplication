package com.mywed;

import com.mywed.model.ProductPosService;
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
public class PosController {
    @Autowired
    private ProductPosService service;
    // đẩy dữ liệu các sp trong db cho trang admin quản lý sản phẩm
    @GetMapping("/table-data-product")
    public String showProductList(Model model){
        List<Products> listProduct = service.listAll();
        model.addAttribute("listProduct", listProduct);
        return "table-data-product";
    }
    // hàm này để hiển thị form thêm 1 sản phẩm mới
    @GetMapping("/table-data-product/new")
    public String showNewForm(Model model){
        model.addAttribute("add", new Products());
        model.addAttribute("pageTitle", "Add New product");
        return "form-add-san-pham";
    }
    // hàm lưu dữ liệu khi thêm thông tin của 1 sản phẩm mới vào trong db hiển thị lên view
    @PostMapping("/table-data-product/save")
    public String saveProduct(Products products, RedirectAttributes ra){
        service.save(products);
        ra.addFlashAttribute("message", "The product has been saved successfully");
        return "redirect:/table-data-product";
    }
    // hàm xử lý khi admin muốn chỉnh sửa 1 thông tin nào đó của 1 sản phẩm
    @GetMapping("/table-data-product/edit/{id}")
    public String showEditForm(@PathVariable("id") Integer id, Model model, RedirectAttributes ra){
        try{
            Products products = service.get(id);
            model.addAttribute("product", products);
            model.addAttribute("pageTitle", "Edit product (ID: " + id +")");
            return "form-edit-san-pham";
        } catch (ProductsNotFoundException e){
            ra.addFlashAttribute("message", e.getMessage());
            return "redirect:/table-data-product";
        }
    }
    // hàm xử lý xoá 1 sản phẩm
    @GetMapping("/table-data-product/delete/{id}")
    public String deleteProduct(@PathVariable("id") Integer id, RedirectAttributes ra){
        try{
            service.delete(id);
            ra.addFlashAttribute("message", "The product ID " + id + " has been deleted.");
        } catch (ProductsNotFoundException e){
            ra.addFlashAttribute("message", e.getMessage());
        }
        return "redirect:/table-data-product";
    }
}
