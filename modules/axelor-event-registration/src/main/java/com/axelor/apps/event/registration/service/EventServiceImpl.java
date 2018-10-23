package com.axelor.apps.event.registration.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

import com.axelor.apps.event.registration.db.Discount;
import com.axelor.apps.event.registration.db.Event;
import com.axelor.apps.event.registration.db.EventRegistration;
import com.axelor.apps.event.registration.db.repo.EventRegistrationRepository;
import com.google.inject.Inject;
import com.google.inject.persist.Transactional;

public class EventServiceImpl implements EventService {
	@Inject
	EventRegistrationRepository eventregrepo;

	@Override
	public Discount computeDiscountAmmount(Discount discount, Event event) {
		BigDecimal divisor = BigDecimal.valueOf(100);
		BigDecimal discountAmount = (discount.getDiscountPercent().multiply(event.getFees()).divide(divisor));
		discount.setDiscountAmount(discountAmount);
		return discount;
	}

	@Override
	public EventRegistration computeAmount(Discount discount, EventRegistration eventRegistration, Event event) {
		LocalDate closeDate = event.getRegistrationClose();
		LocalDateTime registrationDate = eventRegistration.getRegistrationDate();
		LocalDate date = registrationDate.toLocalDate();
		long noOfDaysBetweenRegistration = ChronoUnit.DAYS.between(date, closeDate);
		BigDecimal amount = BigDecimal.ZERO;
		if (discount != null) {
			if (discount.getBeforeDays() == noOfDaysBetweenRegistration) {
				amount = event.getFees().subtract(discount.getDiscountAmount());
				eventRegistration.setAmount(amount);
			} else {
				eventRegistration.setAmount(event.getFees());
			}
		} else {
			eventRegistration.setAmount(event.getFees());
		}
		return eventRegistration;
	}

	@Override
	public Event computeAmountCollect(Event event) {
		BigDecimal amountCalculate = BigDecimal.ZERO;
		for (EventRegistration eventRegistration : event.getEventRegistration()) {
			amountCalculate = eventRegistration.getAmount().add(amountCalculate);
			event.setAmountCollected(amountCalculate);
		}
		return event;
	}

}
