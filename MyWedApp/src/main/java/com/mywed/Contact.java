package com.mywed;

import com.mywed.model.CateName;
import com.mywed.repository.CateNameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller

public class Contact {
    @Autowired
    CateNameRepository cate;
    @GetMapping("/contact")
    public String showFomrContact(ModelMap modelMap){
        Iterable<CateName> list1 = cate.findAll();
        modelMap.addAttribute("list1", list1);
        return "contact";
    }
}
