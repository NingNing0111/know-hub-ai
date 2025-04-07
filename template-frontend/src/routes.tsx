import { createBrowserRouter } from "react-router-dom";
import App from "./App";
import Home from "./pages/Home";
import LoginPage from "./pages/Login";

export const router = createBrowserRouter([
  {
    path: "/",
    element: <App />,
    children: [{ path: "home", element: <Home /> }],
  },
  {
    path: "/login",
    element: <LoginPage />,
  },
]);
