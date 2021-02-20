package com.competition.game.webservices.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.competition.game.webservices.model.TaskStatus;

@Repository
public interface TaskRepository extends JpaRepository<TaskStatus, Integer> {



}