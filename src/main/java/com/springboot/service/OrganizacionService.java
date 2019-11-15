package com.springboot.service;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.model.Organizacion;

public interface OrganizacionService extends JpaRepository<Organizacion, String>{
}
