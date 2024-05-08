package jobportal.service;

//import com.example.liveweb.bean.User;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jobportal.beans.User;

public interface SecurityService {

	boolean longin(String username, String password, HttpServletRequest request, HttpServletResponse response);
	
	public User save(User user);
}
