package com.in28minutes.springboot.rest.example.springboot2.jdbcwithh2.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.in28minutes.springboot.rest.example.springboot2.jdbcwithh2.example.model.Student;
import com.in28minutes.springboot.rest.example.springboot2.jdbcwithh2.example.repository.StudentJdbcRepository;

@RestController
public class MyController {
	
	@Autowired
	StudentJdbcRepository repository;
	
	@RequestMapping("/findAll")
	public ModelAndView findAllStudent() {
		Iterable<Student> iterator = repository.findAll();
		List<Student> result = new ArrayList<Student>();
		iterator.forEach(result::add);
		ModelAndView modelAndView = new ModelAndView("studentList");
		modelAndView.addObject("estudiantes",result);
		modelAndView.addObject("student",new Student());
		return modelAndView;
	}
	
	
//	@RequestMapping(value="/passport/{userId}", method=RequestMethod.GET)
//	public Student findPassport(@PathVariable Long userId) {
//		return repository.findById(userId);
//	}
	
	@PostMapping("/findAll")
    public ModelAndView analizaFormulario(@ModelAttribute Student estudiante, @RequestParam(value="id")Long userId, @RequestParam(name="to_do") String opcion) {
		if(opcion.equals("form1")) {
			createStudent(estudiante);
			return findAllStudent();
		}else if(opcion.equals("form2")) {
			DeleteByIdStudent(userId);
			return findAllStudent();
		}else {
			updateStudent(estudiante);
			return findAllStudent();
		}
		//return findAllStudent();
	}
	
	public void DeleteByIdStudent(@RequestParam(value="id")Long userId) {
		repository.deleteById(new Long(userId));
	}
	
	public ModelAndView createStudent(Student estudiante) {
		ModelAndView modelAndView = new ModelAndView("studentList");
		modelAndView.addObject("student",repository.insert(estudiante));
		modelAndView.addObject("student",new Student());
		return modelAndView;
	}
	
	public ModelAndView updateStudent(Student estudiante) {
		ModelAndView modelAndView = new ModelAndView("studentList");
		modelAndView.addObject("student",repository.update(estudiante));
		modelAndView.addObject("student",new Student());
		return modelAndView;
	}
	
	@PostMapping("/studentId")
	public ModelAndView findByIdStudent(Student estudiante, @RequestParam(value="id")Long userId) {
		ModelAndView modelAndView = new ModelAndView("studentId");
		modelAndView.addObject("student",repository.findById(new Long(userId)));
		return modelAndView;
	}
	
	
	@GetMapping("/studentId")
	public ModelAndView findId() {
		ModelAndView modelAndView = new ModelAndView("studentId");
		modelAndView.addObject("student",new Student());
		return modelAndView;	
	}
		
	
//	@PostMapping(params="to_do",path= {"/findAll"})
//	public ModelAndView DeleteByIdStudent(@RequestParam(value="id")Long userId) {
//		ModelAndView modelAndView = new ModelAndView("studentList");
//		modelAndView.addObject("student",repository.deleteById(new Long(userId)));
//		return modelAndView;
//	}
	
//	@GetMapping("/findAll")
//	public ModelAndView DeleteId() {
//		ModelAndView modelAndView = new ModelAndView("studentList");
//		return modelAndView;	
//	}

}
