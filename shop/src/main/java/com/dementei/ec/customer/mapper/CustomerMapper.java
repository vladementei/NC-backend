package com.dementei.ec.customer.mapper;

import com.dementei.ec.customer.dto.CustomerDto;
import com.dementei.ec.customer.entity.Customer;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class CustomerMapper {
    private final ModelMapper mapper;

    @Autowired
    public CustomerMapper(ModelMapper mapper) {
        this.mapper = mapper;
    }

    public Customer toEntity(CustomerDto customerDto) {
        return Objects.isNull(customerDto) ? null : mapper.map(customerDto, Customer.class);
    }

    public CustomerDto toDto(Customer entity) {
        return Objects.isNull(entity) ? null : mapper.map(entity, CustomerDto.class);
    }
}
