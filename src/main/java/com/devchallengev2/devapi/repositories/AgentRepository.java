package com.devchallengev2.devapi.repositories;

import com.devchallengev2.devapi.domains.Agent;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

@RepositoryRestResource(path = "agents", collectionResourceRel = "agents")
public interface AgentRepository extends PagingAndSortingRepository<Agent, Long> {
    @RestResource(path = "email", rel = "email")
    Agent findByEmail(@Param(value = "email") String email);
}
