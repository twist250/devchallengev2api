package com.devchallengev2.devapi.domains;

import com.devchallengev2.devapi.utils.SystemUtils;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
public class House implements Serializable {

  @Id
  @SequenceGenerator(name = "house_generator", sequenceName = "house_seq")
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "house_generator")
  private long id;
  private String houseKey = SystemUtils.generateKey(10, false, true);
  private String location;
  private double latitude;
  private double longitude;
  private Integer status;
  private String sectorKey = SystemUtils.generateKey(10, false, true);
  @ManyToOne
  @JoinColumn(name = "user_key")
  private User user;
}
