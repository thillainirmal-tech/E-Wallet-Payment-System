import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { Container, TextField, Button, Typography, Paper, Box, Alert } from '@mui/material';
import { loginUser } from '../services/auth';

const Login = () => {
  const [form, setForm] = useState({ username: '', password: '' });
  const [error, setError] = useState('');
  const navigate = useNavigate();

  const handleChange = (e) => setForm({ ...form, [e.target.name]: e.target.value });

  const handleLogin = async (e) => {
    e.preventDefault();
    try {
      const res = await loginUser(form);
      localStorage.setItem('token', res.data.token);
      navigate('/dashboard');
    } catch {
      setError('Login failed. Check credentials.');
    }
  };

  return (
    <Container maxWidth="sm">
      <Paper elevation={3} sx={{ p: 4, mt: 8 }}>
        <Typography variant="h4" align="center">Login</Typography>
        {error && <Alert severity="error">{error}</Alert>}
        <Box component="form" onSubmit={handleLogin} sx={{ mt: 2 }}>
          <TextField fullWidth name="username" label="Username" value={form.username} onChange={handleChange} required />
          <TextField fullWidth name="password" label="Password" type="password" value={form.password} onChange={handleChange} required sx={{ mt: 2 }} />
          <Button fullWidth type="submit" variant="contained" sx={{ mt: 3 }}>Login</Button>
        </Box>
      </Paper>
    </Container>
  );
};

export default Login;
