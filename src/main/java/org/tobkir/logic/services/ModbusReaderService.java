package org.tobkir.logic.services;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import net.solarnetwork.io.modbus.ModbusClient;
import net.solarnetwork.io.modbus.tcp.netty.NettyTcpModbusClientConfig;
import net.solarnetwork.io.modbus.tcp.netty.TcpNettyModbusClient;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tobkir.logic.utils.ModbusReader;
import org.tobkir.model.ModbusValueContainer;

import java.util.concurrent.ExecutionException;

import static org.tobkir.constants.Addresses.*;

@RequestScoped
public class ModbusReaderService {

    Logger logger = LoggerFactory.getLogger(ModbusReaderService.class);

    @ConfigProperty(name = "lightbringer.plant.ip")
    String ip;

    @ConfigProperty(name = "lightbringer.plant.port")
    int port;

    @Inject
    ModbusReader modbusReader;

    private ModbusClient client;

    /**
     * Liest Werte aus einem Modbus-Server und speichert sie in einem ModbusValueContainer.
     * <p>
     * Diese Methode stellt eine Verbindung zu einem Modbus-Server her, liest verschiedene Werte
     * (wie aktuelle PV-Leistung, Verbrauch von Batterie, PV und Netz) und speichert diese in
     * einem ModbusValueContainer-Objekt.
     * <p>
     * Die Methode verwendet eine TCP-Verbindung über Netty zur Kommunikation mit dem Modbus-Server.
     * Nach dem Lesen der Werte wird die Verbindung geschlossen.
     *
     * @throws RuntimeException wenn während der Ausführung ein Fehler auftritt (z.B. Verbindungsfehler)
     */
    public ModbusValueContainer readValuesContainer() {
        try {
            ModbusValueContainer values = new ModbusValueContainer();
            NettyTcpModbusClientConfig config = new NettyTcpModbusClientConfig(ip, port);
            client = new TcpNettyModbusClient(config);

            modbusReader = new ModbusReader(client);

            client.start().get();
            values.setBatteryChargingState(readBatteryChargingState());
            values.setActualPVPower(readActualPVPower());
            values.setConsumptionFromBattery(readConsumptionFromBattery());
            values.setConsumptionFromPV(readConsumptionFromPV());
            values.setConsumptionFromGrid(readConsumptionFromGrid());
            values.setBatteryChargingPower(-readBatteryChargingPowerCurrent());
            return values;

        } catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            client.stop();
        }
    }

    private int readBatteryChargingState() {
        return (int) modbusReader.readFloat(BATTERY_CHARGING_STATE);
    }

    private float readConsumptionFromGrid() {
        return modbusReader.readFloat(CONSUMPTION_FROM_GRID);
    }

    private float readConsumptionFromPV() {
        return modbusReader.readFloat(CONSUMPTION_FROM_PV);
    }

    private float readConsumptionFromBattery() {
        return modbusReader.readFloat(CONSUMPTION_FROM_BATTERY);
    }

    private float readActualPVPower() {
        float result;
        float power1 = modbusReader.readFloat(PV_POWER_1);
        float power2 = modbusReader.readFloat(PV_POWER_2);
        float power3 = modbusReader.readFloat(PV_POWER_3);
        result = power1 + power2 + power3;
        return result;
    }

    private float readBatteryChargingPower() {
        return modbusReader.readFloat(BATTERY_CHARGING_POWER);
    }

    private int readBatteryChargingPowerCurrent() {
        return modbusReader.readS16(BATTERY_CHARGING_POWER_CURRENT);
    }
}
