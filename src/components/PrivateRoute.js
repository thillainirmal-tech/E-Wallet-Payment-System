import React from 'react';
import { Navigate, useLocation } from 'react-router-dom';

// Protects routes from unauthenticated access
const PrivateRoute = ({ children }) => {
  const token = localStorage.getItem('token');
  const location = useLocation();

  // If token exists, render the children, else redirect to login
  return token ? (
    children
  ) : (
    <Navigate to="/login" state={{ from: location }} replace />
  );
};

export default PrivateRoute;
