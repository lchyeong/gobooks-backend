package org.team.bookshop.domain.category.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.team.bookshop.domain.category.entity.CategoryPath;

public interface CategoryPathRepository extends JpaRepository<CategoryPath, Long> {

}
