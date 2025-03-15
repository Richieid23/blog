package id.web.fitrarizki.blog.repository;

import id.web.fitrarizki.blog.entity.Media;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MediaRepository extends JpaRepository<Media, Integer> {
}
