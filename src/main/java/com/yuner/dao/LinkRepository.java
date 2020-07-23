package com.yuner.dao;

import com.yuner.Entity.Link;
import com.yuner.Entity.Type;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LinkRepository extends JpaRepository<Link,Long> {

    @Query("select t from Type t")
    List<Type> findTop(Pageable pageable);
}
