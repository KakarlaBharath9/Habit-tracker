import React,{useEffect,useState} from "react";
import{useParams}from "react-router-dom";
import axios from "axios";

function HabitDetails(){
    const{id}=useParams();
    const[habit,setHabit]=useState(null);
    const[stats,setStats]=useState(null);

    const loadData=async()=>{
        const h =await axios.get(`http://localhost:9090/api/habits/${id}`,{
            headers:{Authorization:`Bearer${localStorage.getItem("token")}`},
        });
        setHabit(h.data);
        const s=await axios.get(`http://localhost:9090/api/progress/${id}/stats`,{
            headers:{Authorization:`Bearer${localStorage.getItem("token")}`},
        });
        setStats(s.data);
    };
        
        useEffect(()=>{loadData();},[]);

        return(
            <div className="container">
                {habit &&(
                    <div className="card">
                        <h2>{habit.name}</h2>
                        <p>{habit.description}</p>
                    </div>
                )}
                {stats &&(
                    <div className="card">
                        <h3>Statistics</h3>
                        <p>Total Days:{stats.totalDays}</p>
                        <p>Completed Days:{stats.completedDays}</p>
                        <p>Success Rate: {stats.successRate}%</p>
                    </div>
                )}
            </div>
        );
    
}
export default HabitDetails;