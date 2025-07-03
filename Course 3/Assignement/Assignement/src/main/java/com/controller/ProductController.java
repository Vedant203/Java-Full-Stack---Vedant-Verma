package com.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bean.Category;
import com.bean.Product;
import com.service.CategoryService;
import com.service.ProductService;


@Controller
@RequestMapping(value = "product")
public class ProductController {

  @Autowired
  ProductService productService;

  @Autowired
  CategoryService categoryService;


  @GetMapping(value = "addProductPage")
  public String addProductPage(Model mm,Product product){
    mm.addAttribute("info", "Welcome to add product page!");
    mm.addAttribute("product", product);
    mm.addAttribute("categories", categoryService.displayAllCategories());
    return "addProductPage";
  }

  
@PostMapping(value = "addProduct")
public String addProduct(Model mm, @ModelAttribute Product product) {
    mm.addAttribute("info", "Welcome to add product page!");

    if (product.getProductCategory() == null || 
        product.getProductCategory().getCategoryName() == null) {
        mm.addAttribute("msg", "Enter a valid category!");
        mm.addAttribute("product", product);
        mm.addAttribute("categories", categoryService.displayAllCategories());
        return "addProductPage";
    }

    // Fetch the Category object from DB
    String categoryName = product.getProductCategory().getCategoryName();
    Category category = categoryService.searchCategoryByName(categoryName);
    if (category == null) {
        mm.addAttribute("msg", "Category '" + categoryName + "' does not exist.");
        mm.addAttribute("product", product);
        mm.addAttribute("categories", categoryService.displayAllCategories());
        return "addProductPage";
    }

    product.setProductCategory(category);
    String result = productService.addProduct(product);

    // Clear fields
    product.setProductName("");
    product.setProductPrice(0);
    product.setProductCategory(null);

    mm.addAttribute("msg", result);
    mm.addAttribute("product", product);
    mm.addAttribute("categories", categoryService.displayAllCategories());
    return "addProductPage";
}



  @GetMapping(value = "displayAllProductsPage")
  public String displayAllCustomersPage(Model mm){
    String result = "All Customer Info: ";
    mm.addAttribute("result",result);
    List<Product> products = productService.displayAllProducts();
    mm.addAttribute("products", products);
    return "displayAllProducts";
  }

  @GetMapping(value = "deleteProductPage")
  public String deleteProductPage(Model mm){
    mm.addAttribute("products", productService.displayAllProducts());
    return "deleteProductPage";
  }   

  @PostMapping(value = "deleteProduct")
  public String deleteProduct(Model mm,int productId){
    String result = productService.deleteProduct(productId);
    productId=0;
    mm.addAttribute("productId", productId);
    mm.addAttribute("result", result);
    mm.addAttribute("products", productService.displayAllProducts());
    return "deleteProductPage";
  }

  @GetMapping(value = "updateProductCategoryPage")
public String updateProductCategory(Model model) {
    model.addAttribute("products", productService.displayAllProducts());
    model.addAttribute("categories", categoryService.displayAllCategories());
    return "updateProductCategoryPage";
}



  @PostMapping("updateProductCategory")
public String updateProductCategoryPage(Model model,
                                        @RequestParam("productId") int productId,
                                        @RequestParam("newCategory") String newCategory) {
    String result = productService.updateProductCategory(productId, newCategory);
    model.addAttribute("result", result);
    model.addAttribute("products", productService.displayAllProducts());
    model.addAttribute("categories", categoryService.displayAllCategories());
    return "updateProductCategoryPage";
}

}
