package com.pensionrun.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pensionrun.dao.AccountPensionDao;
import com.pensionrun.dto.AccountPensionDto;
import com.pensionrun.dto.JsonDto;
import com.pensionrun.entity.AccountPension;
import com.pensionrun.entity.AccountPensionId;

@Service
public class AccountPensionServiceImpl implements AccountPensionService {

	@Autowired
	AccountPensionDao accountPensionDao;
	
	@Override
	public JsonDto<AccountPensionDto> AccountPensionCreate(AccountPensionDto _accountPensionDto) {
		JsonDto<AccountPensionDto> jDto = new JsonDto<AccountPensionDto>();
		AccountPension accountPension = new AccountPension();
		AccountPensionId accountPensionId = new AccountPensionId();
		accountPensionId.setIdfAccount(_accountPensionDto.getIdfAccount());
		accountPensionId.setIdfPension(_accountPensionDto.getIdfPension());		
		
		if(accountPensionDao.readById(accountPensionId) == null){			
			accountPension.setId(accountPensionId);			
			accountPensionDao.create(accountPension);		
			jDto.setResultCode("S");
			jDto.setDataObject(_accountPensionDto);		
		}else{
			jDto.setResultCode("F");
			jDto.setResultMessage("Exist AccountPension");
		}
		
		
		return jDto;
	}

	@Override
	public JsonDto<AccountPensionDto> AccountPensionDelete(AccountPensionDto _accountPensionDto) {
		JsonDto<AccountPensionDto> jDto = new JsonDto<AccountPensionDto>();
		AccountPensionId accountPensionId = new AccountPensionId();	
		accountPensionId.setIdfAccount(_accountPensionDto.getIdfAccount());
		accountPensionId.setIdfPension(_accountPensionDto.getIdfPension());
		AccountPension accountPension = accountPensionDao.readById(accountPensionId);		
		if (accountPension == null) {
			jDto.setResultCode("F");
			jDto.setResultMessage("Not Exist AccountPension");
		} else {
			accountPensionDao.delete(accountPension);
			jDto.setResultCode("S");
		}
		return jDto;
	}

}
