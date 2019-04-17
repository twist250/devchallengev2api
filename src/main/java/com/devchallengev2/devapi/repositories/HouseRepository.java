package com.devchallengev2.devapi.repositories;

import com.devchallengev2.devapi.domains.House;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(path = "houses",collectionResourceRel = "houses")
public interface HouseRepository extends PagingAndSortingRepository<House,Long> {

    List<House> getHousesByUserFirstNameOrUserLastName(String user_firstName, String user_lastName);

    House getHouseByHouseKey(String houseKey);
}
