package shop.mtcoding.buyer.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PurchaseAllDto {
    private int id;
    private String username;
    private String name;
    private int count;
}
