package com.javaseeds.javaseeds.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class ProduitDTO {
    
    @NotBlank(message = "Le catégorie est obligatoire")
    @Size(min = 2, max = 100, message = "Le catégorie doit contenir entre 2 et 100 caratères")
    private String categorie;

    @NotBlank(message = "Le nom est obligatoire")
    @Size(min = 2, max = 100, message = "Le nom doit contenir entre 2 et 100 caratères")
    private String nom;

    @NotNull(message = "Le prix est obligatoire")
    @Positive(message = "Le prix doit être positif")
    private BigDecimal prix;

    @NotNull(message = "La quantité est obligatoire")
    @Min(value = 0, message = "La quantité ne peut être négative")
    private Integer quantite;

    @Size(max = 500, message = "La description ne doit pas dépasser 500 caractères")
    private String description;

    // ✅ AJOUTEZ CE CHAMP
    private String imageUrl;

    public ProduitDTO() {
    }

    public ProduitDTO(
            @NotBlank(message = "Le catégorie est obligatoire") @Size(min = 2, max = 100, message = "Le catégorie doit contenir entre 2 et 100 caratères") String categorie,
            @NotBlank(message = "Le nom est obligatoire") @Size(min = 2, max = 100, message = "Le nom doit contenir entre 2 et 100 caratères") String nom,
            @NotNull(message = "Le prix est obligatoire") @Positive(message = "Le prix doit être positif") BigDecimal prix,
            @NotNull(message = "La quantité est obligatoire") @Min(value = 0, message = "La quantité ne peut être négative") Integer quantite,
            @Size(max = 500, message = "La description ne doit pas dépasser 500 caractères") String description,
            String imageUrl) {  // ✅ AJOUTEZ CE PARAMÈTRE
        this.categorie = categorie;
        this.nom = nom;
        this.prix = prix;
        this.quantite = quantite;
        this.description = description;
        this.imageUrl = imageUrl;  // ✅ AJOUTEZ CETTE LIGNE
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

    // ✅ AJOUTEZ CES GETTERS ET SETTERS
    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
