package json.app.domain.entity;

import json.app.domain.BaseEntity;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static javax.persistence.CascadeType.*;

@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@Entity
@NoArgsConstructor
@Data
@Table(name = "users")
public class User extends BaseEntity {

    @Column(length = 20)
    private String firstName;

    @NonNull
    @NotNull
    @Length(min = 3)
    @Column(nullable = false, length = 20)
    private String lastName;

    @PositiveOrZero(message = "Age cannot be negative.")
    @Column(columnDefinition = "TINYINT UNSIGNED")
    private Short age;

    @ManyToMany(cascade = {MERGE, REFRESH})
    @ToString.Exclude
    private Set<User> friends = new HashSet<>();

    @OneToMany(mappedBy = "buyer")
    private Set<Product> productsBought = new HashSet<>();

    @OneToMany(mappedBy = "seller")
    private List<Product> productsSold = new ArrayList<>();
}
