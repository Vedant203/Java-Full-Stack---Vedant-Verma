package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bean.Product;
import com.repository.CategoryRepository;
import com.repository.ProductRepository;

@Service
public class ProductService {

  @Autowired
  ProductRepository productRepository;
  
  @Autowired
  CategoryRepository categoryRepository;

  public String addProduct(Product product){
    int result = productRepository.storeProduct(product);
    if(result==0)
      return "Product not stored";
    else
      return "Product stored successfully";
  }

  public List<Product> displayAllProducts(){
    return productRepository.displayAllProducts();
  }
  
  public String deleteProduct(int productId) {
    int result = productRepository.deleteProduct(productId);
    if(result == 0)
      return "Product not found";
    if(result == -1)
      return "Error occured!";
    else
      return "Product deleted successfully";
  }

  public Product searchProductById(int productId){
    return productRepository.searchProductById(productId);
  }

  public String updateProductCategory(int productId,String productCategory){
    com.bean.Category productCategoryy = categoryRepository.findCategoryByName(productCategory);
    int result = productRepository.updateProductCategory(productId, productCategoryy);
    if(result==0)
      return "Product category not updated";
    return "Product category updated successfully";
  }

  public List<Product> searchByCategory(String productCategory){
    return productRepository.searchByCategory(productCategory);
  }

}
