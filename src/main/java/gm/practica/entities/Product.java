package gm.practica.entities;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    @Column(name = "product_name")
    private String productName;

    @Column
    private String type;

    @Column
    private String brand;

    @Column
    private String color;

    @Column
    private String notes;

    @Column
    private Integer category;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}