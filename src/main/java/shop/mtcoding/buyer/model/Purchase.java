package shop.mtcoding.buyer.model;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Purchase {
    private Integer id;
    private Integer userId;
    private Integer productId;
    private Timestamp createdAt;
}
