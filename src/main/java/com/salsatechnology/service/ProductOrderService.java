package com.salsatechnology.service;


import com.salsatechnology.dto.ProductOrderDTO;
import com.salsatechnology.enums.ProductPricing;
import com.salsatechnology.factory.ProductPricingFactory;
import com.salsatechnology.model.ProductOrder;
import com.salsatechnology.model.ProductType;
import com.salsatechnology.repository.ProductOrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import static com.salsatechnology.repository.ProductOrderRepository.toSpecification;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ProductOrderService {

	private final ProductOrderRepository productOrderRepository;
	
	@Transactional
	public ProductOrder createOrder(ProductOrderDTO productOrderDTO) {
		return this.productOrderRepository.save(createProductOrder(productOrderDTO));
	}

	private ProductOrder createProductOrder(ProductOrderDTO productOrderDTO) {
		if (productOrderDTO.getTimeHour() <= 0) throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
				"timeHour deve ser inteiro positivo");
		ProductOrder productOrder = new ProductOrder();
		productOrder.setUserName(productOrderDTO.getUserName());
		productOrder.setProductType(productOrderDTO.getProductType());
		productOrder.setTimeHour(productOrderDTO.getTimeHour());
		setFinancialValues(productOrder, productOrderDTO.getProductType(), productOrderDTO.getTimeHour());
		return productOrder;
	}

	private void setFinancialValues(ProductOrder productOrder, ProductType productType, int timeHour) {
		ProductPricing productPricing = ProductPricingFactory.of(productType);
		long productValue = Math.round(productPricing.getPriceHour() * 100);
		long productTotal = productValue * timeHour;
		Long userAmount = BigDecimal.valueOf(productTotal)
				.multiply(BigDecimal.valueOf(productPricing.getEmployeePercentage()))
				.divide(BigDecimal.valueOf(100), 0, RoundingMode.HALF_UP)
				.longValue();

		productOrder.setProductValue(productValue);
		productOrder.setProductTotal(productTotal);
		productOrder.setUserAmount(userAmount);
	}

	@Transactional(readOnly = true)
	public List<ProductOrder> findAll(String userName, ProductType productType, Integer timeHour) {
		return this.productOrderRepository.findAll(toSpecification(userName, productType, timeHour));
	}

}
