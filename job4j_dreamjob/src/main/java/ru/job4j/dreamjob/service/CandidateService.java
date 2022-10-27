package ru.job4j.dreamjob.service;

import org.springframework.stereotype.Service;
import ru.job4j.dreamjob.model.Candidate;
import ru.job4j.dreamjob.store.CandidateStore;

import java.util.Collection;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class CandidateService {
    private final CandidateStore store = CandidateStore.instOf();
    private AtomicInteger id = new AtomicInteger(4);

    public Collection<Candidate> findAll() {
        return store.findAll();
    }

    public Candidate add(Candidate candidate) {
        candidate.setId(id.getAndIncrement());
        store.add(candidate);
        return candidate;
    }

    public Candidate findById(int id) {
        return store.findById(id);
    }

    public Candidate update(Candidate candidate) {
        return store.update(candidate);
    }
}
