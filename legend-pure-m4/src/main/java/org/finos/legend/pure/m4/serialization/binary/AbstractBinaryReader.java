// Copyright 2020 Goldman Sachs
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//      http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package org.finos.legend.pure.m4.serialization.binary;

import org.finos.legend.pure.m4.serialization.Reader;

import java.io.IOException;

public abstract class AbstractBinaryReader extends AbstractBinaryReaderWriter implements Reader
{
    @Override
    public synchronized byte[] readByteArray()
    {
        // Read length
        int length = readInt();

        // Read bytes
        return readBytes(length);
    }

    @Override
    public synchronized void skipByteArray()
    {
        // Read length
        int length = readInt();

        // Skip bytes
        skipBytes(length);
    }

    @Override
    public synchronized boolean readBoolean()
    {
        byte b = readByte();
        switch (b)
        {
            case TRUE_BYTE:
            {
                return true;
            }
            case FALSE_BYTE:
            {
                return false;
            }
            default:
            {
                throw new RuntimeException(new IOException("Expected " + TRUE_BYTE + " or " + FALSE_BYTE + "; found " + b));
            }
        }
    }

    @Override
    public synchronized void skipBoolean()
    {
        skipBytes(1L);
    }

    @Override
    public synchronized void skipShort()
    {
        skipBytes(2L);
    }

    @Override
    public synchronized short[] readShortArray()
    {
        // Read length
        int length = readInt();

        // Read shorts
        short[] shorts = new short[length];
        for (int i = 0; i < length; i++)
        {
            shorts[i] = readShort();
        }
        return shorts;
    }

    @Override
    public synchronized void skipShortArray()
    {
        skipArray(2L);
    }

    @Override
    public synchronized void skipInt()
    {
        skipBytes(4L);
    }

    @Override
    public synchronized int[] readIntArray()
    {
        // Read length
        int length = readInt();

        // Read ints
        int[] ints = new int[length];
        for (int i = 0; i < length; i++)
        {
            ints[i] = readInt();
        }
        return ints;
    }

    @Override
    public synchronized void skipIntArray()
    {
        skipArray(4L);
    }

    @Override
    public synchronized void skipLong()
    {
        skipBytes(8L);
    }

    @Override
    public synchronized long[] readLongArray()
    {
        // Read length
        int length = readInt();

        // Read longs
        long[] longs = new long[length];
        for (int i = 0; i < length; i++)
        {
            longs[i] = readLong();
        }
        return longs;
    }

    @Override
    public synchronized void skipLongArray()
    {
        skipArray(8L);
    }

    @Override
    public synchronized void skipFloat()
    {
        skipBytes(4L);
    }

    @Override
    public synchronized float[] readFloatArray()
    {
        // Read length
        int length = readInt();

        // Read floats
        float[] floats = new float[length];
        for (int i = 0; i < length; i++)
        {
            floats[i] = readFloat();
        }
        return floats;
    }

    @Override
    public synchronized void skipFloatArray()
    {
        skipArray(4L);
    }

    @Override
    public synchronized void skipDouble()
    {
        skipBytes(8L);
    }

    @Override
    public synchronized double[] readDoubleArray()
    {
        // Read length
        int length = readInt();

        // Read doubles
        double[] doubles = new double[length];
        for (int i = 0; i < length; i++)
        {
            doubles[i] = readDouble();
        }
        return doubles;
    }

    @Override
    public synchronized void skipDoubleArray()
    {
        skipArray(8L);
    }

    @Override
    public synchronized String readString()
    {
        return byteArrayToString(readByteArray());
    }

    @Override
    public synchronized void skipString()
    {
        skipByteArray();
    }

    @Override
    public synchronized String[] readStringArray()
    {
        // Read length
        int length = readInt();

        // Read strings
        String[] strings = new String[length];
        for (int i = 0; i < length; i++)
        {
            strings[i] = readString();
        }
        return strings;
    }

    @Override
    public synchronized void skipStringArray()
    {
        // Read length
        int length = readInt();

        // Skip strings
        for (int i = 0; i < length; i++)
        {
            skipString();
        }
    }

    protected void skipArray(long elementSizeInBytes)
    {
        // Read length
        long length = (long)readInt();

        // Skip elements
        skipBytes(elementSizeInBytes * length);
    }
}
