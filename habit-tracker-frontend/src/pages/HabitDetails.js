import React, { useEffect, useState, useCallback } from "react";
import { useParams, Link, useNavigate } from "react-router-dom";
import API from "../services/api";

function HabitDetails() {
  const { id } = useParams();
  const navigate = useNavigate();

  const [habit, setHabit] = useState(null);
  const [stats, setStats] = useState(null);
  const [todayDone, setTodayDone] = useState(false);

  // Load habit + stats + today's status
  const loadData = useCallback(async () => {
    try {
      const h = await API.get(`/habits/${id}`);
      setHabit(h.data);

      const s = await API.get(`/progress/${id}/stats`);
      setStats(s.data);

      const today = new Date().toISOString().substring(0, 10);
      try{
      const t = await API.get(`/progress/${id}/day/${today}`);
      setTodayDone(t.data.completed);
      }catch{
        setTodayDone(false);
      }
    } catch (err) {
      console.error("Habit load error:",err);
    }
  }, [id]);

  // Mark today's completion
  const markToday = async () => {
    try {
      const today = new Date().toISOString().substring(0, 10);

      await API.post(`/progress/mark`, {
        habitId: Number(id),
        date: today,
        completed: true,
      });

      alert("Marked today!");
      loadData();
    } catch (err) {
      console.error(err);
      alert("Error marking today's progress");
    }
  };

  // DELETE HABIT FUNCTION
  const deleteHabit = async () => {
    const confirmed = window.confirm("Are you sure you want to delete this habit?");
    if (!confirmed) return;

    try {
      await API.delete(`/habits/${id}`);
      alert("Habit deleted!");
      navigate("/");
    } catch (err) {
      console.error(err);
      alert("Failed to delete habit");
    }
  };

  useEffect(() => {
    loadData();
  }, [loadData]);

  return (
    <div className="container">

      {habit && (
        <div className="card">
          <h2>{habit.name}</h2>
          <p>{habit.description}</p>
          <p>Frequency: {habit.frequency}</p>
          <p>Start Date: {habit.startDate}</p>

          {/* Checkbox for today */}
          <label>
            <input
              type="checkbox"
              checked={todayDone}
              onChange={markToday}
            />{" "}
            Mark Today
          </label>

          {/* Edit button */}
          <Link to={`/habit/${id}/edit`}>
            <button className="btn-primary mt-2">Edit Habit</button>
          </Link>

          {/* Delete button */}
          <button
            className="btn-danger"
            onClick={deleteHabit}
            style={{
              marginTop: "10px",
              background: "red",
              color: "white",
              padding: "8px 12px",
              border: "none",
              borderRadius: "5px",
              cursor: "pointer",
            }}
          >
            Delete Habit
          </button>
        </div>
      )}

      {stats && (
        <div className="card">
          <h3>Statistics</h3>
          <p>Best Streak: {stats.bestStreak}</p>
          <p>Current Streak: {stats.currentStreak}</p>
          <p>Completion Rate: {stats.completionRate}%</p>
        </div>
      )}
    </div>
  );
}

export default HabitDetails;
