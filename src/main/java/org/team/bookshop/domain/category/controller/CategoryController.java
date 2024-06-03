package org.team.bookshop.domain.category.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.team.bookshop.domain.category.service.CategoryService;

@RestController
@RequestMapping("/api")
public class CategoryController {

  private final CategoryService categoryService;

  public CategoryController(CategoryService categoryService) {
    this.categoryService = categoryService;
  }

  // 전체 사용자 ========

  // 카테고리 목록 조회

  // 관리자 ============

  // 카테고리 추가

  // 카테고리 수정

  // 카테고리 삭제
}