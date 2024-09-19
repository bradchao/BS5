package tw.brad.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tw.brad.model.Cust;
import tw.brad.model.Orders;
import tw.brad.repository.CustRespository;

@Service
public class CustServiceImpl {

	@Autowired
	private CustRespository custRespository;
	
	public Cust addCust(Cust cust) {
		return custRespository.save(cust);
	}
	
	public void delCust(Long id) {
		custRespository.deleteById(id);
	}
	
	public List<Orders> getAllOrders(Long id){
		Cust cust = custRespository.findById(id).orElse(null);
		return cust.getOrders();
	}
}
