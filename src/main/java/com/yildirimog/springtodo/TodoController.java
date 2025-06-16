package com.yildirimog.springtodo;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/todos")
public class TodoController {

    private final TodoService todoService;
    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping
    public ResponseEntity<List<TodoResponse>> getAllTodos(
            @RequestParam(required = false) Boolean completed) {
        List<TodoResponse> todos = todoService.getTodosByCompleted(completed);
        return ResponseEntity.ok(todos);
    }
    @GetMapping("/{id}")
    public ResponseEntity<TodoResponse> getTodoById(@PathVariable Long id) {
        Optional<TodoResponse> todo = todoService.getTodoById(id);
        return todo.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    @PostMapping
    public ResponseEntity<TodoResponse> createTodo(@Valid @RequestBody TodoRequest request) {
        TodoResponse todo = todoService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(todo);
    }

    // PUT /api/todos/{id} - Todo güncelle (Partial Update DTO)
    @PutMapping("/{id}")
    public ResponseEntity<TodoResponse> updateTodo(
            @PathVariable Long id,
            @Valid @RequestBody TodoUpdateRequest request) {
        Optional<TodoResponse> updatedTodo = todoService.updateTodo(id, request);
        return updatedTodo.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // PATCH /api/todos/{id}/toggle - Todo durumunu değiştir
    @PatchMapping("/{id}/toggle")
    public ResponseEntity<TodoResponse> toggleTodoStatus(@PathVariable Long id) {
        Optional<TodoResponse> todo = todoService.toggleTodoStatus(id);
        return todo.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // DELETE /api/todos/{id} - Todo sil
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTodo(@PathVariable Long id) {
        boolean deleted = todoService.deleteTodo(id);
        return deleted ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }

    // GET /api/todos/stats - İstatistikler
    @GetMapping("/stats")
    public ResponseEntity<Map<String, Object>> getStats() {
        Map<String, Object> stats = new HashMap<>();
        stats.put("total", todoService.getTotalCount());
        stats.put("completed", todoService.getCompletedCount());
        stats.put("pending", todoService.getTotalCount() - todoService.getCompletedCount());
        return ResponseEntity.ok(stats);
    }

    // POST /api/todos/bulk - Toplu todo oluştur (Bonus)
    @PostMapping("/bulk")
    public ResponseEntity<List<TodoResponse>> createMultipleTodos(
            @Valid @RequestBody List<TodoRequest> requests) {
        List<TodoResponse> todos = todoService.createMultipleTodos(requests);
        return ResponseEntity.status(HttpStatus.CREATED).body(todos);
    }

    // PATCH /api/todos/mark-all-completed - Hepsini tamamla
    @PatchMapping("/mark-all-completed")
    public ResponseEntity<List<TodoResponse>> markAllAsCompleted() {
        List<TodoResponse> todos = todoService.markAllAsCompleted();
        return ResponseEntity.ok(todos);
    }
}
