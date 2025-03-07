package id.web.fitrarizki.blog.repository;

import id.web.fitrarizki.blog.entity.Comment;
import id.web.fitrarizki.blog.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends CrudRepository<Comment, Integer> {
    List<Comment> findByPost(Post post);
    Page<Comment> findByPost(Post post, Pageable pageable);
}
