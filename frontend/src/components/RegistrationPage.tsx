import './RegistrationPage.css'

export default function RegistrationPage() {
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
                            <form><h2>
                                <label>Your avatars name: </label>
                                <input type="text" name="name"/>
                                Job:
                                <input type="radio" name="job" value="warrior"/>Warrior 
                                <input type="radio" name="job" value="mage"/>Mage</h2>
                                <button>save</button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </>
    )
}