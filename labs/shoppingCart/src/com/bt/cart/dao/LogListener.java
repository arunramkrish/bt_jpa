package com.bt.cart.dao;

import javax.persistence.PrePersist;

public class LogListener {
	@PrePersist
	private void log(Object object) {
		System.out.println("prepersist " + object);
	}
}
