package com.ifpi.ted2019.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ifpi.ted2019.domain.Medico;
import com.ifpi.ted2019.repositories.MedicoRepository;

@Service
public class MedicoService {

	@Autowired
	private MedicoRepository repository;
	
	public Medico buscar(Integer id) {
		Optional<Medico> object = repository.findById(id);
		return object.orElse(null);
	}
}
