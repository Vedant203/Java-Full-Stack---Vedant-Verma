package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bean.Category;
import com.repository.CategoryRepository;

@Service
public class CategoryService {

  @Autowired
  CategoryRepository categoryRepository;

  public String addCategory(Category category){
    int result = categoryRepository.storeCategory(category);
    if(result==0)
      return "Category not stored";
    else
      return "Category stored successfully";
  }

  public List<Category> displayAllCategories(){
    return categoryRepository.displayAllCategories();
  }

  public Category searchCategoryByName(String category){
    return categoryRepository.findCategoryByName(category);
  }

}
