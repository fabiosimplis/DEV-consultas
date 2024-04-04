package com.devsuperior.uri2602.repositories;

import com.devsuperior.uri2602.dto.CustomerMinDTO;
import com.devsuperior.uri2602.entities.Customer;
import com.devsuperior.uri2602.projections.CustomerMinProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    @Query(nativeQuery = true, value = "SELECT name FROM customers WHERE UPPER(state) = UPPER(:state)")
    List<CustomerMinProjection> getCustomersNameByStateSQL(String state);

    @Query("SELECT new com.devsuperior.uri2602.dto.CustomerMinDTO(c.name)" +
            " FROM Customer c" +
            " WHERE UPPER(c.state) = UPPER(:state)")
    List<CustomerMinDTO> getCustomersNameByStateJPQL(String state);
}
