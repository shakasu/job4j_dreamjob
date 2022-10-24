package ru.job4j.dreamjob.store;


import ru.job4j.dreamjob.model.Candidate;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CandidateStore {

    private static final CandidateStore INST = new CandidateStore();

    private final Map<Integer, Candidate> candidates = new ConcurrentHashMap<>();

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
}