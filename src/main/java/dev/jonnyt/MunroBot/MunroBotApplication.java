package dev.jonnyt.MunroBot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class MunroBotApplication {

	public static void main(String[] args) {
		SpringApplication.run(MunroBotApplication.class, args);
	}

	@GetMapping("/*")
	public String home() throws Exception {
		TweetBuilder tb = new TweetBuilder();
		return "@MunroBot";
	}
}

@RestController
class ErrorHandlingController implements ErrorController {
	@GetMapping("/error")
	public String error() {
		return "@MunroBot";
	}

	@Override
	public String getErrorPath() {
		return "/error";
	}
}
