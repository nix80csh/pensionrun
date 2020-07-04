package com.pensionrun.dao;

import java.util.List;

import com.pensionrun.entity.Order;
import com.pensionrun.vo.OrderSearchVo;

public interface OrderDao extends GenericDao<Order>{
	public List<Order> readByOrderSearchVo(OrderSearchVo orderSearchVo);
	public int getMaxSizeByOrderSearchVo(OrderSearchVo orderSearchVo);
	public List<Order> readByAccount(Integer idfAccount);
}
