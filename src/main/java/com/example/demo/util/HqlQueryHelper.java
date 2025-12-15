package com.example.demo.util;

import java.util.List;

import org.springframework.stereotype.Component;

import com.example.demo.model.Claim;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Component
public class HqlQueryHelper {
    @PersistenceContext
    private EntityManager entityManager;
    public List<Claim>findClaimsByDescripionKeyword(String Keyword){
        return entityManager
        .createQuery("FROM Claim c WHERE LOWER(c.description) LIKE LOWER(:kw)", Claim.class)
        .setParameter("kw","%" + Keyword + "%").getResultList();
    }
    public List<Claim> findHighValueclaims(Double minAmount){
        return entityManager.createQuery("FROM Claim c WHERE c.claimAmount > :amt", Claim.class).setParameter("amt", minAmount).getResultList();
    }
    
}
