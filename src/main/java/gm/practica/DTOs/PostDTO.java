package gm.practica.DTOs;

import gm.practica.entities.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PostDTO {
    private Long userId;
    private Date date;
    private Product product;
    private Integer category;
    private Double price;
}