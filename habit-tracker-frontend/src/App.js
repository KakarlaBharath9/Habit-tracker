import React from "react";
import { BrowserRouter as Router,Routes,Route } from "react-router-dom";
import Header from"./components/Header";
import LoginPage from "./pages/LoginPage";
import RegisterPage from "./pages/RegisterPage";
import Dashboard from "./pages/Dashboard";
import HabitForm from "./pages/HabitForm";
import HabitDetails from "./pages/HabitDetails";

function App(){
  return(
    <Router>
      <Header/>
      <Routes>
        <Route path="/" element={<Dashboard/>} />
        <Route path="/login" element={<LoginPage/>} />
        <Route path="/register" element={<RegisterPage/>} />
        <Route path="/habit/new" element={<HabitForm />} />
        <Route path="/habit/:id" element={<HabitDetails/>} />
      </Routes>
    </Router>
  );
}export default App;