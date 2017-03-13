# GEOpenGL

## Java Project Setup
Open the Git Bash and navigate to the workspace folder.<code>cd [Path to workspace]</code>

Clone the repository.<code>git clone https://github.com/musdasch/GEOpenGL.git</code>

Create a folder for the libraries.<code>mkdir lib; mkdir lib/jar; mkdir lib/native</code>

Download the slick-util.jar from http://slick.ninjacave.com/slick-util.jar

Copy the slick-util.jar in to the <code>lib/jar/</code> folder.

Download the lwjgl from https://sourceforge.net/projects/java-game-lib/?source=typ_redirect

Open the lwjgl-2.9.3.zip and copy the folowing jar files from <code>lwjgl-2.9.3/jar/</code> in to the <code>lib/jar/</code> folder:
 - <code>lwjgl.jar</code>
 - <code>lwjgl_util.jar</code>
 - <code>lwjgl_test.jar</code>
 - <code>lwjgl-debug.jar</code>

Copy also all files from <code>lwjgl-2.9.3/native/[your OS]/</code> in to <code>lib/native</code>

Start Eclipse and creat a new Java Project with the name <code>GEOpenGL</code>.

After that you have to add the libs and the natives in the buildpath.
