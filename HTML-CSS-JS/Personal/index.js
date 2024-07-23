function hello(){
    console.log("Hello World!");
}

hello();

const awesomeElement = document.getElementById("Crowd");
console.log(awesomeElement);

const img = document.createElement("img");
img.src="./WhatAWonderfulDay.jpg";
img.alt="What a wonderful day!";

awesomeElement.appendChild(img);