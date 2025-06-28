// service/wallet.js
import api from '../api/axios';

export const getBalance = () => {
  return api.get('/transaction/balance'); // ✅ Correct path (relative to /api base)
};

