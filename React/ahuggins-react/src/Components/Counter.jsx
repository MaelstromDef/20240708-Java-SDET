import { useState } from "react";
import Peekaboo from "./Peekaboo";
import Button from "./Button";

export default function Counter(){
    const [dummyState, setDummyState] = useState(0);

    const triggerRerender = function(){
        setDummyState(dummyState + 1);
    }

    return (<>
        {dummyState % 2 == 0 ? 
            <Button/> :
            <Peekaboo/>}
        <button onClick={triggerRerender}> Trigger Rerender</button>
    </>);
}