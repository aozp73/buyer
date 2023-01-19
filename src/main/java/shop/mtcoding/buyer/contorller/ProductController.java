package shop.mtcoding.buyer.contorller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import shop.mtcoding.buyer.model.Product;
import shop.mtcoding.buyer.model.ProductRepository;

@Controller
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private HttpSession session;

    @GetMapping({ "/", "/product" })
    public String home(Model model) {
        session.removeAttribute("check");
        List<Product> productList = productRepository.findAll();
        model.addAttribute("productList", productList);
        return "product/list";
    }

    @GetMapping("/product/{id}")
    public String detail(@PathVariable int id, Model model) {
        Product product = productRepository.findById(id);
        model.addAttribute("product", product);
        return "product/detail";
    }

    @PostMapping("/product/{id}/purchaseForm")
    public String pruchase(Model model, @PathVariable int id) {
        Product product = productRepository.findById(id);
        model.addAttribute("product", product);
        if (session.getAttribute("principal") == null) {
            return "redirect:/loginForm";
        } else {
            return "product/purchaseForm";
        }
    }

    @GetMapping("/product/{id}/purchaseForm")
    public String pruchase1(Model model, @PathVariable int id) {
        Product product = productRepository.findById(id);
        model.addAttribute("product", product);
        return "product/purchaseForm";
    }

    @PostMapping("/product/{id}/purchase")
    public String insertUpdate(@PathVariable int id, String name, int price, int qty, Model model) {

        Product product = productRepository.findById(id);
        Integer resQty = product.getQty() - qty;
        session.setAttribute("check", resQty);

        if (resQty < 0) {
            return "redirect:/product/" + id + "/purchaseForm";
        } else {
            productRepository.updateById(id, name, price, resQty);
            return "redirect:/";
        }
    }
}
