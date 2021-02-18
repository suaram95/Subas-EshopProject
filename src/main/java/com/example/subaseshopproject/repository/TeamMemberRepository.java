package com.example.subaseshopproject.repository;

import com.example.subaseshopproject.model.TeamMember;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamMemberRepository extends JpaRepository<TeamMember, Long> {
}
