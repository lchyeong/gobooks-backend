package org.team.bookshop.domain.user.entity;

import lombok.Getter;

@Getter
public enum UserRole {
  ADMIN("ROLE_ADMIN","관리자"),
  USER("ROLE_USER","유저");

  private String role;
  private String description;

  UserRole(String role, String description){
    this.role = role;
    this.description = description;
  }
}
