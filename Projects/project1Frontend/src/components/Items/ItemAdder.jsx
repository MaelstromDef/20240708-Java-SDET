import { useState } from "react";
import ItemForm from "./ItemForm";

export default function ItemAdder(){
    const [showForm, setShowForm] = useState(false);

    return <>
        {showForm ?
            <>
                <ItemForm />
                <button onClick={() =>{setShowForm(false)}}>Cancel.</button>
            </> :
            <button onClick={() =>{setShowForm(true)}}>Add item.</button>
            
        }
    </>
}