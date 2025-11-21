import API from "./api";

export const markProgress=(data)=>API.post("/progress/mark",data);
export const getHabitStats=(id)=>API.get(`/progress/${id}/stats`);