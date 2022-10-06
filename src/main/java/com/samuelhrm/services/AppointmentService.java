package com.samuelhrm.services;


import com.samuelhrm.dtos.request.ChangeAppointmentStatus;
import com.samuelhrm.dtos.request.CreateAppointmentRequest;
import com.samuelhrm.dtos.response.AppointmentMiniResponse;
import com.samuelhrm.dtos.response.MessageResponse;
import com.samuelhrm.entities.Appointment;

import java.util.List;

public interface AppointmentService {


		public MessageResponse create(CreateAppointmentRequest createAppointmentRequest, Long userId);


		public List<AppointmentMiniResponse> findUserAllAppointment(Long userid);

		public List<Appointment> getAllAppointment();

		public Appointment getAppointmentById(Long id);
		public Appointment SaveAppointment(Appointment appointment);
		MessageResponse changeAppointmentStatus(ChangeAppointmentStatus change);
		MessageResponse changeAppointmentStatusBack(ChangeAppointmentStatus change);
}
