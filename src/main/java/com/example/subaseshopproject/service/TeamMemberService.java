package com.example.subaseshopproject.service;

import com.example.subaseshopproject.model.TeamMember;
import com.example.subaseshopproject.repository.TeamMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TeamMemberService {

    private final TeamMemberRepository teamMemberRepository;

    public void save(TeamMember teamMember) {
        teamMemberRepository.save(teamMember);
    }

    public List<TeamMember> findAll(){
        return teamMemberRepository.findAll();
    }

    public Optional<TeamMember> findById(long id) {
         return teamMemberRepository.findById(id);
    }

    public void deleteById(long id){
        teamMemberRepository.deleteById(id);
    }
}
