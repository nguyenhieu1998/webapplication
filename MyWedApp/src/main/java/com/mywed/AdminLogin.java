package com.mywed;

import com.mywed.dto.OrderdetailDTO;
import com.mywed.model.Account;
import com.mywed.model.Admin;
import com.mywed.model.ProductPosService;
import com.mywed.model.Products;
import com.mywed.repository.AdminRepository;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class AdminLogin {
    @Autowired
    AdminRepository repo;
    @Autowired
    ProductPosService service;
    @Autowired
    AccountService service1;
    @Autowired
    OrderPosService service2;
    // hàm hiển thi form đăng nhập admin
    @GetMapping("/admin")
    public String adminLogin(Model model){
        model.addAttribute("admin", new Admin());
        return "adminlogin";
    }
    // hàm xử lý phiên đăng nhập của admin
    @PostMapping("/adminLogin")
    public String doLogin(Model model, @ModelAttribute(name = "adminLogin") Admin admin,
                          HttpServletRequest request, @RequestParam(name = "remember", required = false) String checkBox
            , HttpServletResponse response) {
        String username = request.getParameter("username");//lấy dữ liệu nhập vào từ input user
        String password = request.getParameter("password");// lấy dữ liệu nhập vào từ input password
        Optional<Admin> list = repo.findByUserName(username); // tìm kiến tài khoản trong DB bằng tên user
        HttpSession session = request.getSession(true);// lưu trữ phiên đăng nhập bằng session
        if(username.equals(list.get().getUserName()) && password.equals(list.get().getPassword())){// check điều kiện khi đăng nhập
            //save username in to session
            session.setAttribute("user", username);
            //save admin login in to cookie
            Cookie user = new Cookie("username", username);
            Cookie pwd = new Cookie("password", password);
            if(checkBox != null){
                user.setMaxAge(60 * 60 * 24);
                pwd.setMaxAge(60 * 60 * 24);
                user.setPath("/user");
                user.setHttpOnly(true);
                pwd.setPath("/pwd");
                pwd.setHttpOnly(true);
                response.addCookie(user);
                response.addCookie(pwd);
            } else {
                user.setMaxAge(0);
                pwd.setMaxAge(0);
            }
            List<Products> listProduct = service.listAll();
            model.addAttribute("listProduct", listProduct);

            List<Account> listAcc = service1.listAll();
            model.addAttribute("listAcc", listAcc);

            List<OrderdetailDTO> listOrder = service2.getAllOrder();
            model.addAttribute("listOrder", listOrder);
            return "adminpage";
        } else {
            return "adminlogin";
        }
    }
    // Hàm xử lý chức năng đăng xuất
    @PostMapping("/logout")
    public String logOut(HttpServletResponse response, HttpServletRequest request){
        Cookie cookieUser = new Cookie("username", null);
        cookieUser.setPath("/user");
        cookieUser.setHttpOnly(true);
        cookieUser.setMaxAge(0);
        response.addCookie(cookieUser);
        Cookie cookiePwd = new Cookie("password", null);
        cookiePwd.setPath("/pwd");
        cookiePwd.setHttpOnly(true);
        cookiePwd.setMaxAge(0);
        response.addCookie(cookiePwd);
        try{
            HttpSession session = request.getSession(false);
            if(session != null){
                session.invalidate();
            }
        } catch (Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return "adminlogin";
    }
}