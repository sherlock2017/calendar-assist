package com.calendar.assist.repository;

import java.math.BigInteger;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.calendar.assist.entity.Invitation;

@Repository
public interface InvitationRepository extends JpaRepository<Invitation, BigInteger> {

}
