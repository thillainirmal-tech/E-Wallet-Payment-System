import React, { useEffect, useState } from 'react';
import {
  Container,
  Typography,
  TextField,
  Button,
  Box,
  Grid,
  Paper,
  List,
  ListItem,
  ListItemText,
  Divider,
  Alert
} from '@mui/material';
import { useNavigate } from 'react-router-dom';
import { getTransactionHistory, deposit, withdraw, transfer } from '../services/transaction';
import { getBalance } from '../services/wallet'; // ✅ Corrected import

const Dashboard = () => {
  const [balance, setBalance] = useState(0);
  const [amount, setAmount] = useState('');
  const [recipient, setRecipient] = useState('');
  const [transactions, setTransactions] = useState([]);
  const [error, setError] = useState('');

  const navigate = useNavigate();
  const token = localStorage.getItem('token');

  useEffect(() => {
    if (!token) {
      navigate('/');
    } else {
      loadBalance();
      loadTransactions();
    }
  }, []);

  const loadBalance = () => {
    getBalance()
      .then(res => {
        console.log("Balance response:", res.data); // ✅ Debug log
        const value = typeof res.data === 'number' ? res.data : parseFloat(res.data);
        setBalance(isNaN(value) ? 0 : value);
      })
      .catch(err => {
        console.error("Failed to load balance:", err); // ✅ Debug error
        setError('❌ Failed to fetch balance');
      });
  };

  const loadTransactions = () => {
    getTransactionHistory()
      .then(res => setTransactions(res.data))
      .catch(() => setError('❌ Failed to load transaction history'));
  };

  const handleTransaction = async (type) => {
    const amt = parseFloat(amount);
    if (!amt || amt <= 0) {
      setError('⚠️ Enter a valid amount');
      return;
    }

    try {
      if (type === 'DEPOSIT') await deposit(amt);
      else if (type === 'WITHDRAW') await withdraw(amt);
      else if (type === 'TRANSFER') {
        if (!recipient.trim()) {
          setError('⚠️ Enter recipient username');
          return;
        }
        await transfer(recipient, amt);
      }

      setAmount('');
      setRecipient('');
      setError('');
      loadBalance();
      loadTransactions();
    } catch (err) {
      console.error(`${type} failed`, err); // ✅ Debug
      setError(`❌ ${type} failed`);
    }
  };

  const handleLogout = () => {
    localStorage.removeItem('token');
    navigate('/');
  };

  return (
    <Container maxWidth="md">
      <Paper elevation={3} sx={{ padding: 4, mt: 5 }}>
        <Typography variant="h4" gutterBottom>Dashboard</Typography>

        {error && <Alert severity="error" sx={{ mb: 2 }}>{error}</Alert>}

        <Typography variant="h6" sx={{ mt: 2 }}>
          Wallet Balance: ₹{Number(balance || 0).toFixed(2)}
        </Typography>

        <Grid container spacing={2} sx={{ mt: 2 }}>
          <Grid item xs={12} sm={4}>
            <TextField
              label="Amount"
              fullWidth
              type="number"
              value={amount}
              onChange={(e) => setAmount(e.target.value)}
              required
            />
          </Grid>

          <Grid item xs={12} sm={4}>
            <TextField
              label="Recipient (for transfer)"
              fullWidth
              value={recipient}
              onChange={(e) => setRecipient(e.target.value)}
              disabled={!amount}
            />
          </Grid>

          <Grid item xs={12} sm={4}>
            <Box display="flex" flexDirection="column" gap={1}>
              <Button variant="contained" color="primary" onClick={() => handleTransaction('DEPOSIT')}>
                Deposit
              </Button>
              <Button variant="contained" color="secondary" onClick={() => handleTransaction('WITHDRAW')}>
                Withdraw
              </Button>
              <Button variant="contained" color="success" onClick={() => handleTransaction('TRANSFER')}>
                Transfer
              </Button>
            </Box>
          </Grid>
        </Grid>

        <Button variant="outlined" color="error" onClick={handleLogout} sx={{ mt: 3 }}>
          Logout
        </Button>

        <Typography variant="h5" sx={{ mt: 4 }}>
          Transaction History
        </Typography>

        <List>
          {transactions.map((txn) => (
            <React.Fragment key={txn.id}>
              <ListItem>
                <ListItemText
                  primary={`${txn.type} - ₹${txn.amount}`}
                  secondary={`${txn.description} • ${new Date(txn.timestamp).toLocaleString()}`}
                />
              </ListItem>
              <Divider />
            </React.Fragment>
          ))}
        </List>
      </Paper>
    </Container>
  );
};

export default Dashboard;
