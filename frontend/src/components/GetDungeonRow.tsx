import GetDungeonTile from "./GetDungeonTile.tsx";

type GetDungeonRowProbs = {
    position: Vector2d
    dungeonRow: number[]
    dungeonContentRow: number[]
}



export default function GetDungeonRow(props: GetDungeonRowProbs) {

    const counter: number[] = []

    for (let i = 0; i < 11; i++) {
        counter[i] = props.position.x - 5 + i
    }



    function CheckIndex(index: number) {
        if(index < 0) index = 0
        if(index > props.dungeonRow.length - 1) {
                index = props.dungeonRow.length - 1
        }
        return index
    }

    function getXPosition(index: number) {
        const linkPosition: Vector2d = {x: index, y: props.position.y}
        return linkPosition
    }

    return (
        <div style={{height: 64}} className="dungeonRow">
            {counter.map(index => <GetDungeonTile position={getXPosition(index)} tile={props.dungeonRow[CheckIndex(index)]} content={props.dungeonContentRow[CheckIndex(index)]}/>)}
        </div>
    )
}