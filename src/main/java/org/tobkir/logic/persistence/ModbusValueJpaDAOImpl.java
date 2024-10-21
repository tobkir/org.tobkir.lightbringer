package org.tobkir.logic.persistence;


import org.tobkir.model.ModbusValueEntity;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import java.time.ZonedDateTime;
import java.util.List;

@ApplicationScoped
public class ModbusValueJpaDAOImpl implements ModbusValueDAO {

    @Inject
    EntityManager em;

    @Override
    public List<ModbusValueEntity> findAll() {
        return em.createQuery("SELECT m FROM ModbusValueEntity m", ModbusValueEntity.class)
                .getResultList();
    }

    @Override
    public List<Integer> findAllBatteryChargingStates() {
        return em.createQuery("SELECT m.batteryChargingState FROM ModbusValueEntity m", Integer.class)
                .getResultList();
    }

    @Override
    public List<Float> findAllConsumptionFromBattery() {
        return em.createQuery("SELECT m.consumptionFromBattery FROM ModbusValueEntity m", Float.class)
                .getResultList();
    }

    @Override
    public List<Float> findAllConsumptionFromGrid() {
        return em.createQuery("SELECT m.consumptionFromGrid FROM ModbusValueEntity m", Float.class)
                .getResultList();
    }

    @Override
    public List<Float> findAllConsumptionFromPV() {
        return em.createQuery("SELECT m.consumptionFromPV FROM ModbusValueEntity m", Float.class)
                .getResultList();
    }

    @Override
    public List<Float> findAllActualPVPower() {
        return em.createQuery("SELECT m.actualPVPower FROM ModbusValueEntity m", Float.class)
                .getResultList();
    }

    @Override
    @Transactional
    public void save(ModbusValueEntity entity) {
        em.persist(entity);
    }

    @Override
    public List<ModbusValueEntity> findByTimeRange(ZonedDateTime start, ZonedDateTime end) {
        return em.createQuery("SELECT m FROM ModbusValueEntity m WHERE m.timestamp BETWEEN :start AND :end", ModbusValueEntity.class)
                .setParameter("start", start)
                .setParameter("end", end)
                .getResultList();
    }

    @Override
    public ModbusValueEntity findLatest() {
        List<ModbusValueEntity> results = em.createQuery("SELECT m FROM ModbusValueEntity m ORDER BY m.timestamp DESC", ModbusValueEntity.class)
                .setMaxResults(1)
                .getResultList();
        return results.isEmpty() ? null : results.get(0);
    }
}