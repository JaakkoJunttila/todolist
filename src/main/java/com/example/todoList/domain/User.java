package com.example.todoList.domain;

import java.util.List;

import javax.persistence.*;

import com.example.todoList.domain.Todo;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name = "password", nullable = false)
    private String passwordHash;

    @Column(name = "role", nullable = false)
    private String role;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
	private List<Todo> todos;
    
    public User() {
    }

	public User(String username, String passwordHash, String role) {
		super();
		this.username = username;
		this.passwordHash = passwordHash;
		this.role = role;
		this.todos = null;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPasswordHash() {
		return passwordHash;
	}

	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	public List<Todo> getTodos() {
		return todos;
	}

	public void setTodos(List<Todo>todos) {
		this.todos = todos;
	}
	
	public void addTodo(Todo todo) {
		this.todos.add(todo);
	}
	
	public void removeTodo(Todo todo) {
		int i = 0;
		for (i = 0;i < this.todos.size(); i++) {
			if (this.todos.get(i).equals(todo)) {
				this.todos.remove(i);
			}
		}
	}
	
}