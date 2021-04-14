package com.calendar.assist.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.calendar.assist.entity.Invitation;
import com.calendar.assist.repository.InvitationRepository;

/**
 * Service to operate on Invitation
 * 
 * @author Rishabh Jain
 * @since 4/13/2021
 *
 */
@Service
public class InvitationService {

	@Autowired
	private InvitationRepository invitationRepository;
	
	/**
	 * This service API save the invitation for the employee to accept
	 * 
	 * @param invitation
	 */
	public Invitation sendInvite(final Invitation invitation) {
		return invitationRepository.save(invitation);
	}

}
