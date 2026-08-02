package com.javaseeds.javaseeds.service;



import com.javaseeds.javaseeds.dto.ProduitDTO;
import com.javaseeds.javaseeds.entity.Produit;
import com.javaseeds.javaseeds.exception.ResourceAlreadyExistsException;
import com.javaseeds.javaseeds.exception.ResourceNotFoundException;
import com.javaseeds.javaseeds.repository.ProduitRepository;
import com.javaseeds.javaseeds.service.ProduitService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;



@Service
@Transactional
public class ProduitServiceImpl implements ProduitService {
    
    private static final Logger logger = LoggerFactory.getLogger(ProduitServiceImpl.class);
    
    private final ProduitRepository produitRepository;
 
    @Autowired
    public ProduitServiceImpl(ProduitRepository produitRepository) {
        this.produitRepository = produitRepository;
    }
    
    @Override
    public Produit save(ProduitDTO produitDTO) {
        logger.info("Sauvegarde d'un nouveau produit: {}", produitDTO.getNom());
        
        // Vérification si le produit existe déjà
        if (produitRepository.existsByNom(produitDTO.getNom())) {
            throw new ResourceAlreadyExistsException("Un produit avec le nom '" + produitDTO.getNom() + "' existe déjà");
        }
        
        Produit produit = new Produit();
        produit.setNom(produitDTO.getNom());
        produit.setDescription(produitDTO.getDescription());
        produit.setPrix(produitDTO.getPrix());
        produit.setQuantite(produitDTO.getQuantite());
        
        return produitRepository.save(produit);
    }
@Override
    public Produit update(Long id, ProduitDTO produitDTO) {
        logger.info("Mise à jour du produit avec l'id: {}", id);
        
        Produit produit = produitRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Produit non trouvé avec l'id: " + id));
        
        // Vérification si un autre produit existe avec le même nom
        Produit existingProduit = produitRepository.findByNom(produitDTO.getNom()).orElse(null);
        if (existingProduit != null && !existingProduit.getId().equals(id)) {
            throw new ResourceAlreadyExistsException("Un produit avec le nom '" + produitDTO.getNom() + "' existe déjà");
        }
        
        produit.setNom(produitDTO.getNom());
        produit.setDescription(produitDTO.getDescription());
        produit.setPrix(produitDTO.getPrix());
        produit.setQuantite(produitDTO.getQuantite());
        
        return produitRepository.save(produit);
    }
    
@Override
    @Transactional(readOnly = true)
    public Optional<Produit> findById(Long id) {
        logger.debug("Recherche du produit avec l'id: {}", id);
        return produitRepository.findById(id);
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<Produit> findAll() {
        logger.debug("Récupération de tous les produits");
        return produitRepository.findAll();
    }
    
    @Override
    @Transactional(readOnly = true)
    public Page<Produit> findAll(Pageable pageable) {
        logger.debug("Récupération paginée des produits");
        return produitRepository.findAll(pageable);
    }
    
    @Override
  public void deleteById(Long id) {
        logger.info("Suppression du produit avec l'id: {}", id);
        if (!produitRepository.existsById(id)) {
            throw new ResourceNotFoundException("Produit non trouvé avec l'id: " + id);
        }
        produitRepository.deleteById(id);
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<Produit> searchByNom(String nom) {
        logger.debug("Recherche de produits par nom: {}", nom);
        return produitRepository.findByNomContainingIgnoreCase(nom);
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<Produit> findByPrixRange(BigDecimal min, BigDecimal max) {
        logger.debug("Recherche de produits par plage de prix: {} - {}", min, max);
        return produitRepository.findByPrixBetween(min, max);
    }
@Override
    @Transactional(readOnly = true)
    public boolean existsByNom(String nom) {
        return produitRepository.existsByNom(nom);
    }
    
    @Override
    @Transactional(readOnly = true)
    public long count() {
        return produitRepository.count();
    }
}