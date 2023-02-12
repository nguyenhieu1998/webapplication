package com.mywed;

import com.mywed.model.Account;
import com.mywed.repository.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class FormSignUp {
    // @RequestMapping("/formsignup") sẽ định tuyến các yêu cầu đến trang web có đường dẫn là "/formsignup".
    // Phương thức signUp sẽ được gọi để trả về trang web có đường dẫn là "/formsignup".
    // Phương thức saveUser sẽ được gọi khi người dùng nhấn nút "Submit" trên trang web.
    // Phương thức sẽ lưu thông tin người dùng vào cơ sở dữ liệu và sau đó chuyển hướng về
    // trang web có đường dẫn là "/formsignup".
    @Autowired
    LoginRepository repo;
    @RequestMapping("/formsignup")
    public String signUp(Model model){
        model.addAttribute("signup", new Account());
        return "formsignup";
    }

    @PostMapping("/signupsucsess")
    public String saveUser(Account account, Model model){
        repo.save(account);
        return "redirect:/formsignup";
    }
}
