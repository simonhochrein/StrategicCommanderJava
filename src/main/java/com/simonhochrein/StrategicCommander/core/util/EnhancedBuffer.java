package com.simonhochrein.StrategicCommander.core.util;

import io.netty.buffer.ByteBuf;
import org.joml.Vector2f;
import org.joml.Vector3f;

import java.nio.charset.Charset;

public class EnhancedBuffer {
    private ByteBuf internalBuffer;

    public EnhancedBuffer(ByteBuf buffer) {
        internalBuffer = buffer;
    }

    public boolean readBoolean() {
        return internalBuffer.readBoolean();
    }

    public String readString() {
        int length = internalBuffer.readInt();
        return internalBuffer.readCharSequence(length, Charset.defaultCharset()).toString();
    }

    public float readFloat() {
        return internalBuffer.readFloat();
    }

    public Vector2f readVector2f() {
        return new Vector2f(readFloat(), readFloat());
    }

    public Vector3f readVector3f() {
        return new Vector3f(readFloat(), readFloat(), readFloat());
    }

    public int readInt() {
        return internalBuffer.readInt();
    }

    public long readLong() {
        return internalBuffer.readLong();
    }

    public void writeBoolean(boolean bool) {
        internalBuffer.writeBoolean(bool);
    }

    public void writeString(String chars) {
        internalBuffer.writeInt(chars.length());
        internalBuffer.writeCharSequence(chars, Charset.defaultCharset());
    }

    public void writeFloat(float number) {
        internalBuffer.writeFloat(number);
    }

    public void writeVector2f(Vector2f vec) {
        writeFloat(vec.x);
        writeFloat(vec.y);
    }
    public void writeVector3f(Vector3f vec) {
        writeFloat(vec.x);
        writeFloat(vec.y);
        writeFloat(vec.z);
    }

    public void writeInt(int number) {
        internalBuffer.writeInt(number);
    }

    public void writeBytes(int start, int length, ByteBuf bytes) {
        internalBuffer.writeBytes(bytes, start, length);
    }

    public void writeLong(long number) {
        internalBuffer.writeLong(number);
    }
}
