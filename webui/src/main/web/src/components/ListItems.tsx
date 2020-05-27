import React, {useEffect, useState} from "react";
import {Link, Route, Switch, useParams, useRouteMatch} from "react-router-dom";
import ItemList, {Row, FieldsMeta} from "./ItemList";

const EMPTY_ROWS: Array<Row> = [];

interface ListItemsProps {
    feature: string;
    serverUri: string;
    meta: FieldsMeta;
}

const ListItems = (props: ListItemsProps) => {
    const {path} = useRouteMatch();

    const [rows, setRows] = useState(EMPTY_ROWS);

    useEffect(() => {
        fetch(`${props.serverUri}/api/${props.feature}`)
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
    }, []);

    return (
        <Switch>
            <Route exact path={path}>
                <div className="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
                    <h1 className="h2">{props.meta.name}</h1>
                    <div className="btn-toolbar mb-2 mb-md-0">
                        <Link to="new"
                              className="btn btn-sm btn-outline-secondary">New</Link>
                    </div>
                </div>
                <ItemList meta={props.meta}
                          rows={rows}
                />
            </Route>
        </Switch>
    );
};

export default ListItems;
