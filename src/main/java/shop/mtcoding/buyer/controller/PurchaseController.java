package shop.mtcoding.buyer.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;

import shop.mtcoding.buyer.model.ProductRepository;
import shop.mtcoding.buyer.model.PurchaseRepository;
import shop.mtcoding.buyer.model.User;
import shop.mtcoding.buyer.service.PurchaseService;

@Controller
public class PurchaseController {

    @Autowired
    private HttpSession session;

    @Autowired
    private PurchaseRepository purchaseRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private PurchaseService purchaseService;

    // if-else

    /*
     * 목적)
     * 1. 세션이 있는지 체크 (로그인이 된 사람만 인증 된 사람만 구매하게)
     * 2. 구매 히스토리 남기기
     * 3. 재고 수량 변경
     */

    @Transactional
    @PostMapping("/purchase/insert")
    public String insert(int productId, int count) {
        // 아래 4가지를 이 Controller을 실행하기 위한 비지니스 로직이라고 함
        // Controller의 책임 : Client의 요청을 받아서 return

        // principal이라는 오브젝트를 보면 딱 Session에서 가져 온
        // 것이라고 생각 가능 (인증된 객체)

        // userId로 받아도 어차피 아래 User, if, int 코드는 필요 함
        // 1. 세션이 있는지 체크
        User principal = (User) session.getAttribute("principal");
        if (principal == null) {
            return "redirect:/notfound";
        }

        int result = purchaseService.구매하기(principal.getId(), productId, count);
        if (result == -1) {
            return "redirect:/notfound";
        }

        return "redirect:/";
    }
}
