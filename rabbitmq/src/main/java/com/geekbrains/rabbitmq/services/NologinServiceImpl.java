package com.geekbrains.rabbitmq.services;

import com.geekbrains.rabbitmq.entity.Nologin;
import com.geekbrains.rabbitmq.repositories.NologinRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class NologinServiceImpl implements NologinService {
	private NologinRepository nologinRepository;

	@Autowired
	public void setNologinRepository(NologinRepository nologinRepository) {
		this.nologinRepository = nologinRepository;
	}


	@Override
	@Transactional
	public void save(Nologin nologin) {
		nologinRepository.save(nologin);
	}

}
