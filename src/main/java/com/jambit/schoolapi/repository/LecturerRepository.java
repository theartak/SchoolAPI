package com.jambit.schoolapi.repository;

import com.jambit.schoolapi.model.Lecturer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LecturerRepository extends JpaRepository<Lecturer, Long> {
    @Query("SELECT s FROM Lecturer s WHERE s.email = ?1")
    Optional<Lecturer> findLecturerByEmail(String email);

    @Query("SELECT s FROM Lecturer s WHERE s.name = ?1")
    Optional<Lecturer> findLecturerByName(String name);
}