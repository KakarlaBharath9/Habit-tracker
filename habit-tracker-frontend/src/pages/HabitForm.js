import React, { useState } from "react";
import API from "../services/api";   

function HabitForm() {
    const [name, setName] = useState("");
    const [description, setDescription] = useState("");

    const submit = async () => {
        try {
            await API.post("/habits", {
                name,
                description,
                frequency: "DAILY",
                startDate: new Date().toISOString().substring(0, 10)
            });

            alert("Habit created!");
        } catch (err) {
            console.error("Error creating habit:", err.response ? err.response.data:err);
            alert("Error creating habit.");
        }
    };

    return (
        <div className="container">
            <h2>Create Habit</h2>

            <input
                className="input-box"
                placeholder="Name"
                onChange={(e) => setName(e.target.value)}
            />

            <input
                className="input-box"
                placeholder="Description"
                onChange={(e) => setDescription(e.target.value)}
            />

            <button className="btn-primary" onClick={submit}>
                Create
            </button>
        </div>
    );
}

export default HabitForm;
