import "./CharacterPage.css"
import imageToAdd from "./../assets/fg4_borders_insetBlack.png"

export default function CharacterPage() {
    function startGame() {

    }

    return (
        <div className="characterPage">
            <div className="characterPictureFrame">
                <div className="characterPageGreeting">
                    <div>
                        <h2>Adventurer Licence</h2>
                    </div>
                    <div>
                        <div>
                            <img alt="" typeof="image/png" src={imageToAdd}/>
                            Gandalf
                            <input type="radio" defaultChecked={false} name="job" value="warrior"/>Warrior
                            <input type="radio" defaultChecked={true} name="job" value="mage"/>Mage
                        </div>
                    </div>
                    <div>
                        There seems to be nobody who is able to stop the chaos.
                    </div>
                    <div style={{textAlign: "end"}}><button>save</button></div>

                </div>
            </div>
            <div className="characterPageButton">
                <button disabled={true} className= "disabled greenButton" onClick={startGame}>Enter<br/><br/>Dungeon</button>
            </div>
        </div>
    )
}