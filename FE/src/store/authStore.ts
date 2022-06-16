import { createSlice, PayloadAction } from '@reduxjs/toolkit';

export type authStateType = {
  isLogin: boolean;
  hasError: boolean;
  secureToken: string | null;
};

const initialAuthState: authStateType = {
  isLogin: false,
  hasError: false,
  secureToken: null,
};

const authSlice = createSlice({
  name: 'login',
  initialState: initialAuthState,
  reducers: {
    login: (state, action: PayloadAction<string>) => {
      state.isLogin = true;
      state.hasError = false;
      state.secureToken = action.payload;
    },
    logout: (state) => {
      state.isLogin = false;
      state.secureToken = null;
    },
    error: (state) => {
      state.hasError = true;
    },
  },
});

export const authActions = authSlice.actions;
export default authSlice.reducer;
