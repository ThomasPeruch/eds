package com.tperuch.edstest.dto;

import com.tperuch.edstest.entity.VehicleEntity;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

public class SearchVehicleCriteria implements Specification<VehicleEntity>{
    private String field;
    private String operator;
    private String value;

    @Override
    public Predicate toPredicate(Root<VehicleEntity> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        if(operator.equalsIgnoreCase(">")){
            return criteriaBuilder.greaterThan(root.<String> get(field), value);
        }
        if(operator.equalsIgnoreCase("<")){
            return criteriaBuilder.lessThan(root.<String> get(field), value);
        }
        if(operator.equalsIgnoreCase(">=")){
            return criteriaBuilder.greaterThanOrEqualTo(root.<String> get(field), value);
        }
        if(operator.equalsIgnoreCase("<=")){
            return criteriaBuilder.lessThanOrEqualTo(root.<String> get(field), value);
        }
        if(operator.equalsIgnoreCase("=")){
            if (root.get(field).getJavaType() == String.class){
                return criteriaBuilder.like(root.<String>get(field), "%" + value + "%");
            } else {
                return criteriaBuilder.equal(root.get(field), value);
            }
        }
        return null;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
