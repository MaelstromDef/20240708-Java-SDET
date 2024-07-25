import React from "react";
import Item, { numItems, addItem as itemInc } from "./Item"
import { useState } from "react";
import wonderful from '/Images/WhatAWonderfulDay.jpg'

// Button Handlers.
const helloWorld = () =>{console.log("Hello World!")}
const addItem = () => {
    itemInc();
    console.log("Number of items: " + numItems);
}

export default function Button(){
    const [count, setCount] = useState(1);
    const addItemState = () => {
        setCount(count + 1);
        console.log("Count: " + count);
    }

    return (
        <>
            <button onClick={addItemState}>Click me!</button>
            <img src={wonderful}></img>
        </>
    )
}