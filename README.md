# Minecraft LAN Broadcaster documentation
_I believe that like me, you don't like huge documentation to explain the simple things, so I'll try to be as direct as possible._

### In the servers.txt file, just add the following properties:
> (MOTD of your server); (your server's port number) <br/>

### Examples:
> \# Comment line <br/>
> My server 1 MOTD and it's port; 25565 <br/>
> §a $\color{green}{\textsf{Colored server 2}}$ §9 $\color{blue}{\textsf{MOTD}}$; 25566 <br/>
> This is my 3th server with a different port; 4002 <br/>

This information is enough for the project to detect the server on your local machine and collect other information such as the game version, the number of online players and the maximum possible players.

## Important things
### This project only reads information from the server on your **LOCAL MACHINE**. This means that the project needs to run on the same machine as your server.
**(i) information MUST be separated by semicolons. Otherwise the project will not be able to read the information correctly**

**(i) It is possible to comment a line by adding the # symbol at the beginning**
