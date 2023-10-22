import './App.css'
import {Route, Routes} from "react-router-dom";
import LandingPage from "./components/LandingPage.tsx";
import RegistrationPage from "./components/RegistrationPage.tsx";
import CharacterPage from "./components/CharacterPage.tsx";
import {useState} from "react";
import axios from "axios";

function App() {

    const [user, setUser] = useState<string>()

    function me() {
        axios.get("/api/users/me")
            .then(response => {setUser(response.data)})

    }

    me()

    return (
        <>
            <Routes>
              <Route path="/" element={<LandingPage user={user}/>}/>
              <Route path="/registration" element={<RegistrationPage user={user}/>}/>
              <Route path="/characterpage" element={<CharacterPage user={user}/>}/>
            </Routes>
        </>
    )
}

export default App
