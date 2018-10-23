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

import java.util.Objects;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Basic;
import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Type;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.axelor.auth.db.AuditableModel;
import com.axelor.db.annotations.NameColumn;
import com.axelor.db.annotations.VirtualColumn;
import com.axelor.db.annotations.Widget;
import com.google.common.base.MoreObjects;

@Entity
@Cacheable
@DynamicInsert
@DynamicUpdate
@Table(name = "REGISTRATION_ADDRESS", indexes = { @Index(columnList = "fullName") })
public class Address extends AuditableModel {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "REGISTRATION_ADDRESS_SEQ")
	@SequenceGenerator(name = "REGISTRATION_ADDRESS_SEQ", sequenceName = "REGISTRATION_ADDRESS_SEQ", allocationSize = 1)
	private Long id;

	@Widget(title = "Flat/HouseNo")
	private String houseNo;

	@Widget(title = "Street")
	private String street;

	@Widget(title = "LandMark")
	private String landMark;

	@Widget(title = "City")
	private String city;

	@Widget(title = "Country")
	private String country;

	@Widget(title = "Full Name", readonly = true, search = { "houseNo", "street", "landMark", "city", "country" })
	@NameColumn
	@VirtualColumn
	@Access(AccessType.PROPERTY)
	private String fullName;

	@Widget(title = "Attributes")
	@Basic(fetch = FetchType.LAZY)
	@Type(type = "json")
	private String attrs;

	public Address() {
	}

	public Address(String houseNo, String street, String landMark, String city, String country) {
		this.houseNo = houseNo;
		this.street = street;
		this.landMark = landMark;
		this.city = city;
		this.country = country;
	}

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	public String getHouseNo() {
		return houseNo;
	}

	public void setHouseNo(String houseNo) {
		this.houseNo = houseNo;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getLandMark() {
		return landMark;
	}

	public void setLandMark(String landMark) {
		this.landMark = landMark;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getFullName() {
		try {
			fullName = computeFullName();
		} catch (NullPointerException e) {
			Logger logger = LoggerFactory.getLogger(getClass());
			logger.error("NPE in function field: getFullName()", e);
		}
		return fullName;
	}

	protected String computeFullName() {
		if (houseNo != null && street != null){
			return houseNo + " " + street + " " + landMark + " " + city + " " + country;}
		return null;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
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
		if (!(obj instanceof Address)) return false;

		final Address other = (Address) obj;
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
			.add("houseNo", getHouseNo())
			.add("street", getStreet())
			.add("landMark", getLandMark())
			.add("city", getCity())
			.add("country", getCountry())
			.omitNullValues()
			.toString();
	}
}
