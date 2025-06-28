import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import {
  Container,
  TextField,
  Button,
  Typography,
  Paper,
  Box,
  Alert,
  MenuItem,
} from '@mui/material';
import { registerUser } from '../services/auth';
import { toast } from 'react-toastify';

const Register = () => {
  const [form, setForm] = useState({
    username: '',
    password: '',
    email: '',
    role: 'USER',
  });
  const [error, setError] = useState('');
  const navigate = useNavigate();

  const handleChange = (e) => {
    setForm(prev => ({ ...prev, [e.target.name]: e.target.value }));
  };

  const handleRegister = async (e) => {
    e.preventDefault();
    try {
      const res = await registerUser(form);
      toast.success(res.data); // ✅ backend returns a success message
      setError('');
      navigate('/');
    } catch (err) {
      setError(err.response?.data || '❌ Registration failed');
    }
  };

  return (
    <Container maxWidth="sm">
      <Paper elevation={3} sx={{ p: 4, mt: 8 }}>
        <Typography variant="h4" align="center">Register</Typography>
        {error && <Alert severity="error" sx={{ mt: 2 }}>{error}</Alert>}

        <Box component="form" onSubmit={handleRegister} sx={{ mt: 2 }}>
          <TextField
            fullWidth
            name="username"
            label="Username"
            value={form.username}
            onChange={handleChange}
            required
          />
          <TextField
            fullWidth
            name="email"
            label="Email"
            value={form.email}
            onChange={handleChange}
            required
            sx={{ mt: 2 }}
          />
          <TextField
            fullWidth
            name="password"
            label="Password"
            type="password"
            value={form.password}
            onChange={handleChange}
            required
            sx={{ mt: 2 }}
          />
          <TextField
            fullWidth
            name="role"
            label="Role"
            select
            value={form.role}
            onChange={handleChange}
            sx={{ mt: 2 }}
          >
            <MenuItem value="USER">USER</MenuItem>
            <MenuItem value="ADMIN">ADMIN</MenuItem>
          </TextField>

          <Button fullWidth type="submit" variant="contained" sx={{ mt: 3 }}>
            Register
          </Button>
        </Box>
      </Paper>
    </Container>
  );
};

export default Register;
