package m2.miage.m2gestionmembres.services;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

@Service
public class ToolServiceImpl implements IToolService{
    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");


    @Override
    public LocalDateTime getDateTimeFromString(String dateTime) {
        return LocalDateTime.parse(dateTime, dateTimeFormatter);
    }

    @Override
    public String getStringFromDateTime(LocalDateTime dateTime) {
        return dateTime.format(dateTimeFormatter);
    }

    @Override
    public int getDateDifferent(LocalDateTime dateBefore, LocalDateTime dateAfter) {
        long daysBetween = dateBefore.until(dateAfter, ChronoUnit.DAYS);
        return (int) daysBetween;
    }
}
