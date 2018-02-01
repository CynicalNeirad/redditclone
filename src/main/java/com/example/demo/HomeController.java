package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
public class HomeController {
    @Autowired
    PostRepository postrepository;

    @RequestMapping("/")
    public String listPosts(Model model){
        model.addAttribute("post", postrepository.findAll());
        return "list";
    }
    @GetMapping("/add")
    public String postrepositoryform(Model model) {
        model.addAttribute("post", new Post());
        return "postrepositoryform";
    }

    @PostMapping("/process")
    public String processForm(@Valid Post post, BindingResult result) {
        if (result.hasErrors()) {
            return "postrepositoryform";
        }
        postrepository.save(post);
        return "redirect:/";
    }
    @RequestMapping("/detail/{id}")
    public String showPost(@PathVariable("id") long id, Model model) {
        model.addAttribute("post", postrepository.findOne(id));
        return "show";
    }
    @RequestMapping("/update/{id}")
    public String updatePost(@PathVariable("id") long id, Model model){
        model.addAttribute("post", postrepository.findOne(id));
        return "postrepositoryform";
    }
    @RequestMapping("/delete/{id}")
    public String delPost(@PathVariable("id") long id){
        postrepository.delete(id);
        return "redirect:/";
    }

}
