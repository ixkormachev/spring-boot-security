package com.example.controller;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.BasePathAwareController;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.dto.RegisterResponse;
import com.example.dto.RegisterResponseHolder;
import com.example.model.User;
import com.example.service.UserService;

@BasePathAwareController
public class RestController {

	@Autowired
	private UserService userService;

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    @ResponseBody
    	public RegisterResponse createNewUser(@Valid @RequestBody User user, HttpServletResponse response) {
    		User userExists = userService.findUserByEmail(user.getEmail());	
    		RegisterResponseHolder registerResponseHolder = new RegisterResponseHolder();
    		if (userExists != null) {
    			registerResponseHolder.getResponseResult().setResult("There is already a user registered with the email provided");
    			registerResponseHolder.setStatus(HttpStatus.CONFLICT);
    		} else {
    			registerResponseHolder.getResponseResult().setResult("The user has been registrated");
    			userService.saveUser(user);
    		}
    		response = updateResponse(response, registerResponseHolder.getStatus().value());
        return registerResponseHolder.getResponseResult();
    }
    
    @RequestMapping(value = "/process", method = RequestMethod.POST)
    @ResponseBody
    public RegisterResponse process(@RequestBody RegisterResponse body, HttpServletResponse response) {
    	RegisterResponseHolder registerResponseHolder = new RegisterResponseHolder();
		registerResponseHolder.getResponseResult().setResult("proccess ok for: " + body.getResult() );

    	response = updateResponse(response, registerResponseHolder.getStatus().value());
    	return registerResponseHolder.getResponseResult();
    }

    private HttpServletResponse updateResponse(HttpServletResponse response, int status) {
        response.setStatus(status);
        response.setContentType(MediaType.APPLICATION_JSON.toString());
        return response;
    }
}
