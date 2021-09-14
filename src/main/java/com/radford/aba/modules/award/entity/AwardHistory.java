package com.radford.aba.modules.award.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.radford.aba.modules.person.entity.Person;
import com.radford.aba.modules.team.entity.Team;

@Entity
@Table(name="award_history")
@SequenceGenerator(name="award_history_seq", allocationSize=1)
public class AwardHistory {

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="award_history_seq")
	private Integer id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="award")
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@NotNull
	private Award award;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="person")
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	private Person person;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="team")
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	private Team team;
	
	@Column(name="award_date")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@NotNull
	private Date awardDate;
	
	@Column(name="related_stats", length = 80)
	@Size(min=0, max=80)
	private String relatedStats;
	
	public AwardHistory() {
		super();
	}

	/**
	 * @param id
	 * @param award
	 * @param person
	 * @param team
	 * @param awardDate
	 * @param relatedStats
	 */
	public AwardHistory(Integer id, @NotNull Award award, Person person, Team team, @NotNull Date awardDate,
			@Size(min = 0, max = 80) String relatedStats) {
		super();
		this.id = id;
		this.award = award;
		this.person = person;
		this.team = team;
		this.awardDate = awardDate;
		this.relatedStats = relatedStats;
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
	 * @return the award
	 */
	public Award getAward() {
		return award;
	}

	/**
	 * @param award the award to set
	 */
	public void setAward(Award award) {
		this.award = award;
	}

	/**
	 * @return the person
	 */
	public Person getPerson() {
		return person;
	}

	/**
	 * @param person the person to set
	 */
	public void setPerson(Person person) {
		this.person = person;
	}

	/**
	 * @return the team
	 */
	public Team getTeam() {
		return team;
	}

	/**
	 * @param team the team to set
	 */
	public void setTeam(Team team) {
		this.team = team;
	}

	/**
	 * @return the awardDate
	 */
	public Date getAwardDate() {
		return awardDate;
	}

	/**
	 * @param awardDate the awardDate to set
	 */
	public void setAwardDate(Date awardDate) {
		this.awardDate = awardDate;
	}

	/**
	 * @return the relatedStats
	 */
	public String getRelatedStats() {
		return relatedStats;
	}

	/**
	 * @param relatedStats the relatedStats to set
	 */
	public void setRelatedStats(String relatedStats) {
		this.relatedStats = relatedStats;
	}
	
	
}
