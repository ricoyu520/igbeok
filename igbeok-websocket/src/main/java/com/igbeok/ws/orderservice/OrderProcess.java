package com.igbeok.ws.orderservice;

import javax.jws.WebService;

@WebService
public interface OrderProcess {

	String processOrder(Order order);
}
