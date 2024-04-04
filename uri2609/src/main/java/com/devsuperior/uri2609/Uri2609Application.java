package com.devsuperior.uri2609;

import com.devsuperior.uri2609.dto.CategorySumDTO;
import com.devsuperior.uri2609.projections.CategorySumProjection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.devsuperior.uri2609.repositories.CategoryRepository;

import java.util.List;
import java.util.stream.Collectors;

@SpringBootApplication
public class Uri2609Application implements CommandLineRunner {

	@Autowired
	private CategoryRepository repository;
	
	public static void main(String[] args) {
		SpringApplication.run(Uri2609Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		List<CategorySumProjection> groupSumList = repository.groupQuantityByCategorySQL();
		List<CategorySumDTO> categorySumList = groupSumList.stream().map(CategorySumDTO::new).collect(Collectors.toList());

		System.out.println("\n*** SQL ***");
		for (CategorySumDTO cDto : categorySumList){
			System.out.println(cDto);
		}

		System.out.println("\n\n");

		List<CategorySumDTO> categorySumListJPQL = repository.groupQuantityByCategoryJPQL();

		System.out.println("\n*** JPQL ***");
		for (CategorySumDTO cDto : categorySumListJPQL){
			System.out.println(cDto);
		}
	}
}
