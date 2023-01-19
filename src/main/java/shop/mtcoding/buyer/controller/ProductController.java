package shop.mtcoding.buyer.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import shop.mtcoding.buyer.model.Product;
import shop.mtcoding.buyer.model.ProductRepository;

@Controller
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    // @Autowired와 같은 내용의 코드임
    // 스프링이 ProductController 클래스를 new해야하는데, 아래 생성자밖에 없다.
    // ProductRepository가 떠 있어야 하므로 IOC에 있는지 확인해서 있다면 매개변수를 알아서 주입해 준다.
    // public ProductController(ProductRepository productRepository) {
    // this.productRepository = productRepository;
    // }

    @GetMapping({ "/", "/product" })
    public String home(Model model) {
        // 테이블명 + List 이런식으로 통일
        List<Product> productList = productRepository.findAll();
        // 이걸 들고 가는 것이 아니라 request에 보관 하는 것
        model.addAttribute("productList", productList);
        return "product/list";
    }

    // select * from product where price = 1000을 하고 싶다면 queryString으로 전송
    // pk가 아니면 queryString으로 전송
    @GetMapping("/product/{id}")
    public String detail(Model model, @PathVariable int id) {
        Product product = productRepository.findById(id);

        if (product == null) {
            return "redirect:/notfound";
        } else {
            model.addAttribute("product", product);
            return "product/detail";
        }
    }
}
