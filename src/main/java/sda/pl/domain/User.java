package sda.pl.domain;


import lombok.*;
import sda.pl.repository.CartRepository;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(exclude = {"productRatingSet", "cart", "orderSet"})
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String firstName;
    String lastName;
    @Column(unique = true)
    String email;
    String zipCode;
    String cityName;
    String street;
    String password;
    @OneToMany(mappedBy = "user")
    Set<Order> orderSet;
    @OneToOne(mappedBy = "user")
    Cart cart;
    @OneToMany(mappedBy = "user")
    Set<ProductRating> productRatingSet;

    @Transient
    BigDecimal totalOrderPrice;

   public User(Long id, String email, BigDecimal totalOrderPrice){
        this.id = id;
        this.email = email;
        this.totalOrderPrice = totalOrderPrice;
    }


    public Cart createCart(){
        Cart cart = new Cart();
        cart.setUser(this);
        return cart;
    }

    public ProductRating rateProduct(int rate, String description, Product product){
       ProductRating productRating = new ProductRating();
       productRating.setActive(false);
       productRating.setRate(rate);
       productRating.setDescription(description);
       productRating.setProduct(product);
       productRating.setUser(this);
       return productRating;
    }

}
