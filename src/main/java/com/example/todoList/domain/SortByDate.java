package com.example.todoList.domain;


import java.util.Comparator;


public class SortByDate implements Comparator<Todo> {	
	public DaysTillDue countDays = new DaysTillDue(); 
	
	public int compare(Todo a, Todo b) {
		return countDodo(a) - countDodo(b);
	}
	public int countDodo(Todo todo) {
		return DaysTillDue.daysTillDue(todo.getDeadline());
	}
}
