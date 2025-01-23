package org.tobkir.logic.services;

import jakarta.enterprise.context.RequestScoped;
import net.solarnetwork.io.modbus.ModbusClient;
import net.solarnetwork.io.modbus.tcp.netty.NettyTcpModbusClientConfig;
import net.solarnetwork.io.modbus.tcp.netty.TcpNettyModbusClient;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tobkir.logic.utils.ModbusReader;
import org.tobkir.model.GeneralInformationContainer;
import org.tobkir.model.ModbusValueContainer;

import java.util.concurrent.CompletionStage;

import static org.tobkir.constants.Addresses.*;

/**
 * Serviceklasse, die für das Lesen von Werten von einem Modbus-Server zuständig ist.
 * <p>
 * Diese Klasse stellt eine Verbindung zu einem Modbus-Server her und liest verschiedene Messwerte
 * wie PV-Leistung und Verbrauchsdaten. Sie verwendet eine TCP-Verbindung über Netty für die Kommunikation.
 * Die gelesenen Werte werden in einem {@link ModbusValueContainer} gespeichert.
 * <p>
 * Die Klasse ist als @RequestScoped deklariert, was bedeutet, dass sie während einer HTTP-Anfrage instanziiert wird.
 */
@RequestScoped
public class ModbusReaderService {

    Logger logger = LoggerFactory.getLogger(ModbusReaderService.class);

    @ConfigProperty(name = "lightbringer.plant.ip")
    String ip;

    @ConfigProperty(name = "lightbringer.plant.port")
    int port;

    private ModbusReader modbusReader;

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
    public CompletionStage<ModbusValueContainer> readValuesContainer() {
        ModbusValueContainer values = new ModbusValueContainer();
        NettyTcpModbusClientConfig config = new NettyTcpModbusClientConfig(ip, port);
        client = new TcpNettyModbusClient(config);

        modbusReader = new ModbusReader(client);

        return client.start().thenApplyAsync(voidResult -> {
            // Werte lesen
            values.setBatteryChargingState(readBatteryChargingState());
            values.setActualPVPower(readActualPVPower(PV_POWER_1, PV_POWER_2, PV_POWER_3));
            values.setConsumptionFromBattery(readConsumptionFromBattery());
            values.setConsumptionFromPV(readConsumptionFromPV());
            values.setConsumptionFromGrid(readConsumptionFromGrid());
            values.setBatteryChargingPower(-readBatteryChargingPowerCurrent());
            values.setDailyYield(readDailyYield());
            values.setMonthlyYield(readMonthlyYield());
            values.setYearlyYield(readYearlyYield());
            return values;
        }).exceptionally(ex -> {
            // Fehlerbehandlung
            logger.error("Fehler beim Starten der Modbus-Verbindung: {}", ex.getMessage(), ex);
            throw new RuntimeException("Fehler beim Starten der Modbus-Verbindung", ex);
        }).whenComplete((result, throwable) -> {
            // Verbindung schließen, egal ob erfolgreich oder fehlerhaft
            logger.info("Connection closed");
            client.stop();
        });
    }

    // Private Hilfsmethoden zum Lesen spezifischer Modbus-Werte
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

    private float readActualPVPower(int... addresses) {
        float totalPower = 0;

        for (int address : addresses) {
            totalPower += modbusReader.readFloat(address);
        }

        return totalPower;
    }


    private float readBatteryChargingPower() {
        return modbusReader.readFloat(BATTERY_CHARGING_POWER);
    }

    private int readBatteryChargingPowerCurrent() {
        return modbusReader.readS16(BATTERY_CHARGING_POWER_CURRENT);
    }

    public GeneralInformationContainer getIp() {
        return new GeneralInformationContainer(ip);
    }

    private long readBatteryFirmware() {
        return modbusReader.readU32(BATTERY_FIRMWARE);
    }

    private long readBatteryCapacity() {
        return modbusReader.readU32(BATTERY_GROSS_CAPACITY);
    }

    private float readDailyYield() {
        return modbusReader.readFloat(322
        );
    }

    private float readYearlyYield() {
        logger.info("Read Yearly Yield {}", modbusReader.readFloat(324));
        return modbusReader.readFloat(324);
    }
    private float readMonthlyYield() {
        logger.info("Read Monthly Yield {}", modbusReader.readFloat(324));
        return modbusReader.readFloat(326);
    }
}
