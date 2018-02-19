package hello;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApplicationController {

//	@RequestMapping("/error")
	public String sayHi()
	{
		return "Hi";
	}
}
