package com.skoneczny.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skoneczny.entites.Task;
import com.skoneczny.entites.User;

public interface TaskRepository extends JpaRepository<Task, Long>{

	List<Task> findByUser(User user);

}
