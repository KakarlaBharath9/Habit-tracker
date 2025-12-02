import React,{useState} from "react";
import API from "../services/api";

function RegisterPage(){
    const[username,setUsername]=useState("");
    const[email,setEmail]=useState("");
    const[password,setPassword]=useState("");

    const register=async()=>{
        try{
        const response=await API.post("/auth/register",{
        username,
        email,
        password,
        });
        if(response.status===200 || response.status===201){
            alert("Registration sccessful!!");
        }else{
            alert("Registration failed");
        }
        
    }catch(err){
        console.error(err);
        alert("Registration failed")
    }
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