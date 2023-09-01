package com.skcet.day6Pagination.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skcet.day6Pagination.model.Child;

public interface ChildRepo extends JpaRepository<Child, Integer>{
          boolean existsByBabyId(int babyId);

		List<Child> findByBabyFirstName(String babyFirstName);
}
