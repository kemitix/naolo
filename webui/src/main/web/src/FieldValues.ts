import {Field} from "./components/ItemList";

export interface FieldValue {
    type: string;
    name: string;
    value: any;

    withValue(value: string): FieldValue;
}

export class TextFieldValue implements FieldValue {
    type: string = "text";
    name: string;
    value: string;
    constructor(name: string, value: string) {
        this.name = name;
        this.value = value;
    }

    withValue(value: string): FieldValue {
        return new TextFieldValue(this.name, value);
    }
}

export class NumberFieldValue implements FieldValue {
    type: string = "number";
    name: string;
    value: number;
    constructor(name: string, value: string) {
        this.name = name;
        this.value = parseInt(value);
    }

    withValue(value: string): FieldValue {
        return new NumberFieldValue(this.name, value);
    }
}

export class EnumerationFieldValue extends TextFieldValue {
    type: string = "enumeration";
}

export class KeyFieldValue extends TextFieldValue {
    type: string = "key";
}

export class DateFieldValue extends TextFieldValue {
    type: string = "date";
}

class FieldValues {
    constructor(source: Map<string, FieldValue> = new Map<string, FieldValue>()) {
        this.fields = source;
    }
    fields: Map<string, FieldValue>;
    with: (name: string, value: string) => FieldValues =
        (name, value) => {
            const copy = new Map<string, FieldValue>();
            if (this.fields.size > 0) {
                this.fields.forEach((field: FieldValue) => {
                    if (field.name === name) {
                        copy.set(field.name, field.withValue(value));
                    } else {
                        copy.set(field.name, field)
                    }
                });
            }
            return new FieldValues(copy);
        }
    get: (name: string) => FieldValue =
        (name) => {
            console.log(`get ${name} from:`);
            console.log(this.fields);
            const fieldValue = this.fields.get(name);
            if (fieldValue === undefined) {
                throw new Error(`Unknown field: ${name}`);
            }
            return fieldValue;
        }
}

export const createField = (
    type: string,
    name: string,
    value: string
): FieldValue => {
    switch (type) {
        case "text":
            return new TextFieldValue(name, value);
        case "number":
            return new NumberFieldValue(name, value);
        case "enumeration":
            return new EnumerationFieldValue(name, value);
        case "key":
            return new KeyFieldValue(name, value);
        case "date":
            return new DateFieldValue(name, value);
        default:
            throw new Error(`Unknown type: ${type}`);
    }
}

const foldRight = <A, B>(xs: Array<A>, zero: B) => (f: (b: B, a: A) => B): B => {
    const len = xs.length;
    if (len === 0) return zero;
    else {
        const last = xs[len - 1];
        const inits = xs.slice(0, len - 1);
        return foldRight(inits, f(zero, last))(f);
    }
}

export const fieldValues = (fields: Array<Field>) => {
    const array: FieldValue[] = fields.map(field =>
        createField(field.type, field.name, field.defaultValue));
    const foldFieldValues = foldRight(array, new FieldValues());
    return foldFieldValues((fv, f) =>
        fv.with(f.name, f.value));
}

export default FieldValues;
