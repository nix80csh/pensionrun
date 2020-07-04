package com.pensionrun.service;

import org.springframework.transaction.annotation.Transactional;

import com.pensionrun.dto.AccountDto;
import com.pensionrun.dto.JsonDto;

@Transactional
public interface AccountService {
	JsonDto<AccountDto> prAccountCreate(AccountDto _accountDto);
	JsonDto<AccountDto> snsAccountCreate(AccountDto _accountDto);
	JsonDto<AccountDto> readAccountByIdfAccount(Integer idfAccount);
}
