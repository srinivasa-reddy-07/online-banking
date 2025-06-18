// Reusable validation utility functions
function showError(element, message) {
  element.addClass('is-invalid');
  element.siblings('.invalid-feedback').text(message).addClass('d-block');
}

function clearErrors(element) {
  element.removeClass('is-invalid');
  element.siblings('.invalid-feedback').text('').removeClass('d-block');
}

function isEmpty(value) {
  return !value || value.trim() === '';
}

function isValidEmail(email) {
  const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
  return emailRegex.test(email);
}

function isValidName(name) {
  const nameRegex = /^[A-Za-z\s]+$/;
  return nameRegex.test(name) && name.length >= 2 && name.length <= 50;
}

function isValidAccountNumber(accountNumber) {
  const accountRegex = /^\d{9,18}$/;
  return accountRegex.test(accountNumber);
}

function isValidIFSC(ifsc) {
  const ifscRegex = /^[A-Z]{4}0\d{6}$/;
  return ifscRegex.test(ifsc);
}

function isValidAmount(amount, min = 1, max = 1000000) {
  const num = Number(amount);
  return !isNaN(num) && Number.isInteger(num) && num >= min && num <= max;
}

function isValidPhone(phone) {
  const phoneRegex = /^\d{10}$/;
  return phoneRegex.test(phone);
}

function isValidPassword(password) {
  const passwordRegex = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)[A-Za-z\d]{8,}$/;
  return passwordRegex.test(password);
}

// Form-specific validation functions
function validateFundTransferForm(form) {
  let isValid = true;
  const maxAmount = 1000000; // 10 lakh

  // Clear previous errors
  form.find('.invalid-feedback').text('').removeClass('d-block');
  form.find('.form-control, .form-select').removeClass('is-invalid');

  // From Account
  const fromAccount = $('#fromAccount').val();
  if (isEmpty(fromAccount)) {
    showError($('#fromAccount'), 'Please select your account.');
    isValid = false;
  }

  // Recipient Account Number
  const recipient = $('#recipient').val().trim();
  if (isEmpty(recipient)) {
    showError($('#recipient'), 'Recipient account number is required.');
    isValid = false;
  } else if (!isValidAccountNumber(recipient)) {
    showError($('#recipient'), 'Account number must be 9 to 18 digits.');
    isValid = false;
  }

  // Receiver's Name
  const receiverName = $('#receiverName').val().trim();
  if (isEmpty(receiverName)) {
    showError($('#receiverName'), 'Receiver\'s name is required.');
    isValid = false;
  } else if (!isValidName(receiverName)) {
    showError($('#receiverName'), 'Name must be 2-50 characters, letters and spaces only.');
    isValid = false;
  }

  // IFSC Code
  const ifsc = $('#ifsc').val().trim().toUpperCase();
  if (isEmpty(ifsc)) {
    showError($('#ifsc'), 'IFSC code is required for inter-bank transfers.');
    isValid = false;
  } else if (!isValidIFSC(ifsc)) {
    showError($('#ifsc'), 'IFSC code must be 11 characters (e.g., SBIN0001234: 4 letters, 0, 6 digits).');
    isValid = false;
  }

  // Amount
  const amount = $('#amount').val();
  if (isEmpty(amount)) {
    showError($('#amount'), 'Amount is required.');
    isValid = false;
  } else if (!isValidAmount(amount, 1, maxAmount)) {
    showError($('#amount'), 'Amount must be a whole number between ₹1 and ₹10,00,000.');
    isValid = false;
  }

  // Remarks
  const remarks = $('#remarks').val().trim();
  if (remarks.length > 100) {
    showError($('#remarks'), 'Remarks cannot exceed 100 characters.');
    isValid = false;
  }
  
  // If the form is valid, create and return the transferData object
  if (isValid) {
    const transferData = {
      fromAccount: fromAccount,
      toAccount: recipient,
      receiverName: receiverName,
      ifscCode: ifsc, 
      amount: parseInt(amount, 10), 
      remarks: remarks || null,
      // timestamp: new Date().toISOString()
    };
    return transferData;
  }

  // If invalid, return false
  return false;
}

function validateWithdrawForm(form) {
  let isValid = true;
  const maxAmount = 1000000; // 10 lakh

  // Clear previous errors
  form.find('.invalid-feedback').text('').removeClass('d-block');
  form.find('.form-control, .form-select').removeClass('is-invalid');

  // From Account
  const account = $('#account').val();
  if (isEmpty(account)) {
    showError($('#account'), 'Please select your account.');
    isValid = false;
  } else if (!isValidAccountNumber(account)) {
    showError($('#account'), 'Account number must be 9 to 18 digits.');
    isValid = false;
  }

  // Amount
  const amount = $('#amount').val();
  if (isEmpty(amount)) {
    showError($('#amount'), 'Amount is required.');
    isValid = false;
  } else if (!isValidAmount(amount, 1, maxAmount)) {
    showError($('#amount'), 'Amount must be a whole number between ₹1 and ₹10,00,000.');
    isValid = false;
  }

  // Remarks
  const remarks = $('#remarks').val().trim();
  if (remarks.length > 100) {
    showError($('#remarks'), 'Remarks cannot exceed 100 characters.');
    isValid = false;
  }

  if (isValid) {
    const withdrawData = {
      account: account,
      amount: parseInt(amount, 10), 
      remarks: remarks || null,
    };
    return withdrawData;
  }

  // If invalid, return false
  return false;
}

function validateDepositForm(form) {
  let isValid = true;
  const maxAmount = 1000000; // 10 lakh

  // Clear previous errors
  form.find('.invalid-feedback').text('').removeClass('d-block');
  form.find('.form-control, .form-select').removeClass('is-invalid');

  // To Account
  const account = $('#account').val();
  if (isEmpty(account)) {
    showError($('#account'), 'Please select your account.');
    isValid = false;
  } else if (!isValidAccountNumber(account)) {
    showError($('#account'), 'Account number must be 9 to 18 digits.');
    isValid = false;
  }

  // Amount
  const amount = $('#amount').val();
  if (isEmpty(amount)) {
    showError($('#amount'), 'Amount is required.');
    isValid = false;
  } else if (!isValidAmount(amount, 1, maxAmount)) {
    showError($('#amount'), 'Amount must be a whole number between ₹1 and ₹10,00,000.');
    isValid = false;
  }

  // Remarks
  const remarks = $('#remarks').val().trim();
  if (remarks.length > 100) {
    showError($('#remarks'), 'Remarks cannot exceed 100 characters.');
    isValid = false;
  }

  if (isValid) {
    const depositData = {
      account: account,
      amount: parseInt(amount, 10), 
      remarks: remarks || null,
    };
    return depositData;
  }

  // If invalid, return false
  return false;
}

function validateLoginForm(form) {
  let isValid = true;

  // Clear previous errors
  form.find('.invalid-feedback').text('').removeClass('d-block');
  form.find('.form-control').removeClass('is-invalid');

  // Email
  const username_or_email = $('#username_or_email').val().trim();
  if (isEmpty(username_or_email)) {
    showError($('#username_or_email'), 'Username or email is required.');
    isValid = false;
  }

  // Password
  const password = $('#password').val();
  if (isEmpty(password)) {
    showError($('#password'), 'Password is required.');
    isValid = false;
  } else if (password.length < 8) {
    showError($('#password'), 'Password must be at least 8 characters long.');
    isValid = false;
  }

  if (isValid) {
    const loginData = {
      username_or_email: username_or_email,
      password: password,
    };
    return loginData;
  }

  // If invalid, return false
  return false;
}

function validateSignupForm(form) {
  let isValid = true;

  // Clear previous errors
  form.find('.invalid-feedback').text('').removeClass('d-block');
  form.find('.form-control').removeClass('is-invalid');

  // Username
  const username = $('#username').val().trim();
  if (isEmpty(username)) {
    showError($('#firstName'), 'First name is required.');
    isValid = false;
  } else if (!isValidName(username)) {
    showError($('#firstName'), 'First name must be 2-50 characters, letters and spaces only.');
    isValid = false;
  }

  // Email
  const email = $('#email').val().trim();
  if (isEmpty(email)) {
    showError($('#email'), 'Email is required.');
    isValid = false;
  } else if (!isValidEmail(email)) {
    showError($('#email'), 'Please enter a valid email address.');
    isValid = false;
  }

  // Password
  const password = $('#password').val();
  if (isEmpty(password)) {
    showError($('#password'), 'Password is required.');
    isValid = false;
  } else if (!isValidPassword(password)) {
    showError($('#password'), 'Password must be at least 8 characters, with 1 uppercase, 1 lowercase, and 1 number.');
    isValid = false;
  }

  // Confirm Password
  const confirmPassword = $('#confirmPassword').val();
  if (isEmpty(confirmPassword)) {
    showError($('#confirmPassword'), 'Please confirm your password.');
    isValid = false;
  } else if (confirmPassword !== password) {
    showError($('#confirmPassword'), 'Passwords do not match.');
    isValid = false;
  }

  // Phone Number (Optional)
  const phone = $('#phone').length ? $('#phone').val().trim() : '';
  if (phone && !isValidPhone(phone)) {
    showError($('#phone'), 'Phone number must be exactly 10 digits.');
    isValid = false;
  }

  if (isValid) {
    const signupData = {
      username: username,
      email: password, 
      password: password,
    };
    return signupData;
  }

  // If invalid, return false
  return false;
}