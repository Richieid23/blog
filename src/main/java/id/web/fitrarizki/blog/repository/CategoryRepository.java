package id.web.fitrarizki.blog.repository;

import id.web.fitrarizki.blog.entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
    Page<Category> findByIsDeleted(Boolean isDeleted, Pageable pageable);
}
