package org.team.bookshop.domain.category.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class CategoryPath {

  @EmbeddedId
  private CategoryPathId id;

  @ManyToOne(fetch = FetchType.EAGER)
  @MapsId("parent")
  private Category parent;

  @ManyToOne(fetch = FetchType.EAGER)
  @MapsId("children")
  private Category children;

  @Column(name = "depth", nullable = false)
  private int depth;

  public CategoryPath(CategoryPathId id) {
    this.id = id;
  }

  public CategoryPath(Category parent, Category children, int depth) {
    this.id = new CategoryPathId(parent, children);
    this.parent = parent;
    this.children = children;
    this.depth = depth;
  }
}
