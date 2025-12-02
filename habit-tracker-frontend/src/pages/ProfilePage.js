import React, { useEffect, useState } from "react";
import API from "../services/api";

function ProfilePage() {
  const [summary, setSummary] = useState(null);

  const loadProfile = async () => {
    try {
      const res = await API.get("/habits/summary");
      setSummary(res.data);
    } catch (err) {
      console.error(err);
      alert("Error loading profile");
    }
  };

  useEffect(() => {
    loadProfile();
  }, []);

  return (
    <div className="container">
      <h2>My Profile</h2>

      {summary ? (
        <div className="card">
          <p><b>Total Habits:</b> {summary.totalHabits}</p>
          <p><b>Completed Today:</b> {summary.completedToday}</p>
          <p><b>Avg Completion Rate:</b> {summary.avgCompletionRate.toFixed(2)}%</p>
        </div>
      ) : (
        <p>Loading...</p>
      )}

      <button
        className="btn-danger"
        onClick={() => {
          localStorage.removeItem("token");
          window.location.href = "/login";
        }}
        style={{ marginTop: "20px" }}
      >
        Logout
      </button>
    </div>
  );
}

export default ProfilePage;
