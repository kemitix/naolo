import React, {useEffect, useState} from "react";
import {Route, Switch} from "react-router-dom";
import ListItems from "./ListItems";
import AddItem from "./AddItem";
import {FieldsMeta} from "./ItemList";
import {fetchUri} from "../App";
import {fieldValues} from "../FieldValues";

const SERVER_URI = "http://localhost:8080/naolo";

const EMPTY_FIELDS: FieldsMeta  = {endpoint: "", name: "", fields: []};

interface MainBodyProps {
    feature: string;
}

const MainBody = (props: MainBodyProps) => {
    const [meta, setMeta] = useState(EMPTY_FIELDS);

    useEffect(() => {
        const uri = `${SERVER_URI}/api/${props.feature}/meta`;
        console.log(`fetch ${uri}`);
        (async () =>
            (await fetchUri(uri))
                .json()
                .then(res => {
                    console.log(`Got ${res.fields.length} fields`);
                    setMeta(res);
                }))();
    }, [props.feature]);

    return (
        <main className="col-md-9 ml-sm-auto col-lg-10 px-4" role="main">
            <Switch>
                <Route path="/:feature/manage">
                    <ListItems feature={props.feature}
                               meta={meta}
                               serverUri={SERVER_URI}/>
                </Route>
                <Route path="/:feature/new">
                    <AddItem feature={props.feature}
                             meta={meta}
                             initialValues={fieldValues(meta.fields)}/>
                </Route>
            </Switch>
        </main>
    );
}

export default MainBody;
