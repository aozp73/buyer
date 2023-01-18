package shop.mtcoding.buyer.model;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/*
 * UserRepository 기능 : 회원가입, 로그인, 회원탈퇴, 회원수정
 * 아이디 중복확인(javascript 알아야함)
 */

// 회원 가입
// - update,insert,delete 중에 하나니까 return타입 int임
// - 매개변수를 알게 된건 기능정리를 해놨기 때문에

// 로그인 
// - select라서 객체 반환 (User)
// - 원래 findByUsernameAndPassword

// 회원탈퇴
// - delete 한 행을 returb 하므로, return타입 int임 

// 회원수정
// - passwordUpdate 이런건 순서를 정해야 함
// - 동사를 먼저 명사를 뒤에 updatePassword
@Mapper
public interface UserRepository {
    // 방법2) CRUD에 따라 생성

    public int insert(@Param("username") String username, @Param("password") String password,
            @Param("email") String email);

    public List<User> findAll();

    public User findById(int id);

    public int updateById(@Param("id") int id, @Param("password") String password);

    public int deleteById(int id);

    public User findByUsernameAndPassword(@Param("username") String username, @Param("password") String password);

}
