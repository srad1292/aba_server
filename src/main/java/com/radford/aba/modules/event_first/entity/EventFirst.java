/**
 * 
 */
package com.radford.aba.modules.event_first.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import com.radford.aba.shared.validator.NotOnlyWhiteSpaceConstraint;

/**
 * @author smr12
 *
 */
@Entity
@Table(name="event_first")
@SequenceGenerator(name="event_first_seq", allocationSize=1)
public class EventFirst {


	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="event_first_seq")
	private Integer id;
	
	@Size(min=1, max = 30)
	@Column(name="event_name", length = 30)
	@NotOnlyWhiteSpaceConstraint
	private String eventName;
	
	@Column(name="event_date")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@NotNull
	private Date eventDate;
	
	@Size(min=1, max = 500)
	@Column(name="event_description", length = 500)
	@NotOnlyWhiteSpaceConstraint
	private String eventDescription;


	public EventFirst() {
		super();
	}
	
	public EventFirst(Integer id, String eventName, Date eventDate) {
		super();
		this.id = id;
		this.eventName = eventName;
		this.eventDate = eventDate;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public Date getEventDate() {
		return eventDate;
	}

	public void setEventDate(Date eventDate) {
		this.eventDate = eventDate;
	}

	public String getEventDescription() {
		return eventDescription;
	}

	public void setEventDescription(String eventDescription) {
		this.eventDescription = eventDescription;
	}
	
	

}
