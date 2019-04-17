package com.devchallengev2.devapi;

import com.devchallengev2.devapi.repositories.AgentRepository;
import com.devchallengev2.devapi.repositories.HouseRepository;
import com.devchallengev2.devapi.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DevapiApplication implements CommandLineRunner {

  @Autowired
  UserRepository userRepository;
  @Autowired
  AgentRepository agentRepository;
  @Autowired
  HouseRepository houseRepository;

  public static void main(String[] args) {
    SpringApplication.run(DevapiApplication.class, args);
  }

  @Override
  public void run(String... args) throws Exception {
        /*User user = new User();
        user.setEmail("irpezzi01");
        user.setFirstName("Olivier");
        user.setLastName("twist");
        user.setPhone(781884367);

        House house = new House();
        house.setLatitude(-1.951208);
        house.setLongitude(30.084052);
        house.setLocation("Kimihurura");
        user.setHouse(house);
        userRepository.save(user);
        Agent agent = new Agent();
        agent.setDob(new Date());
        agent.setEmail("agent@gmail.com");
        agent.setFirstName("IRADUKUNDA");
        agent.setLastName("Olivier");
        agent.setPhone(781884367);
        agent.setSex(Gender.MALE.name());
        agent.setStatus(1);
        agent.setPassword("123456");
        agentRepository.save(agent);*/
//        System.out.println(SystemUtils.generateKey(250, false, true));
        /*House house = houseRepository.findById(1L).get();
        User user = userRepository.findById(1L).get();
        house.setUser(user);
        houseRepository.save(house);*/
    /*House house = new House();
    house.setLatitude(-1.952802);
    house.setLongitude(30.046300);
    house.setLocation("Kimisagara");
    User user = userRepository.findById(1L).get();
    house.setUser(user);
    houseRepository.save(house);*/
  }
}
