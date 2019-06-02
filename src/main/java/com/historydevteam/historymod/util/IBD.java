package com.historydevteam.historymod.util;

import com.google.common.base.Charsets;
import io.netty.buffer.ByteBuf;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class IBD {

  private Map<Integer, Object> map = new HashMap<>();

  public IBD setByte(int key, byte value) {
    map.put(key, value);
    return this;
  }

  public IBD setShort(int key, short value) {
    map.put(key, value);
    return this;
  }

  public IBD setInteger(int key, int value) {
    map.put(key, value);
    return this;
  }

  public IBD setLong(int key, long value) {
    map.put(key, value);
    return this;
  }

  public IBD setFloat(int key, float value) {
    map.put(key, value);
    return this;
  }

  public IBD setDouble(int key, double value) {
    map.put(key, value);
    return this;
  }

  public IBD setBoolean(int key, boolean value) {
    map.put(key, value);
    return this;
  }

  public IBD setString(int key, String value) {
    map.put(key, value);
    return this;
  }

  public IBD setByteArray(int key, byte[] value) {
    map.put(key, value);
    return this;
  }

  public IBD setIntArray(int key, int[] value) {
    map.put(key, value);
    return this;
  }

  public void withByte(int key, Consumer<Byte> action) {
    if (map.containsKey(key)) {
      action.accept((Byte) map.get(key));
    }
  }

  public void withShort(int key, Consumer<Short> action) {
    if (map.containsKey(key)) {
      action.accept((Short) map.get(key));
    }
  }

  public void withInteger(int key, Consumer<Integer> action) {
    if (map.containsKey(key)) {
      action.accept((Integer) map.get(key));
    }
  }

  public void withLong(int key, Consumer<Long> action) {
    if (map.containsKey(key)) {
      action.accept((Long) map.get(key));
    }
  }

  public void withFloat(int key, Consumer<Float> action) {
    if (map.containsKey(key)) {
      action.accept((Float) map.get(key));
    }
  }

  public void withDouble(int key, Consumer<Double> action) {
    if (map.containsKey(key)) {
      action.accept((Double) map.get(key));
    }
  }

  public void withBoolean(int key, Consumer<Boolean> action) {
    if (map.containsKey(key)) {
      action.accept((Boolean) map.get(key));
    }
  }

  public void withString(int key, Consumer<String> action) {
    if (map.containsKey(key)) {
      action.accept((String) map.get(key));
    }
  }

  public void withByteArray(int key, Consumer<byte[]> action) {
    if (map.containsKey(key)) {
      action.accept((byte[]) map.get(key));
    }
  }

  public void withIntArray(int key, Consumer<int[]> action) {
    if (map.containsKey(key)) {
      action.accept((int[]) map.get(key));
    }
  }

  public IBD remove(int key) {
    map.remove(key);
    return this;
  }

  public boolean hasKey(int key) {
    return map.containsKey(key);
  }

  public void addAll(IBD other) {
    other.map.forEach((k, v) -> map.put(k, v));
  }

  public void clear() {
    map.clear();
  }

  public void fromBuffer(ByteBuf buf) {
    clear();
    int size = buf.readInt();
    for (int i = 0; i < size; i++) {
      byte type = buf.readByte();
      int key = buf.readInt();

      //@formatter:off
      switch (type) {
        case 0: setByte(key, buf.readByte()); break;
        case 1: setShort(key, buf.readShort()); break;
        case 2: setInteger(key, buf.readInt()); break;
        case 3: setLong(key, buf.readLong()); break;
        case 4: setFloat(key, buf.readFloat()); break;
        case 5: setDouble(key, buf.readDouble()); break;
        case 6: setBoolean(key, buf.readBoolean()); break;
        //@formatter:on
        case 7:
          int stringSize = (int) buf.readShort() & 0xFFFF;
          byte[] chars = new byte[stringSize];

          for (int j = 0; j < stringSize; j++) {
            chars[j] = buf.readByte();
          }

          setString(key, new String(chars, Charsets.UTF_8));
          break;

        case 8:
          int byteArraySize = (int) buf.readShort() & 0xFFFF;
          byte[] bytes = new byte[byteArraySize];

          for (int j = 0; j < byteArraySize; j++) {
            bytes[j] = buf.readByte();
          }

          setByteArray(key, bytes);
          break;

        case 9:
          int intArraySize = (int) buf.readShort() & 0xFFFF;
          int[] ints = new int[intArraySize];

          for (int j = 0; j < intArraySize; j++) {
            ints[j] = buf.readInt();
          }

          setIntArray(key, ints);
          break;

        default:
          throw new IllegalStateException("Unknown type id: " + type + " at key: " + key);
      }

    }
  }

  public void toBuffer(ByteBuf buf) {
    buf.writeInt(map.size());
    for (Map.Entry<Integer, Object> entry : map.entrySet()) {
      Object obj = entry.getValue();

      if (obj instanceof Byte) {
        buf.writeByte(0);
        buf.writeInt(entry.getKey());
        buf.writeByte((Byte) obj);

      } else if (obj instanceof Short) {
        buf.writeByte(1);
        buf.writeInt(entry.getKey());
        buf.writeShort((Short) obj);

      } else if (obj instanceof Integer) {
        buf.writeByte(2);
        buf.writeInt(entry.getKey());
        buf.writeInt((Integer) obj);

      } else if (obj instanceof Long) {
        buf.writeByte(3);
        buf.writeInt(entry.getKey());
        buf.writeLong((Long) obj);

      } else if (obj instanceof Float) {
        buf.writeByte(4);
        buf.writeInt(entry.getKey());
        buf.writeFloat((Float) obj);

      } else if (obj instanceof Double) {
        buf.writeByte(5);
        buf.writeInt(entry.getKey());
        buf.writeDouble((Double) obj);

      } else if (obj instanceof Boolean) {
        buf.writeByte(6);
        buf.writeInt(entry.getKey());
        buf.writeBoolean((Boolean) obj);

      } else if (obj instanceof String) {
        buf.writeByte(7);
        buf.writeInt(entry.getKey());

        byte[] bytes = ((String) obj).getBytes(Charsets.UTF_8);
        buf.writeShort(bytes.length);

        for (byte aByte : bytes) {
          buf.writeByte(aByte);
        }
      } else if (obj instanceof byte[]) {
        buf.writeByte(8);
        buf.writeInt(entry.getKey());

        byte[] bytes = (byte[]) obj;
        buf.writeShort(bytes.length);

        for (byte aByte : bytes) {
          buf.writeByte(aByte);
        }
      } else if (obj instanceof int[]) {
        buf.writeByte(9);
        buf.writeInt(entry.getKey());

        int[] ints = (int[]) obj;
        buf.writeShort(ints.length);

        for (int aInt : ints) {
          buf.writeInt(aInt);
        }
      } else {
        throw new IllegalStateException("Unknown object type: " + obj.getClass() + " at key: " + entry.getKey());
      }
    }
  }

  public void setObject(int key, Object obj) {
    if (obj instanceof Byte) {
      setByte(key, (Byte) obj);
    } else if (obj instanceof Short) {
      setShort(key, (Short) obj);
    } else if (obj instanceof Integer) {
      setInteger(key, (Integer) obj);
    } else if (obj instanceof Long) {
      setLong(key, (Long) obj);
    } else if (obj instanceof Float) {
      setFloat(key, (Float) obj);
    } else if (obj instanceof Double) {
      setDouble(key, (Double) obj);
    } else if (obj instanceof Boolean) {
      setBoolean(key, (Boolean) obj);
    } else if (obj instanceof String) {
      setString(key, (String) obj);
    } else if (obj instanceof byte[]) {
      setByteArray(key, (byte[]) obj);
    } else if (obj instanceof int[]) {
      setIntArray(key, (int[]) obj);
    } else {
      throw new IllegalStateException("Unknown object type: " + obj.getClass() + " at key: " + key);
    }
  }

  public void withObject(int key, Consumer<Object> action) {
    if (map.containsKey(key)) {
      action.accept(map.get(key));
    }
  }
}
