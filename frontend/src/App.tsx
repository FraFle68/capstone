import './App.css'
import {Route, Routes} from "react-router-dom";
import LandingPage from "./components/LandingPage.tsx";
import RegistrationPage from "./components/RegistrationPage.tsx";
import CharacterPage from "./components/CharacterPage.tsx";

function App() {
  return (
    <>
      <Routes>
          <Route path="/" element={<LandingPage/>}/>
          <Route path="/registration" element={<RegistrationPage/>}/>
          <Route path="/characterPage" element={<CharacterPage/>}/>
      </Routes>
    </>
  )
}

export default App
