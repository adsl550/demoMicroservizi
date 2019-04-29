package com.io.giulio.services;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.io.giulio.daos.AccountDao;
import com.io.giulio.daos.OperationDao;
import com.io.giulio.model.Account;
import com.io.giulio.model.Operation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Service 
@Transactional
public class OperationServiceImpl implements OperationService {

	private static final Logger log = LoggerFactory.getLogger(OperationServiceImpl.class);
	
	@Autowired
	AccountDao accountDao;
	
	@Autowired
	OperationDao operationDao;
		
	
	@Override
	public List<Operation> getAllOperationToAccount(String accountid) {
		
		return operationDao.findAllOperastionByAccount(accountid);
	}

	@Override
	public List<Account> getAllAccountToUser(String userId) {
			return accountDao.getAllAccountToUser(userId);
	}

	@Override
	public Operation saveOperation(Operation operation) {
		
		if(operation.getDate()== null) {
			operation.setDate(new Date());
		}
		return operationDao.save(operation);
	}

}
