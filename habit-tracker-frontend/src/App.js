import React from "react";
import { BrowserRouter as Router,Routes,Route } from "react-router-dom";
import Header from"./components/Header";
import LoginPage from "./pages/LoginPage";
import RegisterPage from "./pages/RegisterPage";
import Dashboard from "./pages/Dashboard";
import HabitForm from "./pages/HabitForm";
import HabitDetails from "./pages/HabitDetails";
import NotFoundPage from "./pages/NotFoundPage";
import EditHabit from "./pages/EditHabit";
import Navbar from "./components/Navbar";
function App(){
  return(
    <Router>
      <Header/>
      <Navbar />
      <Routes>
        <Route path="/" element={<Dashboard/>} />
        <Route path="/login" element={<LoginPage/>} />
        <Route path="/register" element={<RegisterPage/>} />
        <Route path="/habit/create" element={<HabitForm />} />
        <Route path="/habit/:id" element={<HabitDetails/>} />
        <Route path="*" element={<NotFoundPage/>} />
        <Route path="/habit/:id/edit" element={<EditHabit />} />

      </Routes>
    </Router>
  );
}export default App;