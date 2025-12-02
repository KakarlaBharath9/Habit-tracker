import React, { useEffect, useState } from "react";
import { useParams, useNavigate } from "react-router-dom";
import API from "../services/api";

function EditHabit() {
  const { id } = useParams();
  const navigate = useNavigate();

  const [name, setName] = useState("");
  const [description, setDescription] = useState("");
  const [frequency, setFrequency] = useState("DAILY");

  useEffect(() => {
    const loadHabit = async () => {
      try {
        const res = await API.get(`/habits/${id}`);
        setName(res.data.name);
        setDescription(res.data.description);
        setFrequency(res.data.frequency);
      } catch (err) {
        alert("Error loading habit.");
      }
    };
    loadHabit();
  }, [id]);

  const updateHabit = async () => {
    try {
      await API.put(`/habits/${id}`, {
        name,
        description,
        frequency,
      });
      alert("Habit updated!");
      navigate(`/habit/${id}`);
    } catch (err) {
      alert("Error updating habit.");
    }
  };

  return (
    <div className="container">
      <h2>Edit Habit</h2>

      <input
        className="input-box"
        value={name}
        onChange={(e) => setName(e.target.value)}
        placeholder="Name"
      />

      <input
        className="input-box"
        value={description}
        onChange={(e) => setDescription(e.target.value)}
        placeholder="Description"
      />

      <select
        className="input-box"
        value={frequency}
        onChange={(e) => setFrequency(e.target.value)}
      >
        <option value="DAILY">Daily</option>
        <option value="WEEKLY">Weekly</option>
      </select>

      <button className="btn-primary" onClick={updateHabit}>
        Update Habit
      </button>
    </div>
  );
}

export default EditHabit;
