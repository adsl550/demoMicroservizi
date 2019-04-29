package com.io.giulio.daos;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.io.giulio.model.Account;


public interface AccountDao extends JpaRepository<Account, String>{

	
	@Query(value = "SELECT * FROM account WHERE FK_USER=:user",nativeQuery = true)
	List<Account> getAllAccountToUser(@Param("user") String user);
	
	List<Account> findFBykUser(String fkUser);

}
