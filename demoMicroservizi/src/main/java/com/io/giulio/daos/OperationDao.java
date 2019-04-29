package com.io.giulio.daos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.io.giulio.model.Operation;

import java.util.List;

public interface OperationDao extends JpaRepository<Operation, String> {
	

	@Query(value = "SELECT * FROM operation WHERE FK_ACCOUNT1=:account OR FK_ACCOUNT2=:account", nativeQuery= true)
	List<Operation> findAllOperastionByAccount(@Param("account") String account);

}
