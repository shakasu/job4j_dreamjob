package ru.job4j.dreamjob.store;

import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Repository;
import ru.job4j.dreamjob.model.Post;

import java.util.Collection;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
@ThreadSafe
public class PostStore {
    private final Map<Integer, Post> posts = new ConcurrentHashMap<>();

    private final AtomicInteger id = new AtomicInteger(4);

    private PostStore() {
        posts.put(1, new Post(1, "Junior Java Job", "Solving easy tasks", "12.03.2021"));
        posts.put(2, new Post(2, "Middle Java Job", "Solving medium tasks", "12.03.2021"));
        posts.put(3, new Post(3, "Senior Java Job", "Solving hard tasks", "12.03.2021"));
    }

    public Collection<Post> findAll() {
        return posts.values();
    }

    public Post add(Post post) {
        post.setId(id.getAndIncrement());
        return posts.put(post.getId(), post);
    }

    public Post findById(int id) {
        return Optional.ofNullable(posts.get(id)).orElseThrow(NoSuchElementException::new);
    }

    public Post update(Post post) {
        return posts.replace(post.getId(), post);
    }
}