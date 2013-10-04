package com.igbeok.datasource.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Item implements Serializable{
	
	private static final long serialVersionUID = 3837000250345987440L;
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(name="ITEM_NAME", nullable=false, unique=false)
	private String itemName;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	
	public Item() {
		System.out.println("Item created!");
	}
}
