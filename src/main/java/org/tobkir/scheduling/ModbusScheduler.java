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

@ApplicationScoped
public class ModbusScheduler {

    Logger logger = LoggerFactory.getLogger(ModbusScheduler.class);

    private final ModbusReaderService modbusReaderService;
    private final ModbusValueService modbusValueService;
    private final ModbusValueMapper mapper;

    @Inject
    public ModbusScheduler(ModbusReaderService modbusReaderService, ModbusValueService modbusValueService, ModbusValueMapper mapper) {
        this.modbusReaderService = modbusReaderService;
        this.modbusValueService = modbusValueService;
        this.mapper = mapper;
    }

    @Scheduled(cron = "*/5 * * * * ?")
    public void scheduledReadValues() {
        try {
            logger.info("Reading Modbus.");
            ModbusValueEntity actual = modbusValueService.saveModbusValue(mapper.toEntity(modbusReaderService.readValuesContainer()));
            logger.info("Actual Value: ");
            logger.info(actual.toString());
            logger.info("Modbus values successfully read and processed.");
        } catch (RuntimeException e) {
            logger.info("Error reading Modbus values: {}", e.getMessage());
        }
    }
}