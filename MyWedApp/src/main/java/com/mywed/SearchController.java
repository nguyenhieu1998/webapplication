package com.mywed;

import com.mywed.model.CateName;
import com.mywed.model.Products;
import com.mywed.repository.CateNameRepository;
import com.mywed.repository.SearchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class SearchController {
// Đây là một phương thức @GetMapping được sử dụng để xử lý yêu cầu GET.
// Phương thức này nhận một tham số tên là keyword và một đối tượng Model.
// Phương thức truy vấn cơ sở dữ liệu để tìm kiếm sản phẩm có liên quan đến từ khóa keyword.
// Kết quả trả về được lưu trữ trong đối tượng Model và trả về trang web có tên là 'search'.
    @Autowired
    SearchRepository search;
    @Autowired
    CateNameRepository cate;
    @GetMapping("/search")
   public String search(@Param("keyword") String keyword, Model model){
        List<Products> list = search.search(keyword);
        model.addAttribute("list", list);
        Iterable<CateName> list1 = cate.findAll();
        model.addAttribute("list1", list1);
        return "search";
    }
}
