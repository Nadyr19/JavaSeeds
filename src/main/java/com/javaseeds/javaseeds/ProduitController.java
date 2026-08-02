package com.javaseeds.javaseeds;


import com.javaseeds.javaseeds.dto.ProduitDTO;
import com.javaseeds.javaseeds.entity.Produit;
import com.javaseeds.javaseeds.exception.ResourceNotFoundException;
import com.javaseeds.javaseeds.service.ProduitService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/produits")
public class ProduitController {
    
    private final ProduitService produitService;
    @Autowired
    public ProduitController(ProduitService produitService) {
        this.produitService = produitService;
    }
    
    @PostMapping
    public ResponseEntity<Produit> create(@Valid @RequestBody ProduitDTO produitDTO) {
        Produit savedProduit = produitService.save(produitDTO);
        return new ResponseEntity<>(savedProduit, HttpStatus.CREATED);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Produit> getById(@PathVariable Long id) {
        Produit produit = produitService.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Produit non trouvé avec l'id: " + id));
        return ResponseEntity.ok(produit);
    }
    
    @GetMapping
    public ResponseEntity<List<Produit>> getAll() {
        return ResponseEntity.ok(produitService.findAll());
    }
@GetMapping("/pagine")
    public ResponseEntity<Page<Produit>> getAllPagine(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id,asc") String[] sort) {
        
        Sort.Direction direction = Sort.Direction.fromString(sort[1]);
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sort[0]));
        return ResponseEntity.ok(produitService.findAll(pageable));
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Produit> update(@PathVariable Long id, @Valid @RequestBody ProduitDTO produitDTO) {
        Produit updatedProduit = produitService.update(id, produitDTO);
        return ResponseEntity.ok(updatedProduit);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        produitService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
  @GetMapping("/search")
    public ResponseEntity<List<Produit>> searchByNom(@RequestParam String nom) {
        return ResponseEntity.ok(produitService.searchByNom(nom));
    }
    
    @GetMapping("/prix")
    public ResponseEntity<List<Produit>> getByPrixRange(
            @RequestParam BigDecimal min,
            @RequestParam BigDecimal max) {
        return ResponseEntity.ok(produitService.findByPrixRange(min, max));
    }
    
    @GetMapping("/exists")
    public ResponseEntity<Boolean> existsByNom(@RequestParam String nom) {
        return ResponseEntity.ok(produitService.existsByNom(nom));
    }
    
    @GetMapping("/count")
    public ResponseEntity<Long> count() {
        return ResponseEntity.ok(produitService.count());
    }
}