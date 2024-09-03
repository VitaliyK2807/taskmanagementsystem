package com.example.taskmanagementsystem.repositories.specification;

import com.example.taskmanagementsystem.model.Tasks;
import com.example.taskmanagementsystem.web.model.TaskFilterAuthor;
import org.springframework.data.jpa.domain.Specification;

public interface TaskSpecification {

    static Specification<Tasks> withFilter(TaskFilterAuthor filter) {
        return Specification.where(byAuthorId(filter.getAuthorId()))
                .and(byPriority(filter.getPriority()))
                .and(byStatus(filter.getStatus()));
    }

    static Specification<Tasks> byAuthorId(Long authorId) {
        return ((root, query, criteriaBuilder) -> {
            if (authorId == null) {
                return null;
            }

            return criteriaBuilder.equal(root.get("author"), authorId);
        });
    }

    static Specification<Tasks> byPriority(String priority) {
        return ((root, query, criteriaBuilder) -> {
            if (priority == null) {
                return null;
            }

            return criteriaBuilder.equal(root.get("priority"), priority);
        });
    }

    static Specification<Tasks> byStatus(String status) {
        return ((root, query, criteriaBuilder) -> {
            if (status == null) {
                return null;
            }

            return criteriaBuilder.equal(root.get("status"), status);
        });
    }
}
