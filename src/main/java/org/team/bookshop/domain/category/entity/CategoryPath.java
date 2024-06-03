package org.team.bookshop.domain.category.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class CategoryPath {

  @EmbeddedId
  private CategoryPathId id;

  @Column(name = "depth")
  private Long depth;
}
