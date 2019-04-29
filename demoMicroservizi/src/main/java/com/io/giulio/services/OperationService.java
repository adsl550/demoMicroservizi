package com.io.giulio.services;

import java.util.List;

import com.io.giulio.model.Account;
import com.io.giulio.model.Operation;

public interface OperationService {

	List<Operation> getAllOperationToAccount(String accountid);
	List<Account> getAllAccountToUser(String id);
	Operation saveOperation(Operation operation);
}
