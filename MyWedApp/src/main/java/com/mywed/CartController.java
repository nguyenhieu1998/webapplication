package com.mywed;

import com.mywed.model.CartItems;
import com.mywed.model.CateName;
import com.mywed.model.Products;
import com.mywed.repository.CartRepository;
import com.mywed.repository.CateNameRepository;
import com.mywed.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping(path = "cart.html")
public class CartController {

    @Autowired
    ProductRepository productRepository;
    @Autowired
    CartRepository cart;
    @Autowired
    CateNameRepository cate;
//    @RequestMapping("views") sẽ định tuyến URL đến phương thức viewCart().
//    Phương thức này sẽ thêm các thuộc tính vào modelMap để hiển thị thông tin giỏ hàng của người dùng.
    @RequestMapping("views")
    public String viewCart(ModelMap modelMap){
        modelMap.addAttribute("listItem", cart.getAllItem());
        modelMap.addAttribute("total", cart.getAmount());
        modelMap.addAttribute("totalitems", cart.getCount());
        Iterable<CateName> list1 = cate.findAll();
        modelMap.addAttribute("list1", list1);
        return "cart";
    }

//    @ResponseStatus(value = HttpStatus.OK)
//@GetMapping("add/{id}") sẽ định tuyến URL đến phương thức addCart() để thêm sản phẩm vào giỏ hàng.
    @ResponseStatus(value = HttpStatus.RESET_CONTENT)
    @GetMapping("add/{id}")
    public void addCart(@PathVariable int id){
        Optional<Products> product = productRepository.findById(id);
        if(product != null){

            CartItems item = new CartItems();
            item.setProductId(product.get().getId());
            item.setName(product.get().getName());
            item.setPrice(product.get().getPrice());
            item.setPicture(product.get().getPicture());
            item.setQuantity(1);
            cart.add(item);
        }
    }
//    @GetMapping("delete/{id}") sẽ định tuyến URL đến phương thức removeCart() để xóa sản phẩm khỏi giỏ hàng.
    @GetMapping("delete/{id}")
    public String removeCart(@PathVariable("id") int id){
        cart.remove(id);
        return "redirect:/cart.html/views";
    }
//    @PostMapping("update") sẽ định tuyến URL đến phương thức update() để cập nhật số lượng sản phẩm trong giỏ hàng.
    @PostMapping("update")
    public String update(@RequestParam("id") int id, @RequestParam("qty") int qty){
        cart.update(id,qty);
        return "redirect:/cart.html/views";
    }
}
