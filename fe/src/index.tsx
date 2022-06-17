import React from "react";
import { createRoot } from "react-dom/client";

import { defaultTheme } from "styles/defaultStyle";
import { ThemeProvider } from "styled-components";
import GlobalStyle from "styles/globalStyle";
import App from "./App";

const root = createRoot(document.getElementById("root") as HTMLElement);
root.render(
  <React.StrictMode>
    <ThemeProvider theme={defaultTheme}>
      <GlobalStyle />
      <App />
    </ThemeProvider>
  </React.StrictMode>,
);
