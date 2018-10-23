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
package com.axelor.exception.db.repo;

import com.axelor.db.JpaRepository;
import com.axelor.db.Query;
import com.axelor.exception.db.TraceBack;

public class TraceBackRepository extends JpaRepository<TraceBack> {

	public TraceBackRepository() {
		super(TraceBack.class);
	}

	public TraceBack findByName(String name) {
		return Query.of(TraceBack.class)
				.filter("self.name = :name")
				.bind("name", name)
				.fetchOne();
	}

	// TYPE SELECT
	public static final int TYPE_TECHNICAL = 0;
	public static final int TYPE_FUNCTIONNAL = 1;

	// CATEGORY SELECT
	public static final int CATEGORY_MISSING_FIELD = 1;
	public static final int CATEGORY_NO_UNIQUE_KEY = 2;
	public static final int CATEGORY_NO_VALUE = 3;
	public static final int CATEGORY_CONFIGURATION_ERROR = 4;
	public static final int CATEGORY_INCONSISTENCY = 5;
}

