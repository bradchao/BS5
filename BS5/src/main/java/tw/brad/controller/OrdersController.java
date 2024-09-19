package tw.brad.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tw.brad.model.Cust;
import tw.brad.model.Orders;
import tw.brad.service.OrdersServiceImpl;

@RequestMapping("/orders")
@RestController
public class OrdersController {

	@Autowired
	private OrdersServiceImpl ordersServiceImpl;
	
	@PostMapping("/add/{userid}")
	public Orders add(@RequestBody Orders order, @PathVariable Long userid) {
		return ordersServiceImpl.addOrder(order, userid);
	}
	
	@DeleteMapping("/delete/{id}")
	public void del(@PathVariable Long id) {
		ordersServiceImpl.delOrder(id);
	}
	
	
}
