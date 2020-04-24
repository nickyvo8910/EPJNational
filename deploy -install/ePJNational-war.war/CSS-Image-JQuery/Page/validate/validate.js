/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/* Insert + Update Event Validation */
/***********************************/

function InvalidEventName(textbox) {
    if(textbox.value == ''){
        textbox.setCustomValidity('Event Name should not be blank . Please input Event Name.');
    }else if (textbox.value.length > 50){
        textbox.setCustomValidity('Event Name should not be more than 50 character');
    }else{
        textbox.setCustomValidity('');
    }
}

function InvalidEventHost(textbox) {
    if(textbox.value == ''){
        textbox.setCustomValidity('Event Host should not be blank . Please input Event Host.');
    }else if (textbox.value.length > 50){
        textbox.setCustomValidity('Event Host should not be more than 50 character');
    }else{
        textbox.setCustomValidity('');
    }
}

function InvalidMinPart(textbox) {
    if(textbox.value == ''){
        textbox.setCustomValidity('Minimum participants should not be blank. Please input Min participants.');
    }
     else if(textbox.value < 5){
        textbox.setCustomValidity('Minimum participants should not be less than 5');
    }
    else if (textbox.value > parseInt(document.getElementById('max').value,10)) {
        textbox.setCustomValidity('Maximum participants should not be more than minimum one');
    }
   
    else {
        textbox.setCustomValidity('');
    }
}

function InvalidMaxPart(textbox) {
    if(textbox.value == ''){
        textbox.setCustomValidity('Maximum participants should not be left blank. Please input Max participants.');
    }
    else if(textbox.value <10){
         textbox.setCustomValidity('Maximum participants should not be less than 10');
    }
    else if (textbox.value < parseInt(document.getElementById('min').value,10)) {
        textbox.setCustomValidity('Maximum participants should not be less than 10');
    }
   
    else {
        textbox.setCustomValidity('');
    }
}

function InvalidPrize(textbox) {
    if(textbox.value == ''){
        textbox.setCustomValidity('Prize should not be blank . Please input Prize.');
    }else if (textbox.value.length > 15){
        textbox.setCustomValidity('Prize should not be more than 15 digit number');
    }else{
        textbox.setCustomValidity('');
    }
}

function InvalidEventDes(textbox) {
    if(textbox.value == ''){
        textbox.setCustomValidity('Description should not be blank . Please input Description.');
    }else if($.trim(textbox.value.toString()).length == 0){
        textbox.setCustomValidity('Whitespace is not allowed at this location');
    }else if (textbox.value.length > 500){
        textbox.setCustomValidity('Description should not be more than 500 character');
    }
    else{
        textbox.setCustomValidity('');
    }
}

/* Insert + Update FAQs Validation */
/**********************************/

function InvalidFAQQuestion(textbox) {
    if(textbox.value == ''){
        textbox.setCustomValidity('Question should not be blank . Please input Question.');
    }else if($.trim(textbox.value.toString()).length == 0){
        textbox.setCustomValidity('Whitespace is not allowed at this location');
    }else if (textbox.value.length > 500){
        textbox.setCustomValidity('Question should not be more than 500 character');
    }
    else{
        textbox.setCustomValidity('');
    }
}

function InvalidFAQAnswer(textbox) {
    if(textbox.value == ''){
        textbox.setCustomValidity('Answer should not be blank . Please input Answer.');
    }else if($.trim(textbox.value.toString()).length == 0){
        textbox.setCustomValidity('Whitespace is not allowed at this location');
    }else if (textbox.value.length > 500){
        textbox.setCustomValidity('Answer should not be more than 500 character');
}else{
        textbox.setCustomValidity('');
    }
}

/* Change Pass Validation */
/*************************/

function InvalidOldPass(textbox) {
    if(textbox.value == ''){
        textbox.setCustomValidity('Old Password should not be blank . Please input Old Password.');
    }else if (textbox.value.length > 50){
        textbox.setCustomValidity('Old Password should not be more than 50 character');
    }else if (textbox.value == document.getElementById('newpass').value){
        textbox.setCustomValidity('Old Password and New Password should not be same.');
    }else if (textbox.value.length < 6){
        textbox.setCustomValidity('Old Password should not be less than 6 character');
    }else if($.trim(textbox.value.toString()).length == 0){
        textbox.setCustomValidity('Whitespace is not allowed at this location');
    }else{
        textbox.setCustomValidity('');
    }
}

function InvalidNewPass(textbox) {
    if(textbox.value == ''){
        textbox.setCustomValidity('New Password should not be blank . Please input New Password.');
    }else if (textbox.value.length > 50){
        textbox.setCustomValidity('New Password should not be more than 50 character');
    }else if (textbox.value == document.getElementById('oldpass').value){
        textbox.setCustomValidity('New Password and Old Password should not be same.');
    }else if (textbox.value != document.getElementById('confirmpass').value &&  document.getElementById('confirmpass').value != ''){
        textbox.setCustomValidity('New Password and Confirm Password should be same.');
    }else if (textbox.value.length < 6){
        textbox.setCustomValidity('Password should not be less than 6 character');
    }else if($.trim(textbox.value.toString()).length == 0){
        textbox.setCustomValidity('Whitespace is not allowed at this location');
    }else{
        textbox.setCustomValidity('');
    }
}


function InvalidConfirmPass(textbox) {
    if(textbox.value == ''){
        textbox.setCustomValidity('Confirm Password should not be blank . Please input Confirm Password.');
    }else if (textbox.value != document.getElementById('newpass').value){
        textbox.setCustomValidity('New Password and Confirm Password should be same.');
    }
    else if($.trim(textbox.value.toString()).length == 0){
        textbox.setCustomValidity('Whitespace is not allowed at this location');
    }else{
        textbox.setCustomValidity('');
    }
}

/* Registration Validation */
/***********************************/
function InvalidEmail(textbox) {
     var pattern = /^\w+@[a-zA-Z_]+?\.[a-zA-Z]{2,3}$/;
    var email = textbox.value;
    found = email.match(pattern);
    if(textbox.value == ''){
        textbox.setCustomValidity('Email should not be blank . Please input Email.');
    }else if (textbox.value.length > 50){
        textbox.setCustomValidity('Email should not be more than 50 character');
    }else if(found==null){
        textbox.setCustomValidity('Invalid email format.');
    }
    else{
        textbox.setCustomValidity('');
    }
}

function InvalidUserFullName(textbox) {
    if(textbox.value == ''){
        textbox.setCustomValidity('FullName should not be blank . Please input FullName.');
    }else if($.trim(textbox.value.toString()).length == 0){
        textbox.setCustomValidity('Whitespace is not allowed at this location');
    }else if (textbox.value.length > 50){
        textbox.setCustomValidity('FullName should not be more than 50 character');
    }else{
        textbox.setCustomValidity('');
    }
}

function InvalidUserAddress(textbox) {
    if(textbox.value == ''){
        textbox.setCustomValidity('Address should not be blank . Please input Address.');
    }else if($.trim(textbox.value.toString()).length == 0){
        textbox.setCustomValidity('Whitespace is not allowed at this location');
    }else if (textbox.value.length > 500){
        textbox.setCustomValidity('Address should not be more than 500 character');
    }else{
        textbox.setCustomValidity('');
    }
}

function InvalidPhone(textbox){
    if(textbox.value == ''){
        textbox.setCustomValidity('Phone should not be blank . Please input Phone.');
    }else if (textbox.value.length < 8 || textbox.value.length > 11 ){
        textbox.setCustomValidity('Phone should be from 8 to 11 digit number');
    }else if (textbox.value.toString() == '00000000' || textbox.value.toString() == '000000000' || textbox.value.toString() == '0000000000' || textbox.value.toString() == '00000000000' ){
        textbox.setCustomValidity('Invalid phone number');
    }else{
        textbox.setCustomValidity('');
    }
}

function InvalidPass(textbox) {
    if(textbox.value == ''){
        textbox.setCustomValidity('Password should not be blank . Please input Password.');
    }else if (textbox.value.length > 50){
        textbox.setCustomValidity('Password should not be more than 50 characters');
    }else if (textbox.value != document.getElementById('confirmpass').value &&  document.getElementById('confirmpass').value != ''){
        textbox.setCustomValidity('Password and Confirm Password must be same.');
    }else if (textbox.value.length < 6){
        textbox.setCustomValidity('Password should not be less than 6 character');
    }else if($.trim(textbox.value.toString()).length == 0){
        textbox.setCustomValidity('Whitespace is not allowed at this location');
    }else{
        textbox.setCustomValidity('');
    }
}

function InvalidLoginPass(textbox) {
    if(textbox.value == ''){
        textbox.setCustomValidity('Password should not be blank . Please input Password.');
    }else{
        textbox.setCustomValidity('');
    }
}

function InvalidFeedback(textbox) {
    if(textbox.value == ''){
        textbox.setCustomValidity('Feedback should not be blank . Please input Feedback.');
    }else if($.trim(textbox.value.toString()).length == 0){
        textbox.setCustomValidity('Whitespace is not allowed at this location');
    }else if (textbox.value.length > 500){
        textbox.setCustomValidity('Feedback should not be more than 500 character');}

    else{
        textbox.setCustomValidity('');
    }
}

function InvalidComment(textbox) {
    if(textbox.value == ''){
        textbox.setCustomValidity('Comment should not be blank . Please input Comment.');
    }else if($.trim(textbox.value.toString()).length == 0){
        textbox.setCustomValidity('Whitespace is not allowed at this location');
    }else if (textbox.value.length > 500){
        textbox.setCustomValidity('Comment should not be more than 500 character');}
    else{
        textbox.setCustomValidity('');
    }
}