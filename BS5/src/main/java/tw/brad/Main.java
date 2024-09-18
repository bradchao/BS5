package tw.brad;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Main {
	
	@RequestMapping("/")
	public String index() {
		return "index";
	}
	
	@RequestMapping("/main")
	public String main() {
		return "main";
	}
	
	@RequestMapping("/header")
	public String header() {
		return "header";
	}
	
	@RequestMapping("/footer")
	public String footer() {
		return "footer";
	}
	
}
