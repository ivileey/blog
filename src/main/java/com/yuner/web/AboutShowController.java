package com.yuner.web;

import com.yuner.Entity.Link;
import com.yuner.Service.LinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class AboutShowController {

    @Autowired
    private LinkService linkService;

    @GetMapping("/")
    public String about(@PageableDefault(size = 10,sort = {"createTime"},direction = Sort.Direction.ASC)Pageable pageable,
                        Model model) {
        model.addAttribute("page",linkService.listLink(pageable));
        return "index";
    }
}
