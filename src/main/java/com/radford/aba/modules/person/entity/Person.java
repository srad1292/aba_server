/**
 * 
 */
package com.radford.aba.modules.person.entity;

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

import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.radford.aba.modules.team.entity.Team;
import com.radford.aba.shared.validator.NotOnlyWhiteSpaceConstraint;

/**
 * @author smr12
 *
 */
@Entity
@Table(name="person")
@SequenceGenerator(name="person_seq", allocationSize=1)
public class Person {

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="person_seq")
	private Integer id;
	
	@Size(min=1, max=80)
	@Column(name="full_name", length=80)
	@NotOnlyWhiteSpaceConstraint
	private String fullName;
	
	@Column(name="birth_date")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@NotNull
	private Date birthDate;
	
	@Size(min=1, max=30)
	@Column(name="ootp_id", length=30, unique=true)
	@NotOnlyWhiteSpaceConstraint
	private String ootpID;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="current_team")
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	private Team currentTeam;
	
	@Size(min=1, max=30)
	@Column(name="primary_position", length=30)
	@NotOnlyWhiteSpaceConstraint
	private String primaryPosition;
	
	@Column(name="is_manager")
	@NotNull
	private Boolean isManager;
	
	@Column(name="is_retired")
	@NotNull
	private Boolean isRetired;
	
	@Column(name="rookie_year")
	@Range(min=1850, max=3000)
	@NotNull
	private Integer rookieYear;

	public Person() {
		super();
	}
	
	/**
	 * @param id
	 * @param fullName
	 * @param birthDate
	 * @param ootpID
	 * @param currentTeam
	 * @param primaryPosition
	 * @param isManager
	 * @param isRetired
	 * @param rookieYear
	 */
	public Person(Integer id, @Size(min = 1, max = 80) String fullName, @NotNull Date birthDate,
			@Size(min = 1, max = 30) String ootpID, Team currentTeam, @Size(min = 1, max = 30) String primaryPosition,
			@NotNull Boolean isManager, @NotNull Boolean isRetired,
			@Range(min = 1850, max = 3000) @NotNull Integer rookieYear) {
		super();
		this.id = id;
		this.fullName = fullName;
		this.birthDate = birthDate;
		this.ootpID = ootpID;
		this.currentTeam = currentTeam;
		this.primaryPosition = primaryPosition;
		this.isManager = isManager;
		this.isRetired = isRetired;
		this.rookieYear = rookieYear;
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
	 * @return the fullName
	 */
	public String getFullName() {
		return fullName;
	}

	/**
	 * @param fullName the fullName to set
	 */
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	/**
	 * @return the birthDate
	 */
	public Date getBirthDate() {
		return birthDate;
	}

	/**
	 * @param birthDate the birthDate to set
	 */
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	/**
	 * @return the ootpID
	 */
	public String getOotpID() {
		return ootpID;
	}

	/**
	 * @param ootpID the ootpID to set
	 */
	public void setOotpID(String ootpID) {
		this.ootpID = ootpID;
	}

	/**
	 * @return the currentTeam
	 */
	public Team getCurrentTeam() {
		return currentTeam;
	}

	/**
	 * @param currentTeam the currentTeam to set
	 */
	public void setCurrentTeam(Team currentTeam) {
		this.currentTeam = currentTeam;
	}

	/**
	 * @return the primaryPosition
	 */
	public String getPrimaryPosition() {
		return primaryPosition;
	}

	/**
	 * @param primaryPosition the primaryPosition to set
	 */
	public void setPrimaryPosition(String primaryPosition) {
		this.primaryPosition = primaryPosition;
	}

	/**
	 * @return the isManager
	 */
	public Boolean getIsManager() {
		return isManager;
	}

	/**
	 * @param isManager the isManager to set
	 */
	public void setIsManager(Boolean isManager) {
		this.isManager = isManager;
	}

	/**
	 * @return the isRetired
	 */
	public Boolean getIsRetired() {
		return isRetired;
	}

	/**
	 * @param isRetired the isRetired to set
	 */
	public void setIsRetired(Boolean isRetired) {
		this.isRetired = isRetired;
	}

	/**
	 * @return the rookieYear
	 */
	public Integer getRookieYear() {
		return rookieYear;
	}

	/**
	 * @param rookieYear the rookieYear to set
	 */
	public void setRookieYear(Integer rookieYear) {
		this.rookieYear = rookieYear;
	}
	
	
	
}
