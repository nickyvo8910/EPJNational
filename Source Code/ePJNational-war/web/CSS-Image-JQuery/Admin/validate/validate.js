/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/* Admin Change Pass Validation */
/*******************************/

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
        textbox.setCustomValidity('Old Password should not be less than 6 character');
    }else if($.trim(textbox.value.toString()).length == 0){
        textbox.setCustomValidity('Whitespace is not allowed at this location');
    }else{
        textbox.setCustomValidity('');
    }
}


function InvalidCfmPass(textbox) {
    if(textbox.value == ''){
        textbox.setCustomValidity('Confirm Password should not be blank . Please input Confirm Password.');
    }else if (textbox.value != document.getElementById('newpass').value){
        textbox.setCustomValidity('Confirm Password and Password should be same.');
    }else{
        textbox.setCustomValidity('');
    }
}

function InvalidConfirmPass(textbox) {
    if(textbox.value == ''){
        textbox.setCustomValidity('Confirm Password should not be blank . Please input Confirm Password.');
    }else if (textbox.value != document.getElementById('newpass').value){
        textbox.setCustomValidity('New Password and Confirm Password should be same.');
    }else{
        textbox.setCustomValidity('');
    }
}

/* Insert + Update User Validation */
/**********************************/

function InvalidUserEmail(textbox) {
    var pattern = /^\w+@[a-zA-Z_]+?\.[a-zA-Z]{2,3}$/;
    var email = textbox.value;
    found = email.match(pattern);

    if(textbox.value == '' ){
        textbox.setCustomValidity('Email should not be blank . Please input Email.');
    }
    else if(found == null)
        {
              textbox.setCustomValidity('Invalid email format.');
        }
    else
    {
        textbox.setCustomValidity('');



    }
}


function InvalidPass(textbox) {
    if(textbox.value == ''){
        textbox.setCustomValidity('Password should not be blank . Please input Password.');
    }else if (textbox.value.length > 50){
        textbox.setCustomValidity('Password should not be more than 50 character');
    }else if (textbox.value != document.getElementById('confirmpass').value &&  document.getElementById('confirmpass').value != ''){
        textbox.setCustomValidity('Password and Confirm Password should be same.');
    }else if (textbox.value.length < 6){
        textbox.setCustomValidity('Password should not be less than 6 character');
    }else if($.trim(textbox.value.toString()).length == 0){
        textbox.setCustomValidity('Whitespace is not allowed at this location');
    }else{
        textbox.setCustomValidity('');
    }
}

function InvalidUserFullName(textbox) {
    if(textbox.value == ''){
        textbox.setCustomValidity('FullName should not be blank . Please input FullName.');
    }else if (textbox.value.length > 50){
        textbox.setCustomValidity('FullName should not be more than 50 character');
    }else if($.trim(textbox.value.toString()).length == 0){
        textbox.setCustomValidity('Whitespace is not allowed at this location');
    }else{
        textbox.setCustomValidity('');
    }
}

function InvalidUserAddress(textbox) {
    if(textbox.value == ''){
        textbox.setCustomValidity('Address should not be blank . Please input Address.');
    }else if (textbox.value.length > 500){
        textbox.setCustomValidity('Address should not be more than 500 character');
    }else if($.trim(textbox.value.toString()).length == 0){
        textbox.setCustomValidity('Whitespace is not allowed at this location');
    }else{
        textbox.setCustomValidity('');
    }
}

function InvalidUserPhone(textbox) {
    /*var patt = new RegExp(/\D/);
    var abc = textbox.value.toString().match(patt);*/
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


/* Insert + Update Event Validation */
/***********************************/

function InvalidEventName(textbox) {
    if(textbox.value == ''){
        textbox.setCustomValidity('Event Name should not be blank . Please input Event Name.');
    }else if (textbox.value.length > 50){
        textbox.setCustomValidity('Event Name should not be more than 50 character');
    }else if($.trim(textbox.value.toString()).length == 0){
        textbox.setCustomValidity('Whitespace is not allowed at this location');
    }else{
        textbox.setCustomValidity('');
    }
}

function InvalidEventHost(textbox) {
    if(textbox.value == ''){
        textbox.setCustomValidity('Event Host should not be blank . Please input Event Host.');
    }else if (textbox.value.length > 50){
        textbox.setCustomValidity('Event Host should not be more than 50 character');
    }else if($.trim(textbox.value.toString()).length == 0){
        textbox.setCustomValidity('Whitespace is not allowed at this location');
    }else{
        textbox.setCustomValidity('');
    }
}

function InvalidMinPart(textbox) {
    

    if(textbox.value == ''){
        textbox.setCustomValidity('Minimum participants should not be blank. Please input Min participants.');
    }
    else if ((textbox.value > document.getElementById('max').value ) ) {
    textbox.setCustomValidity('Minimum participants should not be more than maximum one');
    }
    else if(textbox.value < 5){
        textbox.setCustomValidity('Minimum participants should not be less than 5');
    }
    else {
        textbox.setCustomValidity('');
    }
}

function InvalidMaxPart(textbox) {
    if(textbox.value == ''){
        textbox.setCustomValidity('Maximum participants should not be left blank. Please input Max participants.');
    }
     else if ((textbox.value < document.getElementById('min').value ) ) {
        textbox.setCustomValidity('Minimum participants should not be more than maximum one');
    }
    else if(textbox.value <5){
         textbox.setCustomValidity('Maximum participants should not be less than 5');
    }
   
    else {
        textbox.setCustomValidity('');
    }
}

function InvalidPrice(textbox) {
    if(textbox.value == ''){
        textbox.setCustomValidity('Price should not be blank . Please input Price.');
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
    }else{
        textbox.setCustomValidity('');
        }
        
    }


/* Update Content Validation */
/***********************************/

function InvalidContentURL(textbox) {
    if(textbox.value == ''){
        textbox.setCustomValidity('Content URL should not be blank . Please input Content URL.');
    }else if (textbox.value.length > 150){
        textbox.setCustomValidity('Content URL should not be more than 150 character');
}else{
        textbox.setCustomValidity('');
    }
}

/* Admin Change Pass Validation */
/*******************************/

function InvalidFee(textbox) {
    if(textbox.value == ''){
        textbox.setCustomValidity('Fee should not be blank . Please input Fee.');
    }else{
        textbox.setCustomValidity('');
    }
}

function InvalidScore(textbox) {
    if(textbox.value == ''){
        textbox.setCustomValidity('Score should not be blank . Please input Score.');
    }else if(parseInt(textbox.value,10) > 100 || parseInt(textbox.value,10) < 0 ){
        textbox.setCustomValidity('Score should be from 0 to 100');
    }else{
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
}else{
        textbox.setCustomValidity('');
    }
}

function InvalidFAQAnswer(textbox) {
    if(textbox.value == ''){
        textbox.setCustomValidity('Answer should not be blank . Please input Answer.');
    }else if($.trim(textbox.value.toString()).length == 0){
        textbox.setCustomValidity('Whitespace is not allowed at this location');
    }else if (textbox.value.length > 500){
        textbox.setCustomValidity('Answer should not be more than 500 character.');
}else{
        textbox.setCustomValidity('');
    }
}

/* Update Feedback Validation */
/*****************************/

function InvalidFeedbackAnswer(textbox) {
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
