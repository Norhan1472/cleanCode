package com.spring.gucms.repository;

import com.spring.gucms.dto.UtilsDTO;
import com.spring.gucms.entity.Utils;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UtilsRepository extends JpaRepository<Utils, Long> {
    @Query("From Utils u where u.auditTopic.topic_id = :topicId")
    public List<Utils> getByTopicId(@Param("topicId") long topicId);

    @Query("From Utils u where u.auditTopic.topic_id = :topicId and u.auditTopicRoleInfo.info_ID= :info_ID")
    public Utils getByTopicIdAndAuditId(@Param("topicId") long topicId,@Param("info_ID") long info_ID);
}