package com.devsuperior.uri2621;

import com.devsuperior.uri2621.dto.ProductMinDTO;
import com.devsuperior.uri2621.projections.ProductMinProjection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.devsuperior.uri2621.repositories.ProductRepository;

import java.util.List;
import java.util.stream.Collectors;

@SpringBootApplication
public class Uri2621Application implements CommandLineRunner {

	@Autowired
	private ProductRepository repository;
	
	public static void main(String[] args) {
		SpringApplication.run(Uri2621Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		List<ProductMinProjection> listSql = repository.findProductByProducerAndQuantitySQL("P", 10, 20);
		List<ProductMinDTO> listProductDTO = listSql.stream().map(ProductMinDTO::new).collect(Collectors.toList());

		System.out.println("\n*** SQL ***");
		for (ProductMinDTO pDto : listProductDTO){
			System.out.println(pDto);
		}

		System.out.println("\n\n");

		List<ProductMinDTO> listProductJPQL = repository.findProductByProducerAndQuantityJPQL("P", 10, 20);

		System.out.println("\n*** JPQL ***");
		for (ProductMinDTO pDto : listProductJPQL){
			System.out.println(pDto);
		}
	}
}
