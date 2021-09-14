package com.radford.aba.modules.award.respository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import com.radford.aba.modules.award.entity.AwardHistory;

public interface AwardHistoryRepository extends CrudRepository<AwardHistory, Integer> {

	Page<AwardHistory> findAll(Pageable page);
}
