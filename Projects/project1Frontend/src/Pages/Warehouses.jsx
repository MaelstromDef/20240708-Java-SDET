import { useContext, useEffect, useState } from "react"
import { UserContext } from "../App"
import Warehouse from "../components/Model Representations/Warehouse";
import axios from "axios";

const url = "http://localhost:8080/";

// Shows a list of all of the warehouses and allows you to navigate into one of them, and also provides the ability to add, remove,
// and change warehouses.
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
export default function Warehouses(){
    const {user, setUser} = useContext(UserContext);
    const [warehouses, setWarehouses] = useState([]);

    const handleResponseSuccess = (res) =>{
        setWarehouses([...res.data]);
    }

    const handleResponseError = (error) =>{
        if(error.response.status === 302){
            handleResponseSuccess(error.response);
            return;
        }

        console.log("ERROR :(\n" + JSON.stringify(error));
    }

    useEffect(() =>{
        // Retrieve warehouses from backend
        let getUrl = url + user.adminInfo.id;
        
        axios.get(getUrl)
        .then(handleResponseSuccess)
        .catch(handleResponseError);
    }, [])

    return <>
        <h1>Warehouses</h1>
        <table>
            <thead>
                <tr>
                    <th>Name</th>
                    <th>Location</th>
                    <th>Size</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                {
                    warehouses.map((warehouse) =>{
                        return <Warehouse id={warehouse.id} name={warehouse.name} location={warehouse.location} size={warehouse.size}/>
                    })
                }
            </tbody>
        </table>
    </>
}