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
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
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
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import com.axelor.auth.db.AuditableModel;
import com.axelor.db.annotations.Widget;
import com.google.common.base.MoreObjects;

@Entity
@Cacheable
@Table(name = "REGISTRATION_EVENT", indexes = { @Index(columnList = "venu") })
public class Event extends AuditableModel {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "REGISTRATION_EVENT_SEQ")
	@SequenceGenerator(name = "REGISTRATION_EVENT_SEQ", sequenceName = "REGISTRATION_EVENT_SEQ", allocationSize = 1)
	private Long id;

	@Widget(title = "Reference")
	private String reference;

	@Widget(title = "Start Date")
	private LocalDateTime startDate;

	@Widget(title = "End Date")
	private LocalDateTime endDate;

	@Widget(title = "Venu")
	@ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	private Address venu;

	@Widget(title = "Registration Open")
	private LocalDate registrationOpen;

	@Widget(title = "Registration Close")
	private LocalDate registrationClose;

	@Widget(title = "Capacity")
	private Integer capacity = 0;

	@Widget(title = "Fees")
	private BigDecimal fees = BigDecimal.ZERO;

	@Widget(title = "Discounts")
	@OneToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	private List<Discount> discount;

	@Widget(title = "Event Registrations")
	@OneToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	private List<EventRegistration> eventRegistration;

	@Widget(title = "Total Entery", readonly = true)
	private Integer totalEntery = 0;

	@Widget(title = "Amount Collected", readonly = true)
	private BigDecimal amountCollected = BigDecimal.ZERO;

	@Widget(title = "Total Discount", readonly = true)
	private BigDecimal totalDiscount = BigDecimal.ZERO;

	@Widget(title = "Description")
	private String description;

	@Widget(title = "Attributes")
	@Basic(fetch = FetchType.LAZY)
	@Type(type = "json")
	private String attrs;

	public Event() {
	}

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public LocalDateTime getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDateTime startDate) {
		this.startDate = startDate;
	}

	public LocalDateTime getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDateTime endDate) {
		this.endDate = endDate;
	}

	public Address getVenu() {
		return venu;
	}

	public void setVenu(Address venu) {
		this.venu = venu;
	}

	public LocalDate getRegistrationOpen() {
		return registrationOpen;
	}

	public void setRegistrationOpen(LocalDate registrationOpen) {
		this.registrationOpen = registrationOpen;
	}

	public LocalDate getRegistrationClose() {
		return registrationClose;
	}

	public void setRegistrationClose(LocalDate registrationClose) {
		this.registrationClose = registrationClose;
	}

	public Integer getCapacity() {
		return capacity == null ? 0 : capacity;
	}

	public void setCapacity(Integer capacity) {
		this.capacity = capacity;
	}

	public BigDecimal getFees() {
		return fees == null ? BigDecimal.ZERO : fees;
	}

	public void setFees(BigDecimal fees) {
		this.fees = fees;
	}

	public List<Discount> getDiscount() {
		return discount;
	}

	public void setDiscount(List<Discount> discount) {
		this.discount = discount;
	}

	/**
	 * Add the given {@link Discount} item to the {@code discount}.
	 *
	 * @param item
	 *            the item to add
	 */
	public void addDiscount(Discount item) {
		if (getDiscount() == null) {
			setDiscount(new ArrayList<>());
		}
		getDiscount().add(item);
	}

	/**
	 * Remove the given {@link Discount} item from the {@code discount}.
	 *
	 * <p>
	 * It sets {@code item.null = null} to break the relationship.
	 * </p>
	 *
 	 * @param item
	 *            the item to remove
	 */
	public void removeDiscount(Discount item) {
		if (getDiscount() == null) {
			return;
		}
		getDiscount().remove(item);
	}

	/**
	 * Clear the {@code discount} collection.
	 *
	 * <p>
	 * It sets {@code item.null = null} to break the relationship.
	 * </p>
	 */
	public void clearDiscount() {
		if (getDiscount() != null) {
			getDiscount().clear();
		}
	}

	public List<EventRegistration> getEventRegistration() {
		return eventRegistration;
	}

	public void setEventRegistration(List<EventRegistration> eventRegistration) {
		this.eventRegistration = eventRegistration;
	}

	/**
	 * Add the given {@link EventRegistration} item to the {@code eventRegistration}.
	 *
	 * @param item
	 *            the item to add
	 */
	public void addEventRegistration(EventRegistration item) {
		if (getEventRegistration() == null) {
			setEventRegistration(new ArrayList<>());
		}
		getEventRegistration().add(item);
	}

	/**
	 * Remove the given {@link EventRegistration} item from the {@code eventRegistration}.
	 *
	 * <p>
	 * It sets {@code item.null = null} to break the relationship.
	 * </p>
	 *
 	 * @param item
	 *            the item to remove
	 */
	public void removeEventRegistration(EventRegistration item) {
		if (getEventRegistration() == null) {
			return;
		}
		getEventRegistration().remove(item);
	}

	/**
	 * Clear the {@code eventRegistration} collection.
	 *
	 * <p>
	 * It sets {@code item.null = null} to break the relationship.
	 * </p>
	 */
	public void clearEventRegistration() {
		if (getEventRegistration() != null) {
			getEventRegistration().clear();
		}
	}

	public Integer getTotalEntery() {
		return totalEntery == null ? 0 : totalEntery;
	}

	public void setTotalEntery(Integer totalEntery) {
		this.totalEntery = totalEntery;
	}

	public BigDecimal getAmountCollected() {
		return amountCollected == null ? BigDecimal.ZERO : amountCollected;
	}

	public void setAmountCollected(BigDecimal amountCollected) {
		this.amountCollected = amountCollected;
	}

	public BigDecimal getTotalDiscount() {
		return totalDiscount == null ? BigDecimal.ZERO : totalDiscount;
	}

	public void setTotalDiscount(BigDecimal totalDiscount) {
		this.totalDiscount = totalDiscount;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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
		if (!(obj instanceof Event)) return false;

		final Event other = (Event) obj;
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
			.add("reference", getReference())
			.add("startDate", getStartDate())
			.add("endDate", getEndDate())
			.add("registrationOpen", getRegistrationOpen())
			.add("registrationClose", getRegistrationClose())
			.add("capacity", getCapacity())
			.add("fees", getFees())
			.add("totalEntery", getTotalEntery())
			.add("amountCollected", getAmountCollected())
			.add("totalDiscount", getTotalDiscount())
			.add("description", getDescription())
			.omitNullValues()
			.toString();
	}
}
