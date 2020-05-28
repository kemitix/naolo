package net.kemitix.naolo.core;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface FeatureMeta<T> {

    String getName();
    List<Field<?>> getFields();
    String getEndpoint();

    @Getter @SuperBuilder
    abstract class Field<T> {
        final String name;
        final String label;
        final int order;
        abstract FieldType getType();
        final boolean generated;
        final boolean required;
        final T defaultValue;
    }

    @Getter @SuperBuilder
    class NumberField extends Field<Integer> {
        final FieldType type = FieldType.number;
        final MinMax minMax;
    }

    @Getter @SuperBuilder
    class MinMax {
        final int min;
        final int max;
    }

    @Getter @SuperBuilder
    class TextField extends Field<String> {
        final FieldType type = FieldType.text;
        final int minLength;
        final int maxLength;
        final String regex;
    }

    @Getter @SuperBuilder
    class DateField extends Field<LocalDate> {
        final FieldType type = FieldType.date;
    }

    @Getter @SuperBuilder
    class DateTimeField extends Field<LocalDateTime> {
        final FieldType type = FieldType.datetime;
    }

    @Getter @SuperBuilder
    class EnumField extends Field<String> {
        final FieldType type = FieldType.enumeration;
        List<String> values;
    }

    @Getter @SuperBuilder
    class KeyField extends Field<String> {
        final FieldType type = FieldType.key;
        String target;
    }

    enum FieldType {
        number,
        text,
        date,
        datetime,
        enumeration,
        key,
    }
}
