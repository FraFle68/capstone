export default function LandingPage() {
    function login() {
        const host: string = window.location.host === 'localhost:5173' ? 'http://localhost:8080' : window.location.host
        window.open(host + '/oauth2/authorization/github', '_blank')
    }

    return (
        <div className="landingPage">
            <div className="landingPageGreetingFrame">
                <div className="landingPageGreeting">
                    <h1>Potion of Power</h1>
                    <p>
                        The known world is in trouble. Orcs are attacking the free races,
                        elves and dwarves are fighting each other and nature is preparing
                        for the apocalypse.
                    </p>
                    <p>
                        There seems to be nobody who is able to stop the chaos.</p>
                    <p>
                        But there is hope. Rumors are circulating about a potion
                        of power hidden in the dungeon of an ancient wizard. They promise that the
                        person who drinks this potion will be able to save the world.
                    </p>
                    <p>
                        You stand in front of this dungeon, determined to obtain this
                        potion, whatever the cost may be.
                    </p>
                </div>
            </div>
            <div className="landingPageButton">
                <button className="greenButton" onClick={login}>Login<br/>with<br/>GitHub</button>
            </div>
        </div>
    )
}