package com.mywed;

import com.mywed.model.CateName;
import com.mywed.model.Products;
import com.mywed.repository.CartRepository;
import com.mywed.repository.CateNameRepository;
import com.mywed.repository.ProductRepository;
import com.mywed.repository.SearchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
// http:localhost:8080/index.html
public class MainController {
    // sử dụng Autowired để tự động inject bean tương ứng vào vị trí được đánh dấu.
    @Autowired
    ProductRepository productRepository;//interface dùng để tìm kiếm Sp bằng hãng của sản phẩm đó
    @Autowired
    CartRepository cart;// hiển thị các sản phẩm dk lưu trong giỏ hàng

    @Autowired
    CateNameRepository cate;//hiển thị danh sách các hãng sp cho thanh narbar
    @Autowired
    SearchRepository repo;
    @GetMapping("/index.html")
    public String showHomePage(ModelMap modelMap, @RequestParam("page") Optional<Integer> page){
        // sử dụng Pageable để tuỳ chỉnh số lượng Sp có thể hiển thị lên web và phân trang
        Pageable pageable = PageRequest.of(page.orElse(0), 8);
        String text = "kawasaki";
        // sử dụng phương pháp findby... để tìm kiếm hãng sp trong DB
        // bằng cách dùng CRUB để tự tạo ra 1 câu query để tìm kiếm trong db
        Page<Products> getProduct= productRepository.findByMotor(text, pageable);
        // sử dụng ModelMap để chuyền dữ liệu từ controller sang view
        modelMap.addAttribute("getProduct", getProduct);


        String text1 = "BMW";
        Page<Products> getProductBmw= productRepository.findByMotor(text1, pageable);
        modelMap.addAttribute("getProductBmw", getProductBmw);
        modelMap.addAttribute("totalitems", cart.getCount());

        // chuyển dữ liệu tên các hãng SP sang view cho phần menu
        Iterable<CateName> list1 = cate.findAll();
        modelMap.addAttribute("list1", list1);
        // trả tất cả dữ liệu trên sang view ở trang index
        return "index";
    }
//RequestMapping Chú thích để ánh xạ các yêu cầu web lên các phương thức trong các lớp
// xử lý yêu cầu với chữ ký phương thức linh hoạt.
// sử request để tạo 1 url có chứa dữ liệu mà ng dùng
    @RequestMapping(value = "/getCateByName/{name}", method = RequestMethod.GET)
    public String showCateName(Model model, @PathVariable("name") String name){
        List<Products> list = repo.search(name);
        model.addAttribute("search", list);
        Iterable<CateName> list1 = cate.findAll();
        model.addAttribute("list1", list1);
        return "searchbycate";
    }


}
