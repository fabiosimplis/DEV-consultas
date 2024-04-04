package com.devsuperior.uri2990.repositories;

import com.devsuperior.uri2990.dto.EmpregadoDeptDTO;
import com.devsuperior.uri2990.projections.EmpregadoDeptProjection;
import org.springframework.data.jpa.repository.JpaRepository;

import com.devsuperior.uri2990.entities.Empregado;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EmpregadoRepository extends JpaRepository<Empregado, Long> {

    @Query(nativeQuery = true, value =
            "SELECT empregados.cpf, empregados.enome, departamentos.dnome " +
            "FROM empregados " +
            "INNER JOIN departamentos ON empregados.dnumero = departamentos.dnumero " +
            "WHERE empregados.cpf NOT IN ( " +
            " SELECT trabalha.cpf_emp " +
            " FROM trabalha) " +
            "ORDER BY empregados.cpf")
    List<EmpregadoDeptProjection> searchSQL();


   /*@Query("SELECT new com.devsuperior.uri2990.dto.EmpregadoDeptDTO(e.cpf, e.enome, e.departamento.dnome) " +
                    "FROM Empregado e " +
                    "WHERE e.cpf NOT IN ( " +
                    " SELECT e.projetosOndeTrabalha FROM Empregado e " +
                    ") " +
                    "ORDER BY e.cpf")*/
   @Query("SELECT new com.devsuperior.uri2990.dto.EmpregadoDeptDTO(e.cpf, e.enome, e.departamento.dnome) " +
           "FROM Empregado e " +
           "WHERE e.cpf NOT IN ( " +
           " SELECT e.cpf " +
           " FROM Empregado e " +
           " INNER JOIN e.projetosOndeTrabalha " +
           ") " +
           "ORDER BY e.cpf")
    List<EmpregadoDeptDTO> searchJPQL();
}
