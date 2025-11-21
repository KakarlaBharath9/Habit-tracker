import API from "./api";

export const getAllHabits=()=>API.get("/habits/all");
export const createHabit=(data)=>API.get("/habits/create",data);
export const getHabit=(id)=>API.get(`/habits/${id}`);
export const updateHabit=(id)=>API.put(`/habits/${id}`,data);
export const deleteHabit=(id)=>API.delete(`/habits/${id}`);