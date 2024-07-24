console.log("Hello!");
document.getElementById("1").textContent="Hello!";

var x = 5000;
let intervalId = setInterval(() => {
    console.log("Ayyyy macarena!");
}, x / 3);

setTimeout(() =>{
    document.getElementById("1").textContent = (x / 1000) + " Seconds have passed.";
    //document.getElementById("1").remove();
    clearInterval(intervalId);
    console.log("aaAI");
}, x);

let btn = document.getElementsByClassName("btnWoo");

const func = (elem, event) =>{
    console.log("Woo!\nIt's currently " + event.timeStamp);
};

for(let i = 0; i < btn.length; i++){
    console.log("Adding event.");
    btn.item(i).addEventListener(onclick, 
        (elem, event) =>{
            console.log("Woo!\nIt's currently " + event.timeStamp);
        });
}