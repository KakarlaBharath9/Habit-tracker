import React, {useState} from "react";
import axios from "axios";

function HabitForm(){
    const[name,setName]=useState("");
    const[description,setDescription]=useState("");

    const submit=async()=>{
        await axios.post("http://localhost:9090/api/habits",{
            name,
            description,
            frequency:"Daily",
            startDate:new Date().toISOString().substring(0,10)
        },{
            headers:{Authorization:`Bearer ${localStorage.getItem("token")}`},
        
        });
        alert("Habit created!");
    };
    return(
        <div className="container">
            <h2>Create Habit</h2>
            <input className="input-box" placeholder="Name" onChange={e=>setName(e.target.value)}/>
            <input className="input-box" placeholder="Description" onChange={e=>setDescription(e.target.value)}/>
            <button className="btn-primary" onClick={submit}>Create</button>
        </div>
    );
}
export default HabitForm;