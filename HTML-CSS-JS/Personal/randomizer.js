function shuffle(array) {
let currentIndex = array.length;

// While there remain elements to shuffle...
while (currentIndex != 0) {

    // Pick a remaining element...
    let randomIndex = Math.floor(Math.random() * currentIndex);
    currentIndex--;

    // And swap it with the current element.
    [array[currentIndex], array[randomIndex]] = [
    array[randomIndex], array[currentIndex]];
}
}

let filename = process.argv[2];
let arr = new File()

// Used like so
let arr = [2, 11, 37, 42];
shuffle(arr);
console.log(arr);