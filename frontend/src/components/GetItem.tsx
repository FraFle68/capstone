import {useNavigate, useParams} from "react-router-dom";
import axios from "axios";

type getItemProps = {
    user?: string
}

export default function GetItem(props: getItemProps) {

    const navigate = useNavigate()
    const { params } = useParams()

    let item: number = 0
    const position: Vector2d={x: 0, y: 0}

    if (params) {
        item = parseInt(params.split("-")[0])
        position.x = parseInt(params.split("-")[1])
        position.y = parseInt(params.split("-")[2])
        console.log(item)
    }

    axios.put("/api/game/getitem/" + props.user, position)
        .then(() => navigate("/gamepage"))
        .catch((error) => {
            alert('Fehler:' + error.response.data)
            navigate("/gamepage" )
        })

    return (
        <>Waiting for the other players</>
    )


}