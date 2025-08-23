package com.salsatechnology.controller;

import com.salsatechnology.dto.ProductOrderDTO;
import com.salsatechnology.dto.ProductOrderResponseDTO;
import com.salsatechnology.mapper.ProductOrderMapper;
import com.salsatechnology.model.ProductType;
import com.salsatechnology.service.ProductOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ProductOrderController {

	private final ProductOrderService productOrderService;

	@PostMapping("/create")
	@ResponseStatus(HttpStatus.CREATED)
	public ProductOrderResponseDTO createOrder(@RequestBody @Valid ProductOrderDTO productOrderDTO) {
		return ProductOrderMapper.toProductOrderResponseDTO(this.productOrderService.createOrder(productOrderDTO));
	}

	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<ProductOrderResponseDTO> searchOrders(
			@RequestParam(required = false) String userName,
			@RequestParam(required = false) ProductType productType,
			@RequestParam(required = false) Integer timeHour
	) {
		return this.productOrderService.findAll(userName, productType, timeHour).stream()
				.map(ProductOrderMapper::toProductOrderResponseDTO).collect(Collectors.toList());
	}
}
