package com.salsatechnology.service;


import com.salsatechnology.dto.ProductOrderDTO;
import com.salsatechnology.service.pricing.PricingStrategyResolver;
import com.salsatechnology.service.pricing.ProductPricingStrategy;
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

	private static final BigDecimal ONE_HUNDRED = new BigDecimal(100);

	private final ProductOrderRepository productOrderRepository;

	private final PricingStrategyResolver pricingStrategyResolver;
	
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
		ProductPricingStrategy productPricing = this.pricingStrategyResolver.resolve(productType);
		BigDecimal priceHour = productPricing.getPriceHour();
		BigDecimal total = priceHour.multiply(new BigDecimal(timeHour));
		long productValue = priceHour.multiply(ONE_HUNDRED).longValue();
		long productTotal = total.multiply(ONE_HUNDRED).longValue();
		Long userAmount = total.multiply(productPricing.getEmployeePercentage())
						.divide(ONE_HUNDRED, 2, RoundingMode.HALF_UP)
						.multiply(ONE_HUNDRED).longValue();

		productOrder.setProductValue(productValue);
		productOrder.setProductTotal(productTotal);
		productOrder.setUserAmount(userAmount);
	}

	@Transactional(readOnly = true)
	public List<ProductOrder> findAll(String userName, ProductType productType, Integer timeHour) {
		return this.productOrderRepository.findAll(toSpecification(userName, productType, timeHour));
	}

}
