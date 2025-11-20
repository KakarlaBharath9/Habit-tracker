import React from "react";
import {Link} from "react-router-dom";

function Header(){
    return(
        <div className="header-bar">
            <span className="header-title">Habit Tracker</span>
            <div>
                <link className="nav-link" to="/">Dashboard</link>
                <Link className="nav-link" to="/login">Login</Link>
                <Link className="nav-link" to="/register">Register</Link>
            </div>
        </div>
    );
}
export default Header;