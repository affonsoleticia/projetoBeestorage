package com.beestorageproject.techafropretas.validator;

import java.util.List;

import org.hibernate.query.Query;

import jakarta.persistence.EntityManager;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ExisteProdutoValidator implements ConstraintValidator<ExisteProduto, Long> {

    private final  EntityManager manager;

    public ExisteProdutoValidator(EntityManager manager) {
        this.manager = manager;
    }

    public void initialize(ExisteProduto constraint) {
    }

    public boolean isValid(Long idProduto, ConstraintValidatorContext context) {
        String jpql = "select r from ProdutoModel r where r.id=:id";
        Query query = (Query) manager.createQuery(jpql);
        query.setParameter("id", idProduto);
        List<?> result=query.getResultList();
        return !result.isEmpty();
    }
}