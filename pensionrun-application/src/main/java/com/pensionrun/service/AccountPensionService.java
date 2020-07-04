package com.pensionrun.service;

import org.springframework.transaction.annotation.Transactional;


import com.pensionrun.dto.AccountPensionDto;
import com.pensionrun.dto.JsonDto;

@Transactional
public interface AccountPensionService {
	JsonDto<AccountPensionDto> AccountPensionCreate(AccountPensionDto _accountPensionDto);
	JsonDto<AccountPensionDto> AccountPensionDelete(AccountPensionDto _accountPensionDto);
}
