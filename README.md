### How to use?
+ Add lzrlgl.jar to depencies/put into a one dir with lazurite interpreter
+ type
```lua
using "reflection"
JUpload("lzrlgl.jar", "com.monsler.lzrlgl")
```
or
```lua
using "lzrlgl.jar" "com.monsler.lzrlgl"
```
+ Now you can use the library in your code!
### Example
```cpp
using "reflection"

System = JClass("java.lang.System")
JUpload("lzrlgl.jar", "com.monsler.lzrlgl")

// GLFW initialization
if (!glfw.init()) {
    print("Error initializing glfw")
}


// Creating a window
glfw.defaultWindowHints()
glfw.windowHint(glfw.VISIBLE, glfw.FALSE)
window = glfw.createWindow(500, 400, "Lazurite Lightweight Game Library")

// Setup a window
glfw.makeContextCurrent(window)
glfw.showWindow(window)
glfw.swapInterval(1)

// Translate the window to center of the primary monitor
monitor = glfw.getVideoMode(glfw.getPrimaryMonitor())
glfw.setWindowPos(window, monitor.width/2-250, monitor.height/2-200)

// Enable v-sync
gl.createCapabilities()

// Setup the dimensions of the window
gl.matrixMode(gl.PROJECTION)
gl.viewport(0, 0, 500, 400)
gl.loadIdentity()

gl.frustum(-(500/400), (500/400), -1.0, 1.0, 2.0, 100.0)
// Window loop
while(!glfw.windowShouldClose(window)) {
    gl.clear(gl.COLOR_BUFFER_BIT | gl.DEPTH_BUFFER_BIT)

    gl.begin(gl.QUADS)
    gl.color3d(1, 0, 0)
    gl.vertex3f(-1f, -1f, -10f)
    gl.color3d(0, 0, 1)
    gl.vertex3f(1f, -1f, -10f)
    gl.color3d(0, 1, 0)
    gl.vertex3f(1f, 1f, -10f)
    gl.color3d(1, 1, 1)
    gl.vertex3f(-1f, 1f, -10f)
    gl.end()

    glfw.swapBuffers(window)
    glfw.pollEvents()
}

// Terminate GLFW
glfw.terminate()
```
<br>
Compiled result:<br>

![image](https://github.com/Monsler/lzrlgl/assets/105060825/03e61df5-041e-46fd-9a3c-dc1f7c6628c0)

