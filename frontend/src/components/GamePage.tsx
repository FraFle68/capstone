import {useEffect, useState} from "react";
import axios from "axios";
import GetDungeonRow from "./GetDungeonRow.tsx";

type GameProps = {
    user?: string
}
export default function GamePage(props: GameProps) {

    const [dungeon, setDungeon] = useState<Dungeon>()
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

    console.log(dungeon)



    return (
        <div style={{width: 3300}} className="dungeonColumn">
            {dungeon && dungeon.tileMap.map(dungeonRow => <GetDungeonRow dungeonRow={dungeonRow}/>)}
        </div>
    )
}