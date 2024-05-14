package gm.practica.repository;

import gm.practica.DTOs.ProductDTO;
import gm.practica.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByUserId(Long userId);
}
