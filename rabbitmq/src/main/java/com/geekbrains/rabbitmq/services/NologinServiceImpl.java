package com.geekbrains.rabbitmq.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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
	public void save(Nologin nologin) throws JsonProcessingException {

		ObjectMapper mapper = new ObjectMapper();
		String noLoginAsString = mapper.writeValueAsString(nologin);
		System.out.println(noLoginAsString);
		nologin = mapper.readValue(noLoginAsString, Nologin.class);

		nologinRepository.save(nologin);
	}



}
