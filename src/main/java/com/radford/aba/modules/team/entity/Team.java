package com.radford.aba.modules.team.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import com.radford.aba.shared.validator.NotOnlyWhiteSpaceConstraint;

@Entity
@Table(name="team")
@SequenceGenerator(name="team_seq", allocationSize=1)
public class Team {

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="team_seq")
	private Integer id;
	
	@Size(min=1, max=30)
	@Column(name="city", length=30)
	@NotOnlyWhiteSpaceConstraint
	private String city;
	
	@Size(min=1, max=30)
	@Column(name="nickname", length=30)
	@NotOnlyWhiteSpaceConstraint
	private String nickname;
	
	public Team() {
		super();
	}

	/**
	 * @param id
	 * @param city
	 * @param nickname
	 */
	public Team(Integer id, @Size(min = 1, max = 30) String city, @Size(min = 1, max = 30) String nickname) {
		super();
		this.id = id;
		this.city = city;
		this.nickname = nickname;
	}

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * @return the nickname
	 */
	public String getNickname() {
		return nickname;
	}

	/**
	 * @param nickname the nickname to set
	 */
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	
	
}
