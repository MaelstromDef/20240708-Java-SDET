/* Warehouse schema:
{
    id: INTEGER
    name: STRING
    location: STRING
    size: INTEGER
    administrator: ADMINISTRATOR
    storedItems: List<STORED_ITEM>
}
*/

import { useContext } from "react";
import { UserContext } from "../../App";
import { useNavigate } from "react-router-dom";

export default function Warehouse(props){
    const {user, setUser} = useContext(UserContext);
    const navigate = useNavigate();

    const btnSelect_Handler = (event) =>{
        // Set warehouse appropriately
        let newUser = user;
        newUser.warehouse = {
            id: props.id,
            name: props.name,
            location: props.location,
            size: props.size
        };
        setUser(newUser);

        // Navigate to warehouse's items page
        navigate('/items');
    }

    return <tr key={props.id}>
        <td>{props.name}</td>
        <td>{props.location}</td>
        <td>{props.size}</td>
        <td><button onClick={btnSelect_Handler}>Manage</button></td>
    </tr>
}