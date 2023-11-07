import {useEffect, useState} from "react";
import axios from "axios";
import "./Game.css"
import GetDungeonRow from "./GetDungeonRow.tsx";

type GameProps = {
    user?: string
}

export default function Game(props: GameProps) {

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



    const counter: number[] = []
    console.log(dungeon)
    for (let i = 0; i < 11; i++) {
        if (dungeon) {
            counter[i] = dungeon.position.y - 5 + i;
        }
    }

    function CheckIndex(index: number) {
        if(index < 0) index = 0
        if(dungeon) {
            if(index > dungeon.tileMap.length - 1) {
                index = dungeon.tileMap.length - 1
            }
        }
        return index
    }

    return (
        <div>
            {dungeon && counter.map(index =>
                <GetDungeonRow position={{x: dungeon.position.x, y: CheckIndex(index)}} dungeonRow={dungeon.tileMap[CheckIndex(index)]} dungeonContentRow={dungeon.contentMap[CheckIndex(index)]}/>)}
        </div>
    )
}