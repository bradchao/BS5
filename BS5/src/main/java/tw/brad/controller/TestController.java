package tw.brad.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.http.HttpSession;
import tw.brad.model.MyTest;
import tw.brad.model.MyTestResonse;
import tw.brad.service.TestService;

@RequestMapping("/test")
@RestController
public class TestController {

	@GetMapping("/01")
	public MyTest test01() {
		MyTest myTest = new MyTest();
		myTest.setId(1L);
		myTest.setName("Brad");
		myTest.addFriend("Peter").addFriend("Tony").addFriend("Amy");
		
		return myTest;
	}
	
	@PostMapping("/02")
	public MyTest test02(@RequestBody MyTest myTest, HttpSession httpSession) {
		System.out.println(myTest.getId() + ":" + myTest.getName());
		myTest.addFriend("Peter").addFriend("Tony").addFriend("Amy");
		
		httpSession.setAttribute("myTest", myTest);
		
		return myTest;
	}
	
	@PostMapping("/03")
	public MyTestResonse test03(HttpSession httpSession) {
		MyTestResonse myTestResonse = new MyTestResonse();
		
		if (httpSession.getAttribute("myTest") != null) {
			MyTest myTest = (MyTest)httpSession.getAttribute("myTest");
			myTestResonse.setCode(0);
			myTestResonse.setMesg("success");
			myTestResonse.setMyTest(myTest);
			
		}else {
			myTestResonse.setCode(-1);
			myTestResonse.setMesg("no session");
			
		}
		
		return myTestResonse;
	}
	
	@PostMapping("/04")
	public MyTestResonse test04(HttpSession httpSession) {
		httpSession.invalidate();
		MyTestResonse myTestResonse = new MyTestResonse();
		myTestResonse.setCode(0);
		myTestResonse.setMesg("logout");
		return myTestResonse;
	}
	
	@Autowired
	private TestService testService;

	@PostMapping("/05")
	public ResponseEntity<String> test05(@RequestParam("uploadFile") MultipartFile uploadFile) {
		System.out.println(uploadFile.getSize());
		
		try {
			testService.uploadFile(uploadFile);
			return ResponseEntity.status(HttpStatus.OK).body("upload success");
		} catch (IOException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("upload failure");
		}
	}
	
	
}
