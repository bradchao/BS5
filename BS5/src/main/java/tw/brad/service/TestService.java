package tw.brad.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import tw.brad.model.Test;
import tw.brad.repository.TestRepository;

@Service
public class TestService {

	@Autowired
	private TestRepository testRepository;
	
	public Test uploadFile(MultipartFile file) throws IOException{
		String filename = file.getOriginalFilename();
		byte[] uploadData = file.getBytes();
		
		Test test = new Test("brad", uploadData);
		return testRepository.save(test);
	}
}
