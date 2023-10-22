import "./CharacterPage.css"
import imageToAdd from "./../assets/fg4_borders_insetBlack.png"
import axios from "axios";
import {ChangeEvent, useEffect, useState} from "react";
import {useNavigate} from "react-router-dom";

type Props = {
    user?: string;
}

export default function CharacterPage(props: Props) {
    const [avatar, setAvatar] = useState<Avatar>()
    const [avatarNameCharacterPage, setAvatarNameCharacterPage] = useState("")
    const [jobCharacterPage, setJobCharacterPage] = useState("")
    const navigate = useNavigate()
    function startGame() {
        //
    }

    const fetchData = () => {
        axios.get("/api/character/" + props.user)
            .then(response => {
                setAvatar(response.data)
                setAvatarNameCharacterPage(response.data.name)
                setJobCharacterPage(response.data.job)
            })
            .catch(error => {
                console.error('Error fetching data:', error)
            })
    }

    useEffect(() => {
        fetchData()
    }, [])


    if (avatar === null) navigate("/registration")



    function saveCharacter(newAvatar: NewAvatar) {

        axios.put("/api/character/edit", newAvatar)
            .then(() => fetchData())
            .catch((error) => {
                alert('Fehler:' + error.response.data)
                navigate("/")
            })

    }

    function onFormSubmit() {
        const newAvatar: NewAvatar = {
            id: props.user,
            name: avatarNameCharacterPage,
            job: jobCharacterPage
        }
        saveCharacter(newAvatar)
    }

    function onNameChange(event: ChangeEvent<HTMLInputElement>) {
        setAvatarNameCharacterPage(event.target.value)

    }

    function onJobChange(event: ChangeEvent<HTMLInputElement>) {
        setJobCharacterPage(event.target.value)
    }

    return (
        <div className="characterPage">
            <div className="characterPictureFrame">
                <div className="characterPageGreeting">
                    <div>
                        <h2>Adventurer Licence</h2>
                    </div>

                    <div className="cardBoxRow">
                        <img alt="" typeof="image/png" src={imageToAdd}/>
                        <div className="cardBoxColumn">
                            <div>Name: <input type="text" name="name" value={avatarNameCharacterPage} onChange={onNameChange}/></div>
                            <div>Titel: {avatar?.title}</div>
                        </div>
                        <div className="cardBoxColumn">

                            <div><input type="radio" checked={jobCharacterPage === "warrior"} name="job" value="warrior" onChange={onJobChange}/>Warrior</div>
                            <div><input type="radio" checked={jobCharacterPage === "mage"} name="job" value="mage" onChange={onJobChange}/>Mage</div>
                        </div>

                    </div>
                    <div className="cardBoxRow">
                        <div>
                            Level: 1<br/>
                            Strength: {avatar?.strength}<br/>
                            Intelligence: {avatar?.intelligence}
                        </div>
                        <div>
                            Experience: {avatar?.experience}<br/>
                            Weapon: {avatar?.weapon}<br/>
                            Armor: {avatar?.armor}
                        </div>
                    </div>
                    <div style={{textAlign: "end"}}><button onClick={onFormSubmit}>save</button></div>

                </div>
            </div>
            <div className="characterPageButton">
                <button disabled={true} className= "disabled greenButton" onClick={startGame}>Enter<br/><br/>Dungeon</button>
            </div>
        </div>
    )
}