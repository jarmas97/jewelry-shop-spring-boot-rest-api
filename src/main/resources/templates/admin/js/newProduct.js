const container = $('#product-add-form');
const divImagePreview = $('<div>').addClass('image-preview').attr('id', 'image-preview');
const divAddPhoto = $('<div>').addClass('add-photo');
const divMaterials = $('<div>').attr('id', 'materials');
const photos = [];
let profilePhotoIndex = null;
let availebleMaterials = [];
let selectedMaterialIDs = [];
const nameError = $('<p>').addClass('error');
const descriptionError = $('<p>').addClass('error');
const materialsError = $('<p>').addClass('error');
const categoryError = $('<p>').addClass('error');
const priceError = $('<p>').addClass('error');
const sizeError = $('<p>').addClass('error');
const availabilityError = $('<p>').addClass('error');
const photosError = $('<p>').addClass('error');

$(document).ready(async function () {
  availebleMaterials = await getMaterials();
  loadForm();
});

async function loadForm() {

  container.append($('<h2>').text('Nowy produkt'));
  //-------------------------------------------------------------------NAME----------------------------------
  container.append($('<p>').text('Nazwa'));
  const name = $('<input>').attr('type', 'text');
  container.append(name);
  container.append(nameError);
  //----------------------------------------------------------------DESCRIPTION---------------------------------
  container.append($('<p>').text('Opis'));
  const description = $('<textarea>').attr('type', 'text').addClass('description-field');
  container.append(description);
  container.append(descriptionError);
  //-----------------------------------------------------------------MATERIALS---------------------------------
  container.append($('<p>').text('Materiały'));
  showMaterials();
  container.append(divMaterials);
  container.append(materialsError);
  //-----------------------------------------------------------------CATEGORY---------------------------------
  container.append($('<p>').text('Kategoria'));
  const selectionCategory = $('<select>').html(`
    <option value=null>Wybierz kategorię</option>
    <option value='RING'>Pierścionki</option>
    <option value='NECKLACE'>Naszyjniki</option>
    <option value='EARRINGS'>Kolczyki</option>
    <option value='BRACELET'>Bransoletki</option>
  `);
  container.append(selectionCategory);
  container.append(categoryError);
  //-------------------------------------------------------------------PRICE-------------------------------
  container.append($('<p>').text('Cena'));
  const price = $('<input>').attr('type', 'text');
  container.append(price);
  container.append(priceError);
  //--------------------------------------------------------------------SIZE--------------------------------
  container.append($('<p>').text('Rozmiar'));
  const size = $('<input>').attr('type', 'text');
  container.append(size);
  container.append(sizeError);
  //-----------------------------------------------------------------AVAILABILITY-------------------------------
  container.append($('<p>').text('Dostępność'));
  const availability = $('<input>').attr('type', 'text');
  container.append(availability);
  container.append(availabilityError);
  //--------------------------------------------------------------------PHOTOS--------------------------------
  container.append($('<p>').text('Zdjęcia'));
  showPhotos();
  divImagePreview.append(photosError);
  container.append(divImagePreview);
  //--------------------------------------------------------------------SUBMIT--------------------------------
  const submitButton = $("<button>").text("Dodaj produkt").on("click", async function() {
    let result = true;

    console.log(selectionCategory.val());

    if (!name.val() || !/^[a-zA-Z]+$/.test(name.val())) {
      nameError.text("Wprowadź poprawną nazwę produktu");
      result = false;
    }
    if (!description.val() || !/^[a-zA-Z#]+$/.test(description.val())) {
      descriptionError.text("Wprowadź poprawny opis produktu (tylko litery i znak #).");
      result = false;
    }
    if (selectedMaterialIDs.length === 0) {
      materialsError.text("Wybierz minimum jeden materiał dla produktu");
      result = false;
    }
    if (selectionCategory.val() === null) {
      categoryError.text("Wybierz kategorię");
      result = false;
    }
    if (!price.val() || !/^\d+$/.test(price.val())) {
      priceError.text("Wprowadź poprawną cenę produktu");
      result = false;
    }
    if (!size.val() || !/^\d+$/.test(size.val())) {
      sizeError.text("Wprowadź poprawny rozmiar produktu");
      result = false;
    }
    if (!availability.val() || !/^\d+$/.test(availability.val())) {
      availabilityError.text("Wprowadź poprawny dostępny zapas produktu");
      result = false;
    }
    if (photos.length === 0) {
      photosError.text("Dodaj zdjęcia");
      result = false;
    } else if (profilePhotoIndex === null) {
      photosError.text("Wybierz zdjęcie profilowe");
      result = false;
    }

    if (result === true) {
      //removing duplicates
    const uniqueMaterials = [...new Set(selectedMaterialIDs)];
    const status = await addNewProduct(
      name.val(), description.val(), selectionCategory.val(), uniqueMaterials, price.val(), 
      photos, profilePhotoIndex, size.val(), availability.val()
    );

    if (status === 200) {
      alert('Dodano nowy produkt');
      window.location.href = '../index.html';
    } else {
      alert('Nie udało się dodać produktu. Status błędu: ' + status);
    }
    }
    
  });
  container.append(submitButton);

}

async function showMaterials() {
  if (Array.isArray(availebleMaterials) && availebleMaterials.length > 0) {
    availebleMaterials.forEach(material => {
      const checkbox = $('<input>').attr({
        type: 'checkbox',
        name: 'materialCheckbox',
        value: material.id
      });
      checkbox.on('change', function() {
        const materialId = $(this).val();
        if (this.checked) {
          selectedMaterialIDs.push(materialId);
        } else {
          selectedMaterialIDs = selectedMaterialIDs.filter(id => id !== materialId);
        }
      });
      divMaterials.append(checkbox, $('<label>').text(material.name), $('<br>'));
    });
  } else {
    divMaterials.append($('<p>').text('Brak dostępnych materiałów.'));
  }
  const divNewMaterial = $('<div>');
  const inputNewMaterial = $('<input>').attr({
    type: 'text',
    placeholder: 'Nowy materiał'
  });
  const buttonNewMaterial = $('<button>').text('Dodaj').click(() => addNewMaterial(inputNewMaterial.val()));
  divNewMaterial.append(inputNewMaterial, buttonNewMaterial);
  divMaterials.append(divNewMaterial);
}

async function addNewMaterial(material) {
  const newMaterialName = material.toUpperCase();
  const userConfirmation = confirm(`Czy na pewno chcesz dodać materiał: ${newMaterialName}?`);
  if (userConfirmation) {
    await sendNewMaterial(material);
    availebleMaterials = await getMaterials();
    divMaterials.empty();
    showMaterials();
  } else {
    console.log('Anulowano dodawanie materiału.');
  }    
}

function setProfilePhoto(index) {
  profilePhotoIndex = index;
  divImagePreview.empty();
  showPhotos();
}

function showPhotos() {
  photos.forEach((photo, index) => {
    const div = $('<div>');
    if(index === profilePhotoIndex) {
      div.addClass('image-item-selected');
      div.html(`
        <img src="${photo.data}" alt="">
        <span class='profile-text'>Zdjęcie profilowe</span>
        <button onclick="removePhoto(${index})">Usuń</button>
        <button onclick="setProfilePhoto(${index})">Ustaw jako profilowe</button>
      `);
    } else {
      div.addClass('image-item');
      div.html(`
        <img src="${photo.data}" alt="">
        <button onclick="removePhoto(${index})">Usuń</button>
        <button onclick="setProfilePhoto(${index})">Ustaw jako profilowe</button>
      `);
    }
    divImagePreview.append(div);
  });
  divAddPhoto.html(`
    <label for="add-photo" class="add-photo-label">
      <div class="plus-icon">DODAJ ZDJĘCIE</div>
    </label>
    <input id="add-photo" type="file" style="display: none;" onchange="addPhoto(event)">
  `);
  divImagePreview.append(divAddPhoto);
}

function addPhoto(e) {
  const file = e.target.files[0];
  const reader = new FileReader();
  reader.onload = function (event) {
    const data = event.target.result;
    photos.push({
      data: data
    });
    divImagePreview.empty();
    showPhotos();
  };
  reader.readAsDataURL(file);
}

function removePhoto(index) {
  photos.splice(index, 1);
  divImagePreview.empty();
  showPhotos();
}