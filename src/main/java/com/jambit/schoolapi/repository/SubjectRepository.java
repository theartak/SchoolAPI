package com.jambit.schoolapi.repository;

import com.jambit.schoolapi.model.Student;
import com.jambit.schoolapi.model.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Long> {
    @Query("SELECT s FROM Student s WHERE s.name = ?1")
    Optional<Student> findStudentByEmail(String name);
}