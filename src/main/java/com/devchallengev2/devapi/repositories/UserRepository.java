package com.devchallengev2.devapi.repositories;

import com.devchallengev2.devapi.domains.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "users", collectionResourceRel = "users")
public interface UserRepository extends PagingAndSortingRepository<User, Long> {

  @Query(value = "from User u where u.user_key =:userKey")
  User findByUserKey(@Param(value = "userKey") String userKey);
}
