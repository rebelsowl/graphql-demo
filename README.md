# Player Registration app with GraphQL

A Spring Boot application with maven using GraphQL. Registers player for a basketball team. A Player can be added, deleted and also all players in the team can be listed.

Player has a name, a surname and a position: Point guard(PG), Shooting guard(SG), Small forward(SF), Power forward(PF), Center(C)
Team can have maximum 5 players.


## build
Make sure you have [java 11](https://www.oracle.com/tr/java/technologies/javase/jdk11-archive-downloads.html) and [maven](https://maven.apache.org/download.cgi) installed and added to path.

```
mvn clean package
cd target
java -jar basketball-player-registration-0.0.1-SNAPSHOT.jar
```
You can interact with graphiql ui at http://localhost:8080/graphiql


## Usage examples
Open the graphql ui: http://localhost:8080/graphiql 

#### Add player
```
mutation addPlayer {
  addPlayer(player: {name: "testName", surname: "testSurname", position: C}) {
    id
    name
    surname
    position
  }
}
```

#### Delete player
```
mutation deletePlayer {
  deletePlayer(id: 1)
}
```

#### List all players
```
query getPlayers {
  getPlayers {
    id
    name
    surname
    position
  }
}
```
