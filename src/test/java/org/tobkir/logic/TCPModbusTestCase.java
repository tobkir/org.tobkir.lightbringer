package org.tobkir.logic;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import net.solarnetwork.io.modbus.ModbusClient;
import net.solarnetwork.io.modbus.tcp.netty.NettyTcpModbusClientConfig;
import net.solarnetwork.io.modbus.tcp.netty.TcpNettyModbusClient;
import org.junit.jupiter.api.Test;
import org.tobkir.logic.utils.ModbusReader;

import java.util.concurrent.ExecutionException;

@QuarkusTest
public class TCPModbusTestCase {
    NettyTcpModbusClientConfig config = new NettyTcpModbusClientConfig("192.168.178.51", 1502);
    ModbusClient client = new TcpNettyModbusClient(config);

    @Inject
    ModbusReader modbusReader;

    @Test
    public void testModubus() {

        modbusReader = new ModbusReader(client);
        // create the TCP client

        try {
            // connect the client
            client.start().get();


            System.out.println(modbusReader.readStr(6));
            System.out.println(modbusReader.readStr(46));
            System.out.println("IP: " + modbusReader.readStr(420));
            System.out.println("Total: " + (double) modbusReader.readFloat(320) / 1000 + "kW");
            System.out.println("Tag: " + modbusReader.readFloat(322) / 1000 + "kW");
            System.out.println("Batterie: " + (int) modbusReader.readFloat(210) + "%");
            System.out.println("100: " + modbusReader.readFloat(100));
            System.out.println("106: " + modbusReader.readFloat(106));
            System.out.println("108: " + modbusReader.readFloat(108));
            System.out.println("116: " + modbusReader.readFloat(116));
            System.out.println("156: " + modbusReader.readFloat(260));
            System.out.println("162: " + modbusReader.readFloat(270));
            System.out.println("168: " + modbusReader.readFloat(280));

        } catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            client.stop();
        }
    }
}
