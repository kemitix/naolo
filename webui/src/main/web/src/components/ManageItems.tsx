import React, {useEffect, useState} from "react";
import {Link, Route, Switch, useParams, useRouteMatch} from "react-router-dom";
import ItemList from "./ItemList";

const SERVER_URI = "http://localhost:8080/naolo";

const DUMMY_FIELDS = [
    {label: "Name", key: "name"},
    {label: "Other", key: "other"}
];
const DUMMY_DATA = [
    {id: 1, values: {name: "Bob", other: 34}},
    {id: 2, values: {name: "Adam", other: 42}}
];
const ManageItems = () => {
    const {path, url} = useRouteMatch();
    const {feature} = useParams();

    const [fields, setFields] = useState(DUMMY_FIELDS);
    const [data, setData] = useState(DUMMY_DATA);

    useEffect(() => {
        fetch(`${SERVER_URI}/api/${feature}/fields`)
            .then(response => response.json())
            .then(_fields => setFields(DUMMY_FIELDS));
        fetch(`${SERVER_URI}/api/${feature}`)
            .then(response => response.json())
            .then(rows => {
                const d = [];
                let id = 0;
                for (let rowsKey in rows) {
                    d.push({id: id, values: rows[rowsKey]});
                }
                return d;
            })
            .then(_data => setData(DUMMY_DATA));
    }, [feature]);

    return (
        <Switch>
            <Route exact path={path}>
                <div className="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
                    <h1 className="h2">{feature}</h1>
                    <div className="btn-toolbar mb-2 mb-md-0">
                        <Link to={`${url}/new`}
                              className="btn btn-sm btn-outline-secondary">New</Link>
                    </div>
                </div>
                <ItemList fields={fields}
                          data={data}
                />
            </Route>
        </Switch>
    );
};

export default ManageItems;
