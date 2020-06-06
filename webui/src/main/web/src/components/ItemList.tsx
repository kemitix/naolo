import React from "react";

function propValue(obj: object, property: string) {
    return Object.entries(obj)
        .filter((v, i, a) =>
            v[0] === property)
        .map((v, i, a) =>
            v[1]);
}

export interface Field {
    name: string;
    label: string;
    type: string;
    generated: boolean;
    order: number;
    required: boolean;
    defaultValue: string;
    minMax: MinMax;
    minLength: number;
    maxLength: number;
    values: Array<string>;
}

interface MinMax {
    min: number;
    max: number;
}

export interface FieldsMeta {
    name: string;
    fields: Array<Field>;
    endpoint: string;
}

export interface Row {
    id: number;
    values: object;
}

interface ItemListProps {
    meta: FieldsMeta;
    rows: Array<Row>;
}

const ItemList = (props: ItemListProps) => {
    return (
        <div className="table-responsive">
            <table className={"table table-striped table-sm"}>
                <thead>
                <tr>
                    {props.meta.fields.sort((a,b) => a.order - b.order)
                        .map(field =>
                            field.generated || (<th key={field.name}>{field.label}</th>)
                        )}
                </tr>
                </thead>
                <tbody>
                {props.rows.map(row => (
                    <tr key={row.id}>
                        {props.meta.fields.map(field => (
                            <td key={field.name}>
                                {propValue(row.values, field.name)}
                            </td>
                        ))}
                    </tr>
                ))}
                </tbody>
            </table>
        </div>
    );
};

export default ItemList;
