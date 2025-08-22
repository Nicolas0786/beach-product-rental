package com.salsatechnology.service;


import com.salsatechnology.dto.ProductOrderDTO;
import com.salsatechnology.enums.ProductPricing;
import com.salsatechnology.factory.ProductPricingFactory;
import com.salsatechnology.model.ProductOrder;
import com.salsatechnology.repository.ProductOrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ProductOrderService {

	private final ProductOrderRepository productOrderRepository;
	
	@Transactional
	public void createOrder(ProductOrderDTO productOrderDTO) {
		productOrderRepository.save(createProductOrder(productOrderDTO));
	}

	private ProductOrder createProductOrder(ProductOrderDTO productOrderDTO) {
		ProductOrder productOrder = new ProductOrder();
		productOrder.setUserName(productOrderDTO.getUserName());
		productOrder.setProductType(productOrderDTO.getProductType());
		productOrder.setTimeHour(productOrderDTO.getTimeHour());
		
		productOrder.setProductValue(null);
		productOrder.setProductTotal(null);
		productOrder.setUserAmount(null);
		return productOrder;
	}

	private Long calculaProductValue(ProductOrderDTO productOrderDTO) {
		ProductPricing productPricing = ProductPricingFactory.of(productOrderDTO.getProductType());
		double pricing = productPricing.getPriceHour();
		double porcentage = productPricing.getEmployeePercentage();

		double precingHour = pricing * productOrderDTO.getTimeHour();
		double precoCOmPorcentagem = precingHour * (productPricing.getEmployeePercentage() /100);



		return 1l;
	}

	@Transactional(readOnly = true)
	public List<ProductOrder> findAll() {
		return this.productOrderRepository.findAll();
	}



}
