 const reponse = await fetch("http://localhost:8081/api/produits");
 const produits = await reponse.json();

 console.log(produits);

for(let i = 0; i < produits.length; i++) {
    const produit = produits[i];

    const produitElement = document.querySelector(".grid-cards");

    const cardElement = document.createElement("div");
     
    const titreElement = document.createElement("h2");
    titreElement.innerText = produit.nom;

    const imgElement = document.createElement("img");
    // Use API image URL when available; fallback to a bundled image via API
    let imgSrc = produit.imageUrl || produit.image_url || '/api/images/coffee-seeds.jpeg';
    imgElement.src = imgSrc;
    imgElement.alt = produit.nom || 'Produit';

    const descriptionElement = document.createElement("p");
    descriptionElement.innerText = produit.description;

    const prixElement = document.createElement("p");
    prixElement.innerText = `${produit.prix} € ${produit.prix < 7 ? "(Prix en baisse)" : ""}`;

    cardElement.appendChild(titreElement);
    cardElement.appendChild(imgElement);
    cardElement.appendChild(descriptionElement);
    cardElement.appendChild(prixElement);

    produitElement.appendChild(cardElement);
}

console.log(produits); 



/* 
async function chargerProduits() {
    try {
        const reponse = await fetch('/api/produits');
        if (!reponse.ok) {
            throw new Error('Erreur HTTP : ' + reponse.status);
        }
        const produits = await reponse.json();
        
        const container = document.getElementById('produits-container');
        container.innerHTML = '';
        
        produits.forEach(produit => {
            // Gère les deux cas : "/api/images/..." et "/images/..."
            let imageUrl = produit.imageUrl;
            if (imageUrl && imageUrl.startsWith('/api/images/')) {
                imageUrl = imageUrl.replace('/api/images/', '/images/');
            }
            // Fallback si pas d'image
            imageUrl = imageUrl || '/images/default.jpg';
            
            const carte = document.createElement('div');
            carte.className = 'carte-produit';
            carte.innerHTML = `
                <img src="${imageUrl}" alt="${produit.nom}">
                <h3>${produit.nom}</h3>
                <p>${produit.description || ''}</p>
                <p>Prix : ${produit.prix} €</p>
            `;
            container.appendChild(carte);
        });
    } catch (erreur) {
        console.error('Erreur chargement produits :', erreur);
    }
}
 */






/* async function afficherProduits() {
  const reponse = await fetch("http://localhost:8081/api/produits");
  const produits = await reponse.json();

  console.log(produits); // ✅ Affichera les produits maintenant

  for (let i = 0; i < produits.length; i++) {
    const produit = produits[i];

    const produitElement = document.querySelector(".grid-cards");

    const titreElement = document.createElement("h2");
    titreElement.innerText = produit.nom;

    const imgElement = document.createElement("img");
    imgElement.src = produit.image_url;

    const descriptionElement = document.createElement("p");
    descriptionElement.innerText = produit.description;

    const prixElement = document.createElement("p");
    prixElement.innerText = `${produit.prix} € ${produit.prix < 7 ? "(Prix en baisse)" : ""}`;

    produitElement.appendChild(titreElement);
    produitElement.appendChild(imgElement);
    produitElement.appendChild(descriptionElement);
    produitElement.appendChild(prixElement);
  }
}

// Appelez la fonction
afficherProduits(); */