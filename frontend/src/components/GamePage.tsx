import {useEffect, useState} from "react";
import axios from "axios";
import GetDungeonRow from "./GetDungeonRow.tsx";
import {useNavigate} from "react-router-dom";

type GameProps = {
    user?: string
}
export default function GamePage(props: GameProps) {

    const [dungeon, setDungeon] = useState<Dungeon>()
    const navigate = useNavigate()
    const fetchDungeon = () => {
        axios.get("/api/game/" + props.user)
            .then(response => {
                setDungeon(response.data.dungeon)
            })
            .catch(error => {
                console.error('Error fetching data:', error)
            })
    }

    useEffect(() => {
        fetchDungeon()
    }, [])


    function resetDungeon() {
        axios.delete("/api/game/" + props.user)
            .then(() => {fetchDungeon()})
        navigate("/gamepage")
    }

    const helperArray: number[] = []

    if (dungeon) {
        for (let i = 0; i < dungeon.tileMap.length; i++) {
            helperArray[i] = i;
        }
    }

    return (
        <div style={{width: 3300}} className="dungeonColumn">
            {dungeon && helperArray.map(index => <GetDungeonRow position={dungeon.position} dungeonRow={dungeon.tileMap[index]} dungeonContentRow={dungeon.contentMap[index]}/>)}
            <button onClick={resetDungeon}>New Dungeon</button>
        </div>
    )

}