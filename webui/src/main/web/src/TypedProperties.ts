type Class<T> = new (...args: any[]) => T;


function cast<T>(theClass: Class<T>, obj: any): T {
    if (! (obj instanceof theClass)) {
        throw new Error(`Not an instance of ${theClass.name}: ${obj}`);
    }
    return obj;
}

class TypedProperties {

    private readonly valuesMap: Map<any, any>;

    constructor(valueMap: Map<any, any>) {
        this.valuesMap = valueMap;
    }

    private EMPTY: TypedProperties = new TypedProperties(new Map<any, any>());

    create = () => this.EMPTY;

    with = <T> (key: Class<T>, value: T) => {
        cast(key, value);
        const updated = new Map<any, any>();
        this.valuesMap.forEach((k, v) => updated.set(k, v));
        updated.set(key, value);
        return new TypedProperties(updated);
    }

    find = <T> (key: Class<T>): T => {
        return this.valuesMap.get(key);
    }

}

export default TypedProperties;
