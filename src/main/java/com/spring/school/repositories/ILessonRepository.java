package com.spring.school.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.spring.school.models.Lesson;

public interface ILessonRepository extends JpaRepository<Lesson, Long> {

}
