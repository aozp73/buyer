package shop.mtcoding.buyer.model;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ProductRepository {
    public int insert(String name, String price, String qty);

    public List<Product> findAll();

    public Product findById(int id);

    public int updateById(String name, String price, String qty);

    public int deleteById(int id);
}
