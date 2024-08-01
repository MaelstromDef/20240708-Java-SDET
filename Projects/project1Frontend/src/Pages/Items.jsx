import { useContext, useEffect, useState } from "react";
import Item from "../components/Model Representations/Item";
import { UserContext } from "../App";
import axios from "axios";

const url = "http://localhost:8080/";   // .../adminId/warehouseId/items

export default function Items(){
    const {user, setUser} = useContext(UserContext);
    const [storedItems, setStoredItems] = useState([]);

    // Handlers
    const handleResponseSuccess = (res) =>{
        console.log(res.data);
        setStoredItems([...res.data]);
    }

    const handleResponseError = (error) => {
        if(error.response.status === 302){  // Found code, not an error.
            handleResponseSuccess(error.response);
            return;
        }

        console.log("ERROR\n" + JSON.stringify(error));
    }

    // Grab stored items on load
    useEffect(()=>{
        const getUrl = url + user.adminInfo.id + "/" + user.warehouse.id + "/items";

        axios.get(getUrl)
        .then(handleResponseSuccess)
        .catch(handleResponseError);
    }, [])

    return <>
        <h1>{user.warehouse.name}</h1>
        <table>
            <thead>
                <tr>
                    <th>Name</th>
                    <th>Quantity</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                {
                    storedItems.length > 0 ? 
                        storedItems.map((storedItem) =>{
                            return <Item id={storedItem.item.id} name={storedItem.item.name} quantity={storedItem.quantity}/>
                        }) :
                        <tr><td>No items found.</td></tr>
                }
            </tbody>
        </table>
    </>
}