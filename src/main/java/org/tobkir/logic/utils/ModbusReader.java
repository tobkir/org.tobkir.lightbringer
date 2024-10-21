package org.tobkir.logic.utils;


import jakarta.enterprise.context.RequestScoped;
import net.solarnetwork.io.modbus.ModbusClient;
import net.solarnetwork.io.modbus.ModbusMessage;
import net.solarnetwork.io.modbus.netty.msg.RegistersModbusMessage;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import static net.solarnetwork.io.modbus.netty.msg.RegistersModbusMessage.readHoldingsRequest;

@RequestScoped
public class ModbusReader {

    ModbusClient client;

    public ModbusReader(ModbusClient client) {
        this.client = client;
    }

    public ModbusReader(){}

    // Methode zum Lesen eines Strings von einer Adresse mit 8 Registern
    public String readStr(int addr) {
        ModbusMessage req = readHoldingsRequest(71, addr, 8);
        RegistersModbusMessage response = client.send(req).unwrap(RegistersModbusMessage.class);
        return new String(response.dataCopy());
    }

    // Methode zum Lesen eines Floats von einer Adresse mit 2 Registern
    public float readFloat(int addr) {
        ModbusMessage req = readHoldingsRequest(71, addr, 2);
        RegistersModbusMessage response = client.send(req).unwrap(RegistersModbusMessage.class);
        short[] registers = response.dataDecode();
        ByteBuffer byteBuffer = ByteBuffer.allocate(4).order(ByteOrder.LITTLE_ENDIAN);
        byteBuffer.putShort(registers[0]);
        byteBuffer.putShort(registers[1]);
        return Math.round(byteBuffer.getFloat(0) * 100.0) / 100.0f;
    }

    // Methode zum Lesen eines Unsigned 16-bit Integer (U16) von einer Adresse mit 1 Register
    public int readU16(int addr) {
        ModbusMessage req = readHoldingsRequest(71, addr, 1);
        RegistersModbusMessage response = client.send(req).unwrap(RegistersModbusMessage.class);
        ByteBuffer registers = ByteBuffer.wrap(response.dataCopy());
        return registers.getShort();
    }

    // Überladene Methode zum Lesen eines Unsigned 16-bit Integer (U16) von einer Adresse mit 2 Registern
    public int readU16(int addr, int count) {
        ModbusMessage req = readHoldingsRequest(71, addr, count);
        if (count != 2) {
            throw new IllegalArgumentException("Für diese Methode müssen count = 2 sein.");
        }
        RegistersModbusMessage response = client.send(req).unwrap(RegistersModbusMessage.class);
        ByteBuffer registers = ByteBuffer.wrap(response.dataCopy());
        return registers.getShort();
    }

    // Methode zum Lesen eines Unsigned 32-bit Integer (U32) von einer Adresse mit 2 Registern
    public long readU32(int addr) {
        ModbusMessage req = readHoldingsRequest(71, addr, 2);
        RegistersModbusMessage response = client.send(req).unwrap(RegistersModbusMessage.class);
        ByteBuffer registers = ByteBuffer.wrap(response.dataCopy());
        ByteBuffer byteBuffer = ByteBuffer.allocate(4).order(ByteOrder.LITTLE_ENDIAN);
        byteBuffer.putShort(registers.getShort());
        byteBuffer.putShort(registers.getShort());
        return byteBuffer.getInt(0) & 0xFFFFFFFFL;
    }

    // Methode zum Lesen eines Signed 16-bit Integer (S16) von einer Adresse mit 1 Register
    public short readS16(int addr) {
        ModbusMessage req = readHoldingsRequest(71, addr, 1);
        RegistersModbusMessage response = client.send(req).unwrap(RegistersModbusMessage.class);
        ByteBuffer registers = ByteBuffer.wrap(response.dataCopy());
        return registers.getShort();
    }
}