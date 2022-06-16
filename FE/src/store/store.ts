import { configureStore } from '@reduxjs/toolkit';
import authReducer, { authStateType } from './authStore';

export type RootStateType = {
  auth: authStateType;
};

const store = configureStore({
  reducer: {
    auth: authReducer,
  },
});

export default store;
