package com.devsuperior.uri2990;

import com.devsuperior.uri2990.dto.EmpregadoDeptDTO;
import com.devsuperior.uri2990.projections.EmpregadoDeptProjection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.devsuperior.uri2990.repositories.EmpregadoRepository;

import java.util.List;
import java.util.stream.Collectors;

@SpringBootApplication
public class Uri2990Application implements CommandLineRunner {

	@Autowired
	private EmpregadoRepository repository;
	
	public static void main(String[] args) {
		SpringApplication.run(Uri2990Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		List<EmpregadoDeptProjection> listProj = repository.searchSQL();
		List<EmpregadoDeptDTO> listDTO = listProj.stream().map(EmpregadoDeptDTO::new).collect(Collectors.toList());

		System.out.println("\n**** SQL ****");
		for (EmpregadoDeptDTO empDto : listDTO){
			System.out.println(empDto);
		}
		System.out.println("\n\n");

		List<EmpregadoDeptDTO> listDTOJPQL = repository.searchJPQL();

		System.out.println("\n**** JPQL ****");
		for (EmpregadoDeptDTO empDto : listDTOJPQL){
			System.out.println(empDto);
		}
		System.out.println("\n\n");
	}
}
