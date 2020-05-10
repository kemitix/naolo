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
    const tableStyle = "f6 w-100 mw8 center";
    const thStyle = "fw6 tl pa3 bg-white";
    return (<div className="pa4">
            <div className="overflow-auto">
                <table className={tableStyle}>
                    <thead>
                    <tr className="stripe-dark">
                        {props.fields.map(field => (
                            <th key={field.key}
                                className={thStyle}>
                                {field.label}
                            </th>
                        ))}
                    </tr>
                    </thead>
                    <tbody className="lh-copy">
                    {props.data.map(row => (
                        <tr key={row.id}>
                            {props.fields.map(field => (
                                <td key={field.key}
                                    className="pa3">
                                    {propValue(row.values, field.key)}
                                </td>
                            ))}
                        </tr>
                    ))}
                    </tbody>
                </table>
            </div>
        </div>
    );
};

export default ItemList;
