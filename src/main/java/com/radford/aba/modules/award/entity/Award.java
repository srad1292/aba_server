package com.radford.aba.modules.award.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.radford.aba.shared.validator.NotOnlyWhiteSpaceConstraint;

@Entity
@Table(name="award")
@SequenceGenerator(name="award_seq", allocationSize=1)
public class Award {

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="award_seq")
	private Integer id;
	
	@Column(name="award_name",length=30)
	@NotOnlyWhiteSpaceConstraint
	private String awardName;

	public Award() {}
	
	public Award(Integer id, String awardName) {
		this.setId(id);
		this.setAwardName(awardName);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAwardName() {
		return awardName;
	}

	public void setAwardName(String awardName) {
		this.awardName = awardName;
	}
	
}
