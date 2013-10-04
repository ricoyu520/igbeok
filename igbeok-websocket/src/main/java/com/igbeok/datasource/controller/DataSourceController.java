package com.igbeok.datasource.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.igbeok.datasource.CustomerContextHolder;
import com.igbeok.datasource.CustomerType;
import com.igbeok.datasource.entity.Item;
import com.igbeok.datasource.service.Catalog;

@Controller
@RequestMapping("/datasource")
public class DataSourceController {

	@Autowired
	private Catalog catalog;

	@RequestMapping(value = "/save/{datasource}", method = RequestMethod.POST)
	@ResponseBody
	public Long saveItem(@RequestBody Item item, @PathVariable("datasource") String dataSource) {
		CustomerContextHolder.setCustomerType(CustomerType.valueOf(dataSource));
		catalog.saveItem(item);
		return item.getId();
	}

	@RequestMapping(value = "/list/{datasource}", method = RequestMethod.GET)
	@ResponseBody
	public List<Item> getItems(@PathVariable("datasource") String dataSource) {
		CustomerContextHolder.setCustomerType(CustomerType.valueOf(dataSource));
		return catalog.getItems();
	}

}
