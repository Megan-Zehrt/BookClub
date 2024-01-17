// pair programmed with Jacob

package com.megan.bookclub.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.megan.bookclub.models.LoginUser;
import com.megan.bookclub.models.User;
import com.megan.bookclub.services.BookService;
import com.megan.bookclub.services.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class UserController {

	@Autowired
	private UserService userServ;
	private final BookService bookServ;

	// manually bringing in the BookService
	public UserController(BookService bookServ) {
		this.bookServ = bookServ;
	}

	// Route for Login and Register JSP file (loginreg.jsp)
	@GetMapping("/")
	public String loginRegUser(@ModelAttribute("user") User user, @ModelAttribute("loginUser") LoginUser loginUser,
			HttpSession session) {
		return "users/loginreg.jsp";
	}

	// Route to process the Registration for user + validations
	@PostMapping("/user/process/register")
	public String processRegister(@Valid @ModelAttribute("user") User user, BindingResult result, Model model,
			HttpSession session) {

		// Validation if statements
		if (userServ.getUser(user.getEmail()) != null) {
			result.rejectValue("email", "Unique", "Email already in use!");
		}

		if (!user.getPassword().equals(user.getConfirm())) {
			result.rejectValue("password", "Match", "passwords dont match");
		}

		if (result.hasErrors()) {
			model.addAttribute("loginUser", new LoginUser());
			return "users/loginreg.jsp";
		}
		User newUser = userServ.registerUser(user);
		session.setAttribute("user_id", newUser.getId());

		return "redirect:/books";
	}

	// Route to process the Login for user + validations
	@PostMapping("/user/process/login")
	public String processLoginUser(@Valid @ModelAttribute("loginUser") LoginUser loginUser, BindingResult result,
			Model model, HttpSession session) {

		User loggingUser = userServ.login(loginUser, result);
		if (result.hasErrors()) {
			model.addAttribute("user", new User());
			return "users/loginreg.jsp";
		}
		session.setAttribute("user_id", loggingUser.getId());
		return "redirect:/books";
	}

	// don't forget to add the / for redirect (mapping error for user not in session)
	// Route for welcome page
	@GetMapping("/books")
	public String welcome(Model model, HttpSession session) {
		if (session.getAttribute("user_id") == null) {
			return "redirect:/";
		}
		model.addAttribute("oneUser", userServ.getUser((Long) session.getAttribute("user_id")));
		model.addAttribute("AllBook", bookServ.getAll());
		return "books/welcome.jsp";
	}

	// Route for Logout
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}

}
