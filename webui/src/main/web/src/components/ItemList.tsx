import React from "react";

function propValue(obj: object, property: string) {
    return Object.entries(obj)
        .filter((v, i, a) =>
            v[0] === property)
        .map((v, i, a) =>
            v[1]);
}

interface Field {
    key: string,
    label: string
}

interface Row {
    id: number
    values: object
}

interface ItemListProps {
    fields: Array<Field>,
    data: Array<Row>
}

const ItemList = (props: ItemListProps) => {
    return (
        <div className="table-responsive">
            <table className={"table table-striped table-sm"}>
                <thead>
                <tr>
                    {props.fields.map(field => (
                        <th key={field.key}>
                            {field.label}
                        </th>
                    ))}
                </tr>
                </thead>
                <tbody>
                {props.data.map(row => (
                    <tr key={row.id}>
                        {props.fields.map(field => (
                            <td key={field.key}>
                                {propValue(row.values, field.key)}
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
