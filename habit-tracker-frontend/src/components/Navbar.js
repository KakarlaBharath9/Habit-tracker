import React from "react";
import { Link, useNavigate } from "react-router-dom";

function Navbar() {
  const navigate = useNavigate();
  const token = localStorage.getItem("token");

  const logout = () => {
    localStorage.removeItem("token");
    navigate("/login");
  };

  return (
    <nav className="navbar">
      

      <div className="nav-right">
        {!token ? (
          <>
            <Link to="/login" className="nav-link">Login</Link>
            <Link to="/register" className="nav-link">Register</Link>
          </>
        ) : (
          <>
            <Link to="/dashboard" className="nav-link">Dashboard</Link>

            {/* User Menu */}
            <div className="user-menu">
              <button className="user-btn">☰</button>
              <div className="dropdown">
                <Link to="/profile" className="dropdown-item">Profile</Link>
                <button onClick={logout} className="dropdown-item">Logout</button>
              </div>
            </div>
          </>
        )}
      </div>
    </nav>
  );
}

export default Navbar;
