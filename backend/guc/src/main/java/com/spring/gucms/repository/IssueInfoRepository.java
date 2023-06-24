package com.spring.gucms.repository;

import com.spring.gucms.entity.IssueInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IssueInfoRepository extends JpaRepository<IssueInfo,Long> {
}
