package com.yuner.Service.impl;

import com.yuner.Entity.Link;
import com.yuner.NotFoundException;
import com.yuner.Service.LinkService;
import com.yuner.dao.LinkRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LinkServiceImpl implements LinkService {

    @Autowired
    private LinkRepository linkRepository;

    @Transactional
    @Override
    public Link saveLink(Link link) {
        dealLink(link);
        return linkRepository.save(link);
    }

    @Transactional
    @Override
    public Link getLink(Long id) {
        return linkRepository.getOne(id);
    }

    @Transactional
    @Override
    public Page<Link> listLink(Pageable pageable) {
        return linkRepository.findAll(pageable);
    }

    @Transactional
    @Override
    public Link updateLink(Long id, Link link) {
        Link l = linkRepository.getOne(id);
        if(l == null){
            throw new NotFoundException("不存在该类型");
        }
        dealLink(link);
        BeanUtils.copyProperties(link,l);
        return linkRepository.save(l);
    }

    @Transactional
    @Override
    public void deleteLink(Long id) {
        linkRepository.delete(linkRepository.getOne(id));
    }

    private void dealLink(Link l) {
        String address = l.getLink_address();
        String github = l.getLink_github();
        if(address.length() >= 4 && address.substring(0,4) == "http") {

        } else {
            l.setLink_address("http://"+address);
        }

        if(github.length() >= 4 && github.substring(0,4) == "http") {

        } else {
            l.setLink_github("http://" + github);
        }
    }

    public static void main(String[] args) {
        LinkServiceImpl linkService = new LinkServiceImpl();
        Link l = new Link();
        l.setLink_github("www.baidu.com");
        l.setLink_address("www.hao123.com");
        linkService.dealLink(l);
        System.out.println(l.getLink_address());
        System.out.println(l.getLink_github());
    }
}
