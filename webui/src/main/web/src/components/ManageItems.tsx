import React from "react";
import {Link, Route, Switch, useParams, useRouteMatch} from "react-router-dom";

const ManageItems = () => {
    const {feature} = useParams();
    const {path, url} = useRouteMatch();
    const buttonStyle = "w-25 f6 link dim ph3 pv2 mb2 dib white bg-near-black";
    const tableStyle = "f6 w-100 mw8 center";
    const thStyle = "fw6 tl pa3 bg-white";
    const fields = [
        {label: "Name", key: "name"},
        {label: "Other", key: "other"}
    ];
    const data = [
        {id: 1, name: "Bob", other: 34},
        {id: 2, name: "Adam", other: 42}
    ];

    function propValue(obj: object, property: string) {
        return Object.entries(obj)
            .filter((v, i, a) =>
                v[0] == property)
            .map((v, i, a) =>
                v[1]);
    }

    return (
        <>
            <Switch>
                <Route exact path={path}>
                    <div className="cf">
                        <Link to={`${url}/new`}
                              className={buttonStyle}>New</Link>
                    </div>
                    <div className="pa4">
                        <div className="overflow-auto">
                            <table className={tableStyle}>
                                <thead>
                                <tr className="stripe-dark">
                                    {fields.map(field => (
                                        <th className={thStyle}>
                                            {field.label}
                                        </th>
                                    ))}
                                </tr>
                                </thead>
                                <tbody className="lh-copy">
                                {data.map(row => (
                                    <tr key={row.id}>
                                        {fields.map(field => (
                                            <td className="pa3">
                                                {propValue(row, field.key)}
                                            </td>
                                        ))}
                                    </tr>
                                ))}
                                </tbody>
                            </table>
                        </div>
                    </div>
                </Route>
            </Switch>
        </>
    );
};

export default ManageItems;
