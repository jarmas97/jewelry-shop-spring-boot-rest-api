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
      `);
      div.on('click', function() {
        redirect(product.id);
      });
      container.append(div);
    });
  } else {
    alert('Błąd podczas pobierania produktów');
  }
}

function redirect(id) {
  window.location.href = `pages/product.html?id=${id}`;
}