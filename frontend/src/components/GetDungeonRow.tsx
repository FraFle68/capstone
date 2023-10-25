import GetDungeonTile from "./GetDungeonTile.tsx";

type GetDungeonRowProbs = {
    dungeonRow: number[]
}

export default function GetDungeonRow(props: GetDungeonRowProbs) {

    return (
        <div style={{height: 64}} className="dungeonRow">
            {props.dungeonRow.map(tile => <GetDungeonTile tile={tile} />)}
        </div>
    )
}