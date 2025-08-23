package com.salsatechnology.repository;

import com.salsatechnology.model.ProductType;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.salsatechnology.model.ProductOrder;

import javax.persistence.criteria.Predicate;
import java.util.LinkedList;
import java.util.List;

@Repository
public interface ProductOrderRepository extends JpaRepository<ProductOrder, Long>, JpaSpecificationExecutor<ProductOrder> {

    static Specification<ProductOrder> toSpecification(String userName, ProductType productType, Integer timeHour) {
        return (root, query, builder) -> {
            List<Predicate> predicates = new LinkedList<>();
            if (userName != null) {
                predicates.add(builder.like(root.get("userName"), "%" +userName+ "%"));
            }
            if (productType != null) {
                predicates.add(builder.equal(root.get("productType"), productType));
            }
            if (timeHour != null) {
                predicates.add(builder.equal(root.get("timeHour"), timeHour));
            }
            return builder.and(predicates.toArray(new Predicate[predicates.size()]));
        };
    }

}
