import { Outlet, Link } from "react-router-dom";
import './Navbar.css';

export default function Navbar(){
    return (<div className="Navbar">
        <Link to='/'>Landing</Link>
        <Link to='/account'>Account</Link>
        <Link to='/home'>Home</Link>
        <Link to='/items'>Items</Link>
        <Link to='/login'>Login</Link>
        <Link to='/signup'>Signup</Link>
        <Link to='/warehouses'>Warehouses</Link>
    </div>);
}