package com.jambit.schoolapi.service;

import com.jambit.schoolapi.additionalmethods.AdditionalMethods;
import com.jambit.schoolapi.model.Lecturer;
import com.jambit.schoolapi.model.Student;
import com.jambit.schoolapi.model.Subject;
import com.jambit.schoolapi.repository.LecturerRepository;
import com.jambit.schoolapi.repository.StudentRepository;
import com.jambit.schoolapi.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class SchoolService {
    private final StudentRepository studentRepository;
    private final SubjectRepository subjectRepository;
    private final LecturerRepository lecturerRepository;

    @Autowired
    public SchoolService(StudentRepository studentRepository,
                         SubjectRepository subjectRepository,
                         LecturerRepository lecturerRepository) {
        this.studentRepository = studentRepository;
        this.subjectRepository = subjectRepository;
        this.lecturerRepository = lecturerRepository;
    }

    //GET
    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

    public List<Lecturer> getLecturers() {
        return lecturerRepository.findAll();
    }

    public List<Subject> getSubject() {
        return subjectRepository.findAll();
    }

    //ADD
    public void addStudent(Student student) {
        Optional<Student> studentOptional =
                studentRepository.findById(student.getId());
        if (AdditionalMethods.isEmailValid(student.getEmail())) {
            if (studentOptional.isPresent()) {
                throw new IllegalStateException("Email already taken");
            }
        }
        studentRepository.save(student);
    }

    public void addLecturer(Lecturer lecturer) {
        Optional<Lecturer> lecturerOptional =
                lecturerRepository.findById(lecturer.getId());
        if (AdditionalMethods.isEmailValid(lecturer.getEmail())) {
            if (lecturerOptional.isPresent()) {
                throw new IllegalStateException("Email already taken");
            }
        }
        lecturerRepository.save(lecturer);
    }

    public void addSubject(Subject subject) {
        Optional<Subject> subjectOptional =
                subjectRepository.findById(subject.getId());
        if (subjectOptional.isPresent()) {
            throw new IllegalStateException("Subject already exists");
        }
        subjectRepository.save(subject);
    }

    //DELETE
    public void deleteStudent(Long studentId) {
        if (!studentRepository.existsById(studentId)) {
            throw new IllegalStateException("Student with ID " + studentId + " doesn't exist");
        }
        studentRepository.deleteById(studentId);
    }

    public void deleteLecturer(Long lecturerId) {
        if (!lecturerRepository.existsById(lecturerId)) {
            throw new IllegalStateException("Lecturer with ID " + lecturerId + " doesn't exist");
        }
        lecturerRepository.deleteById(lecturerId);
    }

    public void deleteSubject(Long subjectId) {
        if (!subjectRepository.existsById(subjectId)) {
            throw new IllegalStateException("Student with ID " + subjectId + " doesn't exist");
        }
        subjectRepository.deleteById(subjectId);
    }

    //UPDATE
    @Transactional
    public void updateStudent(Long studentId, String name, String email) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new IllegalStateException("Student with ID " + studentId + " doesn't exist"));

        if (name != null && name.length() > 0 &&
                !Objects.equals(student.getName(), name)) {
            student.setName(name);
        }

        if (email != null && email.length() > 0 &&
                !Objects.equals(student.getEmail(), email)) {
            Optional<Student> studentOptional =
                    studentRepository.findStudentByEmail(student.getEmail());
            if (studentOptional.isPresent()) {
                throw new IllegalStateException("Email already taken");
            }
            student.setEmail(email);
        }
    }

    @Transactional
    public void updateLecturer(Long lecturerId, String name, String email) {
        Lecturer lecturer = lecturerRepository.findById(lecturerId)
                .orElseThrow(() -> new IllegalStateException("Lecturer with ID " + lecturerId + " doesn't exist"));

        if (name != null && name.length() > 0 &&
                !Objects.equals(lecturer.getName(), name)) {
            lecturer.setName(name);
        }

        if (email != null && email.length() > 0 &&
                !Objects.equals(lecturer.getEmail(), email)) {
            Optional<Lecturer> lecturerOptional =
                    lecturerRepository.findLecturerByEmail(lecturer.getEmail());
            if (lecturerOptional.isPresent()) {
                throw new IllegalStateException("Email already taken");
            }
            lecturer.setEmail(email);
        }
    }

    @Transactional
    public void updateSubject(Long subjectId, String name) {
        Subject subject = subjectRepository.findById(subjectId)
                .orElseThrow(() -> new IllegalStateException("Subject with ID " + subjectId + " doesn't exist"));
        if (name != null && name.length() > 0 &&
                !Objects.equals(subject.getName(), name)) {
            subject.setName(name);
        }
    }
}
