/*  Item Schema
{
    id: INTEGER
    name: STRING
    quantity: INTEGER
}
*/
export default function Item(props){
    return <tr key={props.id}>
        <td>{props.name}</td>
        <td>{props.quantity}</td>
    </tr>
}