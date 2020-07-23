package com.yuner.Service;

import com.yuner.Entity.Link;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface LinkService {

    Link saveLink(Link link);

    Link getLink(Long id);

    Page<Link> listLink(Pageable pageable);

    Link updateLink(Long id, Link link);

    void deleteLink(Long id);

}
