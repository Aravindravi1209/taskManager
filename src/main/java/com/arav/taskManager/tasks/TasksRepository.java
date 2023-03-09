package com.arav.taskManager.tasks;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TasksRepository extends JpaRepository<TaskEntity, Long> {

    List<TaskEntity> findByCompleted(Boolean status);
}
