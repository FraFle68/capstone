import imageToAdd from "../assets/fg4_borders_stone-mini.png";
import leerFeld from "../assets/Rough_Rock_1_Albedo.png";
import playerIcon from "../assets/fg4_iconsBrown_profile64.png";
import sword from "../assets/sword.png"
import potion from "../assets/fg4_iconsGold_potion64.png"
import "./GetDungeonTile.css"
import {Link} from "react-router-dom";

type GetDungeonTileProbs = {
    position: Vector2d
    tile: number
    content: number
}
export default function GetDungeonTile(props: GetDungeonTileProbs) {

    const linkAddressTile: string = "../changeposition/" + props.position.x + "-" + props.position.y
    const linkAddressItem: string = "../getitem/" + props.content + "-" + props.position.x + "-" + props.position.y
    const linkAddressPotion: string = "../victory/"

    return (
        <div className="imagePositioner">
            {props.tile === 0 ? <img alt={"Tile"} src={leerFeld}/> : <Link to={linkAddressTile}><img alt={"Tile"} src={imageToAdd}/></Link>}
            {props.content === 1 && <Link to={linkAddressItem}><img alt={"Tile"} title={"Sword +3 Attack"} src={sword}/></Link>}
            {props.content === 42 && <Link to={linkAddressPotion}><img alt={"Potion"} title={"Potion of Power"} src={potion}/></Link>}
            {props.content === 99 && <img alt={"Player"} src={playerIcon}/>}
        </div>
    )
}
