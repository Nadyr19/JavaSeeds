package com.javaseeds.javaseeds.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Min;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Column;

@Entity
@Table(name = "produits")
public class Produit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Le catégorie est obligatoire")
    @Size(min = 2, max = 100, message = "Le catégorie doit contenir entre 2 et 100 caratères")
    @Column(nullable = false, length = 100)
    private String categorie;

    @NotBlank(message = "Le nom est obligatoire")
    @Size(min = 2, max = 100, message = "Le nom doit contenir entre 2 et 100 caratères")
    @Column(nullable = false, length = 100)
    private String nom;

    @NotNull(message = "Le prix est obligatoire")
    @Positive(message = "Le prix doit être positif")
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal prix;

    @NotNull(message = "La quantité est obligatoire")
    @Min(value = 0, message = "La quantité ne peut être négative")
    @Column(nullable = false)
    private Integer quantite;

    @Size(max = 500, message = "La description ne doit pas dépasser 500 caractères")
    @Column(nullable = false, length = 100)
    private String description;

    @Column(name = "image_url")
    private String imageUrl;  

    @Column(name = "date_creation", nullable = false, updatable = false)
    private LocalDateTime dateCreation;
    
    @Column(name = "date_modification")
    private LocalDateTime dateModification;

    public Produit() {
    }

    public Produit(
            @NotBlank(message = "Le catégorie est obligatoire") @Size(min = 2, max = 100, message = "Le catégorie doit contenir entre 2 et 100 caratères") String categorie,
            @NotBlank(message = "Le nom est obligatoire") @Size(min = 2, max = 100, message = "Le nom doit contenir entre 2 et 100 caratères") String nom,
            @NotNull(message = "Le prix est obligatoire") @Positive(message = "Le prix doit être positif") BigDecimal prix,
            @NotNull(message = "La quantité est obligatoire") @Min(value = 0, message = "La quantité ne peut être négative") Integer quantite,
            @Size(max = 500, message = "La description ne doit pas dépasser 500 caractères") String description, 
            String imageUrl) {
        this.categorie = categorie;
        this.nom = nom;
        this.prix = prix;
        this.quantite = quantite;
        this.description = description;
        this.imageUrl =  imageUrl;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public BigDecimal getPrix() {
        return prix;
    }

    public void setPrix(BigDecimal prix) {
        this.prix = prix;
    }

    public Integer getQuantite() {
        return quantite;
    }

    public void setQuantite(Integer quantite) {
        this.quantite = quantite;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public LocalDateTime getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(LocalDateTime dateCreation) {
        this.dateCreation = dateCreation;
    }

    public LocalDateTime getDateModification() {
        return dateModification;
    }

    public void setDateModification(LocalDateTime dateModification) {
        this.dateModification = dateModification;
    }
    
     // Callbacks JPA
    @PrePersist
    protected void onCreate() {
        dateCreation = LocalDateTime.now();
        dateModification = LocalDateTime.now();
    }
    
    @PreUpdate
    protected void onUpdate() {
        dateModification = LocalDateTime.now();
    }
/* 
    public String getImageUrl() {
        return imageUrl;
    } */

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    // Méthode utilitaire pour obtenir l'URL complète de l'image
    // Modifier data.sql avec les noms de fichiers uniquement
    public String getImageUrl() {
        if (imageUrl == null) {
            return null;
        }
        // Return absolute URLs unchanged
        if (imageUrl.startsWith("http")) {
            return imageUrl;
        }
        // Strip any leading API/static prefixes to avoid duplicates
        String cleaned = imageUrl;
        if (cleaned.startsWith("/api/images/")) {
            cleaned = cleaned.substring("/api/images/".length());
        }
        if (cleaned.startsWith("/images/")) {
            cleaned = cleaned.substring("/images/".length());
        }
        // Ensure a single API image prefix
        return "/api/images/" + cleaned;
    }
    
}
