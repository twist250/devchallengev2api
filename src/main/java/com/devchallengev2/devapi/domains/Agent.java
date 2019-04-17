package com.devchallengev2.devapi.domains;

import com.devchallengev2.devapi.utils.SystemUtils;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@NoArgsConstructor
public class Agent {
    @Id
    @SequenceGenerator(name = "agent_generator", sequenceName = "agent_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "agent_generator")
    private long id;
    private String agentKey = SystemUtils.generateKey(10, false, true);
    @Temporal(TemporalType.DATE)
    private Date dob;
    private String email;
    private String firstName;
    private String lastName;
    private int phone;
    private String sex;
    private int status;
    private String password;
}
