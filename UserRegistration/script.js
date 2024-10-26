document.getElementById('registrationForm').addEventListener('submit', function(event) {
    event.preventDefault(); // Prevent form from submitting the traditional way

    // Get form data
    const formData = {
        first: document.getElementById('first').value,
        last: document.getElementById('last').value,
        email: document.getElementById('email').value,
        password: document.getElementById('password').value,
        repassword: document.getElementById('repassword').value,
        mobile: document.getElementById('mobile').value,
        gender: document.getElementById('gender').value
    };

    // Save form data to localStorage
    let records = JSON.parse(localStorage.getItem('formRecords')) || [];
    records.push(formData);
    localStorage.setItem('formRecords', JSON.stringify(records));

    // Display records
    displayRecords();
});

function displayRecords() {
    const records = JSON.parse(localStorage.getItem('formRecords')) || [];
    const recordsDiv = document.getElementById('records');
    recordsDiv.innerHTML = '<h3>Submitted Records:</h3>';
    records.forEach((record, index) => {
        recordsDiv.innerHTML += `
            <div>
                <h4>Record ${index + 1}</h4>
                <p>First Name: ${record.first}</p>
                <p>Last Name: ${record.last}</p>
                <p>Email: ${record.email}</p>
                <p>Contact: ${record.mobile}</p>
                <p>Gender: ${record.gender}</p>
            </div>
            <hr>
        `;
    });
}

// Display records on page load
document.addEventListener('DOMContentLoaded', displayRecords);