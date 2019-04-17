package com.devchallengev2.devapi.domains;

import com.devchallengev2.devapi.utils.SystemUtils;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "users")
public class User implements Serializable {

  @Id
  @SequenceGenerator(name = "user_generator", sequenceName = "user_seq")
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_generator")
  private long id;
  private String user_key = SystemUtils.generateKey(10, false, true);
  private String firstName;
  private String lastName;
  private String email;
  private int phone;
  private int status;
  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
  @JsonIgnore
  private List<House> houses = new ArrayList<>();

  public void setHouse(House house) {
    if (houses != null) {
      this.houses.add(house);
    } else {
      houses = new ArrayList<>();
      houses.add(house);
    }
  }

}
