package com.example.segomarketnew.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "buckets")
@Builder
public class Bucket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    }, fetch = FetchType.LAZY)
    @JoinTable(name = "buckets_products",
    joinColumns = @JoinColumn(name = "bucket_id"),
    inverseJoinColumns = @JoinColumn(name = "product_id"))
    private List<Product> products;

    public void removeProductById(Long id){
        Product product = this.products.stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Product not found"));
        if (product != null){
                this.products.remove(product);
        }
    }

}
