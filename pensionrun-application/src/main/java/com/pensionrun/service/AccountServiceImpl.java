package com.pensionrun.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pensionrun.dao.AccountDao;
import com.pensionrun.dto.AccountDto;
import com.pensionrun.dto.JsonDto;
import com.pensionrun.entity.Account;
import com.pensionrun.util.EncryptionSha256Util;

@Service
public class AccountServiceImpl implements AccountService {

	@Autowired
	AccountDao accountDao;

	@Override
	public JsonDto<AccountDto> prAccountCreate(AccountDto _accountDto) {
		JsonDto<AccountDto> jDto = new JsonDto<AccountDto>();
		Account account = new Account();
		Account _account = accountDao.readByUserId(_accountDto.getEmail());
		if (_account == null) {
			BeanUtils.copyProperties(_accountDto, account);
			account.setUserid(_accountDto.getEmail());
			account.setPassword(EncryptionSha256Util.getEncSHA256(_accountDto.getPassword()));
			account.setTypeServicePlatform('P');
			account.setCoin(2000); // 초기코인세팅
			account.setState('N'); // 정상회원
			accountDao.create(account);		
			BeanUtils.copyProperties(account, _accountDto);	
			_accountDto.setPassword(null);
			jDto.setResultCode("S");
			jDto.setDataObject(_accountDto);
		} else {
			jDto.setResultCode("F");
			jDto.setResultMessage("Exist Account");
		}
		return jDto;
	}

	@Override
	public JsonDto<AccountDto> snsAccountCreate(AccountDto _accountDto) {
		JsonDto<AccountDto> jDto = new JsonDto<AccountDto>();
		Account account = new Account();
		Account _account = accountDao.readByUserId(_accountDto.getUserid());
		if (_account == null) {
			BeanUtils.copyProperties(_accountDto, account);			
			account.setPassword(EncryptionSha256Util.getEncSHA256(_accountDto.getUserid()));			
			account.setCoin(2000); // 초기코인세팅
			account.setState('N'); // 정상회원
			accountDao.create(account);		
			BeanUtils.copyProperties(account, _accountDto);	
			_accountDto.setPassword(null);
			jDto.setResultCode("S");
			jDto.setDataObject(_accountDto);
		} else {
			jDto.setResultCode("F");
			jDto.setResultMessage("Exist Account");
		}
		return jDto;
	}

	@Override
	public JsonDto<AccountDto> readAccountByIdfAccount(Integer idfAccount) {
		JsonDto<AccountDto> jDto = new JsonDto<AccountDto>();
		AccountDto accountDto = new AccountDto();
		Account account = accountDao.readById(idfAccount);
		if (account == null) {
			jDto.setResultCode("F");
			jDto.setResultMessage("Not Exist Account");
		} else {			
			BeanUtils.copyProperties(account, accountDto);
			accountDto.setPassword(null);
			jDto.setResultCode("S");
			jDto.setDataObject(accountDto);
		}
		return jDto;
	}

}
