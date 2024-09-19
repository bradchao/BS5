package tw.brad.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import tw.brad.model.Cust;

public interface CustRespository extends JpaRepository<Cust, Long>{

}
