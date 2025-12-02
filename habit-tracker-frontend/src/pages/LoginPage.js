import React, {useState}from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";


function LoginPage(){
    const[username,setUsername]=useState("");
    const[password,setPassword]=useState("");
    const navigate =useNavigate();
    const login=async()=>{
        try{
        const res = await axios.post("http://localhost:9090/api/auth/login", {
            username,
            password,
        });
        localStorage.setItem("token",res.data.token);
        alert("Logged in successfully!");
        navigate("/");
    }catch(err){
        console.error(err);
        alert("Invalid credentias");
    }
    };

    return(
        <div className="container">
            <h2>Login</h2>
            <input className="input-box" placeholder="Username" onChange={e=>setUsername(e.target.value)}/>
            <input className="input-box" placeholder="Password" type="password" onChange={e=>setPassword(e.target.value)}/>
            <button className="btn-primary" onClick={login}>Login</button>
        </div>
    );
} export default LoginPage;