package com.igbeok.ws.orderservice.impl;

import javax.jws.WebService;

import com.igbeok.ws.orderservice.Order;
import com.igbeok.ws.orderservice.OrderProcess;

@WebService(endpointInterface="com.igbeok.ws.orderservice.OrderProcess")
public class OrderProcessImpl implements OrderProcess{

	@Override
	public String processOrder(Order order) {
		return order.validate();
	}

}
