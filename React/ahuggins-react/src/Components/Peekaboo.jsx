import { useState } from "react";
import peek from "../assets/peekaboo-cat1.png";
import boo from "../assets/peekaboo-cat2.png";

export default function Peekaboo(){
    let x = 500;
    let timeout = null;

    const [imageRef, setImageRef] = useState(peek);
    const btnChangeImg_Handler = () =>{
        if(timeout != null) clearTimeout(timeout);

        setImageRef(boo);
        timeout = setTimeout(() =>{
            setImageRef(peek);
            timeout = null;
        }, x)
    }

    return(
        <>
            <div height="327px">
                <img src={imageRef}></img>
            </div>
            <button onClick={btnChangeImg_Handler}>Peekaboo!</button>
        </>
    )
}