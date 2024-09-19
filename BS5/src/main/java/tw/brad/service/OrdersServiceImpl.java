package tw.brad.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tw.brad.model.Orders;
import tw.brad.repository.OrderRepository;

@Service
public class OrdersServiceImpl {
	@Autowired
	private OrderRepository orderRepository;
	
	public Orders addOrder(Orders orders) {
		return orderRepository.save(orders);
	}
	
	public void addOrder(Long id) {
		orderRepository.deleteById(id);
	}
	
}
