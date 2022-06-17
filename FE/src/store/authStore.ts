import { createSlice } from '@reduxjs/toolkit';

export type authStateType = {
  isLogin: boolean;
  hasError: boolean;
};

const initialAuthState: authStateType = {
  isLogin: localStorage.getItem('token') ? true : false,
  hasError: false,
};

const authSlice = createSlice({
  name: 'login',
  initialState: initialAuthState,
  reducers: {
    login: (state) => {
      state.isLogin = true;
      state.hasError = false;
    },
    logout: (state) => {
      state.isLogin = false;
    },
    error: (state) => {
      state.hasError = true;
    },
  },
});

export const authActions = authSlice.actions;
export default authSlice.reducer;
