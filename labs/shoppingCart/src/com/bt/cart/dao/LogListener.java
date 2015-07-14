package com.bt.cart.dao;

import javax.annotation.PreDestroy;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

public class LogListener {
	@PrePersist
	@PreUpdate
	@PreDestroy
	private void log(Object object) {
//		System.out.println("prepersist " + object);
	}

}
