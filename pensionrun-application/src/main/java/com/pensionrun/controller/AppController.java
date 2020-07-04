package com.pensionrun.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pensionrun.dto.AppInfoDto;
import com.pensionrun.dto.JsonDto;
import com.pensionrun.service.AppInfoService;

@Controller
@RequestMapping(value = "/app")
public class AppController {
	
	@Autowired
	AppInfoService appInfoService;
	
	@ResponseBody
    @RequestMapping(value = "/readAppInfoListByVersion/{version}/{typeOs}", method = RequestMethod.GET)
    public ResponseEntity<Object> readAppInfoListByVersion(@PathVariable String version, @PathVariable char typeOs) {        
        JsonDto<AppInfoDto> jDto = appInfoService.readAppInfoListByVersion(version, typeOs);
        return new ResponseEntity<Object>(jDto, HttpStatus.OK);
    }
}
