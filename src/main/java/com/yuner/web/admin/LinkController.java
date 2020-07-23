package com.yuner.web.admin;

import com.yuner.Entity.Link;
import com.yuner.Service.LinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/admin")
public class LinkController {

    @Autowired
    private LinkService linkService;

    @GetMapping("/links")
    public String links(@PageableDefault(size = 10,sort = {"id"},direction = Sort.Direction.DESC) Pageable pageable,
                        Model model) {
        model.addAttribute("page",linkService.listLink(pageable));
        return "admin/links";
    }

    @GetMapping("/links/input")
    public String input(Model model)
    {
        model.addAttribute("link", new Link());
        return "admin/links-input";
    }

    @GetMapping("/links/{id}/input")
    public String editInput(@PathVariable Long id, Model model) {
        model.addAttribute("link",linkService.getLink(id));
        return "admin/links-input";
    }

    @PostMapping("/links")
    public String post(Link link, RedirectAttributes attributes) {
        Link l = linkService.saveLink(link);
        if (l == null) {
            attributes.addFlashAttribute("message", "新增失败");
        } else {
            attributes.addFlashAttribute("message","新增成功");
        }
        return "redirect:/admin/links";
    }

    @PostMapping("/links/{id}")
    public String editPost(Link link, @PathVariable Long id,RedirectAttributes attributes) {
        Link l = linkService.updateLink(id, link);
        if( l == null ) {
            attributes.addFlashAttribute("更新失败");
        } else {
            attributes.addFlashAttribute("更新成功");
        }
        return "redirect:/admin/links";
    }

    @GetMapping("/links/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes attributes) {
        linkService.deleteLink(id);
        attributes.addFlashAttribute("message","删除成功");
        return "redirect:/admin/links";
    }
}
