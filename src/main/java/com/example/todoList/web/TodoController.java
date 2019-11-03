package com.example.todoList.web;



import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.todoList.domain.Todo;
import com.example.todoList.domain.TodoRepository;
import com.example.todoList.domain.UserRepository;
import com.example.todoList.domain.DaysTillDue;
import com.example.todoList.domain.SortByDate;

@Controller
public class TodoController {
	
	@Autowired
    private UserRepository repository; 
	
	@Autowired
    private TodoRepository trepository;
	
	private SimpleDateFormat dFormat = new SimpleDateFormat("yyyy-MM-dd");
	private Date today = new Date();
	private String stoday = dFormat.format(today);
	
	
	@RequestMapping(value= {"/todolist", "/"})
    public String todolist(Model model) {
		List<Todo> todos = sortTodos(trepository.findAll());
		setExpiryDates(todos);
    	model.addAttribute("todos", todos);
        return "todolist";
    }
	@RequestMapping(value = "/add")
    public String addBook(Model model){
		Todo todo = new Todo();
		todo.setDeadline(stoday);
    	model.addAttribute("todo", todo);
    	model.addAttribute("users", repository.findAll());
        return "addtodo";
    }
	@RequestMapping(value = {"/save"}, method = RequestMethod.POST)
    public String save(Todo todo) {
        trepository.save(todo);
        return "redirect:todolist";
    }
	@RequestMapping(value = "/delete/{id}")
    public String deleteTask(@PathVariable("id") Long taskid) {
    	trepository.deleteById(taskid);
    	return "redirect:../todolist";
    }
	@RequestMapping(value = "/edit/{id}")
	public String editTask(@PathVariable("id") Long taskid, Model model){
	model.addAttribute("todo", trepository.findById(taskid));
	model.addAttribute("users", repository.findAll());
	trepository.deleteById(taskid);
	return "addtodo";
	}
	@RequestMapping(value = {"edit/save"}, method = RequestMethod.POST)
	public String saveEdited(Todo todo) {
        trepository.save(todo);
        return "redirect:../todolist";
    }
	@RequestMapping(value = "/setstate/{id}")
    public String setState(@PathVariable("id") Long taskid) {
		Todo todo = trepository.findById(taskid).get();
		String state = todo.getState();
		todo.setState(state);
		replace(taskid, todo);
    	return "redirect:../todolist";
    }
	public List<Todo> sortTodos(List<Todo> todos) {		
		Collections.sort(todos, new SortByDate());
		return todos;
	}
	public void setExpiryDates(List<Todo> todos) {
		for (int i = 0;i < todos.size();i++) {
			Todo todo = todos.get(i);
			String deadline = todo.getDeadline();
			int tillDue = DaysTillDue.daysTillDue(deadline);
			todo.setDue(tillDue);
		}
	}
	public void replace(Long id, Todo todo) {
		List<Todo> todos = trepository.findAll();
		for (int i = 0;i < todos.size();i++) {
			if (todos.get(i).getId().equals(id)) {
				todos.set(i, todo);
			}
		}
		trepository.deleteAll();
		trepository.saveAll(todos);
		
	}
}
