package org.tobkir.scheduling;

import io.quarkus.scheduler.Scheduled;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tobkir.logic.mapper.ModbusValueMapper;
import org.tobkir.logic.services.ModbusReaderService;
import org.tobkir.logic.services.ModbusValueService;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.tobkir.model.ModbusValueEntity;

/**
 * Scheduler-Klasse, die in regelmäßigen Abständen Modbus-Werte liest und speichert.
 * <p>
 * Diese Klasse verwendet den Quarkus Scheduler, um in einem festen Zeitintervall (alle 5 Sekunden)
 * eine Methode zur Abfrage und Speicherung der Modbus-Werte auszuführen.
 * <p>
 * Die erfassten Werte werden in der Datenbank gespeichert und zu Informationszwecken protokolliert.
 */
@ApplicationScoped
public class ModbusScheduler {

    Logger logger = LoggerFactory.getLogger(ModbusScheduler.class);

    private final ModbusReaderService modbusReaderService;
    private final ModbusValueService modbusValueService;
    private final ModbusValueMapper mapper;

    /**
     * Konstruktor zur Injektion der erforderlichen Services und Mapper.
     *
     * @param modbusReaderService der Service zum Lesen der Modbus-Werte
     * @param modbusValueService der Service zur Speicherung der Modbus-Werte
     * @param mapper der Mapper zur Umwandlung von Modbus-Daten in Entitäten
     */
    @Inject
    public ModbusScheduler(ModbusReaderService modbusReaderService, ModbusValueService modbusValueService, ModbusValueMapper mapper) {
        this.modbusReaderService = modbusReaderService;
        this.modbusValueService = modbusValueService;
        this.mapper = mapper;
    }

    /**
     * Geplante Methode zum periodischen Lesen und Speichern von Modbus-Werten.
     * <p>
     * Diese Methode wird alle 5 Sekunden ausgeführt, um Daten zu lesen, in ein Entitätsobjekt
     * umzuwandeln und in der Datenbank zu speichern. Bei Erfolg werden die gelesenen Daten
     * protokolliert, bei Fehlern wird eine Fehlermeldung ausgegeben.
     */
    @Scheduled(cron = "*/5 * * * * ?")
    public void scheduledReadValues() {
        try {
            logger.info("Reading Modbus.");
            ModbusValueEntity actual = modbusValueService.saveModbusValue(
                    mapper.toEntity(
                            modbusReaderService.readValuesContainer().toCompletableFuture().join()
                    )
            );
            logger.info("Actual Value: ");
            logger.info(actual.toString());
            logger.info("Modbus values successfully read and processed.");
        } catch (RuntimeException e) {
            logger.info("Error reading Modbus values: {}", e.getMessage());
        }
    }
}