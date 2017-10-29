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
. SPACE 
X WALL
S SAND
G GRASS
V VICTORY
```

#### Example map
```
XXXXXXXXXXXXXXXXXXXXXXX
X.X.XXX.X.XXXSS.S.XXS.X
X..XG.XSXXX..SS.GXS.S.X
XSX.XX..XGG...GXSX.X..X
X.SX..XXXSX..G.SGGSXGXX
XX...XXXSXX.XGXS.GXXX.X
XX.XXX.SG.S..XS....X..X
XS.S.XSGGG.GXG.X.XXGX.X
XSG.SS...X...X.X.X.XGXX
X.S.XX.XXG.G.XS.SXG.XGX
X.GX.SGX..XS.S.GX.G.GXX
X.G..SS...XXSS.X.S.SX.X
XS.XXX.GSSXGGSG.X.XG.GX
XSXG..S.X.XXXGXXG.X.XXX
XX.X.X..S.X.....XS.XSXX
XXG.XSGSXGX..S.SXXSXSXX
XG.XX..SSXXS.X.X..SX.XX
XS.XGS.S..SS..XSGXSXG.X
XGXXG..GGXX.X.XXS.X..SX
XX.XXX.XGXXSXSXG.XX.X.X
XGG.X.X.X....SG.XG.X.XX
X..XX.X.X..GSGXX.X..SVX
XXXXXXXXXXXXXXXXXXXXXXX
```

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