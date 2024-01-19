const container = $('#product-list');

$(document).ready(async function () {
  const products = await getProducts();
  render(products);
});

function render(products) {
  if (products && products.length > 0) {
    products.forEach(product => {
      const div = $('<div>').addClass('product');
      div.html(`
        <img src=${product.profilePhoto.data} alt='x'></img>
        <p>${product.name}</p>
        <p>Cena: ${product.price} PLN</p>
        <a class="edit" href="" onclick="handleEditProduct(${product.id})">Edytuj</a>
        <a class="remove" href="" onclick="handleRemoveProduct(${product.id})">Usuń</a>
      `);
      container.append(div);
    });
  } else {
    alert('Błąd podczas pobierania produktów');
  }
}

function handleEditProduct(id) {

}

async function handleRemoveProduct(id) {
  const userConfirmation = confirm('Czy na pewno chcesz usunąć ten produkt?');
  if (userConfirmation) {
    const status = await removeProduct(id);
    if (status == 200) {
      alert('Usunięto produkt');
      window.location.reload();
    } else {
      alert('Nie udało sie usunąć produktu. Status błędu: ' + status);
    }
  }
  
}