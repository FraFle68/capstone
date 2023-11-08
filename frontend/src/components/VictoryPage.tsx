import "./VictoryPage.css"
import axios from "axios";
import {useNavigate} from "react-router-dom";

type VictoryPageProbs = {
    user?: string
}

export default function VictoryPage(props: VictoryPageProbs) {

    const navigate = useNavigate()

    axios.delete("/api/game/" + props.user)

    function restartGame() {
        navigate("/gamepage")
    }

    return (
        <>
            <div className="victoryPage">
                <div className="mainFrame">
                    <div className="victoryPageContent">
                        <div>
                            <h1>Victory !!!</h1>
                            <h2>
                                You found the Potion of Power and therefore you are able to safe the world.
                            </h2>
                            <button onClick={restartGame}>Start new Game</button>
                        </div>
                        <div>

                        </div>
                    </div>
                </div>
            </div>
        </>
    )
}