package com.javaseeds.javaseeds.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.javaseeds.javaseeds.dto.ProduitDTO;
import com.javaseeds.javaseeds.entity.Produit;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface ProduitService {
    Produit save(ProduitDTO produitDTO);
    Produit update(Long id, ProduitDTO produitDTO);
    Optional<Produit> findById(Long id);
    List<Produit> findAll();
    Page<Produit> findAll(Pageable pageable);
    void deleteById(Long id);
    List<Produit> searchByNom(String nom);
    List<Produit> findByPrixRange(BigDecimal min, BigDecimal max);
    boolean existsByNom(String nom);
    long count();

    //Récupérer liste produits par catégorie
    List<Produit> findByCategorie(String categorie);
}