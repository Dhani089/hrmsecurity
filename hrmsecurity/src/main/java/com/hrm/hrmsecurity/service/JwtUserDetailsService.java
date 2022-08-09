package com.hrm.hrmsecurity.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.hrm.hrmsecurity.model.Employee;

@Service
public class JwtUserDetailsService implements UserDetailsService {

	@Autowired
	RestTemplate restTemplate;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		HttpHeaders headers = new HttpHeaders();
		headers.set("username", username);
		HttpEntity<String> entity = new HttpEntity<String>(headers);
		ResponseEntity<Employee> result = restTemplate.exchange("http://hrm-employee-service/userdetails", HttpMethod.GET, entity,
				Employee.class);
		Employee emp= result.getBody();
		//Employee emp = repositoryDetailsRepository.findByUsername(username);
		if (emp.getUsername().equals(username)) {
			return new User(emp.getUsername(), "$2a$10$slYQmyNdGzTn7ZLBXBChFOC9f6kFjAqPhccnP6DxlWXx2lPk1C3G6",
					new ArrayList<>());
		} else {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
	}

}