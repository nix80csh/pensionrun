package com.pensionrun.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pensionrun.dto.AccountDto;
import com.pensionrun.dto.AccountPensionDto;
import com.pensionrun.dto.JsonDto;
import com.pensionrun.service.AccountPensionService;
import com.pensionrun.service.AccountService;

@Controller
@RequestMapping(value = "/account")
public class AccountController {

	@Autowired
	AccountService accountService;
	@Autowired
	AccountPensionService accountPensionService;
	
	@ResponseBody
	@RequestMapping(value = "/prAccountCreate", method = RequestMethod.POST)
	public ResponseEntity<Object> prAccountCreate(@RequestBody AccountDto accountDto) {
		JsonDto<AccountDto> jDto = accountService.prAccountCreate(accountDto);
		return new ResponseEntity<Object>(jDto, HttpStatus.OK);
	}

	@ResponseBody
	@RequestMapping(value = "/snsAccountCreate", method = RequestMethod.POST)
	public ResponseEntity<Object> snsAccountCreate(@RequestBody AccountDto accountDto) {
		JsonDto<AccountDto> jDto = accountService.snsAccountCreate(accountDto);
		return new ResponseEntity<Object>(jDto, HttpStatus.OK);
	}
	
	@ResponseBody
	@RequestMapping(value = "/readAccountByIdfAccount/{idfAccount}", method = RequestMethod.GET)
    public ResponseEntity<Object> readAccountByIdfAccount(@PathVariable Integer idfAccount) {   
		JsonDto<AccountDto> jDto = accountService.readAccountByIdfAccount(idfAccount);
		return new ResponseEntity<Object>(jDto, HttpStatus.OK);
	}	
	
	@ResponseBody
	@RequestMapping(value = "/accountPensionCreate", method = RequestMethod.POST)
	public ResponseEntity<Object> accountPensionCreate(@RequestBody AccountPensionDto accountPensionDto) {
		JsonDto<AccountPensionDto> jDto = accountPensionService.AccountPensionCreate(accountPensionDto);
		return new ResponseEntity<Object>(jDto, HttpStatus.OK);
	}
	
	@ResponseBody
	@RequestMapping(value = "/accountPensionDelete", method = RequestMethod.POST)
	public ResponseEntity<Object> accountPensionDelete(@RequestBody AccountPensionDto accountPensionDto) {
		JsonDto<AccountPensionDto> jDto = accountPensionService.AccountPensionDelete(accountPensionDto);
		return new ResponseEntity<Object>(jDto, HttpStatus.OK);
	}
	

}
