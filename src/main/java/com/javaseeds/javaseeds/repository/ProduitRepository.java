package com.javaseeds.javaseeds.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.javaseeds.javaseeds.entity.Produit;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;


public interface ProduitRepository extends JpaRepository<Produit, Long> {
    
    // Méthodes dérivées
    List<Produit> findByNomContainingIgnoreCase(String nom);
    List<Produit> findByPrixBetween(BigDecimal prixMin, BigDecimal prixMax);
    List<Produit> findByQuantiteLessThan(Integer seuil);
    Optional<Produit> findByNom(String nom);
    boolean existsByNom(String nom);
    
    // Requêtes JPQL
    @Query("SELECT p FROM Produit p WHERE p.prix > :prixMin ORDER BY p.prix ASC")
    List<Produit> findProduitsPlusChersQue(@Param("prixMin") BigDecimal prixMin);
    
@Query("SELECT p FROM Produit p WHERE p.quantite = 0")
    List<Produit> findProduitsEnRupture();
    
    // Requête native
    @Query(value = "SELECT * FROM produits WHERE prix < :prixMax AND quantite > 0", nativeQuery = true)
    List<Produit> findProduitsDisponiblesPrixMax(@Param("prixMax") BigDecimal prixMax);
}