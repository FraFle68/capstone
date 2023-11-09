import {useNavigate, useParams} from "react-router-dom";
import axios from "axios";

type changePositionProps = {
    user?: string
}

export default function ChangePosition(props: changePositionProps) {

    const navigate = useNavigate()
    const { params } = useParams()
    const position: Vector2d = {x: 0, y: 0}

    if (params) {
        position.x = parseInt(params.split("-")[0])
        position.y = parseInt(params.split("-")[1])
    }

    axios.put("/api/game/changeposition/" + props.user, position)
        .then(() =>navigate("/gamepage"))
        .catch((error) => {
            alert('Fehler:' + error.response.data)
            navigate("/gamepage" )
        })

    return (
        <>
            <div className="gamePage">
                <div className="gameBorder">
                </div>
            </div>
        </>
    )
}