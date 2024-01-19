const container = $('#product-details');
let currentImageIndex = 1;

$(document).ready(async function () {
  const productId = getProductIdFromURL();
  const product = await getProductById(productId);
  render(product);
});

function getProductIdFromURL() {
  const urlParams = new URLSearchParams(window.location.search);
  return urlParams.get('id');
}

function render(product) {
  function updateImage() {
    img.attr('alt', product.name);
    img.attr('src', product.photos[currentImageIndex].data);
  }
  const divImages = $('<div>').addClass('images').attr('id', 'images');
  const arrowLeft = $('<div>').addClass('arrow-left').text('←');
  arrowLeft.on('click', function () {
    currentImageIndex = currentImageIndex > 0 ? currentImageIndex - 1 : product.photos.length - 1;
    updateImage();
  });
  const img = $('<img>').attr({
    'src': product.profilePhoto.data,
    'alt': 'x'
  });
  const arrowRight = $('<div>').addClass('arrow-right').text('→');
  arrowRight.on('click', function () {
    currentImageIndex = currentImageIndex < product.photos.length - 1 ? currentImageIndex + 1 : 0;
    updateImage();
  });
  divImages.append(arrowLeft, img, arrowRight);
  container.append(divImages);
  container.append($('<h1>').text(product.name));
  const divDescription = $('<div>');
  product.description.split('\n').forEach((line, index) => {
    const element = line.startsWith('# ') ? $('<h3>') : $('<p>');
    if (line.startsWith('# ')) {
      const headerText = line.substring(2);
      element.text(headerText);
    } else {
      element.text(line);
    }
    divDescription.append(element);
  });

  container.append(divDescription);
  container.append($('<div>').html(`<b>Cena: </b>${product.price} PLN`));
  container.append($('<div>').html(`<b>Rozmiar: </b>${product.size} CM`));

  const pAvailableStock = $('<div>');
  if (product.availableStock < 0) {
    pAvailableStock.html(`<b>Status:</b> <a class="green-label">Dostępny</a>`);
  } else {
    pAvailableStock.html(`<b>Status:</b> <a class="orange-label">Na zamówienie</a>`);
  }
  container.append(pAvailableStock);

  container.append($('<h2>').text("Płatność"));
}
