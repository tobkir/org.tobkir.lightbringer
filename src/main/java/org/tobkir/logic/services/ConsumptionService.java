package org.tobkir.logic.services;

import jakarta.enterprise.context.RequestScoped;
import org.tobkir.model.ConsumptionState;

import java.time.ZonedDateTime;
import java.util.List;

@RequestScoped
public class ConsumptionService {

    private static Float calculateTotalConsumption(List<ConsumptionState> states, ZonedDateTime startTime, ZonedDateTime endTime) {
        List<ConsumptionState> filteredStates = states.stream()
                .filter(state -> !state.getTimestamp().isBefore(startTime) && !state.getTimestamp().isAfter(endTime))
                .toList();

        Float totalConsumption = 0.0F;
        for (int i = 0; i < filteredStates.size() - 1; i++) {
            ConsumptionState currentState = filteredStates.get(i);
            ConsumptionState nextState = filteredStates.get(i + 1);

            Float timeDifferenceHours = (nextState.getTimestamp().toEpochSecond() - currentState.getTimestamp().toEpochSecond()) / 3600.0F;
            Float averagePower = (currentState.getTotalConsumption() + nextState.getTotalConsumption()) / 2;

            totalConsumption += averagePower * timeDifferenceHours;
        }

        return totalConsumption;
    }

    public Float calculateDailyConsumption(List<ConsumptionState> states, ZonedDateTime date) {
        ZonedDateTime startOfDay = date.toLocalDate().atStartOfDay(date.getZone());
        ZonedDateTime endOfDay = startOfDay.plusDays(1);
        return calculateTotalConsumption(states, startOfDay, endOfDay);
    }
    public static double calculateMonthlyConsumption(List<ConsumptionState> states, int year, int month) {
        ZonedDateTime startOfMonth = ZonedDateTime.of(year, month, 1, 0, 0, 0, 0, states.get(0).getTimestamp().getZone());
        ZonedDateTime endOfMonth = startOfMonth.plusMonths(1);
        return calculateTotalConsumption(states, startOfMonth, endOfMonth);
    }

    public static double calculateYearlyConsumption(List<ConsumptionState> states, int year) {
        ZonedDateTime startOfYear = ZonedDateTime.of(year, 1, 1, 0, 0, 0, 0, states.get(0).getTimestamp().getZone());
        ZonedDateTime endOfYear = startOfYear.plusYears(1);
        return calculateTotalConsumption(states, startOfYear, endOfYear);
    }

    public static double calculateTotalConsumptionAllTime(List<ConsumptionState> states) {
        ZonedDateTime startTime = states.get(0).getTimestamp();
        ZonedDateTime endTime = states.get(states.size() - 1).getTimestamp();
        return calculateTotalConsumption(states, startTime, endTime);
    }

}
