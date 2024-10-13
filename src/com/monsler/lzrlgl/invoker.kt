package com.monsler.lzrlgl

import com.kingmang.lazurite.core.Arguments
import com.kingmang.lazurite.core.Function
import com.kingmang.lazurite.libraries.Library
import com.kingmang.lazurite.runtime.Variables
import com.kingmang.lazurite.runtime.values.LzrFunction
import com.kingmang.lazurite.runtime.values.LzrMap
import com.kingmang.lazurite.runtime.values.LzrNumber
import org.lwjgl.glfw.Callbacks.glfwFreeCallbacks
import org.lwjgl.glfw.GLFW.*
import org.lwjgl.glfw.GLFWErrorCallback
import org.lwjgl.opengl.GL.createCapabilities
import org.lwjgl.opengl.GL11.*
import java.io.PrintStream


class invoker : Library {
    override fun init() {
        val glfwTable = LzrMap(32)
        val glTable = LzrMap(32)

        glfwTable["init"] = Function { if(glfwInit()) LzrNumber.ONE else LzrNumber.ZERO }
        glfwTable["createWindow"] = Function { args ->
            Arguments.check(3, args.size)

            LzrObject(glfwCreateWindow(args[0].asInt(), args[1].asInt(), args[2].asString(), 0, 0))
        }
        glfwTable["defaultWindowHints"] = Function {
            glfwDefaultWindowHints()
            LzrNumber.MINUS_ONE
        }
        glfwTable["windowHint"] = Function { args ->
            Arguments.check(2, args.size)
            glfwWindowHint(args[0].asInt(), args[1].asInt())
            LzrNumber.MINUS_ONE
        }
        glfwTable["TRUE"] = LzrNumber(GLFW_TRUE)
        glfwTable["FALSE"] = LzrNumber(GLFW_FALSE)
        glfwTable["VISIBLE"] = LzrNumber(GLFW_VISIBLE)
        glfwTable["RESIZABLE"] = LzrNumber(GLFW_RESIZABLE)
        glfwTable["showWindow"] = Function { args ->
            Arguments.check(1, args.size)
            glfwShowWindow(args[0].raw() as Long)
            LzrNumber.ZERO
        }
        glfwTable["windowShouldClose"] = Function { args ->
            Arguments.check(1, args.size)

            if(glfwWindowShouldClose(args[0].raw() as Long)) LzrNumber.ONE else LzrNumber.ZERO
        }
        glfwTable["makeContextCurrent"] = Function { args ->
            Arguments.check(1, args.size)
            glfwMakeContextCurrent(args[0].raw() as Long)
            LzrNumber.MINUS_ONE
        }
        glfwTable["pollEvents"] = Function {
            glfwPollEvents()
            LzrNumber.ONE
        }
        glTable["clear"] = Function { args ->
            Arguments.check(1, args.size)
            glClear(args[0].asInt())
            LzrNumber.ONE
        }
        glTable["clearColor"] = Function { args ->
            Arguments.check(4, args.size)
            glClearColor(args[0].raw() as Float, args[1].raw() as Float, args[2].raw() as Float, args[3].raw() as Float)
            LzrNumber.ONE
        }
        glTable["createCapabilities"] = Function {
            createCapabilities()
            LzrNumber.MINUS_ONE
        }
        glfwTable["terminate"] = Function {
            glfwTerminate()
            LzrNumber.MINUS_ONE
        }
        glfwTable["swapInterval"] = Function { args ->
            Arguments.check(1, args.size)
            glfwSwapInterval(args[0].asInt())
            LzrNumber.ONE
        }
        glfwTable["swapBuffers"] = Function { args ->
            Arguments.check(1, args.size)
            glfwSwapBuffers(args[0].raw() as Long)
            LzrNumber.ONE
        }
        glfwTable["getPrimaryMonitor"] = Function {
            LzrNumber(glfwGetPrimaryMonitor())
        }
        glfwTable["getVideoMode"] = Function { args ->
            Arguments.check(1, args.size)
            val out = LzrMap(2)
            val vidmode = glfwGetVideoMode(args[0].raw() as Long)!!
            out["width"] = LzrNumber(vidmode.width())
            out["height"] = LzrNumber(vidmode.height())
            out["redBits"] = LzrNumber(vidmode.redBits())
            out["greenBits"] = LzrNumber(vidmode.greenBits())
            out["blueBits"] = LzrNumber(vidmode.blueBits())

            out
        }
        glfwTable["setWindowPos"] = Function { args ->
            Arguments.check(3, args.size)
            glfwSetWindowPos(args[0].raw() as Long, args[1].asInt(), args[2].asInt())

            LzrNumber.MINUS_ONE
        }
        glTable["begin"] = Function { args ->
            Arguments.check(1, args.size)
            glBegin(args[0].asInt())
            LzrNumber.MINUS_ONE
        }
        glTable["end"] = Function {
            glEnd()
            LzrNumber.MINUS_ONE
        }
        glTable["PROJECTION"] = LzrNumber(GL_PROJECTION)
        glTable["matrixMode"] = Function { args ->
            Arguments.check(1, args.size)
            glMatrixMode(args[0].asInt())
            LzrNumber.MINUS_ONE
        }
        glTable["QUADS"] = LzrNumber(GL_QUADS)
        glTable["COLOR_BUFFER_BIT"] = LzrNumber(GL_COLOR_BUFFER_BIT)
        glTable["DEPTH_BUFFER_BIT"] = LzrNumber(GL_DEPTH_BUFFER_BIT)
        glfwTable["MAXIMIZED"] = LzrNumber(GLFW_MAXIMIZED)
        glfwTable["DECORATED"] = LzrNumber(GLFW_DECORATED)
        glTable["MODELVIEW"] = LzrNumber(GL_MODELVIEW)
        glfwTable["pushMatrix"] = Function {
            glPushMatrix()
            LzrNumber.ONE
        }
        glfwTable["popMatrix"] = Function {
            glPopMatrix()
            LzrNumber.ONE
        }
        glTable["rotatef"] = Function { args ->
            Arguments.check(4, args.size)
            glRotatef(args[0].raw() as Float, args[1].raw() as Float, args[2].raw() as Float, args[3].raw() as Float)
            LzrNumber.ONE
        }
        glTable["rotated"] = Function { args ->
            Arguments.check(4, args.size)
            glRotated(args[0].asNumber(), args[1].asNumber(), args[2].asNumber(), args[3].asNumber())
            LzrNumber.ONE
        }
        glfwTable["popMatrix"] = Function {
            glPopMatrix()
            LzrNumber.ONE
        }
        glTable["color3d"] = Function {args ->
            Arguments.check(3, args.size)
            glColor3d(args[0].asNumber(), args[1].asNumber(), args[2].asNumber())
            LzrNumber.ONE
        }
        glTable["vertex3f"] = Function {args ->
            Arguments.check(3, args.size)
            glVertex3f(args[0].raw() as Float, args[1].raw() as Float, args[2].raw() as Float)
            LzrNumber.ONE
        }
        glTable["viewport"] = Function { args ->
            Arguments.check(4, args.size)
            glViewport(args[0].asInt(), args[1].asInt(), args[2].asInt(), args[3].asInt())
            LzrNumber.ONE
        }
        glTable["loadIdentity"] = Function {
            glLoadIdentity()
            LzrNumber.ONE
        }
        glTable["frustum"] = Function { args ->
            Arguments.check(6, args.size)
            glFrustum(args[0].asNumber(), args[1].asNumber(), args[2].asNumber(), args[3].asNumber(), args[4].asNumber(), args[5].asNumber())
            LzrNumber.ONE
        }
        glfwTable["freeCallbacks"] = Function { args ->
            Arguments.check(1, args.size)
            glfwFreeCallbacks(args[0].raw() as Long)
            LzrNumber.ONE
        }
        glfwTable["destroyWindow"] = Function { args ->
            Arguments.check(1, args.size)
            glfwDestroyWindow(args[0].raw() as Long)
            LzrNumber.ONE
        }
        glTable["vertex2f"] = Function { args ->
            Arguments.check(2, args.size)
            glVertex2f(args[0].raw() as Float, args[1].raw() as Float)
            LzrNumber.ONE
        }
        glTable["vertex2i"] = Function { args ->
            Arguments.check(2, args.size)
            glVertex2i(args[0].asInt(), args[1].asInt())
            LzrNumber.ONE
        }
        glTable["vertex2d"] = Function { args ->
            Arguments.check(2, args.size)
            glVertex2d(args[0].asNumber(), args[1].asNumber())
            LzrNumber.ONE
        }
        glTable["vertex3d"] = Function { args ->
            Arguments.check(3, args.size)
            glVertex3d(args[0].asNumber(), args[1].asNumber(), args[2].asNumber())
            LzrNumber.ONE
        }
        glTable["vertex3i"] = Function { args ->
            Arguments.check(2, args.size)
            glVertex2i(args[0].asInt(), args[1].asInt())
            LzrNumber.ONE
        }
        glfwTable["hideWindow"] = Function { args ->
            Arguments.check(1, args.size)
            glfwHideWindow(args[0].raw() as Long)
            LzrNumber.ONE
        }
        glfwTable["focusWindow"] = Function { args ->
            Arguments.check(1, args.size)
            glfwFocusWindow(args[0].raw() as Long)
            LzrNumber.ONE
        }
        glTable["clearDepth"] = Function { args ->
            Arguments.check(1, args.size)
            glClearDepth(args[0].asNumber())
            LzrNumber.MINUS_ONE
        }
        glTable["vertex4i"] = Function { args ->
            Arguments.check(4, args.size)
            glVertex4i(args[0].asInt(), args[1].asInt(), args[2].asInt(), args[3].asInt())
            LzrNumber.ONE
        }
        glTable["vertex4f"] = Function { args ->
            Arguments.check(4, args.size)
            glVertex4f(args[0].raw() as Float, args[1].raw() as Float, args[2].raw() as Float, args[3].raw() as Float)
            LzrNumber.ONE
        }
        glTable["vertex4d"] = Function { args ->
            Arguments.check(4, args.size)
            glVertex4d(args[0].asNumber(), args[1].asNumber(), args[2].asNumber(), args[3].asNumber())
            LzrNumber.ONE
        }
        val errorCallback = LzrMap(1)
        errorCallback["createPrint"] = Function { args ->
            GLFWErrorCallback.createPrint(args[0].raw()!! as PrintStream)
            LzrNumber.ZERO
        }
        glfwTable["setKeyCallback"] = Function { args ->
            Arguments.check(2, args.size)
            glfwSetKeyCallback(args[0].raw() as Long) { window: Long, key: Int, scancode: Int, action: Int, mods: Int ->
                (args[1] as LzrFunction).value.execute(
                    LzrNumber(window),
                    LzrNumber(key),
                    LzrNumber(scancode),
                    LzrNumber(action),
                    LzrNumber(mods)
                )
            }
            LzrNumber.MINUS_ONE
        }
        glfwTable["setWindowCloseCallback"] = Function { args ->
            Arguments.check(2, args.size)
            glfwSetWindowCloseCallback(args[0].raw() as Long) { window: Long ->
                (args[1] as LzrFunction).value.execute(
                    LzrNumber(window)
                )
            }
            LzrNumber.ONE
        }
        glfwTable["setWindowShouldClose"] = Function { args ->
            val win = args[0].raw() as Long
            val `is` = args[1].asInt()
            glfwSetWindowShouldClose(win, `is` == 1)
            LzrNumber.ONE
        }
        setKeys(glfwTable)

        Variables.define("GLFWErrorCallback", errorCallback)
        Variables.define("glfw", glfwTable)
        Variables.define("gl", glTable)
    }

    private fun setKeys(t: LzrMap) {
        t["KEY_ESCAPE"] = LzrNumber(GLFW_KEY_ESCAPE)
        t["KEY_Q"] = LzrNumber(GLFW_KEY_Q)
        t["KEY_A"] = LzrNumber(GLFW_KEY_A)
        t["KEY_B"] = LzrNumber(GLFW_KEY_B)
        t["KEY_C"] = LzrNumber(GLFW_KEY_C)
        t["KEY_D"] = LzrNumber(GLFW_KEY_D)
        t["KEY_E"] = LzrNumber(GLFW_KEY_E)
        t["KEY_F"] = LzrNumber(GLFW_KEY_F)
        t["KEY_G"] = LzrNumber(GLFW_KEY_G)
        t["KEY_H"] = LzrNumber(GLFW_KEY_H)
        t["KEY_I"] = LzrNumber(GLFW_KEY_I)
        t["KEY_J"] = LzrNumber(GLFW_KEY_J)
        t["KEY_K"] = LzrNumber(GLFW_KEY_K)
        t["KEY_L"] = LzrNumber(GLFW_KEY_L)
        t["KEY_M"] = LzrNumber(GLFW_KEY_M)
        t["KEY_N"] = LzrNumber(GLFW_KEY_N)
        t["KEY_O"] = LzrNumber(GLFW_KEY_O)
        t["KEY_P"] = LzrNumber(GLFW_KEY_P)
        t["KEY_R"] = LzrNumber(GLFW_KEY_R)
        t["KEY_S"] = LzrNumber(GLFW_KEY_S)
        t["KEY_T"] = LzrNumber(GLFW_KEY_T)
        t["KEY_U"] = LzrNumber(GLFW_KEY_U)
        t["KEY_V"] = LzrNumber(GLFW_KEY_V)
        t["KEY_W"] = LzrNumber(GLFW_KEY_W)
        t["KEY_X"] = LzrNumber(GLFW_KEY_X)
        t["KEY_Y"] = LzrNumber(GLFW_KEY_Y)
        t["KEY_Z"] = LzrNumber(GLFW_KEY_Z)
        t["RELEASE"] = LzrNumber(GLFW_RELEASE)
    }
}