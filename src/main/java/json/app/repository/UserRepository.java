package json.app.repository;

import json.app.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Set;

public interface UserRepository extends JpaRepository<User, Integer> {

    @Query("select u from User u join u.productsSold p where p.buyer is not null")
    Set<User> findAllSellersWithItemSold();

    @Query("select u from User u where u.productsSold.size > 0")
    List<User> countOfSellers();
}
