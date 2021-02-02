package json.app.service.contract;

import json.app.domain.entity.User;

import java.util.List;
import java.util.Set;

public interface UserService {

    void seedUser(String jsonUsers);

    Set<User> getRandomUsers();

    int getCount();

    User findUserById(Integer id);

    User getRandomUser();

    //Query 2
    String soldProducts();

    //Query 4
    String usersWithAtLeastOneSoldProduct();
}
