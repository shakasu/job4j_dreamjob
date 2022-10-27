package ru.job4j.dreamjob.store;


import org.springframework.stereotype.Repository;
import ru.job4j.dreamjob.model.Candidate;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class CandidateStore {

    private static final CandidateStore INST = new CandidateStore();

    private final Map<Integer, Candidate> candidates = new ConcurrentHashMap<>();

    private AtomicInteger id = new AtomicInteger(4);
    private CandidateStore() {
        candidates.put(1, new Candidate(1, "Матвей Иванович", "Умный и недорогой", "12.03.2021"));
        candidates.put(2, new Candidate(2, "Данил Петрович", "Умный и быстрый", "12.03.2021"));
        candidates.put(3, new Candidate(3, "Василий Каренович","Быстрый и недорогой", "12.03.2021"));
    }

    public static CandidateStore instOf() {
        return INST;
    }

    public Collection<Candidate> findAll() {
        return candidates.values();
    }

    public Candidate add(Candidate candidate) {
        candidate.setId(id.getAndIncrement());
        return candidates.put(candidate.getId(), candidate);
    }

    public Candidate findById(int id) {
        return Optional.ofNullable(candidates.get(id)).orElseThrow(RuntimeException::new);

    }

    public Candidate update(Candidate candidate) {
        return candidates.replace(candidate.getId(), candidate);
    }
}