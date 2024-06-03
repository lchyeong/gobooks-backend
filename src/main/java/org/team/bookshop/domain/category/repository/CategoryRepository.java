package org.team.bookshop.domain.category.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.team.bookshop.domain.category.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}
