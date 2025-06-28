import api from '../api/axios';

export const loginUser = (credentials) => api.post('/auth/login', credentials);
export const registerUser = (user) => api.post('/auth/register', user);
