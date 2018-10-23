/*
 * Axelor Business Solutions
 * 
 * Copyright (C) 2005-2018 Axelor (<http://axelor.com>).
 * 
 * This program is free software: you can redistribute it and/or  modify
 * it under the terms of the GNU Affero General Public License, version 3,
 * as published by the Free Software Foundation.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.axelor.apps.event.registration.db;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.Basic;
import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import com.axelor.auth.db.AuditableModel;
import com.axelor.db.annotations.Widget;
import com.google.common.base.MoreObjects;

@Entity
@Cacheable
@Table(name = "REGISTRATION_EVENT_REGISTRATION", indexes = { @Index(columnList = "name"), @Index(columnList = "event"), @Index(columnList = "address") })
public class EventRegistration extends AuditableModel {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "REGISTRATION_EVENT_REGISTRATION_SEQ")
	@SequenceGenerator(name = "REGISTRATION_EVENT_REGISTRATION_SEQ", sequenceName = "REGISTRATION_EVENT_REGISTRATION_SEQ", allocationSize = 1)
	private Long id;

	@Widget(title = "Name")
	private String name;

	@Widget(title = "Event")
	@ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	private Event event;

	@Widget(title = "Email")
	private String email;

	@Widget(title = "Address")
	@ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	private Address address;

	@Widget(title = "Registration Date")
	private LocalDateTime registrationDate;

	@Widget(title = "Amount", readonly = true)
	private BigDecimal amount = BigDecimal.ZERO;

	@Widget(title = "Email Sent", readonly = true)
	private Boolean emailSent = Boolean.FALSE;

	@Widget(title = "Attributes")
	@Basic(fetch = FetchType.LAZY)
	@Type(type = "json")
	private String attrs;

	public EventRegistration() {
	}

	public EventRegistration(String name) {
		this.name = name;
	}

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public LocalDateTime getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(LocalDateTime registrationDate) {
		this.registrationDate = registrationDate;
	}

	public BigDecimal getAmount() {
		return amount == null ? BigDecimal.ZERO : amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public Boolean getEmailSent() {
		return emailSent == null ? Boolean.FALSE : emailSent;
	}

	public void setEmailSent(Boolean emailSent) {
		this.emailSent = emailSent;
	}

	public String getAttrs() {
		return attrs;
	}

	public void setAttrs(String attrs) {
		this.attrs = attrs;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) return false;
		if (this == obj) return true;
		if (!(obj instanceof EventRegistration)) return false;

		final EventRegistration other = (EventRegistration) obj;
		if (this.getId() != null || other.getId() != null) {
			return Objects.equals(this.getId(), other.getId());
		}

		return false;
	}

	@Override
	public int hashCode() {
		return 31;
	}

	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this)
			.add("id", getId())
			.add("name", getName())
			.add("email", getEmail())
			.add("registrationDate", getRegistrationDate())
			.add("amount", getAmount())
			.add("emailSent", getEmailSent())
			.omitNullValues()
			.toString();
	}
}
