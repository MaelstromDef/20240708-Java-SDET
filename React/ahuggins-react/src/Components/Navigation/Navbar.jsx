import { Link } from "react-router-dom";
import './navigation.css'

export default function Navbar(){
    return (<>
        <nav className="Navbar">
            <Link to='/'>Home</Link>
            <Link to='/cat'>Cat</Link>
            <Link to='/dog'>Dog</Link>
        </nav>
    </>)
}