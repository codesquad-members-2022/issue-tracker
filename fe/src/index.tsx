import React from "react";
import { createRoot } from "react-dom/client";

import GlobalStyle from "styles/globalStyle";
import App from "./App";

const root = createRoot(document.getElementById("root") as HTMLElement);
root.render(
  <React.StrictMode>
    <GlobalStyle />
    <App />
  </React.StrictMode>,
);
