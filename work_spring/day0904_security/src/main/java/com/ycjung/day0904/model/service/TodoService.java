package com.ycjung.day0904.model.service;

import com.ycjung.day0904.model.entity.TodoEntity;
import com.ycjung.day0904.model.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoService {
    @Autowired
    private TodoRepository todoRepository;

    public List<TodoEntity> create(TodoEntity todoEntity) { // 할일 생성
        validate(todoEntity);

        todoRepository.save(todoEntity);
        return todoRepository.findByUsername(todoEntity.getUsername()); // 방금 추가한거 포함해서 할일 목록
    }

    public List<TodoEntity> todoList(String username) {
        return todoRepository.findByUsername(username);
    }

    public TodoEntity update(TodoEntity todoEntity) {
        TodoEntity original = todoRepository.findById(todoEntity.getId());

        if(todoEntity.getTitle() != null) // 할일 제목을 새로 입력했네요? : 갱신
            original.setTitle(todoEntity.getTitle());
        if(original.isDone() != todoEntity.isDone()) // 수행 여부가 달려졌네요? : 갱신
            original.setDone(todoEntity.isDone());

        todoRepository.save(original);

        return todoRepository.findById(original.getId());
    }

    // 전송 여부 보다는 지금 내가 사용해야 하는 '데이터(재료) 가 멀쩡'한 지 체크하기
    private void validate(TodoEntity todoEntity) { // entity 유효한지 체크하기
        if(todoEntity == null)
            throw new RuntimeException("엔티티 null 이다.");
        if(todoEntity.getUsername() == null)
            throw new RuntimeException("정체 불명의 사용자가 엔티티 생성 요청됨.");

    }
}
