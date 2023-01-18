package shop.mtcoding.buyer.contorller;

import org.apache.catalina.startup.ClassLoaderFactory.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import shop.mtcoding.buyer.model.UserRepository;

@Controller
public class ProductController {

    @GetMapping({ "/", "/product" })
    public String home() {
        return "product/list";
    }

}
