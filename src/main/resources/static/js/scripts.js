//Voting

const tooltipTriggerList = document.querySelectorAll('[data-bs-toggle="tooltip"]')
const tooltipList = [...tooltipTriggerList].map(tooltipTriggerEl => new bootstrap.Tooltip(tooltipTriggerEl))


// New Session

var containerCount = 0;

function addInput() {
    const container = document.getElementById('input-cont');
    containerCount += 1;
    let input = document.createElement('input');
    removeButton.removeAttribute("style");
    removeButton.className = 'btn btn-outline-dark mb-4';

    input.placeholder = 'Your friends name';
    input.id = 'friend' + containerCount;
    input.name = 'friendsNames'
    input.className = 'form-control mb-2'
    container.appendChild(input);     
}

function removeInput() {
    const input = document.getElementById('friend' + containerCount);
    input.remove();
    containerCount -= 1;

    if (containerCount == 0) {
        const removeButton = document.getElementById('removeButton');
        removeButton.style.pointerEvents = 'none';
        removeButton.className = 'btn btn-outline-dark mb-4 disabled';
    }
}

function uncheckElementAllCb() { 
    const allCb = document.getElementById('allCb');
    allCb.checked = false;
}

function uncheckAllCheckboxes() {
    var allCheckboxes = document.getElementsByName('selectedGenres');

    for (var i = 0; i < allCheckboxes.length; i++){
        allCheckboxes[i].checked = false;
    }
}
        
function checkCheckboxes() {
    var len = document.querySelectorAll('.checkbox input[type="checkbox"]:checked').length

    if (len <= 0) {
        alert("Please choose at least one genre.")
        return false;
    }
    return true;
}


// Summary

function copyToClipboard(id) {
    var copyText = document.getElementById(id).value;
    navigator.clipboard.writeText(copyText).then(() => {
        alert("Copied to clipboard");
    });
  }