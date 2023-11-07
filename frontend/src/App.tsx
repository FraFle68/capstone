import './App.css'
import {Route, Routes} from "react-router-dom";
import LandingPage from "./components/LandingPage.tsx";
import RegistrationPage from "./components/RegistrationPage.tsx";
import CharacterPage from "./components/CharacterPage.tsx";
import {useState} from "react";
import axios from "axios";
import Game from "./components/Game.tsx";
import ChangePosition from "./components/ChangePosition.tsx"
import VictoryPage from "./components/VictoryPage.tsx";
import GetItem from "./components/GetItem.tsx";

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
                <Route path="/gamepage" element={<Game user={user}/>}/>
                <Route path="/changeposition/:params" element={<ChangePosition user={user}/>}/>
                <Route path="/getitem/:params" element={<GetItem user={user}/>}/>
                <Route path="/victory" element={<VictoryPage/>}/>
            </Routes>
        </>
    )
}

export default App
