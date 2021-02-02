package json.app.service;

import com.google.gson.Gson;
import json.app.Util.contract.ValidatorUtil;
import json.app.domain.entity.Product;
import json.app.domain.model.binding.UserSeedDto;
import json.app.domain.entity.User;
import json.app.domain.model.view.ProductDto;
import json.app.domain.model.view.query2.SuccessfullySoldProductsDto;
import json.app.domain.model.view.query4.ProductCountDto;
import json.app.domain.model.view.query4.UserDto;
import json.app.domain.model.view.query4.UsersAndProductsDto;
import json.app.repository.UserRepository;
import json.app.service.contract.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ValidatorUtil validatorUtil;
    private final ModelMapper modelMapper;
    private final Gson gson;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, ValidatorUtil validatorUtil, ModelMapper modelMapper, Gson gson) {
        this.userRepository = userRepository;
        this.validatorUtil = validatorUtil;
        this.modelMapper = modelMapper;
        this.gson = gson;
    }

    @Transactional
    @Override
    public void seedUser(String jsonUsers) {
        if (this.userRepository.count() != 0) {
            return;
        }
        UserSeedDto[] userSeedDtos = this.gson.fromJson(jsonUsers, UserSeedDto[].class);

        for (UserSeedDto userSeedDto : userSeedDtos) {
            if (this.validatorUtil.ifNotValidPrintViolations(userSeedDto)) {
                return;
            }
            User user = this.modelMapper.map(userSeedDto, User.class);
            this.userRepository.saveAndFlush(user);
        }
        this.addFriends();
    }

    private void addFriends(){
        System.out.println();
        for (int i = 1; i <= this.getCount(); i++) {
            User user = this.findUserById(i);
            user.getFriends().addAll(this.getRandomUsers());
            this.userRepository.saveAndFlush(user);
        }
    }


    @Override
    public int getCount() {
        return Math.toIntExact(this.userRepository.count());
    }

    @Override
    public User findUserById(Integer id) {
        return this.userRepository.findById(id).orElse(null);
    }

    @Override
    public Set<User> getRandomUsers() {
        Set<User> friends = new HashSet<>();
        Random random = new Random();
        for (int i = 0; i < random.nextInt(this.getCount()) + 1; i++) {
            friends.add(this.getRandomUser());
        }
        return friends;
    }

    @Override
    public User getRandomUser(){
        Random random = new Random();
        return this.findUserById(random.nextInt(this.getCount()) + 1);
    }

    //Query 2
    @Override
    @Transactional
    public String soldProducts(){
        Set<User> sellers = this.userRepository.findAllSellersWithItemSold();
        List<SuccessfullySoldProductsDto> soldProductsDtos = new ArrayList<>();
        for (User seller : sellers) {
            soldProductsDtos.add(this.modelMapper.map(seller, SuccessfullySoldProductsDto.class));
        }
        System.out.println();
        return this.gson.toJson(soldProductsDtos);
    }


    //Query 4
    @Override
    @Transactional
    public String usersWithAtLeastOneSoldProduct() {
        UsersAndProductsDto usersAndProductsDto = new UsersAndProductsDto();
        usersAndProductsDto.setUserCount(this.userRepository.countOfSellers().size());
        List<User> users = this.userRepository.countOfSellers();
        for (User user : users) {

            UserDto userDto = this.modelMapper.map(user, UserDto.class);
            userDto.getSoldProducts().setCount(user.getProductsSold().size());
            usersAndProductsDto.getUsers().add(userDto);
        }
        System.out.println();
        return this.gson.toJson(usersAndProductsDto);
    }


}
