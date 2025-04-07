import "./App.css";
// 路由出口组件将在此渲染子路由内容

import { Outlet } from "react-router-dom";

function App() {
  return (
    <div className="app-container">
      <Outlet />
    </div>
  );
}

export default App;
