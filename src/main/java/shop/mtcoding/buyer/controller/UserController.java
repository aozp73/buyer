package shop.mtcoding.buyer.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
    @Autowired
    private HttpSession session;

    @GetMapping("/loginForm")
    public String loginForm(HttpServletRequest request) {
        // 체크하고 넘어왔을때 request에 cookie가 있을 것임
        // String cookies = request.getHeader("Cookie");

        // remember=ssar; JESSIONID=6aww12a35512a
        String username = "";
        // getCookies 배열 크기 : 2
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("remember")) {
                username = cookie.getValue();
            }
        }

        request.setAttribute("remember", username);

        // 이런 테스트는 테스트에서 처리
        // getCookies 쓰면 알아서 파싱 해줌
        // remember=ssar; JESSIONID=6aww12a35512a

        System.out.println("디버그" + cookies);
        return "user/loginForm";
    }

    @PostMapping("/login")
    public String login(String username, String password, String remember, HttpServletResponse response) {
        User user = userRepository.findByUsernameAndPassword(username, password);

        if (user == null) {
            return "redirect:/loginForm";
        } else {
            // 요청헤더 : Cookie
            // 응답헤더 : Set-Cookie

            if (remember == null) {
                remember = "";
            }
            if (remember.equals("on")) {
                // Set-Cookie에 알아서 뒤에 추가 해 줌
                Cookie cookie = new Cookie("remember", username);
                response.addCookie(cookie);
                // 응답 헤더의 set-cookie의 키 값에 remember=ssar을 넣는 다는 것

                // session.setAttribute("cookie2", username);
            } else {
                // 새로운 쿠키를 만들어서 덮어 씌움
                // 이 방식은 브라우저에 remember=""이 계속 남아 있음
                Cookie cookie = new Cookie("remember", "");
                // 쿠키 시간을 0으로 지정 가능
                // session.removeAttribute("cookie2");

                cookie.setMaxAge(0);
                response.addCookie(cookie);
            }

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

    @GetMapping("/logout")
    public String logout() {
        // JSESSIONID 손대는 것 x
        // 우리 SESSION에 있는 것 손 대기
        session.invalidate();

        return "redirect:/";
    }
}
