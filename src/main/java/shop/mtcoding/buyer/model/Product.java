package shop.mtcoding.buyer.model;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;
import shop.mtcoding.buyer.util.DataUtil;

@Setter
@Getter
public class Product {
    private Integer id;
    private String name;
    private Integer price;
    private Integer qty;
    private Timestamp createdAt;

    // ${product.createdAt} 은 getreatedAt()이 호출
    // el표현식에서 CreatedAtString가 호출 됨
    public String getCreatedAtToString() {
        return DataUtil.format(createdAt);
    }
}
