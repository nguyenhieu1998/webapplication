package com.mywed;

import com.mywed.model.CateName;
import com.mywed.model.ProductPicture;
import com.mywed.model.Products;
import com.mywed.repository.CartRepository;
import com.mywed.repository.CateNameRepository;
import com.mywed.repository.DetailRepository;
import com.mywed.repository.ProductPath;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.Optional;
// class này sử dụng để hiện thị thông tin chi tiết của 1 sp
@Controller
@RequestMapping(path = "productsDetail")
public class ProductsDetail {
    @Autowired
    DetailRepository repo;

    @Autowired
    ProductPath productPath;
    @Autowired
    CartRepository cart;
    @Autowired
    CateNameRepository cate;
//   lớp này được sử dụng để xử lý yêu cầu HTTP GET tới URL /getProductsdetailBy/{id}.
//    Trong đoạn mã này, nó sẽ truy vấn cơ sở dữ liệu để lấy thông tin chi tiết của sản phẩm với ID được cung cấp,
//    sau đó nó sẽ thêm thông tin đó vào modelMap và trả về trang web có tên là "detail".
    @RequestMapping(value = "/getProductsdetailBy/{id}", method = RequestMethod.GET)
    public String productsDetail(ModelMap modelMap, @PathVariable("id") int id){
        Optional<Products> showProduct = repo.findById(id);
        modelMap.addAttribute("showProduct", showProduct.get());

        List<ProductPicture> list = productPath.path(showProduct.get().getId());
        modelMap.addAttribute("list", list);

        List<Products> product = repo.findByMotor(showProduct.get().getMotor());
        modelMap.addAttribute("product", product);
        modelMap.addAttribute("totalitems", cart.getCount());
        Iterable<CateName> list1 = cate.findAll();
        modelMap.addAttribute("list1", list1);
        return "detail";
    }
}
