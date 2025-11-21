import React from "react";
import "./UserMenu.css";

export default function UserMenu({onLogout}){
    const [open, setOpen]=React.useState(false);
    return(
        <div className="user-menu-container">
            <button className="user-menu-button" onClick={()=>setOpen(!open)}>☰</button>
            {open && (
                <div className="user-menu-dropdown">
                    <button className="logout-btn" onClick={onLogout}>Logout</button>
                    </div>
            )}
        </div>
    );
}