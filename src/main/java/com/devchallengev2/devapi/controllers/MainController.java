package com.devchallengev2.devapi.controllers;

import com.devchallengev2.devapi.domains.Agent;
import com.devchallengev2.devapi.domains.House;
import com.devchallengev2.devapi.domains.User;
import com.devchallengev2.devapi.repositories.AgentRepository;
import com.devchallengev2.devapi.repositories.HouseRepository;
import com.devchallengev2.devapi.repositories.UserRepository;
import com.devchallengev2.devapi.utils.JavaEmail;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@RestController
public class MainController {

  final
  AgentRepository agentRepository;
  final
  HouseRepository houseRepository;
  final
  UserRepository userRepository;
  final
  TemplateEngine templateEngine;

  @Autowired
  public MainController(AgentRepository agentRepository, HouseRepository houseRepository,
      UserRepository userRepository, TemplateEngine templateEngine) {
    this.agentRepository = agentRepository;
    this.houseRepository = houseRepository;
    this.userRepository = userRepository;
    this.templateEngine = templateEngine;
  }

  @GetMapping(value = "/agents")
  public Agent getAgentByEmail(@RequestParam(name = "email") String email) {
    return agentRepository.findByEmail(email);
  }

  @GetMapping(value = "/sector/{key}/house")
  public Iterable<House> getHousesBySectorKey(@PathVariable(name = "key") String sectorKey) {
    return houseRepository.findAll();
  }

  @GetMapping("/search/houses/{user_name}")
  public List<House> getHouses(@PathVariable String user_name) {
    return houseRepository.getHousesByUserFirstNameOrUserLastName(user_name, user_name);
  }

  @GetMapping("/houses/{key}")
  public House getHouseBykey(@PathVariable String key) {
    return houseRepository.getHouseByHouseKey(key);
  }

  @PostMapping("/houses/{userId}")
  public House registerHouse(@RequestBody House house, @PathVariable Long userId) {
    Optional<User> optionalUser = userRepository.findById(userId);
    if (optionalUser.isPresent()) {
      User user = optionalUser.get();
      house.setUser(user);
      house = houseRepository.save(house);
    }
    return house;
  }

  @GetMapping("/users/{userKey}")
  public User verifyUser(@PathVariable String userKey) {
    return userRepository.findByUserKey(userKey);
  }

  @GetMapping("/users/id/{id}")
  public User getUserById(@PathVariable long id) {
    return userRepository.findById(id)
        .orElse(new User());
  }

  @PostMapping("/houses/print/{houseKey}")
  public House sendReceiptByEmail(@PathVariable String houseKey,
      @RequestBody Map<String, String> datas) {
    House house = houseRepository.getHouseByHouseKey(houseKey);
    house.setStatus(1);
    houseRepository.save(house);
    String receipt = datas.get("receipt");
    String to = datas.get("recipient");
    System.out.println("to-> " + to + " receipt->" + receipt);
    String msg = thymeleafTemplating(house, receipt);

    JavaEmail javaEmail = new JavaEmail();
    javaEmail.from("irpezzi01@gmail.com");
    javaEmail.to(to);
    javaEmail.subject(receipt + " Receipt");
    javaEmail.html(msg);
    javaEmail.send();
    return house;
  }

  private String thymeleafTemplating(House house, String receipt) {
    Context context = new Context();
    context.setVariable("house", house);
    context.setVariable("receipt", receipt);
    return templateEngine.process("emailtemplate.html", context);
  }

}
