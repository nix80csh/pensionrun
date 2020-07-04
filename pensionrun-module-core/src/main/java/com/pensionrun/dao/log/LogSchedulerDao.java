package com.pensionrun.dao.log;

import java.util.Date;

import com.pensionrun.dao.GenericDao;
import com.pensionrun.entity.log.LogScheduler;

public interface LogSchedulerDao extends GenericDao<LogScheduler> {

	Date readMaxUpdDate(char type);
	
}
