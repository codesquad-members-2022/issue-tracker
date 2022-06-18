import React, { useReducer } from "react";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import { AuthContext } from "./components/context/AuthContext";
import SignIn from "./components/signIn/signIn";
import EnhancedTable from "./components/issueList/issueList.tsx";
import { initialState, reducer } from "./store/init";

function App() {
    const [state, dispatch] = useReducer(reducer, initialState);

    return (
        <AuthContext.Provider
            value={{
                state,
                dispatch,
            }}
        >
            <Router>
                <Routes>
                    <Route path="/" element={<SignIn />} />
                    <Route path="/list" element={<EnhancedTable />} />
                </Routes>
            </Router>
            {/* <SignIn /> */}
        </AuthContext.Provider>
    );
}

export default App;
