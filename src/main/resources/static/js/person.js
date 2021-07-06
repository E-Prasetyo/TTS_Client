/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(() => {
    var person = $("#personTable").DataTable();
    
    person.on('order.dt search.dt', function () {
        person.column(0, {search: 'applied', order: 'applied'}).nodes().each(function (cell, i) {
            cell.innerHTML = i + 1;
        });
    }).draw();
});

function deleteById(id) {
    Swal.fire({
        title: 'Do you want to delete data?',
        showDenyButton: true,
        confirmButtonText: `Delete`,
        icon: 'warning'
    }).then((result) => {
        if (result.isConfirmed) {
            window.location.href = "/person/" + id + "/delete";
        }
    });
}

function detailPerson(firstName, lastName) {
    $("#firstName").val(firstName);
    $("#lastName").val(lastName);
    disableElement(true);
}

function updatePerson(id, firstName, lastName) {
    disableElement(false);
    $("#personForm").attr('action', "/person/" + id + "/update");
    $("#firstName").val(firstName);
    $("#lastName").val(lastName);
}

function createPerson() {
    disableElement(false);
    $("#personForm").attr('action', "/person/create");
    $("#firstName").val("");
    $("#lastName").val("");
}

function disableElement(isDisable) {
    $("#firstName").prop("disabled", isDisable);
    $("#lastName").prop("disabled", isDisable);
    $("#submitPerson").prop("disabled", isDisable);
}