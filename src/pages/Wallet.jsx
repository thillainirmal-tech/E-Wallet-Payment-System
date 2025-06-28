import React, { useEffect, useState } from 'react';
import {
  Container,
  Paper,
  Typography,
  Box,
  Alert
} from '@mui/material';
import { getBalance } from '../services/wallet';
import { toast } from 'react-toastify';

const Wallet = () => {
  const [balance, setBalance] = useState(0);
  const [error, setError] = useState('');

  useEffect(() => {
  getBalance()
    .then(res => {
      console.log("Balance raw response:", res);            // ✅ Entire Axios response
      console.log("Balance data type:", typeof res.data);   // ✅ Data type
      console.log("Balance value:", res.data);              // ✅ Value received

      const parsed = Number(res.data);
      if (isNaN(parsed)) {
        throw new Error("Invalid balance value");
      }
      setBalance(parsed);
    })
    .catch((err) => {
      console.error("Balance fetch failed:", err);
      setError('Failed to fetch balance');
      toast.error('❌ Failed to fetch balance');
    });
}, []);


  return (
    <Container maxWidth="sm">
      <Paper elevation={3} sx={{ padding: 4, marginTop: 8 }}>
        <Typography variant="h4" align="center">Wallet</Typography>

        {error && <Alert severity="error">{error}</Alert>}

        <Box sx={{ mt: 4, textAlign: 'center' }}>
          <Typography variant="h6">Current Balance</Typography>
          <Typography variant="h3" color="primary">₹{balance.toFixed(2)}</Typography>
        </Box>
      </Paper>
    </Container>
  );
};

export default Wallet;
