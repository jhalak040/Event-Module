package com.axelor.app.event.db.repo;

import com.axelor.apps.event.registration.db.EventRegistration;
import com.axelor.apps.event.registration.db.repo.EventRegistrationRepository;
import com.axelor.db.Query;

public class EventRegistrationRepo extends EventRegistrationRepository {
	
	public int remove(Long id) {
		return Query.of(EventRegistration.class).filter("self.id = :id").delete();

	}
}
