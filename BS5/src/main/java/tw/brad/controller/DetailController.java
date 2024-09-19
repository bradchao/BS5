package tw.brad.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tw.brad.model.Detail;
import tw.brad.repository.DetailRepository;

@RequestMapping("/detail")
@RestController
public class DetailController {
	@Autowired
	private DetailRepository detailRepository;
	
	@RequestMapping("/get/{id}")
	public Detail getDetail(@PathVariable Long id) {
		Detail detail = detailRepository.findById(id).orElse(null);
		return detail;
	}
}
