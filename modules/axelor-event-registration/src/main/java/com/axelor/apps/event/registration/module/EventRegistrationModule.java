package com.axelor.apps.event.registration.module;

import com.axelor.app.AxelorModule;
import com.axelor.app.event.db.repo.EventRegistrationRepo;
import com.axelor.app.event.db.repo.EventRepo;
import com.axelor.apps.event.registration.db.repo.EventRegistrationRepository;
import com.axelor.apps.event.registration.db.repo.EventRepository;
import com.axelor.apps.event.registration.service.EventService;
import com.axelor.apps.event.registration.service.EventServiceImpl;

public class EventRegistrationModule extends AxelorModule {

	@Override
	protected void configure() {

		bind(EventService.class).to(EventServiceImpl.class);
		bind(EventRegistrationRepository.class).to(EventRegistrationRepo.class);
		bind(EventRepository.class).to(EventRepo.class);
	}
}
