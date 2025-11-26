import React,{useEffect,useState} from "react";
import axios from "axios";
import {Link}from "react-router-dom";

function Dashboard(){
    const[habits, setHabits]=useState([]);

    const loadHabits=async()=>{
        const res = await axios.get("http://localhost:9090/api/habits/all",{
            headers:{Authorization: `Bearer ${localStorage.getItem("token")}`},
        });
        setHabits(res.data);
    };

    useEffect(()=>{loadHabits(); },[]);

    return(
        <div className="container">
            <h2>Your Habits</h2>
            <Link to="/habit/new"><button className="btn-primary">+Add habit</button></Link>
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