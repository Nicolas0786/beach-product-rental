package com.salsatechnology.controller;

import javax.validation.Valid;

import com.salsatechnology.model.ProductOrder;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.salsatechnology.dto.ProductOrderDTO;
import com.salsatechnology.service.ProductOrderService;

import java.util.List;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ProductOrderController {

	private final ProductOrderService productOrderService;

	@PostMapping("/create")
	@ResponseStatus(HttpStatus.CREATED)
	public void createOrder(@RequestBody @Valid ProductOrderDTO productOrderDTO) {
		this.productOrderService.createOrder(productOrderDTO);
	}

	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<ProductOrder> findAll() {
		return this.productOrderService.findAll();
	}
}
