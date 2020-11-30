/**
 * Gregory Pohlod Student ID 300311820
 * Nov 30, 2020
 * HardwareManagement_gpo_20.js
 * CSIS 3275 Group Project
 * Group Name: Untitled
 */

$(document).ready(function() {
	// Clear textboxes whenever DOM reloads
	$(".newHardware").val("");
	
	// Change handlers for form inputs
	$(".hardwareTypeDescription").change(function(){
		$("#hidden-hardwareTypeDescription").val($(this).val());
	});
});

// Adds a table row to insert a new user
function addNewTableRow() {
	// Display new form row, disable buttons
	$("#newTableRow").css("display","");
	$("#addNewHardware").attr("disabled", true);
}


// Manipulates display to disappear add form
function cancelAddHardware(e) {
	$("#newTableRow").css("display","none");
	$("#addNewHardware").attr("disabled", false);
	e.preventDefault();
}