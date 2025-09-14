package com.emtech.logistics.service.impl;

import com.emtech.logistics.dto.Customer;
import com.emtech.logistics.entity.CustomerEntity;
import com.emtech.logistics.repository.CustomerRepository;
import com.emtech.logistics.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Customer getCustomerById(Long id) {
        return customerRepository.findById(id).map(this::toDTO).orElse(null);
    }

    @Override
    public Customer addCustomer(Customer customer) {
        CustomerEntity entity = toEntity(customer);
        CustomerEntity saved = customerRepository.save(entity);
        return toDTO(saved);
    }

    @Override
    public Customer updateCustomer(Long id, Customer customer) {
        CustomerEntity entity = customerRepository.findById(id).orElseThrow();
        entity.setName(customer.getName());
        entity.setBusinessType(customer.getBusinessType());
        entity.setPhone(customer.getPhone());
        entity.setTotalOrders(customer.getTotalOrders());
        entity.setLastOrderDate(customer.getLastOrderDate());
        return toDTO(customerRepository.save(entity));
    }

    @Override
    public boolean deleteCustomer(Long id) {
        if (!customerRepository.existsById(id)) return false;
        customerRepository.deleteById(id);
        return true;
    }

    private Customer toDTO(CustomerEntity entity) {
        return new Customer(
                entity.getId(),
                entity.getName(),
                entity.getBusinessType(),
                entity.getPhone(),
                entity.getTotalOrders(),
                entity.getLastOrderDate()
        );
    }

    private CustomerEntity toEntity(Customer dto) {
        CustomerEntity entity = new CustomerEntity();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setBusinessType(dto.getBusinessType());
        entity.setPhone(dto.getPhone());
        entity.setTotalOrders(dto.getTotalOrders());
        entity.setLastOrderDate(dto.getLastOrderDate());
        return entity;
    }
}
