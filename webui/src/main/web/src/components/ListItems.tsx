import React, {useEffect, useState} from "react";
import {Link, Route, Switch, useParams, useRouteMatch} from "react-router-dom";
import ItemList, {Row, FieldsMeta} from "./ItemList";

const SERVER_URI = "http://localhost:8080/naolo";

const EMPTY_FIELDS: FieldsMeta  = {endpoint: "", name: "", fields: []};
const EMPTY_ROWS: Array<Row> = [];

const ListItems = () => {
    const {path} = useRouteMatch();
    const {feature} = useParams();

    const [meta, setMeta] = useState(EMPTY_FIELDS);
    const [rows, setRows] = useState(EMPTY_ROWS);

    useEffect(() => {
        fetch(`${SERVER_URI}/api/${feature}/meta`)
            .then(response => response.json())
            .then(_meta => setMeta(_meta));
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
            .then(_rows => setRows(_rows));
    }, [feature]);

    return (
        <Switch>
            <Route exact path={path}>
                <div className="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
                    <h1 className="h2">{meta.name}</h1>
                    <div className="btn-toolbar mb-2 mb-md-0">
                        <Link to="new"
                              className="btn btn-sm btn-outline-secondary">New</Link>
                    </div>
                </div>
                <ItemList meta={meta}
                          rows={rows}
                />
            </Route>
        </Switch>
    );
};

export default ListItems;
