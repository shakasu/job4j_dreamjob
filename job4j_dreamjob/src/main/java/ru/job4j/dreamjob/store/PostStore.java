package ru.job4j.dreamjob.store;

import ru.job4j.dreamjob.model.Post;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class PostStore {

    private static final PostStore INST = new PostStore();

    private final Map<Integer, Post> posts = new ConcurrentHashMap<>();

    private int id;

    private PostStore() {
        posts.put(1, new Post(1, "Junior Java Job", "Solving easy tasks", "12.03.2021"));
        posts.put(2, new Post(2, "Middle Java Job", "Solving medium tasks", "12.03.2021"));
        posts.put(3, new Post(3, "Senior Java Job","Solving hard tasks", "12.03.2021"));
    }

    public static PostStore instOf() {
        return INST;
    }

    public Collection<Post> findAll() {
        return posts.values();
    }

    public Post add(Post post) {
        return posts.put(post.getId(), post);
    }
}