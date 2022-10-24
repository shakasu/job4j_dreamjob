package ru.job4j.dreamjob.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.job4j.dreamjob.store.CandidateStore;
import ru.job4j.dreamjob.store.PostStore;

@Controller
public class CandidateController {

    private final CandidateStore candidateStore = CandidateStore.instOf();

    @GetMapping("/candidate")
    public String posts(Model model) {
        model.addAttribute("candidates", candidateStore.findAll());
        return "candidates";
    }
}