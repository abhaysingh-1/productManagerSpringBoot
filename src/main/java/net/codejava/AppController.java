package net.codejava;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AppController {

	@Autowired
	private ProductService service; 
	@Autowired
	private UserService userService; 
	@Autowired
	private UserRepo userRepo; 
	
	
	
	
	@RequestMapping("/home")
	public String viewHomePage(Model model) {
		List<Product> listProducts = service.listAll();
		model.addAttribute("listProducts", listProducts);
		
		return "index";
	}
	
	@RequestMapping("/new")
	public String showNewProductPage(Model model) {
		Product product = new Product();
		model.addAttribute("product", product);
		
		return "new_product";
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String saveProduct(@ModelAttribute("product") Product product) {
		service.save(product);
		
		return "redirect:/home";
	}
	
	@RequestMapping("/edit/{id}")
	public String  showEditProductPage(@PathVariable(name = "id") int id,Model model) {
		Product product = service.get(id);
		model.addAttribute("product", product);
		return "edit_product" ;
	}
	
	@RequestMapping("/delete/{id}")
	public String deleteProduct(@PathVariable(name = "id") int id) {
		service.delete(id);
		return "redirect:/home";		
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ModelAndView login(@ModelAttribute("user") User user) {
		User user1=userRepo.findByNameAndPasword(user.getName(), user.getPasword());
		if(user1==null)
		{
			ModelAndView userInfo = new ModelAndView("error");
			return userInfo;	
		}
		ModelAndView userInfo = new ModelAndView("loginedinfo");
		userInfo.addObject("user", user1);
		
		return userInfo;
	}
	
	@RequestMapping("/login")
	public String login(Model model) {
		User user = new User();
		model.addAttribute("user", user);
		return "login";
	}
	
	@RequestMapping("/register")
	public String register(Model model) {
		User register = new User();
		model.addAttribute("register", register);
		
		return "register";
	}
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String register(@ModelAttribute("register") User register) {
		userService.save(register);
		return "redirect:/login";
	}
	@RequestMapping("/logout")
	public String logout(Model model) {
		User user = new User();
		model.addAttribute("user", user);
		return "login";
	}
	@RequestMapping("/")
	public String processLogin(Model model) {
		User user = new User();
		model.addAttribute("user", user);
		return "login";
	}
	@RequestMapping("/updatepassword")
	public ModelAndView  processLogin(String email) {
		User user=userRepo.findByName(email);
		ModelAndView userInfo = new ModelAndView("updateinfo");
		userInfo.addObject("user", user);
		return userInfo;
	}
	/*
	 * @RequestMapping(value = "/register", method = RequestMethod.POST) public
	 * String register(@ModelAttribute("register") User register) {
	 * userService.save(register); return "redirect:/login"; }
	 */
}
