import { useContext } from "react"
import { UserContext } from "../../App"
import { ItemsContext } from "../../Pages/Items";
import axios from "axios";

import { baseUrl } from "../../App";

/*  Item Schema
{
    id: INTEGER
    name: STRING
    quantity: INTEGER
}
*/
export default function Item(props){
    const {user, setUser} = useContext(UserContext);
    const {storedItems, setStoredItems} = useContext(ItemsContext);

    let storedItem = props.storedItem;

    // RESPONSE HANDLERS

    const handleResponseSuccess = (res) => {
        const newItems = storedItems.filter(i => i.item.id !== storedItem.item.id);

        if(newItems.length !== 0) setStoredItems(newItems);
        else setStoredItems([]);
    }

    const handleResponseError = (error) =>{
        if(Object.keys(error).length === 0){
            handleResponseSuccess();
            return;
        }

        console.log(JSON.stringify(error));
    }

    // BUTTON HANDLERS

    // Allows modification of item quantity
    const btnModify_Handler = (event) =>{
        console.error("Unimplemented.");
    }

    // Deletes the item
    const btnDelete_Handler = (event) =>{
        const delUrl = baseUrl + '/' + user.adminInfo.id + '/' + user.warehouse.id + '/items/' + storedItem.item.id;
        axios.delete(delUrl)
        .then(handleResponseSuccess)
        .catch(handleResponseError);
    }

    return <tr key={props.index}>
        <td>{storedItem.item.name}</td>
        <td>{storedItem.quantity}</td>
        <td>
            <button onClick={btnModify_Handler}>Manage</button>
            <button onClick={btnDelete_Handler}>Delete</button>
        </td>
    </tr>
}