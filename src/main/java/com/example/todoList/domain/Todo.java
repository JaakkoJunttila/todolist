package com.example.todoList.domain;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Todo {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long taskid;
	private String task;
	private double workload;
	private String deadline;
	private int due;
	private String state;
	
	@ManyToOne
	@JsonIgnore
    @JoinColumn(name = "id")
    private User user;
	
	public Todo() {		
	this.state = "to do";
	}

	public Todo(String task, User user, double workload) {
		super();
		this.task = task;
		this.user = user;
		this.workload = workload;
		this.state = "to do";
	}

	public Long getId() {
		return taskid;
	}

	public void setId(Long taskid) {
		this.taskid = taskid;
	}

	public String getTask() {
		return this.task;
	}

	public void setTask(String task) {
		this.task = task;
	}
	
	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public double getWorkload() {
		return workload;
	}

	public void setWorkload(double workload) {
		this.workload = workload;
	}

	public String getDeadline() {
		return deadline;
	}

	public void setDeadline(String deadline) {
		this.deadline = deadline;
	}

	public int getDue() {
		return due;
	}

	public void setDue(int due) {
		this.due = due;
	}
	
	public String getState() {
		return state;
	}

	public void setState(String state) {
		if (state.equals("to do")) {
			state = "doing";
		} else if (state.equals("doing")) {
			state = "done";
		} else {
			state = "to do";
		}
		this.state = state;
	}

	@Override
	public String toString() {
		return "Todo [taskid=" + taskid + ", task=" + task + ", workload=" + workload + ", deadline=" + deadline
				+ ", user=" + user + "]";
	}
	

	
	
	
}

