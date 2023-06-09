package peaksoft.springbootlesson.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import peaksoft.springbootlesson.dto.*;
import peaksoft.springbootlesson.entity.Company;
import peaksoft.springbootlesson.entity.Course;
import peaksoft.springbootlesson.repository.CompanyRepository;
import peaksoft.springbootlesson.repository.CourseRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseService {
    private final CourseRepository courseRepository;
    private final CompanyRepository companyRepository;
    public List<CourseResponse> getAllCourses(){
        List<CourseResponse> courseResponses = new ArrayList<>();
        for (Course course : courseRepository.findAll()) {
            courseResponses.add(mapToResponse(course));
        }
        return courseResponses;
    }
    public CourseResponse getCourseById(Long courseId){
        Course course = courseRepository.findById(courseId).get();
        return mapToResponse(course);
    }
    public CourseResponse create(CourseRequest request){
        Course course = new Course();
        course.setCourseName(request.getCourseName());
        course.setDurationMonth(request.getDurationMonth());
        course.setLocalDate(LocalDate.now());
        course.setIsActive(course.getIsActive());
        course.setIsDeleted(course.getIsDeleted());
        Company company = companyRepository.findById(request.getCompanyId()).get();
        course.setCompany(company);
        courseRepository.save(course);
        return mapToResponse(course);
    }
    public CourseResponse updateCourse(Long id,CourseRequest request){
        Course course = courseRepository.findById(id).get();
        course.setCourseName(request.getCourseName());
        course.setDurationMonth(request.getDurationMonth());
        Company company = companyRepository.findById(request.getCompanyId()).get();
        course.setIsActive(course.getIsActive());
        course.setCompany(company);
        course.setIsDeleted(course.getIsDeleted());
        courseRepository.save(course);
        return mapToResponse(course);
    }
    public void deleteCourse(Long courseId){
        courseRepository.deleteById(courseId);
    }
    public CourseResponse mapToResponse(Course course){
        CourseResponse courseResponse = new CourseResponse();
        courseResponse.setLocalDate(course.getLocalDate());
        courseResponse.setCourseName(course.getCourseName());
        courseResponse.setCompanyName(course.getCompany().getCompanyName());
        courseResponse.setDurationMonth(course.getDurationMonth());
        courseResponse.setIsActive(course.getIsActive());
        courseResponse.setIsDeleted(course.getIsDeleted());
        return courseResponse;
    }

}
