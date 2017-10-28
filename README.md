# GUTS
### Motivation
This project was made for a hackathon (GUTS 2017), in 48 hours.
It is an asynchronous co-op game played over the internet.

### Contribution
Thank you for wanting to contribute to our project, 
simply clone the project to a local IDE, edit it,
and submit your changes as a pull request.

You may fork the project and use it as your own, if desired,
however, in doing this you **must** add a link to the original project;
 https://github.com/komodo108/GUTS

## Usage
### Creating maps
Use the following characters in the maps. 
New lines denote new rows and each character denotes a new column.
```
  SPACE (simply a spacebar)
X WALL
S SAND
G GRASS
```

#### Example map
```
XXXXXXXXXXXXXXXXXXX
X        X    SS  X
X        X   X S  X
XXXXX    X   X    X
X   X    X   XXXXXX
X   X    X   X    X
X   X    X   X GG X
X G          X GG X
X GG  X           X
X SS  XXXXXXXXXXXXX
X              GS X
XXXXXXXXXXXXXXXXXXX
```
In practice, these maps are generated randomly and will have more grass and sand.

### Asynchrony
Please note when using that the game is designed to be asynchronous, and as such when one thing
is edited in the client side/it may also need to be edited server side.

### Data Transmission
Data is transmitted in the form of packets;
```
PACKET_NAME
. . . DATA . . .
END
```
Please look at the codebase to see examples. 