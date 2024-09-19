package tw.brad.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tw.brad.model.Cust;
import tw.brad.model.Orders;
import tw.brad.repository.CustRespository;
import tw.brad.repository.OrderRepository;

@Service
public class OrdersServiceImpl {
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private CustRespository custRespository;
	
	public Orders addOrder(Orders orders, Long userId) {
		Cust cust = custRespository.findById(userId).orElse(null);
		if (cust != null) {
			orders.setCust(cust);
			return orderRepository.save(orders);
		}else {
			return null;
		}
		
	}
	
	public void delOrder(Long id) {
		orderRepository.deleteById(id);
	}
	
	
}
