package ru.job4j.dreamjob.store;


import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Repository;
import ru.job4j.dreamjob.model.Candidate;
import ru.job4j.dreamjob.model.City;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Collection;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
@ThreadSafe
public class CandidateStore {
    private final Map<Integer, Candidate> candidates = new ConcurrentHashMap<>();

    private final AtomicInteger id = new AtomicInteger(4);

    private CandidateStore() throws IOException {
        var msk = new City(2, "Мск");
        byte[] photo1 = Files.readAllBytes(new File("job4j_dreamjob/src/main/resources/images/1.webp").toPath());
        byte[] photo2 = Files.readAllBytes(new File("job4j_dreamjob/src/main/resources/images/2.webp").toPath());
        byte[] photo3 = Files.readAllBytes(new File("job4j_dreamjob/src/main/resources/images/sticker.webp").toPath());

        candidates.put(1, new Candidate(1, "Матвей Иванович", "Умный и недорогой", "12.03.2021", msk, photo1));
        candidates.put(2, new Candidate(2, "Данил Петрович", "Умный и быстрый", "12.03.2021", msk, photo2));
        candidates.put(3, new Candidate(3, "Василий Каренович","Быстрый и недорогой", "12.03.2021", msk, photo3));
    }

    public Collection<Candidate> findAll() {
        return candidates.values();
    }

    public Candidate add(Candidate candidate) {
        candidate.setId(id.getAndIncrement());
        return candidates.put(candidate.getId(), candidate);
    }

    public Candidate findById(int id) {
        return Optional.ofNullable(candidates.get(id)).orElseThrow(NoSuchElementException::new);

    }

    public Candidate update(Candidate candidate) {
        return candidates.replace(candidate.getId(), candidate);
    }
}