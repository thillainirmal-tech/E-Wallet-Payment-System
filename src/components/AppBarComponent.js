import React from 'react';
import { AppBar, Toolbar, Typography, Button, Box } from '@mui/material';
import { Link, useNavigate } from 'react-router-dom';

const AppBarComponent = () => {
  const navigate = useNavigate();
  const token = localStorage.getItem('token');

  const handleLogout = () => {
    localStorage.removeItem('token');
    navigate('/');
  };

  return (
    <AppBar position="static" color="primary" elevation={2}>
      <Toolbar>
        <Typography variant="h6" sx={{ flexGrow: 1 }}>
          E-Wallet
        </Typography>

        <Box display="flex">
          {token ? (
            <>
              <Button color="inherit" component={Link} to="/dashboard" sx={{ mx: 1 }}>
                Dashboard
              </Button>
              <Button color="inherit" component={Link} to="/wallet" sx={{ mx: 1 }}>
                Wallet
              </Button>
              <Button color="inherit" onClick={handleLogout} sx={{ mx: 1 }}>
                Logout
              </Button>
            </>
          ) : (
            <>
              <Button color="inherit" component={Link} to="/" sx={{ mx: 1 }}>
                Login
              </Button>
              <Button color="inherit" component={Link} to="/register" sx={{ mx: 1 }}>
                Register
              </Button>
            </>
          )}
        </Box>
      </Toolbar>
    </AppBar>
  );
};

export default AppBarComponent;
