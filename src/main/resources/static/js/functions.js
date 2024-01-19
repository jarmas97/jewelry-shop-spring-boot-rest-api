const SERVER_URL = "http://localhost:8080";
const token = localStorage.getItem("token");

async function getProducts() {
  const response = await axios.get(`${SERVER_URL}/products`);
  if (response.status === 200) {
    return response.data;
  } else {
    console.error('Błąd podczas pobierania produktów');
    return [];
  }
}

async function getProductById(id) {
  const response = await axios.get(`${SERVER_URL}/product?id=${id}`);
  if (response.status === 200) {
    return response.data;
  } else {
    console.error('Błąd podczas pobierania danych o produkcie');
    return null;
  }
}

async function getMaterials() {
  try {
    const response = await axios.get(SERVER_URL + '/admin/materials', {
      headers: {
        'Authorization': `${token}`,
        'Content-Type': 'application/json',
      }
    });
    return response.data;
  } catch (error) {
    console.error('Błąd podczas pobierania danych o materiałach:', error);
    alert('Brak połączenia z serwerem');
    if (error.response && error.response.status === 403) {
      window.location.href = '/admin';
    }
    return [];
  }
}

async function login(username, password) {

  try {
    const response = await axios.post(`${SERVER_URL}/login`, { username, password });
    if (response.status === 200) {
      localStorage.setItem('token', response.headers.authorization);
      window.location.reload();
    }
    return response.status;
  } catch (error) {
    
  }
  
}

function logout() {
  localStorage.removeItem('token');
  window.location.reload();
}

async function sendNewMaterial(material) {
  try {
    await axios.get(`${SERVER_URL}/admin/add-material?materialName=${material}`, {
      headers: {
        'Authorization': `${token}`,
        'Content-Type': 'application/json',
      },
    });
  } catch (error) {
    console.error('Błąd podczas dodawania materiału:', error);
  }
}

function removePhoto(index) {
  console.log("removing photo");
}

async function addNewProduct(name, description, category, materialsIDs, price,
  photos, profilePhotoIndex, size, availableStock)
{
  const data = {
    name,
    description,
    category,
    materialsIDs,
    price,
    photos,
    profilePhotoIndex: profilePhotoIndex,
    size,
    availableStock
  };
  const response = await axios.post(SERVER_URL + '/admin/add-product', data, {
    headers: {
      'Content-Type': 'application/json',
      'Authorization': `${token}`,
    },
  });
  return response.status;

}

async function removeProduct(id) {
  const response = await axios.delete(`${SERVER_URL}/admin/remove-product?productId=${id}`, {
    headers: {
      'Authorization': `${token}`,
      'Content-Type': 'application/json',
    },
  });
  return response.status;
}