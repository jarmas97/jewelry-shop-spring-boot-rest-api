const container = $('#admin-content');
const dataErrorLabel = $('<label>').addClass('error');

if (!token) {
  container.append($('<h2>').text("Logowanie"));
  const form = $('<form>').addClass('form');
  const usernameInput = $('<input>').attr({
    'placeholder': 'Nazwa użytkownika',
    'type': 'text',
    'required': true
  });
  const usernameErrorLabel = $('<label>').addClass('error');
  const passwordInput = $('<input>').attr({
    'placeholder': 'Hasło',
    'type': 'password',
    'required': true
  });
  const passwordErrorLabel = $('<label>').addClass('error');
  const buttonSubmit = $('<button>').text('Zaloguj się').on('click', function (e) {
    e.preventDefault();
    if (!usernameInput.val()) {
      usernameErrorLabel.text('Podaj nazwę użytkownika');
    } else if (!passwordInput.val()) {
      passwordErrorLabel.text('Podaj hasło');
    } else {
      submit(usernameInput.val(), passwordInput.val());
    }
  });
  form.append(dataErrorLabel, usernameInput, usernameErrorLabel, passwordInput, passwordErrorLabel, buttonSubmit);
  container.append(form);

} else {

  container.append($('<h2>').text("Menadżer zadań"));

  const ul = $('<ul>').addClass('tasks');
  const li1 = $('<li>');
  const a1 = $('<a>').attr({
    'href': './admin/newProduct.html'
  }).text('Dodaj produkt');;
  li1.append(a1);
  const li2 = $('<li>');
  const a2 = $('<a>').attr({
    'href': './admin/productSearch.html'
  }).text('Znajdź produkt');
  li2.append(a2);
  const li3 = $('<li>');
  const a3 = $('<a>').attr({
    'href': './admin/statistics.html'
  }).text('Statystyki');
  li3.append(a3);
  const li4 = $('<li>');
  const a4 = $('<a>').attr('href', '#').text('Wyloguj').on('click', function (e) {
    e.preventDefault();
    localStorage.removeItem('materials');
    logout();
  });
  li4.append(a4);

  ul.append(li1, li2, li3, li4);
  container.append(ul);
}

async function submit(username, password) {
  const status = await login(username,password);
  if (status !== 200) {
    dataErrorLabel.text("Niepoprawne dane logowania");
  }
}
