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
const BUTTON_STYLE = "w-25 f6 link dim ph3 pv2 mb2 dib white bg-near-black";
const ManageItems = () => {
    const {path, url} = useRouteMatch();
    const {feature} = useParams();

    const [fields, setFields] = useState(DUMMY_FIELDS);
    const [data, setData] = useState(DUMMY_DATA);

    useEffect(() => {
        fetch(`${SERVER_URI}/api/${feature}/fields`)
            .then(response => response.json())
            .then(_fields => setFields(_fields));
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
            .then(_data => setData(_data));
    }, [feature]);

    return (
        <Switch>
            <Route exact path={path}>
                <div className="cf">
                    <Link to={`${url}/new`}
                          className={BUTTON_STYLE}>New</Link>
                </div>
                <ItemList fields={fields}
                          data={data}
                />
            </Route>
        </Switch>
    );
};

export default ManageItems;
