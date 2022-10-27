package ru.job4j.dreamjob.service;

import net.jcip.annotations.ThreadSafe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.job4j.dreamjob.model.Post;
import ru.job4j.dreamjob.store.PostStore;

import java.util.Collection;
import java.util.concurrent.atomic.AtomicInteger;

@Service
@ThreadSafe
public class PostService {
    private final PostStore store;
    private AtomicInteger id = new AtomicInteger(4);

    @Autowired
    public PostService(PostStore store) {
        this.store = store;
    }

    public Collection<Post> findAll() {
        return store.findAll();
    }

    public Post add(Post post) {
        post.setId(id.getAndIncrement());
        store.add(post);
        return post;
    }

    public Post findById(int id) {
        return store.findById(id);
    }

    public Post update(Post post) {
        return store.update(post);
    }
}
