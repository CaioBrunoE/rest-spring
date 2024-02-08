package br.com.caiobruno.restspring.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;


import org.springframework.security.core.GrantedAuthority;

@Entity
@Table(name = "permission" )
public class Permission implements GrantedAuthority, Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String description;

    public Permission() {
    }

    @Override
    public String getAuthority() {

        return this.description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Permission that)) return false;
        return Objects.equals(getId(), that.getId()) && Objects.equals(getDescription(), that.getDescription());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getDescription());
    }
}
