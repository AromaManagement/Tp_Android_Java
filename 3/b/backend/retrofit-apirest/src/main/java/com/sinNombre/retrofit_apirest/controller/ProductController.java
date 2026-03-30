package com.sinNombre.retrofit_apirest.controller;


import com.sinNombre.retrofit_apirest.dto.ProductDTO;
import com.sinNombre.retrofit_apirest.model.Product;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/product")
public class ProductController {
    private List<Product> products = new ArrayList<>(Arrays.asList(
            new Product(1,"product1",100),
            new Product(2,"product2",200),
            new Product(3,"product3",300),
            new Product(4,"product4",400)
    ));

    @GetMapping
    public ResponseEntity<List<Product>> getAll(){
        return ResponseEntity.ok(products);
    }

    @GetMapping("/{id}")
    public  ResponseEntity<Product> getOne(@PathVariable("id") int id){
        Product product = findById(id);
        return  ResponseEntity.ok(product);
    }

    @PostMapping
    public  ResponseEntity<Product> create(@RequestBody ProductDTO dto){
        int index =products.isEmpty()? 1 : getLastIndex() + 1;
        Product product = Product.builder().id(index).name(dto.getName()).price(dto.getPrice()).build();
        products.add(product);
        return ResponseEntity.ok(product);
    }

    @PutMapping("/{id}")
    public  ResponseEntity<Product> update(@PathVariable("id") int id,@RequestBody ProductDTO dto){
        Product product = findById(id);
        product.setName(dto.getName());
        product.setPrice(dto.getPrice());
        return  ResponseEntity.ok(product);
    }

    @DeleteMapping("/{id}")
    public  ResponseEntity<Product> delete(@PathVariable("id") int id){
        Product product = findById(id);
        products.remove(product);
        return ResponseEntity.ok(product);
    }

    private  int getLastIndex(){
        return products.stream().max(Comparator.comparing(Product::getId)).get().getId();
    }

    private Product findById(int id){
        return  products.stream().filter(p -> p.getId() == id).findAny().orElse(null);
    }
}
