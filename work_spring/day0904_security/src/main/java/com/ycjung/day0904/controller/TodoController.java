package com.ycjung.day0904.controller;

import com.ycjung.day0904.model.dto.TodoDTO;
import com.ycjung.day0904.model.entity.TodoEntity;
import com.ycjung.day0904.model.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/todo")
public class TodoController {
    final String tempUser = "programmers";

    @Autowired
    private TodoService todoService;

    @GetMapping
    public ResponseEntity<?> todoList() {
        List<TodoEntity> entities = todoService.todoList(tempUser); // 추후 로그인 정보를 바탕으로 조회되게 수정해야 함.
        List<TodoDTO> dtos = entities.stream().map(TodoDTO::new).collect(Collectors.toList());

        return ResponseEntity.ok().body(dtos);
    }

    @PostMapping
    public ResponseEntity<?> createTodo(@RequestBody TodoDTO todoDTO) {
        TodoEntity todoEntity = new TodoEntity();
        todoEntity.setUsername(tempUser); /* TODO : 로그인 구현하고 수정해야 한다. */

        List<TodoEntity> entities = todoService.create(todoEntity); // 추가된 _todo 까지 포함해서 갱신된 _todo 목록
        List<TodoDTO> dtos = entities.stream().map(TodoDTO::new).collect(Collectors.toList());

        return ResponseEntity.ok().body(dtos);
    }

    @PutMapping
    public ResponseEntity<?> updateTodo(@RequestBody TodoDTO todoDTO) {
        TodoEntity todoEntity = todoDTO.toEntity();
        todoEntity.setUsername(tempUser);
        return ResponseEntity.ok().body(todoService.update(todoEntity));
    }
}
