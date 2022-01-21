package com.spring.school.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.school.models.Classroom;

public interface IClassroomRepository extends JpaRepository<Classroom, Long> {

}
