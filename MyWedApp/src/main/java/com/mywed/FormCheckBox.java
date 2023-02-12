package com.mywed;

import com.mywed.model.CateName;
import com.mywed.model.Products;
import com.mywed.repository.CateNameRepository;
import com.mywed.repository.SearchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;
import java.util.List;

@Controller
public class FormCheckBox {
    @Autowired
    SearchRepository repo;
    @Autowired
    CateNameRepository cate;
    // hàm này xử lý cho phần lọc theo yêu cầu của ng dùng bằng tên hãng sp và truy vấn cơ sở dữ liệu
    @GetMapping("/searchby")
    public String formSearch(Model model, @RequestParam(value = "productType") String[] selected){
        List<Products> search = repo.findByMotor(List.of(selected));
        model.addAttribute("search", search);
        Iterable<CateName> list1 = cate.findAll();
        model.addAttribute("list1", list1);
        return "searchbycate";
    }
    // hàm này xử lý cho phần lọc theo yêu cầu của ng dùng bằng phân khúc của sp và truy vấn cơ sở dữ liệu
    @GetMapping("/searchbycc")
    public String showFormSearchCc(Model model, @RequestParam(value = "cylinder") String[] cylinder){
        List<Products> search = repo.findByCylinder(List.of(cylinder));
        model.addAttribute("search", search);
        Iterable<CateName> list1 = cate.findAll();
        model.addAttribute("list1", list1);
        return "searchbycate";
    }
    // hàm này xử lý cho phần lọc theo yêu cầu của ng dùng bằng khoảng giá nào đó và truy vấn cơ sở dữ liệu
    @GetMapping("/searchbyprice")
    public String showSearchByPrice(@RequestParam(value = "searchprice", required = false)String[] selected
            , Model model){
            List<Products> search = repo.findByPrice(0, List.of(selected));
            model.addAttribute("search", search);
        Iterable<CateName> list1 = cate.findAll();
        model.addAttribute("list1", list1);
        return "searchbycate";
    }
}
