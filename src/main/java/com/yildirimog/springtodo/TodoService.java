package com.yildirimog.springtodo;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;
@Service
public class TodoService {
    private final Map<Long,Todo> todos = new HashMap<>();
    private final AtomicLong idCounter = new AtomicLong(1);

    public TodoService() {
        // Test verisi
        TodoRequest request1 = new TodoRequest("Spring Boot öğren", "Spring Boot tutorial'ını tamamla");
        TodoRequest request2 = new TodoRequest("API test et", "Postman ile endpoint'leri test et");
        create(request1);
        create(request2);
    }
    public List<TodoResponse> getAll(){
      return todos.values().stream()
                .map(TodoResponse::from)
                .collect(Collectors.toList());
    }

    public List<TodoResponse> getTodosByCompleted(Boolean status){
       return todos.values().stream().filter(todo -> status == null || todo.isCompleted() == status)
                .map(TodoResponse::from)
                .collect(Collectors.toList());
    }

    public Optional<TodoResponse> getTodoById(Long id) {
        return Optional.ofNullable(todos.get(id))
                .map(TodoResponse::from);
    }

    public TodoResponse create(TodoRequest todoRequest){
        Todo todo = new Todo(todoRequest.getTitle(), todoRequest.getDescription());
        todo.setId(idCounter.getAndIncrement());
        todos.put(todo.getId(), todo);
        return TodoResponse.from(todo);
    }
    public Optional<TodoResponse> updateTodo(Long id, TodoUpdateRequest request) {
        Todo todo = todos.get(id);
        if (todo == null) {
            return Optional.empty();
        }

        // Sadece null olmayan alanları güncelle
        if (request.hasTitleUpdate()) {
            todo.setTitle(request.getTitle());
        }
        if (request.hasDescriptionUpdate()) {
            todo.setDescription(request.getDescription());
        }
        if (request.hasCompletedUpdate()) {
            todo.setCompleted(request.getCompleted());
        }

        return Optional.of(TodoResponse.from(todo));
    }
    public Optional<TodoResponse> toggleTodoStatus(Long id) {
        Todo todo = todos.get(id);
        if (todo != null) {
            todo.setCompleted(!todo.isCompleted());
            return Optional.of(TodoResponse.from(todo));
        }
        return Optional.empty();
    }

    public boolean deleteTodo(Long id) {
        return todos.remove(id) != null;
    }

    public long getTotalCount() {
        return todos.size();
    }

    public long getCompletedCount() {
        return todos.values().stream()
                .mapToLong(todo -> todo.isCompleted() ? 1 : 0)
                .sum();
    }
    public List<TodoResponse> createMultipleTodos(List<TodoRequest> requests) {
        return requests.stream()
                .map(this::create)
                .collect(Collectors.toList());
    }

    public List<TodoResponse> markAllAsCompleted() {
        return todos.values().stream()
                .peek(todo -> todo.setCompleted(true))
                .map(TodoResponse::from)
                .collect(Collectors.toList());
    }

}
