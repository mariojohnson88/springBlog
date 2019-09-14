package com.codeup.springblog.daos;

import com.codeup.springblog.models.Post;
import com.codeup.springblog.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PostRepository extends CrudRepository<Post, Long> {
    Iterable<Post>findByOwner(User user);
}
