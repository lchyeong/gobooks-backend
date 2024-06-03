package org.team.bookshop.domain.category.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class BookCategory {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

//  @OneToOne
//  @JoinColumn(name = "book_id")
//  private Book book;

  @OneToOne
  @JoinColumn(name = "category_id")
  private Category category;
}
