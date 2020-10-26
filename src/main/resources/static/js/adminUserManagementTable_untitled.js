/** 
 * Jacob Pauls
 * Oct, 25, 2020
 * adminUserManagementTable_untitled.js
 */

$(document).ready(function() {
	// Clear textboxes whenever DOM reloads
	$(".newUser").val("");
	
	// Change handlers for form inputs
	$(".username").change(function(){
		$("#hidden-username").val($(this).val());
	});
	$(".firstName").change(function(){
		$("#hidden-firstName").val($(this).val());
	});
	$(".lastName").change(function(){
		$("#hidden-lastName").val($(this).val());
	});
	$(".email").change(function(){
		$("#hidden-email").val($(this).val());
	});
	$(".role").change(function(){
		$("#hidden-role").val($(this).val());
	});
});

// Adds a table row to insert a new user
function addNewTableRow() {
	// Display new form row, disable buttons
	$("#newTableRow").css("display","");
	$("#addNewUser").attr("disabled", true);
}

// Enables inputs on form for editing
function editTableRow(username) {
	// Pre-populate hidden fields with current row values
	$("#hidden-username").val($("#row-reference-"+username+"-username").val());
	$("#hidden-firstName").val($("#row-reference-"+username+"-firstName").val());
	$("#hidden-lastName").val($("#row-reference-"+username+"-lastName").val());
	$("#hidden-email").val($("#row-reference-"+username+"-email").val());
	$("#hidden-role").val($("#row-reference-"+username+"-role").val());
	// Update form buttons
	$("."+username).removeAttr("disabled");
	$("#button-reference").attr("disabled", false);
}

// Manipulates display to disappear add form
function cancelAddUser(e) {
	$("#newTableRow").css("display","none");
	$("#addNewUser").attr("disabled", false);
	e.preventDefault();
}