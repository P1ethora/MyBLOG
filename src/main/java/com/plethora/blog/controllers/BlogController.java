package com.plethora.blog.controllers;

import com.plethora.blog.models.Post;
import com.plethora.blog.repo.PostRepository;
import org.apache.catalina.filters.ExpiresFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.xml.ws.http.HTTPException;
import java.util.ArrayList;
import java.util.Optional;

@Controller
public class BlogController {

    private final PostRepository postRepository;

    @Autowired
    public BlogController(PostRepository postRepository) {
        this.postRepository = postRepository;
    }


    @GetMapping("/blog")
    public String blogMain(Model model){
Iterable<Post> posts = postRepository.findAll();
model.addAttribute("posts", posts);
        return "blog-main";
    }

    @GetMapping("/blog/add")
    public String blogAdd(Model model){
        //Iterable<Post> posts = postRepository.findAll();
        //model.addAttribute("posts", posts);
        return "blog-add";
    }

    @PostMapping("/blog/add")
    public String blogPostAdd(@RequestParam String title, String anons, String fullText, Model model ){
        Post post = new Post(title,anons,fullText);
        postRepository.save(post);
return "redirect:/blog";
    }

    @GetMapping("/blog/{id}")
    public String blogDetails(@PathVariable( value = "id") long id, Model model){

        if(!postRepository.existsById(id)){
           return "redirect:/blog";
        }

        Optional<Post> post = postRepository.findById(id);
        ArrayList<Post> res = new ArrayList<>();
        post.ifPresent(res::add);
        model.addAttribute("post", res);
        return "blog-details";
    }

    @GetMapping("/blog/{id}/edit")
    public String blogEdit(@PathVariable( value = "id") long id, Model model){

        if(!postRepository.existsById(id)){
            return "redirect:/blog";
        }

       // Optional<Post> post = postRepository.findById(id);
       // ArrayList<Post> res = new ArrayList<>();
       // post.ifPresent(res::add);
       // model.addAttribute("post", res);
        return "blog-edit";
    }

}
