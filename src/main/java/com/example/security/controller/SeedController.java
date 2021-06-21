package com.example.security.controller;

import com.example.security.entity.Account;
import com.example.security.entity.Product;
import com.example.security.repository.AccountRepository;
import com.example.security.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping(value = "/seed/generate")
public class SeedController {
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @RequestMapping(method = RequestMethod.GET)
    public String seed(){
        List<Account> accounts = Arrays.asList(
                new Account(1,"truongph","truong",passwordEncoder.encode("123456"), 1, 1, (Collections.emptySet())),
                new Account(2,"duc","duc",passwordEncoder.encode("123456"),1, 1, (Collections.emptySet()))
        );
        accountRepository.saveAll(accounts);
        Product product1 = new Product(1,"Iphone 5", 10000000.0);
        Product product2 = new Product(2,"Iphone 6", 20000000.0);
        Product product3 = new Product(3,"Iphone 7", 30000.0);
        Product product4 = new Product(4,"Iphone 8", 40000000.0);
        Product product5 = new Product(5,"Iphone X", 50000000.0);
        Product product6 = new Product(6,"Oppo", 2000000.0);
        Product product7 = new Product(7,"Ghế gaming", 4000000.0);
        Product product8 = new Product(8,"Chuột steelseries", 2000000.0);
        Product product9 = new Product(9,"Tai nghe edra", 70000000.0);
        Product product10 = new Product(10,"Màn hình Sony", 1000000.0);
        List<Product> products = Arrays.asList(product1, product2, product3, product4,product5, product6, product7, product8, product9, product10);

        productRepository.saveAll(products);
        return  "done";
    }
}
