import './App.css'
import {Route, Routes} from "react-router-dom";
import LandingPage from "./components/LandingPage.tsx";
import RegistrationPage from "./components/RegistrationPage.tsx";

function App() {
  return (
    <>
      <Routes>
          <Route path="/" element={<LandingPage/>}/>
          <Route path="/registration" element={<RegistrationPage/>}/>
      </Routes>
    </>
  )
}

export default App
