package com.competition.game.webservices.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.competition.game.webservices.model.TaskStatus;
import com.competition.game.webservices.repository.TaskRepository;
import com.competition.game.webservices.service.TaskService;

@Service
public class TaskServiceImpl implements TaskService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	TaskRepository taskRepository;

	@Override
	public TaskStatus getAnyUnusedRandomTask() {
		logger.debug("start of getAnyUnusedRandomTask Of TaskServiceImpl");
		return null;
	}

	

}
