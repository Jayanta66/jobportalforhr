package jobportal;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class AppController {
	@Autowired
	private ProductService service;
		
	@RequestMapping({"/","index"})
	public String viewHomePagee(Model model, @Param("keyword") String keyword) {
		List<Product> listProducts = service.listAll(keyword);
		model.addAttribute("listProducts", listProducts);
		model.addAttribute("keyword", keyword);
		
		return "/index";
	}
	
	@RequestMapping("/index")
	public String viewHomePage1(Model model, @Param("keyword") String keyword) {
		List<Product> listProducts = service.listAll(keyword);
		model.addAttribute("listProducts", listProducts);
		model.addAttribute("keyword", keyword);
		
		return "/index";
	}
	
	
	
	@RequestMapping("/joblist")
	public String viewHomePage(Model model, @Param("keyword") String keyword) {
		List<Product> listProducts = service.listAll(keyword);
		model.addAttribute("listProducts", listProducts);
		model.addAttribute("keyword", keyword);
		
		return "/joblist";
	}
	
	
	
	
	@RequestMapping("/new")
	public String showNewProductForm(Model model) {
		Product product = new Product();
		model.addAttribute("product", product);
		
		return "new_product";
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String saveProduct(@ModelAttribute("product") Product product) {
		service.save(product);
		
		return "redirect:/joblist";
	}
	
	@RequestMapping("/edit/{id}")
	public ModelAndView showEditProductForm(@PathVariable(name = "id") Long id) {
		ModelAndView mav = new ModelAndView("edit_product");
		
		Product product = service.get(id);
		mav.addObject("product", product);
		
		return mav;
	}	
	
	@RequestMapping("/delete/{id}")
	public String deleteProduct(@PathVariable(name = "id") Long id) {
		service.delete(id);
		
		return "redirect:/joblist";
	}
	
	///======================================================================
	
	@Autowired
	private SecurityService securityService;
	
	@Autowired
	private UserRepository userRepository;
		
	@Autowired
	private PasswordEncoder encoder;
	
	@GetMapping({"/login"})
	public String login() {
		return "login";
		}
	
	@GetMapping({"/home"})
	public String home() {
		return "home";
		}
	

	
	@PostMapping("/login")
	public String longinresponse(String email, String password,HttpServletRequest request, HttpServletResponse response) {
		boolean loginResponse = securityService.longin(email, password,request, response);

		if(loginResponse) {
			return "/joblist";
		}
		return "login";
		
	}
		
	@GetMapping("/registration")
	public String registration() {
		return "registration";
		}
	
	@PostMapping("/registration")
	public String register(User user) {
		user.setPassword(encoder.encode(user.getPassword()));
		userRepository.save(user);
		return "login";
		}

	/*
	@GetMapping({"/edit_product"})
	public String editjob() {
		return "edit_product";
		}
	
	@GetMapping({"/new_product"})
	public String newjob() {
		return "new_product";
		}
	
	@GetMapping({"/searchbyid"})
	public String searchbyid() {
		return "searchbyid";
		}
*/
	/*
	@RequestMapping("/new_product")
	public String showNewProductForm(Model model) {
		Product product = new Product();
		model.addAttribute("product", product);
		
		return "new_product";
	}
	*/
	/*
    @GetMapping("/searchbyid")
    public String add(Model model) {
    	List<Product> listemployee = service.listAll();
        model.addAttribute("employee", new Product());
        return "searchbyid";
    }
	
    @PostMapping("/search")
    public String doSearchEmployee(@ModelAttribute("searchbyid") Product formData, Model model) {
           Product product = service.get(formData.getId());
           model.addAttribute("product", product);
           return "searchbyid";            
    }
	
*/
}
