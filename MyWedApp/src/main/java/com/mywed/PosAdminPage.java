package com.mywed;

import com.mywed.dto.OrderdetailDTO;
import com.mywed.model.Account;
import com.mywed.model.ProductPosService;
import com.mywed.model.Products;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class PosAdminPage {

    @Autowired
    ProductPosService service;
    @Autowired
    AccountService service1;
    @Autowired
    OrderPosService service2;
    @GetMapping("/posadmin")
    public String Admin(ModelMap modelMap){
        List<Products> listProduct = service.listAll();
        modelMap.addAttribute("listProduct", listProduct);

        List<Account> listAcc = service1.listAll();
        modelMap.addAttribute("listAcc", listAcc.size());

        List<OrderdetailDTO> listOrder = service2.getAllOrder();
        modelMap.addAttribute("listOrder", listOrder.size());
        return "redirect:/adminLogin";
    }
}
