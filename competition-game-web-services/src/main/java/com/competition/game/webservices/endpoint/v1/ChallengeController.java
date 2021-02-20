package com.competition.game.webservices.endpoint.v1;

import java.io.IOException;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.competition.game.webservices.api.v1.RextesterRequest;
import com.competition.game.webservices.exception.RecordNotFoundException;
import com.competition.game.webservices.helper.Validator;
import com.competition.game.webservices.model.Language;
import com.competition.game.webservices.model.TaskStatus;
import com.competition.game.webservices.repository.TaskRepository;
import com.competition.game.webservices.service.LanguageService;
import com.competition.game.webservices.service.PlayerService;
import com.competition.game.webservices.service.PreLoadedTaskService;
import com.competition.game.webservices.service.RextesterService;
import com.competition.game.webservices.service.TaskService;

@RestController
@RequestMapping("${api.path}")
@CrossOrigin(origins = "${cross.origins}")
public class ChallengeController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private LanguageService languagesService;

	@Autowired
	private RextesterService rextesterService;

	@Autowired
	private TaskService taskService;

	@Autowired
	TaskRepository taskRepository;

	@Autowired
	PreLoadedTaskService preLoadedTaskService;

	@Autowired
	private PlayerService playerService;

	@Autowired
	private Validator validator;

	// Constructor for Integration Testing
	public ChallengeController(LanguageService languagesService, RextesterService rextesterService,
			TaskService taskService, PlayerService playerService, PreLoadedTaskService preLoadedTaskService) {
		this.languagesService = languagesService;
		this.rextesterService = rextesterService;
		this.playerService = playerService;
		this.taskService = taskService;
		this.preLoadedTaskService = preLoadedTaskService;
	}

	// API to get All Languages
	@GetMapping("/getAllLanguages")
	public ResponseEntity<List<Language>> getAllLanguages() throws RecordNotFoundException {

		logger.debug("/v1/getAllChallengeIds method started");

		List<Language> languagelist = languagesService.getAllLanguages();

		if (languagelist.size() <= 0) {
			throw new RecordNotFoundException("Languages are not found");
		}

		return new ResponseEntity<>(languagelist, new HttpHeaders(), HttpStatus.OK);
	}

	// API to get All Languages
	@GetMapping("/getAnyUnusedRandomTask")
	public ResponseEntity<TaskStatus> getAnyUnusedRandomTask() {

		logger.debug("/v1/getAnyUnusedRandomTask method started");

		TaskStatus task = taskService.getAnyUnusedRandomTask();

		return new ResponseEntity<>(task, new HttpHeaders(), HttpStatus.OK);
	}

	// API to submit challenges
	@PostMapping("/submitChallenges")
	public ResponseEntity<Object> submitChallenges(@Valid @ModelAttribute RextesterRequest rextesterReq)
			throws RecordNotFoundException, InterruptedException, IOException {

		logger.debug("submitChallenges method started {}", rextesterReq);

		Language lang = this.validator.validateLanguageMapping(rextesterReq.getLanguageChoice());

		if (lang != null) {
			this.rextesterService.submitChallenge(rextesterReq);
		} else {
			return new ResponseEntity<>("Language Choice and Language Name mismatch ", new HttpHeaders(),
					HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<>("!!! Thank you for participating in Challenge !!!! ", new HttpHeaders(),
				HttpStatus.ACCEPTED);
	}
}
