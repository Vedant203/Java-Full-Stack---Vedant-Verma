package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bean.Category;
import com.service.CategoryService;

@Controller
@RequestMapping(value = "category")
public class CategoryController {

  @Autowired
  CategoryService categoryService;

  @GetMapping(value = "addCategoryPage")
  public String addCategoryPage(Model mm){
    mm.addAttribute("category", new Category());
    mm.addAttribute("msg", "");
    mm.addAttribute("info", "Enter a new category page");
    return "addCategoryPage";
  }

  @PostMapping(value = "addCategory")
  public String addCategory(Model mm,Category category){
    mm.addAttribute("info", "Welcome to add category page");
    String result = categoryService.addCategory(category);
    mm.addAttribute("result", result);
    mm.addAttribute("category", new Category());
    return "addCategoryPage";
  }

}
