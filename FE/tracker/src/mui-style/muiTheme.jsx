import { ThemeProvider, createTheme } from "@mui/material";

export const matrixTheme = createTheme({
    palette: {
        primary: {
            light: "#47ff47",
            main: "#000000",
            contrastText: "ffffff",
        },
        secondary: {
            main: "#00FF41",
            // green: "#008F11",
            // darkgreen: "#003B00",
        },
    },
});

export const listTheme = createTheme({
    palette: {
        primary: {
            main: "#121212",
            light: "#f2f2f2",
        },
        neutral: {
            main: "#bdbdbd",
            dark: "212121",
        },
    },
});
