package com.samuelhrm.controllers;


import com.samuelhrm.dtos.request.ChangeAppointmentStatus;
import com.samuelhrm.dtos.request.CreateAppointmentRequest;
import com.samuelhrm.dtos.request.GetAppointRequest;
import com.samuelhrm.dtos.response.AppointmentMiniResponse;
import com.samuelhrm.dtos.response.MessageResponse;
import com.samuelhrm.dtos.response.UserInfoResponse;
import com.samuelhrm.entities.Appointment;
import com.samuelhrm.entities.HrmUser;
import com.samuelhrm.entities.Status;
import com.samuelhrm.security.AuthService;
import com.samuelhrm.security.services.UserDetailsImpl;
import com.samuelhrm.services.AppointmentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Set;
import java.util.function.LongFunction;


@AllArgsConstructor
@RestController
@RequestMapping("/api/appointment")
public class AppointmentController{
	private AppointmentService appointmentService;
	private AuthService authService;

	@PostMapping("/create")
	public ResponseEntity<?> createAppointment(@RequestBody CreateAppointmentRequest request, @AuthenticationPrincipal UserDetailsImpl user) throws Exception {


		MessageResponse response = appointmentService.create(request,user.getId());
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("/appointments")
	public ResponseEntity<?> getAppointments(@AuthenticationPrincipal UserDetailsImpl user) {
	UserInfoResponse response =  authService.getUserLoginInfo(user);
		List<AppointmentMiniResponse> userList = appointmentService.findUserAllAppointment(response.getId());

		return new ResponseEntity<>(userList, HttpStatus.OK);
	}

	@GetMapping("/appointments/{id}")
	public ResponseEntity<?> getUser(@PathVariable Long id) {

		Appointment mine = appointmentService.getAppointmentById(id);

		return new ResponseEntity<>(mine, HttpStatus.OK);
	}

	@PostMapping("/changestatus")
	public ResponseEntity<?> changeAppointmentStatus(@RequestBody ChangeAppointmentStatus change){
		MessageResponse response = appointmentService.changeAppointmentStatus(change);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	@PostMapping("/changestatusback")
	public ResponseEntity<?> changeAppointmentStatusBack(@RequestBody ChangeAppointmentStatus change){
		MessageResponse response = appointmentService.changeAppointmentStatusBack(change);
		return new ResponseEntity<>(response, HttpStatus.OK);

	}
}