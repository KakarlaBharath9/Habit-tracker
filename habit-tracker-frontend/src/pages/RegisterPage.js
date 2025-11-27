import React,{useState} from "react";
import axios from "axios";
import { Navigate } from "react-router-dom";

function RegisterPage(){
    const[username,setUsername]=useState("");
    const[email,setEmail]=useState("");
    const[password,setPassword]=useState("");

    const register=async()=>{
        await axios.post("http://localhost:9090/api/auth/register",{
        username,
        email,
        password,
        });
        alert("Registration successfull!");
        Navigate("/login");
    };
    return(
        <div className="container">
            <h2>Register</h2>
            <input className="input-box" placeholder="Username" onChange={e=>setUsername(e.target.value)}/>
            <input className="input-box" placeholder="Email" onChange={e=>setEmail(e.target.value)}/>
            <input className="input-box" placeholder="Password" type="password" onChange={e=>setPassword(e.target.value)}/>
            <button className="btn-primary" onClick={register}>Register</button>

        </div>
    );
}export default RegisterPage;