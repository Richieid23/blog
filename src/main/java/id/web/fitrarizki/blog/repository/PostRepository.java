package id.web.fitrarizki.blog.repository;

import id.web.fitrarizki.blog.entity.Category;
import id.web.fitrarizki.blog.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {
    Optional<Post> findBySlug(String slug);
    Optional<Post> findBySlugAndIsDeleted(String slug, boolean isDeleted);
    Optional<Post> findByIdAndIsDeleted(Integer id, boolean isDeleted);

    Long countByCategory(Category category);

    Page<Post> findByIsDeletedAndIsPublished(boolean isDeleted, boolean isPublished, Pageable pageable);
}
