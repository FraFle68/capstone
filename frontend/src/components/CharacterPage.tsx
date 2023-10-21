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

                    <div className="cardBoxRow">
                        <img alt="" typeof="image/png" src={imageToAdd}/>
                        <div className="cardBoxColumn">
                            <div>Name: <input type="text" name="name" value="Gandalf"/></div>
                            <div>Titel: Nobody</div>
                        </div>
                        <div className="cardBoxColumn">
                            <div><input type="radio" defaultChecked={false} name="job" value="warrior"/>Warrior</div>
                            <div><input type="radio" defaultChecked={true} name="job" value="mage"/>Mage</div>
                        </div>

                    </div>
                    <div className="cardBoxRow">
                        <div>
                            Level: 1<br/>
                            Strength: 5<br/>
                            Intelligence: 7
                        </div>
                        <div>
                            Experience: 0<br/>
                            Weapon: Sword<br/>
                            Armor: Cloth
                        </div>
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