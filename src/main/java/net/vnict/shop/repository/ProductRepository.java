package net.vnict.shop.repository;

import net.vnict.shop.domain.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    @Query("select p from Product p")
    List<Product> findAll();

    List<Product> findTop10ByOrderByIdDesc();

    List<Product> findByNameContaining(String keyword);

    @Query("select p from Product p left join fetch p.category c where c.id = ?1")
    List<Product> findByCategoryId(Integer categoryId);

    @Query("select p from Product p left join fetch p.category c where p.id = ?1")
    Product findOneWithCategory(Integer id);

}
