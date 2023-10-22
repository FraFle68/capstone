import './RegistrationPage.css'
import axios from "axios";
import {useNavigate} from "react-router-dom";
import {ChangeEvent, FormEvent, useState} from "react";

type Props = {
    user?: string;
}

export default function RegistrationPage(props: Props) {

    const [avatarName, setAvatarName] = useState("")
    const [job, setJob] = useState("")
    const navigate = useNavigate()
    function saveCharacter(newAvatar: NewAvatar) {

        axios.post("/api/character/new", newAvatar)
            .then(() => navigate("/characterpage"))
            .catch((error) => {
                alert('Fehler:' + error.response.data)
                navigate("/")
            })
    }

    function onFormSubmit(event: FormEvent<HTMLFormElement>) {
        event.preventDefault()
        const newAvatar: NewAvatar = {
            id: props.user,
            name: avatarName,
            job: job
        }
        saveCharacter(newAvatar)
    }

    function onNameChange(event: ChangeEvent<HTMLInputElement>) {
        setAvatarName(event.target.value)
    }

    function onJobChange(event: ChangeEvent<HTMLInputElement>) {
        setJob(event.target.value)
    }

    return (
        <>
            <div className="registrationPage">
                <div className="mainFrame">
                    <div className="registrationPageContent">
                        <div>
                            <h1>Welcome Adventurer</h1>
                            <h2>
                                You see this page because this seems to be your first visit.<br/><br/>
                                In order to proceed it is necessary to give some information.
                                Your Avatar needs a name and a job.
                                Please let us know these things.<br/><br/>
                                We save this so that you will never be asked again.<br/>
                                ( you will be able to change this later on your character page )
                            </h2>
                        </div>
                        <div>
                            <form onSubmit={onFormSubmit}><h2>
                                <label>Your avatars name: </label>
                                <input type="text" name="avatarName" onChange={onNameChange}/>
                                Job:
                                <input type="radio" name="job" value="warrior" onChange={onJobChange}/>Warrior
                                <input type="radio" name="job" value="mage" onChange={onJobChange}/>Mage</h2>
                                <button>save</button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </>
    )
}