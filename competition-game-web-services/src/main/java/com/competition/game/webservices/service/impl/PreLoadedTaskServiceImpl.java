package com.competition.game.webservices.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.competition.game.webservices.model.PreLoadedTask;
import com.competition.game.webservices.model.TaskStatus;
import com.competition.game.webservices.repository.PreLoadedTaskRepository;
import com.competition.game.webservices.repository.TaskStatusRepository;
import com.competition.game.webservices.service.PreLoadedTaskService;

@Service
public class PreLoadedTaskServiceImpl implements PreLoadedTaskService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	PreLoadedTaskRepository preLoadedTaskRepository;

	@Override
	public List<PreLoadedTask> getRandomTaskForPlayer() {
		
		List<PreLoadedTask> preLoadedTasks = null;
		try {
			preLoadedTasks = preLoadedTaskRepository.findAll();
			
		} catch (Exception e) {
			
		}
		return preLoadedTasks;
	}

}