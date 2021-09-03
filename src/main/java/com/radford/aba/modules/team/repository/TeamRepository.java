package com.radford.aba.modules.team.repository;

import org.springframework.data.repository.CrudRepository;

import com.radford.aba.modules.team.entity.Team;

public interface TeamRepository extends CrudRepository<Team, Integer> {

}
