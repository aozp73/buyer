package shop.mtcoding.buyer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import shop.mtcoding.buyer.model.Product;
import shop.mtcoding.buyer.model.ProductRepository;
import shop.mtcoding.buyer.model.PurchaseRepository;

/*
 * IoC에 등록되는 어노테이션 : @Controller, @RestController, @Mapper, @Service, @Component
 */

@Service // IoC 컨테이너 등록
public class PurchaseService {

    @Autowired
    private PurchaseRepository purchaseRepository;

    @Autowired
    private ProductRepository productRepository;

    @Transactional
    // 트랜잭션 종료시에 commit이 됨, insert가 되어도 밑에 메서드 다 수행하고 commit이 됨
    // 다른 애가 못 들어오는 독립성, commit이 안되는 상황을 활용할 수 있음
    // 도중에 걸리면 롤 백
    public int 구매하기(int principalId, int productId, int count) {

        // 아래와 같이 updateById가 아닌 updateCount식으로 만든다면
        // update 문의 뒤에 where id = #{productId}에서 악의적으로 productId를 100000으로 날릴 수 있음
        // 따라서 productRepositroy에 productId로 있는지 체크 함

        // 1. 상품 존재 확인
        Product product = productRepository.findById(productId);

        if (product == null) {
            return -1;
        }

        // 2. 수량 체크
        if (product.getQty() < count) {
            // 기본적으로 DB내부에서 터져야 롤백
            // DB에서 터져야 롤백되므로 이렇게 되면
            // 앞에 내용은 반영 되고, 밑에 꺼는 반영안되게 됨
            return -1;
            // throw new RuntimeException();
        }

        // 3. 수량이든 뭐든 변경
        int result2 = productRepository.updateById(product.getId(), product.getName(), product.getPrice(),
                product.getQty() - count);

        if (result2 != 1) { // -1 x 0일수도 있음
            return -1;
        }

        // 4. 구매 히스토리,이력 남기기 (insert)
        int result = purchaseRepository.insert(principalId, productId, count);

        if (result != 1) {
            return -1; // -1을 보고 controller로 제어
        }

        return 1;
        // commit되는 순간
    }
}
