package edu.mga.itec.springboot.course;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseService {
	
	@Autowired
	private CourseRepository repo;
	
	public List<Course> getAllCourses(String topicId){
		List<Course> courses = new ArrayList<>();
		repo.findByTopicId(topicId).forEach(courses::add);
		return courses;
	}
	
	public Course getCourse(String id){
		return repo.findOne(id);
	}

	public void addCourse(Course course) {
		repo.save(course);
	}
	
	public void editCourse(Course course){
		repo.save(course);
	}
	
	public void deleteCourse(String id){
		repo.delete(id);
	}
	
}
