package ru.job4j.dreamjob.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.job4j.dreamjob.model.Candidate;
import ru.job4j.dreamjob.model.Post;
import ru.job4j.dreamjob.store.CandidateStore;
import ru.job4j.dreamjob.store.PostStore;

import java.time.LocalDateTime;

@Controller
public class CandidateController {

    private final CandidateStore candidateStore = CandidateStore.instOf();

    @GetMapping("/candidates")
    public String candidates(Model model) {
        model.addAttribute("candidates", candidateStore.findAll());
        return "candidates";
    }

    @GetMapping("/formAddCandidate")
    public String addСandidate(Model model) {
        model.addAttribute("candidates", new Candidate(0, "Заполните название", "Заполните описание", LocalDateTime.now().toString()));
        return "addPost";
    }
}