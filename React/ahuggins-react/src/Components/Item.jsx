export default function Item(){
    return <p>I'm an Item!</p>
}

export function addItem(){
    numItems++;
}
export let numItems = 0;