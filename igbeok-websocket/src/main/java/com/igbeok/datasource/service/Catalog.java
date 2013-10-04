package com.igbeok.datasource.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.igbeok.datasource.dao.CommonDao;
import com.igbeok.datasource.entity.Item;

@Service
public class Catalog {

	@Autowired
	private CommonDao dao;

	public Long saveItem(Item item) {
		dao.save(item);
		return item.getId();
	}

	public List<Item> getItems() {
		return dao.getAll();
	}
}
