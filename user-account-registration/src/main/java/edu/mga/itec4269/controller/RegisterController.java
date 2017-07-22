package edu.mga.itec4269.controller;

import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.nulabinc.zxcvbn.Strength;
import com.nulabinc.zxcvbn.Zxcvbn;

import edu.mga.itec4269.model.User;
import edu.mga.itec4269.service.UserService;
import edu.mga.itec4269.service.EmailService;

@Controller
public class RegisterController {
	private BCryptPasswordEncoder passwordEncoder;
	private UserService userService;
	private EmailService emailService;
	
	
	//Something is wrong with the constructor, but I don't know how to fix it.
	//It says there should be a bean for BCryptPasswordEncoder.
	@Autowired
	public RegisterController(BCryptPasswordEncoder passwordEncoder, UserService userService, EmailService emailService){
		this.passwordEncoder = passwordEncoder;
		this.userService = userService;
		this.emailService = emailService;
	}
	
	@RequestMapping(value="/register", method = RequestMethod.GET)
	public ModelAndView showRegistrationPage(ModelAndView mv, User user){
		mv.addObject("user", user);
		mv.setViewName("register");
		return mv;
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ModelAndView processRegistrationForm(ModelAndView mv, @Valid User user, BindingResult bindingResult, HttpServletRequest request){
		User userExists = userService.findByEmail(user.getEmail());
		System.out.println(userExists);
		if(userExists != null){
			mv.addObject("alreadyRegisteredMethod", "There is already an account with that email address.");
			mv.setViewName("register");
			bindingResult.reject("email");
		}
		
		if(bindingResult.hasErrors()){
			mv.setViewName("register");
		}
		else {
			user.setEnabled(false);
			user.setConfirmationToken(UUID.randomUUID().toString());
			userService.saveUser(user);
			String appUrl = request.getScheme() + "://" + request.getServerName();
			SimpleMailMessage registrationEmail = new SimpleMailMessage();
			registrationEmail.setTo(user.getEmail());
			registrationEmail.setSubject("Registration Confirmation");
			registrationEmail.setText("To confirm your email address, please click the link below:\n"
					+ appUrl + "/confirm?token=" + user.getConfirmationToken());
			registrationEmail.setFrom("noreply@lusers.net");
			
			emailService.sendEmail(registrationEmail);
			
			mv.addObject("confirmationMessage", "A confirmation email has been sent to " + user.getEmail());
			mv.setViewName("register");
		}
		return mv;
	}
	
	@RequestMapping(value = "/confirm", method = RequestMethod.GET)
	public ModelAndView showConfirmationPage(ModelAndView mv, @RequestParam("token") String token){
		User user = userService.findByConfirmationToken(token);
		if(user == null){
			mv.addObject("invalidToken", "This is an invalid confirmation link...");
		}
		else {
			mv.addObject("confirmationToken", user.getConfirmationToken());
		}
		
		mv.setViewName("confirm");
		return mv;
	}
	
	@RequestMapping(value = "/confirm", method = RequestMethod.POST)
	public ModelAndView processConfirmationPage(ModelAndView mv, BindingResult bindingResult, @RequestParam Map requestParams, RedirectAttributes redir){
		mv.setViewName("confirm");
		Zxcvbn passwordCheck = new Zxcvbn();
		Strength strength = passwordCheck.measure((String) requestParams.get("password"));
		
		if(strength.getScore() < 3){
			bindingResult.reject("password");
			redir.addFlashAttribute("errorMessage", "Your password is too weak.");
			mv.setViewName("redirect:confirm?token=" + requestParams.get("token"));
			System.out.println(requestParams.get("token"));
			return mv;
		}
		
		User user = userService.findByConfirmationToken((String) requestParams.get("token"));
		user.setPassword(passwordEncoder.encode((CharSequence) requestParams.get("password")));
		user.setEnabled(true);
		userService.saveUser(user);
		mv.addObject("successMessage", "Your password has been set");
		return mv;
	}
}
