package com.example.demo.util;

import com.example.demo.model.Claim;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class HqlQueryHelper {

    @PersistenceContext
    private EntityManager entityManager;

    /**
     * Find claims where claimAmount > given amount
     */
    public List<Claim> findHighValueClaims(double amount) {
        return entityManager.createQuery(
                "SELECT c FROM Claim c WHERE c.claimAmount > :amount",
                Claim.class
        ).setParameter("amount", amount)
         .getResultList();
    }

    /**
     * Find claims whose description contains a keyword
     */
    public List<Claim> findClaimsByDescriptionKeyword(String keyword) {
        return entityManager.createQuery(
                "SELECT c FROM Claim c WHERE LOWER(c.description) LIKE LOWER(:keyword)",
                Claim.class
        ).setParameter("keyword", "%" + keyword + "%")
         .getResultList();
    }
}
