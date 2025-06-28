// 📁 src/services/transaction.js
import api from '../api/axios';

// ✅ Deposit funds
export const deposit = (amount) => {
  return api.post('/transaction/deposit', { amount });
};

// ✅ Withdraw funds
export const withdraw = (amount) => {
  return api.post('/transaction/withdraw', { amount });
};

// ✅ Transfer funds to another user
export const transfer = (recipientUsername, amount) => {
  return api.post('/transaction/transfer', {
    recipientUsername,
    amount,
  });
};

// ✅ Get wallet balance
export const getBalance = () => {
  return api.get('/wallet/balance');
};

// ✅ Get transaction history
export const getTransactionHistory = () => {
  return api.get('/transaction/history');
};
