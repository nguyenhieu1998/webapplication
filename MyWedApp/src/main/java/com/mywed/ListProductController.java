package com.mywed;

import com.mywed.model.CateName;
import com.mywed.model.Products;
import com.mywed.model.ProductsNotFoundException;
import com.mywed.repository.CartRepository;
import com.mywed.repository.CateNameRepository;
import com.mywed.repository.ListRepository;
import com.mywed.repository.SearchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
// class này dùng để hiển thị danh sách cách sp trong db lên view
@Controller
@RequestMapping(path = "shop.html")
public class ListProductController {

    @Autowired
    ListRepository repo;
    @Autowired
    CartRepository cart;
    @Autowired
    SearchRepository search;
    @Autowired
    CateNameRepository cate;
//    tạo một phương thức để lấy danh sách các sản phẩm từ cơ sở dữ liệu và truyền nó vào trang web.
//    Phương thức này cũng tạo một trang phân trang với tối đa 9 sản phẩm trên mỗi trang.
//    Phương thức này cũng tạo một danh sách các danh mục sản phẩm và truyền nó vào trang web.
//    Cuối cùng, phương thức này trả về trang web "shop".
    @RequestMapping(value = "" , method = RequestMethod.GET)
    public String listAllProduct(ModelMap modelMap, @RequestParam("page") Optional<Integer> page){
            Pageable pageable = PageRequest.of(page.orElse(0), 9);
        Page<Products> getlist= repo.findAll(pageable);
        modelMap.addAttribute("getlist", getlist);
        modelMap.addAttribute("totalitems", cart.getCount());
        Iterable<CateName> list1 = cate.findAll();
        modelMap.addAttribute("list1", list1);
        return "shop";
    }
}
