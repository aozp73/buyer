package shop.mtcoding.buyer.contorller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import shop.mtcoding.buyer.model.User;
import shop.mtcoding.buyer.model.UserRepository;

@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;
    // session은 아래와 같이 생성하면 됨
    // IoC 컨테이너에서 관리하고 있음
    @Autowired
    private HttpSession session;

    @GetMapping("/loginForm")
    public String loginForm() {
        return "user/loginForm";
    }

    @GetMapping("/login")
    public String login(String username, String password) {
        User user = userRepository.findByUsernameAndPassword(username, password);
        if (user == null) {
            return "redirect:/loginForm";
        } else {
            // el표현식에서 "principal"이 튀어 나왔다면 인증이 완료 되었다는 것
            // principal : 세계적인 it에서 고유 명사, 인증 주체를 뜻함
            // -> principal이 되었다. 인증이 되었다.
            session.setAttribute("principal", user);
            return "redirect:/";
        }
    }

    @GetMapping("/joinForm")
    public String joinForm() {
        return "user/joinForm";
    }

    @PostMapping("/join")
    public String join(String username, String password, String email) {
        int result = userRepository.insert(username, password, email);
        if (result == 1) {
            return "user/loginForm";
        } else {
            return "user/joinForm";
        }
    }
}
