import { useRef } from "react";

export default function Reference(){
    let y = useRef(0);

    return (<div>
        <h1>Counter Value</h1>
        <button onClick={() => {y.current++; console.log(y.current);}}>Increment</button>
    </div>);
}