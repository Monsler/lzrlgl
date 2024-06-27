package com.monsler.lzrlgl

import com.kingmang.lazurite.core.Types
import com.kingmang.lazurite.runtime.values.LzrValue
import java.util.Objects

class LzrObject(of: Any) : LzrValue {
    val obj = of

    override fun compareTo(other: LzrValue?): Int {
        return if(Objects.equals(this, other)) 1 else 0
    }

    override fun raw(): Any {
        return obj
    }

    override fun asInt(): Int {
        return obj as Int
    }

    override fun asNumber(): Double {
        return obj as Double
    }

    override fun asString(): String {
        return obj as String
    }

    override fun asArray(): IntArray {
        return obj as IntArray
    }

    override fun type(): Int {
        return Types.OBJECT
    }
}