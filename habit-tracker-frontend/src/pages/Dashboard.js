import React,{useEffect,useState} from "react";
import {Link}from "react-router-dom";
import API from "../services/api";


function Dashboard(){
    const[habits, setHabits]=useState([]);

    const loadHabits=async()=>{
        try{
        const res = await API.get("/habits/all");
        setHabits(res.data);
    }catch(err){
        console.error("Failed to load habits:",err);
        alert("Error loading habits. Check backend & token.");
    }
    };

    useEffect(()=>{loadHabits(); },[]);

    return(
        <div className="container">
            <h2>Your Habits</h2>
            <Link to="/habit/create"><button className="btn-primary">+Add habit</button></Link>
            {habits.map(h=>(
                <div className="card"key={h.id}>
                    <h3>{h.name}</h3>
                    <p>{h.description}</p>
                    <Link to={`/habit/${h.id}`}>View</Link>
                    </div>
            ))}
        </div>
    );
}
export default Dashboard;