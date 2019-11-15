package com.springboot.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.model.Organizacion;
import com.springboot.model.OrganizacionRequest;
import com.springboot.service.OrganizacionService;

@RestController
public class OrganizacionController {

	@Autowired
	private OrganizacionService organizacionService;

	@RequestMapping(value = "/organizacion", method = RequestMethod.GET)
	Organizacion getOrg(@RequestParam OrganizacionRequest org) {
		Organizacion organizacion = organizacionService.findOne(org.getId());
		if (organizacion.getCodigo().equals(org.getCode())) {
			organizacion = null;
		}
		return organizacion;
	}

	@RequestMapping(value = "/organizacion", method = RequestMethod.POST)
	String addOrg(@RequestBody Organizacion org) {

		Random rand = new Random();
		int num = rand.nextInt(900000) + 100000;

		String codigo = "C" + String.valueOf(num);

		org.setCodigo(codigo);

		String id = org.getCodigo().substring(0, 4) + org.getCodigo().substring(org.getCodigo().length() - 4, 4);

		org.setId(id);

		organizacionService.save(org);
		return "SUCCESS";
	}

	@RequestMapping(value = "/organizacion", method = RequestMethod.PUT)
	Map updateOrg(@RequestBody Organizacion org) {
		Map<String, String> status = new HashMap<>();
		Organizacion organizacion = organizacionService.findOne(org.getId());
		if (organizacion.getCodigo().equals(org.getCodigo())) {
			organizacion = null;
			organizacionService.save(org);
			status.put("Status", "Success");
		} else {

			status.put("Status", "Fail");
		}
		return status;
	}

	@RequestMapping(value = "/organizacion", method = RequestMethod.DELETE)
	Map deleteOrg(@RequestParam Organizacion org) {

		Map<String, String> status = new HashMap<>();
		Organizacion organizacion = organizacionService.findOne(org.getId());
		if (organizacion.getCodigo().equals(org.getCodigo())) {
			organizacion = null;
			organizacionService.delete(org.getId());
			status.put("Status", "Success");
		} else {

			status.put("Status", "Fail");
		}
		return status;
	}

}
