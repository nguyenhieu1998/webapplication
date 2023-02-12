package com.mywed;

import com.mywed.dto.OrderdetailDTO;
import com.mywed.model.Account;
import com.mywed.model.Admin;
import com.mywed.model.Products;
import com.mywed.repository.LoginRepository;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;


@Controller
public class CustumLogin {

    @Autowired
    LoginRepository repo;
    @GetMapping("/user")
    public String login(Model model, Account account){
        model.addAttribute("account", new Account());
        return "custumlogin";
    }

    @PostMapping("/dologin")
    public String doLogin(Model model,
                          HttpServletRequest request, @RequestParam(name = "remember", required = false) String checkBox
            , HttpServletResponse response) {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        Optional<Account> list = repo.findByEmail(email);
        HttpSession session = request.getSession(true);
        if(email.equals(list.get().getEmail()) && password.equals(list.get().getPassword())){
            //save username in to session
            session.setAttribute("email", email);
            //save admin login in to cookie
            Cookie user = new Cookie("email", email);
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
            return "redirect:/index.html";
        } else {
            String message = "Email or password is wrong please try againt";
            model.addAttribute("message", message);
            return "custumlogin";
        }
    }
    @PostMapping("/userlogout")
    public void logout(HttpServletRequest request, HttpServletResponse response){
        Cookie cookie = new Cookie("email", null);
        cookie.setMaxAge(0);
        cookie.setSecure(true);
        cookie.setHttpOnly(true);
        cookie.setPath("/userlogout");
        response.addCookie(cookie);
        try{
            HttpSession session = request.getSession(false);
            if(session != null){
                session.invalidate();
                response.sendRedirect(request.getContextPath() + "/index.html");
            }
        } catch (Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
}
