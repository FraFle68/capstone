import imageToAdd from "../assets/fg4_borders_stone-mini.png";
import leerFeld from "../assets/Leer.png";

type GetDungeonTileProbs = {
    tile: number
}
export default function GetDungeonTile(props: GetDungeonTileProbs) {
    console.log(props)

    return (
        <>
            {props.tile === 0 ? <img alt={"Tile"} src={leerFeld}/> : <img alt={"Tile"} src={imageToAdd}/>}
        </>
    )
}