import React, {useEffect, useState} from "react";
import {Route, Switch} from "react-router-dom";
import ListItems from "./ListItems";
import AddItem from "./AddItem";
import {FieldsMeta, Row} from "./ItemList";

const SERVER_URI = "http://localhost:8080/naolo";

const EMPTY_FIELDS: FieldsMeta  = {endpoint: "", name: "", fields: []};

interface MainBodyProps {
    feature: string;
}

const MainBody = (props: MainBodyProps) => {
    const [meta, setMeta] = useState(EMPTY_FIELDS);

    useEffect(() => {
        fetch(`${SERVER_URI}/api/${props.feature}/meta`)
            .then(response => response.json())
            .then(_meta => setMeta(_meta));
    }, []);

    return (
        <main className="col-md-9 ml-sm-auto col-lg-10 px-4" role="main">
            <Switch>
                <Route path="/:feature/manage">
                    <ListItems feature={props.feature}
                               meta={meta}
                               serverUri={SERVER_URI}/>
                </Route>
                <Route path="/:feature/new"><AddItem/></Route>
            </Switch>
        </main>
    );
}

export default MainBody;
