package tw.brad.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import tw.brad.model.Orders;

public interface OrderRepository extends JpaRepository<Orders, Long>{

}
