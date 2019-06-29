package com.sapient.learning.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.flowable.task.api.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sapient.learning.domain.Approval;
import com.sapient.learning.domain.Article;

@Service
public class ArticleWorkflowService {

	@Autowired
	private RuntimeService runtimeService;

	@Autowired
	private TaskService taskService;

	@Transactional
	public void startProcess(Article article) {
		System.out.println(article);
		Map<String, Object> variables = new HashMap<String, Object>();
		variables.put("author", article.getAuthor());
		variables.put("url", article.getUrl());
		System.out.println(variables);
		runtimeService.startProcessInstanceByKey("articleReview", variables);
	}

	@Transactional
	public List<Article> getTasks(String assignee) {
		List<Task> tasks = taskService.createTaskQuery().taskCandidateGroup(assignee).list();
		List<Article> articles = new ArrayList<Article>();
		for (Task task : tasks) {
			Map<String, Object> variables = taskService.getVariables(task.getId());
			System.out.println(task.getId());
			System.out.println(variables);
			articles.add(new Article(task.getId(), (String) variables.get("author"), (String) variables.get("url")));
		}
		return articles;
	}
	
	@Transactional
	public void submitReview(Approval approval) {
		Map<String, Object> variables = new HashMap<String, Object>();
		variables.put("approved", approval.isStatus());
		taskService.complete(approval.getId(), variables);
	}

}