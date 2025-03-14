CREATE TABLE IF NOT EXISTS post (
    id INT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(255) NOT NULL,
    body TEXT NOT NULL,
    slug VARCHAR(255) NOT NULL,
    is_published BOOLEAN DEFAULT FALSE,
    is_deleted BOOLEAN DEFAULT FALSE,
    updated_at BIGINT,
    created_at BIGINT NOT NULL,
    published_at BIGINT
) ENGINE=InnoDB DEFAULT CHARSET=UTF8;

ALTER TABLE post
ADD UNIQUE post_slug_unique (slug);